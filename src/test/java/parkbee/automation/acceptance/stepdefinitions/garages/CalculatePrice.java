package parkbee.automation.acceptance.stepdefinitions.garages;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import parkbee.automation.acceptance.models.Period;
import parkbee.automation.acceptance.models.Token;
import parkbee.automation.acceptance.stepdefinitions.auth.AuthSteps;
import parkbee.automation.acceptance.utils.EnvironmentUtilsSteps;

public class CalculatePrice {



    @Steps
    private GarageSteps garageSteps;

    @Steps
    private EnvironmentUtilsSteps environmentUtilsSteps;
    @Steps
    private AuthSteps authSteps;

    @When("user call the calculate price api for {string}, {string}, {string} for a given garage")
    public void user_call_the_calculate_price_api_for_a_given_garage(String id, String startTime, String endTime)  {
        Period period = new Period(startTime,endTime);
        Token token = authSteps.getBearerToken();
        garageSteps.calculatePriceForGivenGarage(id,period,token);
    }

    @Then("response is returned with status {long} and {float}")
    public void response_is_returned_with_status_and(Long responseCode, Float price) {
        garageSteps.validateResponseCode(responseCode);
        garageSteps.validatePrice(price);
    }

    @Then("response is returned with status {int} with {string}")
    public void response_is_returned_with_status_and_error(Integer responseCode, String error){
        garageSteps.validateResponseCode(Integer.toUnsignedLong(responseCode));
        garageSteps.checkErrorMsg(error);
    }

}
