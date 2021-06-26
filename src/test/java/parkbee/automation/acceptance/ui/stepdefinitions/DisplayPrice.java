package parkbee.automation.acceptance.ui.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

import parkbee.automation.acceptance.ui.steps.HomePageSteps;
import parkbee.automation.acceptance.ui.steps.NavigateTo;
import parkbee.automation.acceptance.utils.DateUtil;


public class DisplayPrice {
    @Steps
    NavigateTo navigateTo;
    @Steps
    HomePageSteps homePageSteps;

    @Given("the user on the parkbee.com home page")
    public void the_user_on_the_parkbee_com_home_page() {
        navigateTo.homePage();
    }

    @When("user enter the {string}, select current time to reserve")
    public void user_enter_the_to_reserve(String destination) {
        homePageSteps.enterDestination(destination);
        //TODO time,year, change in time need to implement
        homePageSteps.selectStartTime(DateUtil.getCurrentDay());
        homePageSteps.confirmTime();

    }


    @Then("by default user should see the {string} for one hour for parking at nearest {string}")
    public void user_should_see_the_of_the_nearest_locations(String price, String parkingArea) {
        homePageSteps.verifyNearestLocation(price);
    }


    @When("user enter the {string}")
    public void user_enter_the_destination(String destination) {

        homePageSteps.enterDestination(destination);

    }

    @Then("select yesterday date to reserve and {string}")
    public void date_picker_should_be_disabled(String errorMessage) {
        //TODO time,year, change in time need to implement
        homePageSteps.verifyErrorThatOldDateDisabled(DateUtil.getCurrentDayPlus( -1),errorMessage);
    }
}
