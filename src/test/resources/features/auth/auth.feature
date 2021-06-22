Feature: Operations to get token

  @Positive-Scenarios
  Scenario Outline: A api POST call with User API with valid end point to get bearer token
    Given the url to retrieve a token
    When  user call the retreive a token endpoint
    Then The response is returned with status <responseCode>
    And bearer token is stored for next api call
    Examples:
      | responseCode |
      | 200          |

  @Negative-Scenarios
  Scenario Outline: A api POST call with User API with valid end point and invalid input to get Bad reqeust error
    Given the url to retrieve a token
    When  input data  are <grant_type> <scope>, <client_id>
    Then The response is returned with status <responseCode> and error <error>
    Examples:
      | grant_type        | scope                             | client_id                            | responseCode | error                  |
      | client_credential | garages:pricing:read garages:read | 6d8b4f7a-e62f-4075-8fee-7b72eabb8fa4 | 400          | invalid_client         |
      | client_credential | wrong_scope                       | 6d8b4f7a-e62f-4075-8fee-7b72eabb8fa4 | 400          | invalid_scope          |
      | wrong_credential  | garages:pricing:read garages:read | 6d8b4f7a-e62f-4075-8fee-7b72eabb8fa4 | 400          | unsupported_grant_type |


