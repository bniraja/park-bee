package parkbee.automation.acceptance.api.models;

import lombok.Data;

@Data
public class Token {
    private String access_token;
    private String expires_in;
    private String token_type;
}
