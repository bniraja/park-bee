package parkbee.automation.acceptance.utils;

import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.util.EnvironmentVariables;

public class EnvironmentUtilsSteps {
    public static final String TOKEN_BASE_URL = "token.base.url";
    public static final String API_BASE_URL = "api.base.url";
    public static final String CLIENT_ID = "client.id";
    private EnvironmentVariables environmentVariables;

    public String getTokenUrl() {
        return EnvironmentSpecificConfiguration.from(environmentVariables)
            .getProperty(TOKEN_BASE_URL);
    }

    public String getApiBaseUrl() {
        return EnvironmentSpecificConfiguration.from(environmentVariables)
            .getProperty(API_BASE_URL);
    }

    public String getClientId() {
        return EnvironmentSpecificConfiguration.from(environmentVariables)
            .getProperty(CLIENT_ID);
    }

    public String getClientSecretKey() {
        return EnvironmentSpecificConfiguration.from(environmentVariables)
            .getProperty("client.secret");
    }
}
