package parkbee.automation.acceptance.ui.stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import parkbee.automation.acceptance.ui.steps.ReservationSteps;
import parkbee.automation.acceptance.utils.DateUtil;

public class Reservation {

    public static final int ONE_DAY = 1;
    @Steps
    private ReservationSteps reservationSteps;

    @When("I change the end time to park for one day and one hour from now")
    public void i_change_the_desired_new() {
        reservationSteps.setEndTime(DateUtil.getCurrentDayPlus(ONE_DAY));
        reservationSteps.confirmTime();
    }

    @Then("parking prices are updated to {string} for the {string}")
    public void parking_prices_are_updated_to_for_the(String newPrice, String location) {
        reservationSteps.verifyNearestLocationUpdatedPrice(newPrice);
    }
}
