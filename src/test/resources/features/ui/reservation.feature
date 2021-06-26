Feature: price

  Background:
    Given the user on the parkbee.com home page
    When user enter the "Nassaukade, Amsterdam, Netherlands", select current time to reserve
    Then by default user should see the "€3,90" for one hour for parking at nearest "Van Beuningenstraat"

  @Positive-Scenarios
  Scenario Outline: To verify prices are updated when user want to park for 25 hours
    When I change the end time to park for one day and one hour from now
    Then parking prices are updated to <newprice> for the <nearbyparkingArea>
    Examples:
     | nearbyparkingArea     | newprice |
     | "Van Beuningenstraat" | "€24,25" |

#TODO Negative Scenarios
#  Negative testcases
#  - string selection of next month