package parkbee.automation.acceptance.stepdefinitions.auth;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import net.serenitybdd.core.annotations.events.BeforeScenario;
import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import org.junit.Assert;
import parkbee.automation.acceptance.models.Token;
import parkbee.automation.acceptance.stepdefinitions.utils.BaseTest;

public class GetToken  extends BaseTest {
    private String tokenBaseUrl;
    private String clientId;
    private String clientSecret;

    @BeforeScenario
    public void getNewToken(){

    }

    @Given("the url to retrieve a token")
    public void the_url_to_retrieve_a_token(){
        tokenBaseUrl =  EnvironmentSpecificConfiguration.from(environmentVariables)
            .getProperty("token.base.url");
        clientId =  EnvironmentSpecificConfiguration.from(environmentVariables)
            .getProperty("client.id");
        clientSecret =  EnvironmentSpecificConfiguration.from(environmentVariables)
            .getProperty("client.secret");
    }

    @BeforeScenario
    @When("user call the retreive a token endpoint")
    public void user_call_the_retreive_a_token_endpoint() {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertNotNull(tokenBaseUrl);
        Assert.assertNotNull(clientId);
        Assert.assertNotNull(clientSecret);
        ExtractableResponse<Response> responseBody = authSteps.getToken(tokenBaseUrl, clientId, clientSecret);
        Token token = responseBody.as(Token.class);
        String token_str = token.getAccess_token();
        String s;
    }

    @Then("bearer token is stored for next api call")
    public void bearer_token_is_stored_for_next_api_call() {
        // Write code here that turns the phrase above into concrete actions

    }

}
