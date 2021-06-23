package parkbee.automation.acceptance;

import parkbee.automation.acceptance.exception.TokenNotFoundException;
import parkbee.automation.acceptance.models.Token;

import java.util.HashMap;
import java.util.Map;

public class JobContext {
    private static Map<String, Token> tokenStorage = new HashMap<>();

    public static Token getToken(String clientId) throws TokenNotFoundException {
        if (!tokenStorage.containsKey(clientId)) {
            throw new TokenNotFoundException("Token not found for the client " + clientId);
        }
        return tokenStorage.remove(clientId);

    }

    public static void storeToken(String clientId, Token token) {
        tokenStorage.put(clientId, token);
    }
}
