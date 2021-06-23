package parkbee.automation.acceptance.stepdefinitions.garages;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import parkbee.automation.acceptance.JobContext;
import parkbee.automation.acceptance.exception.TokenNotFoundException;
import parkbee.automation.acceptance.models.Garage;
import parkbee.automation.acceptance.models.Token;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static net.serenitybdd.rest.SerenityRest.rest;
//TODO Need to remove this class
public class GetGarages {
    private static final Logger LOGGER =
        LoggerFactory.getLogger(GetGarages.class);

    private Response response;


    @Step
    public List<Garage> getGarages(String clientId) throws TokenNotFoundException {
        Token token = JobContext.getToken(clientId);
        Map<String, String> headers = new HashMap<>(10);
        headers.put("Authorization", "Bearer " +token.getAccess_token());
        response = rest().headers(headers).accept("*/*").contentType("application/json; charset=utf-8").baseUri("https://api-uat.parkbee.net/v1")
            .get("/garages");
        Assert.assertEquals(200, response.getStatusCode());


        JsonPath jsonPath = response.jsonPath();
        List<Garage> garages = Arrays.asList(response.getBody().as(Garage[].class));
        System.out.println("Hello: "+garages.size());
//        Map<String, ?> headersMap = ;
//        SerenityRest.
//        response = SerenityRest.given()
//            .baseUri("https://api-uat.parkbee.net/v1").headers(headersMap).
//            .accept("*/*")
//            .contentType("application/x-www-form-urlencoded")
//            .head("Authorization", "Bearer " +token.getAccess_token()).
//            .get("/garages");
        return null;
    }
}
