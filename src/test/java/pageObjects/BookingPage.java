package pageObjects;

import common.Utils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import stepDefinitions.Hooks;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public class BookingPage extends Hooks {

    @FindBy(how = How.XPATH, using = "//input[@id='ss']")
    public static WebElement cityInput;
    @FindBy(how = How.XPATH, using = "//*[@id='frm']//div/ul/li/span/span")
    public static WebElement suggestions;
    @FindBy(how = How.CSS, using = "div.bui-calendar__control.bui-calendar__control--next")
    public static WebElement datePicker;
    @FindBy(how = How.CSS, using = "div[class='xp__input-group xp__guests']")
    public static WebElement guestsSelector;
    @FindBy(how = How.CSS, using = "button.bui-button.bui-button--secondary.bui-stepper__add-button")
    public static List<WebElement> addGuestsContainer;
    @FindBy(how = How.CSS, using = "button.bui-button.bui-button--secondary.bui-stepper__subtract-button")
    public static List<WebElement> subtractGuestsContainer;
    @FindBy(how = How.CSS, using = "button[class='sb-searchbox__button ']")
    public static WebElement searchBtn;
    @FindBy(how = How.XPATH, using = "//*[@id='right']/div[4]/div/div[1]/div/h1")
    public static WebElement headResult;
    @FindBy(how = How.CSS, using = "span.sr-hotel__name")
    public static WebElement hotel;
    @FindBy(how = How.XPATH, using = "//*[@id='hp_hotel_name']")
    public static WebElement hotelheader;
    @FindBy(how = How.XPATH, using = "//*[@id='sort_by']/ul/li[3]/a")
    public static WebElement lowestPriceSort;
    @FindBy(how = How.CSS, using = "#sort_by > ul > li.sort_category.selected.sort_price > a")
    public static WebElement selectedSort;

    public BookingPage() throws IOException {
        super();
    }

    private static WebElement setDate(String date) {
        return driver.findElement(By.cssSelector("td[data-date='" + date + "']"));
    }

    public void selectTarget(String target) {
        cityInput.clear();
        cityInput.sendKeys(target);
        Utils.waitForVisibleElement(suggestions);
        suggestions.click();
    }

    public void enterDate(LocalDate startDate, LocalDate endDate) {
        LocalDate currentMonth = LocalDate.now().withDayOfMonth(1);

        int monthsStartDate = getMonthsDate(startDate, currentMonth);
        int monthsEndDate = getMonthsDate(endDate, startDate);

        countToClick(monthsStartDate, 1, datePicker);
        setDate(startDate.toString()).click();

        countToClick(monthsEndDate, 0, datePicker);
        setDate(endDate.toString()).click();
    }

    private int getMonthsDate(LocalDate dateTo, LocalDate dateFrom) {
        int months = Period.between(dateFrom, dateTo).getMonths();
        int years = Period.between(dateFrom, dateTo).getYears();
        return (years * 12) + months;
    }

    public void setGuestsInputs(int numberOfAdults, int numberOfChildren, int numberOfRooms) {
        guestsSelector.click();
        if (numberOfAdults == 1) {
            subtractGuestsContainer.get(0).click();
        } else {
            countToClick(numberOfAdults, 2, addGuestsContainer.get(0));
        }
        countToClick(numberOfChildren, 0, addGuestsContainer.get(1));
        countToClick(numberOfRooms, 1, addGuestsContainer.get(2));

    }

    private void countToClick(int value, int base, WebElement element) {
        for (int i = base; i < value; i++) {
            element.click();
        }
    }

    public void clickSearch() {
        searchBtn.click();
        checkNumberResults();
    }


    public void checkNumberResults() {
        Utils.waitForVisibleElement(headResult);
        String head = Utils.getText(headResult);
        String[] wording = head.split(" ");

        Assert.assertNotEquals(wording[1] + "Stays found", "0", wording[1]);

    }


    public void openFirstStay() throws Exception {
        Utils.waitForVisibleElement(hotel);
        String name = Utils.getText(hotel);
        hotel.click();
        Utils.goToNewWindow();
        Utils.waitForVisibleElement(hotelheader);
        Assert.assertTrue("Hotel name verification", Utils.getText(hotelheader).contains(name));

    }

    public void getLowestPriceSort() {
        Utils.waitForVisibleElement(lowestPriceSort);
        lowestPriceSort.click();
        Utils.waitForPageLoadComplete(Hooks.driver);
        Utils.addSystemWait(2);
        Assert.assertEquals("Checks if the sorting by Low Price is enabled", Utils.getText(lowestPriceSort), Utils.getText(selectedSort));
    }

}
