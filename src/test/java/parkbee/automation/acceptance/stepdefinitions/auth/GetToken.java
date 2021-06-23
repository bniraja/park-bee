package parkbee.automation.acceptance.stepdefinitions.auth;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.core.annotations.events.BeforeScenario;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.util.EnvironmentVariables;
import org.junit.Assert;
import parkbee.automation.acceptance.exception.TokenNotFoundException;
import parkbee.automation.acceptance.stepdefinitions.garages.GetGarages;
import parkbee.automation.acceptance.utils.EnvironmentUtilsSteps;

public class GetToken {
    private String tokenBaseUrl;
    private String clientId;
    private String clientSecret;

    protected EnvironmentVariables environmentVariables;

    @Steps
    private AuthSteps authSteps;

    @Steps
    private GetGarages getGarages;

    @Steps
    private EnvironmentUtilsSteps environmentUtilsSteps;

    @BeforeScenario
    public void the_url_to_retrieve_a_token() {
        tokenBaseUrl = environmentUtilsSteps.getTokenUrl();
        clientId = environmentUtilsSteps.getClientId();
        clientSecret = environmentUtilsSteps.getClientSecretKey();
    }


//    @When("user call the retrieve a token endpoint")
//    public void user_call_the_retrieve_a_token_endpoint() {
//        Assert.assertNotNull(tokenBaseUrl);
//        Assert.assertNotNull(clientId);
//        Assert.assertNotNull(clientSecret);
//        authSteps.getToken(tokenBaseUrl, clientId, clientSecret);
//
//    }

    @Then("The response is returned with status {int}")
    public void the_response_is_returned_with_status(Integer responseCode) {
        authSteps.verifyTheStatus(responseCode);
    }

    @Then("bearer token is stored for next api call")
    public void bearer_token_is_stored_for_next_api_call() throws TokenNotFoundException {
        authSteps.storeTheToken(clientId);
        getGarages.getGarages(clientId);
    }

    @When("user make request for a token with {word},{string},{string} with secret key")
    public void user_make_request_for_a_token_with_scope_clientid_granttype_with_secret_key(String grantType, String scope, String clientId) {
        Assert.assertNotNull(grantType);
        Assert.assertNotNull(clientId);
        Assert.assertNotNull(scope);
        authSteps.getToken(clientId, scope, grantType);
    }



    @Then("application respond with status {int} and an {word}")
    public void application_respond_with_status_and_an_invalid_client(Integer responseCode, String error) {
        authSteps.verifyTheStatus(responseCode);
        authSteps.checkErrorMsg(error);
    }
}
