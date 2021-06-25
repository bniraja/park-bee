Feature: BearerToken

  @Positive-Scenarios
  Scenario Outline: A api POST call with User API with valid end point to get bearer token
    When user make request for a token with <grant_type>,<scope>,<client_id> with secret key
    Then The response is returned with status <responseCode>
    And bearer token is stored for next api call
    Examples:
      | grant_type         | scope                               | client_id                              | responseCode |
      | client_credentials | "garages:pricing:read garages:read" | "6d8b4f7a-e62f-4075-8fee-7b72eabb8fa4" | 200          |

  @Negative-Scenarios
  Scenario Outline: A api POST call with User API with valid end point and invalid input to get Bad reqeust error
    When user make request for a token with <grant_type>,<scope>,<client_id> with secret key
    Then application respond with status <responseCode> and an <error>
    Examples:
      | grant_type         | scope                               | client_id                              | responseCode | error                  |
      | client_credentials | "garages:pricing:read garages:read" | "non_exist_client"                     | 400          | invalid_client         |
      | client_credentials | "wrong_scope"                       | "6d8b4f7a-e62f-4075-8fee-7b72eabb8fa4" | 400          | invalid_scope          |
      | wrong_credential   | "garages:pricing:read garages:read" | "6d8b4f7a-e62f-4075-8fee-7b72eabb8fa4" | 400          | unsupported_grant_type |


