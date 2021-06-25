package parkbee.automation.acceptance.api.steps;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Step;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import parkbee.automation.acceptance.api.models.Period;
import parkbee.automation.acceptance.api.models.Token;

import java.util.HashMap;
import java.util.Map;

import static net.serenitybdd.rest.SerenityRest.rest;
import static org.junit.Assert.assertEquals;

public class GarageSteps {
    private static final int INITIAL_CAPACITY = 1;
    private Response response;

    @Step
    public void calculatePriceForGivenGarage(String garageId, Period period, Token token) {

        Map<String, String> headers = new HashMap<>(INITIAL_CAPACITY);
        headers.put("Authorization", "Bearer " + token.getAccess_token());
        response = rest().headers(headers).accept("*/*").contentType("application/json; charset=utf-8").baseUri("https://api-uat.parkbee.net/v1").body(period)
            .post("/garages/" + garageId + "/pricing/calculate");
    }

    @Step
    public void validateResponseCode(Long responseCode) {
        assertEquals("Testcase failed as expected response code mismatch", responseCode, Long.valueOf(response.getStatusCode()));
    }

    @Step
    public void validatePrice(Float expectedCost) {
        Float actualCost = response.jsonPath().getFloat("cost");
        assertEquals("Actual cost mismatch with expected cost", expectedCost, actualCost);
    }

    @Step
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
