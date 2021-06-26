Feature: price

  Background:
    Given the user on the parkbee.com home page
    When  user enter the "Nassaukade, Amsterdam, Netherlands", "28" to reserve
    Then user should see the "€3,90" of the nearest locations

  @Positive-Scenarios
  Scenario Outline: To verify whether prices are updated according to the time
    When I change the desired new <uitrijden>
    Then parking prices are updated to <newprice> for the <nearbylocation>
    Examples:
      | location                             | uitrijden | nearbylocation        | newprice |
      | "Nassaukade, Amsterdam, Netherlands" | "29"      | "Van Beuningenstraat" | "€24,25" |

#TODO Negative Scenarios
#  Negative testcases
#  - string selection of next month