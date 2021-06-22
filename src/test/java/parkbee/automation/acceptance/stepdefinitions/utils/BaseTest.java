package parkbee.automation.acceptance.stepdefinitions.utils;

import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.util.EnvironmentVariables;
import parkbee.automation.acceptance.stepdefinitions.auth.AuthSteps;

import static helpers.PropertyReader.getProperty;

public class BaseTest {
    public static String BASE_URL = getProperty("BASE_URL");
    protected EnvironmentVariables environmentVariables;


    @Step
    public void invoke_my_webservice() {
        BASE_URL =  EnvironmentSpecificConfiguration.from(environmentVariables)
            .getProperty("api.base.url");
    }

    @Steps
    protected AuthSteps authSteps;
}
