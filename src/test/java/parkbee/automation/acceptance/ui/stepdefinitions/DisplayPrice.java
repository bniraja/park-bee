package parkbee.automation.acceptance.ui.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

import parkbee.automation.acceptance.ui.steps.HomePageSteps;
import parkbee.automation.acceptance.ui.steps.NavigateTo;


public class DisplayPrice {
    @Steps
    NavigateTo navigateTo;
    @Steps
    HomePageSteps homePageSteps;

    @Given("the user on the parkbee.com home page")
    public void the_user_on_the_parkbee_com_home_page() {
        navigateTo.homePage();
    }

    @When("user enter the {string}, {string} to reserve")
    public void user_enter_the_to_reserve(String destination, String time) {
        homePageSteps.enterDestination(destination);
        //TODO time,year, change in time need to implement
        homePageSteps.selectStartTime(time);
        homePageSteps.confirmTime();

    }
    @Then("user should see the {string} of the nearest locations")
    public void user_should_see_the_of_the_nearest_locations(String price) {
        homePageSteps.verifyNearestLocation(price);
    }

}
