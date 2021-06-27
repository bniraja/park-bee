Feature: Calculate price

  @Positive-Scenarios
  Scenario Outline: An api to calculate price for a garage for the given period
    When  user call the calculate price api for <id>, <startTime>, <endTime> for a given garage
    Then response is returned with status <responseCode> and <price>
    Examples:
      | id                                     | startTime          | endTime            | responseCode | price    |
      | "7fc6b6c4-7e31-41d8-ba43-d77174c3e234" | "2020-03-01T08:00" | "2020-03-01T10:00" | 200          | 8.40     |
      | "7fc6b6c4-7e31-41d8-ba43-d77174c3e234" | "2020-03-01T10:00" | "2020-03-01T10:00" | 200          | 0        |
      | "7fc6b6c4-7e31-41d8-ba43-d77174c3e234" | "2020-03-01T08:00" | "2022-03-01T10:00" | 200          | 20083.40 |

  @Negative-Scenarios
  @edge-cases
  Scenario Outline: An api to calculate price for a garage with invalid data
    When user call the calculate price api for <id>, <startTime>, <endTime> for a given garage
    Then response is returned with status <responseCode> with <error>
    Examples:
      | id                                     | startTime          | endTime            | responseCode | error                                                                                  |
      | "7fc6b6c4-7e31-41d8-ba43-d77174c3e234" | "2026-03-01T08:00" | "2022-03-01T10:00" | 400          | "EndTime (03/01/2022 10:00:00) cannot be smaller than StartTime (03/01/2026 08:00:00)" |
      | "7fc6b6c4-7e31-41d8-ba43-d77174c3e234" | "2026-03-01T08:00" | "2022-03-01T8:00"  | 400          | "Could not convert string to DateTime: 2022-03-01T8:00. Path 'endTime'"                |
      | "7fc6b6c4-7e31-41d8-ba43-d77174c3e234" | "500-03-01T08:00"  | "9999-03-01T08:00" | 500          | "An unexpected error occurred, please contact the administrator"                       |
      | "invalidId"                            | "500-03-01T08:00"  | "9999-03-01T08:00" | 400          | "The value 'invalidId' is not valid."                                                  |

