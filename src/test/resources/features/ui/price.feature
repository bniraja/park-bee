Feature: price

  @Positive-Scenarios
  Scenario Outline: Display the prices for the given location and time
    Given the user on the parkbee.com home page
    When  user enter the <location>, <reservationtime> to reserve
    Then user should see the <prices> of the nearest locations
    Examples:
      | location                             | reservationtime | prices  |
      | "Nassaukade, Amsterdam, Netherlands" | "28"            | "€3.90" |

  @Positive-Scenarios
  Scenario Outline: To verify whether prices are updated according to the time
    Given I am on the search results page for the <location>
    When I change the desired new <parkingtime>
    Then parking prices are updated to <newprice> for the <nearbylocation>
    Examples:
      | location                             | parkingtime | nearbylocation        | newprice |
      | "Nassaukade, Amsterdam, Netherlands" | "29"        | "Van Beuningenstraat" | "€24.25" |

#TODO Negative Scenarios