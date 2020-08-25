package common;

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.*;
import stepDefinitions.Hooks;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Utils  {

    public static String fs = File.separator;

    public static final Integer DEFAULT_WAIT_TIMEOUT = 15;

    public static final Long DEFAULT_WAIT_SLEEP = 250L;


    /**
     *This is the method to read the config.properties file
     */
    public static Properties readPropertiesFile() throws IOException {
        String path = "src" + fs + "test" + fs + "resources" + fs + "config.properties";
        String configPath = Paths.get(path).toAbsolutePath().toString();

        FileInputStream fis;
        fis = new FileInputStream(configPath);
        Properties property = new Properties();
        property.load(fis);
        return property;
    }

    /**
     *
     * @param element This is the webelement that has to be visible on the web page.
     */
    public static void waitForVisibleElement(WebElement element){
        WebDriverWait wait = new WebDriverWait(Hooks.driver, 30);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     *
     * @param driver wait for the Page load for complete.
     */
    public static void waitForPageLoadComplete(WebDriver driver) {
        Wait<WebDriver> wait = new WebDriverWait(driver, 30);
        wait.until(driver1 -> String
                .valueOf(((JavascriptExecutor) driver1).executeScript("return document.readyState"))
                .equals("complete"));
    }

    public static boolean containsIgnoreCase(String str, String subString) {
        return str.toLowerCase().contains(subString.toLowerCase());
    }

    public static String getText(WebElement element) {
        waitForVisibleElement(element);
        String text = element.getText();
        if (StringUtils.isEmpty(text)) {
            text = element.getAttribute("innerText");
        }
        return text.trim();
    }

    private static FluentWait<WebDriver> getWait(Integer... timeout) {
        int tout = timeout.length > 0 ? timeout[0] : DEFAULT_WAIT_TIMEOUT;
        return new WebDriverWait(Hooks.driver, tout, DEFAULT_WAIT_SLEEP);
    }

    protected static void waitFor(ExpectedCondition<?> condition, Integer timeout) {
        getWait(timeout).until(condition);
    }

    protected static boolean waitForWindows(Integer numberOfWindows, Integer... timeout) {
        try {
            waitFor(ExpectedConditions.numberOfWindowsToBe(numberOfWindows),
                    (timeout.length > 0 ? timeout[0] : DEFAULT_WAIT_TIMEOUT));
        } catch (Throwable t) {
            return false;
        }
        return true;
    }

    public static void goToNewWindow() throws Exception {
        String currentWindow = Hooks.driver.getWindowHandle();
        List<String> openWindows = new ArrayList<>(Hooks.driver.getWindowHandles());
        if (openWindows == null || openWindows.isEmpty()) {
            if(!waitForWindows(2, 25)) {
                throw new Exception("The window wasn't opened.");
            }
        }
        List<String> newWindows = openWindows.stream()
                .filter(windowsId -> !windowsId.equals(currentWindow))
                .collect(Collectors.toList());
        if(newWindows.size() > 0) {
            Hooks.driver.switchTo().window(newWindows.get(0));
        }
    }

    /***
     * <h1>addSystemWait</h1>
     * <p>purpose: Have the automation wait a specified time. <br>
     * @param lWaitTime = the wait time (in seconds) for the system to wait <br>
     *        Note: Suggested wait time is 3 seconds or less
     * @return None
     */
    public static void addSystemWait(long lWaitTime) {
        try {
            TimeUnit.SECONDS.sleep(lWaitTime);
        } catch (InterruptedException e) {
            System.err.println("InterruptedException" + e.getMessage());
        }
    }

}
