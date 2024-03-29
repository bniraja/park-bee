package parkbee.automation.acceptance.api.steps;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import parkbee.automation.acceptance.api.models.Period;
import parkbee.automation.acceptance.api.models.Token;
import parkbee.automation.acceptance.utils.EnvironmentUtilsSteps;

import java.util.HashMap;
import java.util.Map;

import static net.serenitybdd.rest.SerenityRest.rest;
import static org.junit.Assert.assertEquals;

public class GarageSteps {
    private static final int INITIAL_CAPACITY = 1;
    private Response response;

    @Steps
    private EnvironmentUtilsSteps environmentUtilsSteps;

    @Step("call calculate pricing endpoint for a garage and given time")
    public void calculatePriceForGivenGarage(String garageId, Period period, Token token) {

        Map<String, String> headers = new HashMap<>(INITIAL_CAPACITY);
        headers.put("Authorization", "Bearer " + token.getAccess_token());
        response = rest().headers(headers).accept("*/*").contentType("application/json; charset=utf-8").baseUri(environmentUtilsSteps.getApiBaseUrl()).body(period)
            .post("/garages/" + garageId + "/pricing/calculate");
    }

    @Step("validate the response code")
    public void validateResponseCode(Long responseCode) {
        assertEquals("Testcase failed as expected response code mismatch", responseCode, Long.valueOf(response.getStatusCode()));
    }

    @Step("verify the received price with expected price")
    public void validatePrice(Float expectedCost) {
        Float actualCost = response.jsonPath().getFloat("cost");
        assertEquals("Actual cost mismatch with expected cost", expectedCost, actualCost);
    }

    @Step("verify the error message returned")
    public void checkErrorMsg(String error) {
        final JsonPath jsonPath = response.jsonPath();
        String errorCode = jsonPath.getString("errors.errorCode");
        String errorMsg = "";
        if ("[validation_error]".equals(errorCode)) {
            errorMsg = jsonPath.getString("errors.properties.message");
        } else {
            errorMsg = jsonPath.getString("errors.errorMessage");
        }
        Assert.assertThat(errorMsg, CoreMatchers.containsString(error));
    }


}
