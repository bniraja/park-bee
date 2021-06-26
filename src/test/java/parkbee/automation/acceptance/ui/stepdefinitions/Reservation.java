package parkbee.automation.acceptance.ui.stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import parkbee.automation.acceptance.ui.steps.ReservationSteps;

public class Reservation {

    @Steps
    private ReservationSteps reservationSteps;

    @When("I change the desired new {string}")
    public void i_change_the_desired_new(String endTime) {
        reservationSteps.setEndTime(endTime);
        reservationSteps.confirmTime();
    }

    @Then("parking prices are updated to {string} for the {string}")
    public void parking_prices_are_updated_to_for_the(String newPrice, String location) {
        reservationSteps.verifyNearestLocationUpdatedPrice(newPrice);
    }
}
