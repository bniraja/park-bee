Feature: search

  @Positive-Scenarios
  Scenario Outline: Display the prices for the given location and time
    Given the user on the parkbee.com home page
    When  user enter the <location>, <reservationtime> to reserve
    Then user should see the <prices> of the nearest locations
    Examples:
      | location                             | reservationtime | prices  |
      | "Nassaukade, Amsterdam, Netherlands" | "28"            | "€3,90" |

#//TODO
#    reservation time is only date as of now need to change it to dd-mm-yyyy