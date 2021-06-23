package parkbee.automation.acceptance.stepdefinitions.auth;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import org.apache.http.HttpStatus;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import parkbee.automation.acceptance.JobContext;
import parkbee.automation.acceptance.exception.TokenNotFoundException;
import parkbee.automation.acceptance.models.Token;
import parkbee.automation.acceptance.utils.EnvironmentUtilsSteps;

import static net.serenitybdd.rest.SerenityRest.rest;

public class AuthSteps {
    private static final String TOKEN_ENDPOINT = "/token";

    Response response;

    @Steps
    private EnvironmentUtilsSteps environmentUtilsSteps;

    @Step
    public void getToken(String clientId, String scope, String grantType) {
        response = rest().given()
            .baseUri(environmentUtilsSteps.getTokenUrl())
            .accept("*/*")
            .contentType("application/x-www-form-urlencoded")
            .formParam("grant_type", grantType)
            .formParam("scope", scope)
            .formParam("client_id", clientId)
            .formParam("client_secret", environmentUtilsSteps.getClientSecretKey())
            .post(TOKEN_ENDPOINT);
    }

    @Step
    public void verifyTheStatus(int statusCode) {
        Assert.assertEquals("Authentication Failed ", statusCode, response.getStatusCode());
    }

    @Step
    public void storeTheToken(String clientId) {
        Token token = response.as(Token.class);
        Assert.assertNotNull(token);
        JobContext.storeToken(clientId, token);
    }

    //TODO need to find a better solution for scope ,granttype
    public Token retrieveBearerToken() {
        Token token = SerenityRest.given()
            .baseUri(environmentUtilsSteps.getTokenUrl())
            .accept("*/*")
            .contentType("application/x-www-form-urlencoded")
            .formParam("grant_type", "client_credentials")
            .formParam("scope", "garages:pricing:read garages:read")
            .formParam("client_id", environmentUtilsSteps.getClientId())
            .formParam("client_secret", environmentUtilsSteps.getClientSecretKey())
            .post(TOKEN_ENDPOINT).then().statusCode(HttpStatus.SC_OK).extract().as(Token.class);
        JobContext.storeToken(environmentUtilsSteps.getClientId(), token);
        return token;
    }

    public Token getBearerToken() {
        Token token;
        try {
            token = JobContext.getToken(environmentUtilsSteps.getClientId());
        } catch (TokenNotFoundException e) {
            token = retrieveBearerToken();
        }
        return token;
    }

    public void checkErrorMsg(String error) {
        String errorMsg = response.jsonPath().getString("error");
        Assert.assertThat(errorMsg, CoreMatchers.containsString(error));
    }
}
