package stepDefinitions;

import common.Utils;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class Hooks {

    public static WebDriver driver;

    public static Logger log = Logger.getLogger(String.valueOf(Hooks.class));
    public static Properties configProperties;
    public static String browser;
    private final String line = ("\n===========================================================================\n");
    public String fs = File.separator;


    public Hooks() throws IOException {
        configProperties = Utils.readPropertiesFile();
        browser = configProperties.getProperty("browser");
    }

    public static String getOS() {
        return System.getProperty("os.name").toUpperCase();
    }

    public static void initiateWebdriver() {
        log.info("Opening Browser...." + browser);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(35, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(35, TimeUnit.SECONDS);
        driver.get(configProperties.getProperty("url"));
        Utils.waitForPageLoadComplete(driver);
    }

   @Before()
    public void openBrowser(Scenario scenario) {

        log.info(line + "Starting Scenario: " + scenario.getName());

        if (Utils.containsIgnoreCase(getOS(), "WINDOWS")) {
            switch (browser) {
                case "chrome":
                    String winChromeDriver = "drivers" + fs + "pc" + fs + "chromedriver.exe";
                    String winChromePath = Paths.get(winChromeDriver).toAbsolutePath().toString();
                    System.setProperty("webdriver.chrome.driver", winChromePath);
                    driver = new ChromeDriver();
                    break;
                case "firefox":
                    String winFirefoxDriver = "drivers" + fs + "pc" + fs + "geckodriver.exe";
                    String winFirefoixPath = Paths.get(winFirefoxDriver).toAbsolutePath().toString();
                    System.setProperty("webdriver.gecko.driver", winFirefoixPath);
                    driver = new FirefoxDriver();
                    break;
            }
            initiateWebdriver();
        } else if (Utils.containsIgnoreCase(getOS(), "MAC")) {

            switch (browser) {
                case "chrome":
                    String macChromeDriver = "drivers" + fs + "mac" + fs + "chromedriver";
                    String macChromePath = Paths.get(macChromeDriver).toAbsolutePath().toString();
                    System.setProperty("webdriver.chrome.driver", macChromePath);
                    driver = new ChromeDriver();
                    break;
                case "firefox":
                    String macFirefixDriver = "drivers" + fs + "mac" + fs + "geckodriver";
                    String macFirefoxPath = Paths.get(macFirefixDriver).toAbsolutePath().toString();
                    System.setProperty("webdriver.gecko.driver", macFirefoxPath);
                    driver = new FirefoxDriver();
                    break;
            }
            initiateWebdriver();
        }
    }

    @After()
    public void quitDriver(Scenario scenario) {

        String out = String.format(
                line
                        + " Finishing Scenario: " + scenario.getName() + ""
                        + "\n" + " Test Status: " + scenario.getStatus().toUpperCase()
                        + "\n" + " Local time: " + new java.util.Date().toString()
                        + "\n" + " UTC: " + Instant.now().toString())
                + line;
        log.info(out);


        // get browser logs if failed
        if (scenario.isFailed()) {
            LogEntries logs = driver.manage().logs().get("browser");
            for (LogEntry entry : logs) {
                System.out.println(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
            }
        }

        driver.quit();


    }

}