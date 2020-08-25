package stepDefinitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.support.PageFactory;
import pageObjects.BookingPage;

import java.time.LocalDate;

public class BookingStepDef {

    private BookingPage bookingPage = PageFactory.initElements(BookingPage.driver, BookingPage.class);


    @Given("^I type \"([^\"]*)\" to start my search$")
    public void iTypeToStartMySearch(String city) {
        try {
            bookingPage.selectTarget(city);
        } catch (Exception e) {
            Assert.fail("Test Failed: iTypeToStartMySearch() :\n" + e);
        }
    }

    @And("^I select \"([^\"]*)\" days in the next \"([^\"]*)\" week as date range$")
    public void iSelectInTheNextWeekAsDateRange(int days, int weeks) {
        try {
            LocalDate startDate = LocalDate.now().plusWeeks(weeks);
            LocalDate endDate = startDate.plusDays(days);
            bookingPage.enterDate(startDate, endDate);
        } catch (Exception e) {
            Assert.fail("Test Failed: iSelectInTheNextWeekAsDateRange() :\n" + e);
        }
    }

    @And("^I click to open the guest selector and choose \"([^\"]*)\" adults, \"([^\"]*)\" child, in \"([^\"]*)\" room$")
    public void iClickToOpenTheGuestSelectorAndChooseAdultsChildInRoom(int adult, int child, int rooms) {
        try {
            bookingPage.setGuestsInputs(adult, child, rooms);
        } catch (Exception e) {
            Assert.fail("Test Failed: iClickToOpenTheGuestSelectorAndChooseAdultsChildInRoom() :\n" + e);
        }
    }

    @Then("^I click in the search icon$")
    public void iClickInTheSearchIcon() {
        try {
            bookingPage.clickSearch();
        } catch (Exception e) {
            Assert.fail("Test Failed: iClickInTheSearchIcon() :\n" + e);
        }
    }


    @And("^I open the first result from the search$")
    public void iOpenTheFirstResultFromTheSearch() {
        try {
            bookingPage.openFirstStay();
        } catch (Exception e) {
            Assert.fail("Test Failed: iOpenTheFirstResultFromTheSearch() :\n" + e);
        }
    }

    @And("^I sort the result by price$")
    public void iSortTheResultByPrice() {
        try {
            bookingPage.getLowestPriceSort();
        } catch (Exception e) {
            Assert.fail("Test Failed: iSortTheResultByPrice() :\n" + e);
        }
    }
}
