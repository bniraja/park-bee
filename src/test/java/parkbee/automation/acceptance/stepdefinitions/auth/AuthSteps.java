package parkbee.automation.acceptance.stepdefinitions.auth;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.apache.http.HttpStatus;

public class AuthSteps {

    @Step
    public <R> ExtractableResponse<Response> getToken(String tokenUrl, String clientId, String clientSecret) {
         return SerenityRest.given()
            .baseUri(tokenUrl)
            .accept("*/*")
            .contentType("application/x-www-form-urlencoded")
            .formParam("grant_type", "client_credentials")
            .formParam("scope", "garages:pricing:read garages:read")
            .formParam("client_id", clientId)
            .formParam("client_secret", clientSecret)
            .post("/token").then().statusCode(HttpStatus.SC_OK).extract();

    }
}
