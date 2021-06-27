Feature: search

  @Positive-Scenarios
  Scenario Outline: Display the prices for parking station the given location for 1 hour from now
    Given the user on the parkbee.com home page
    When user enter the <location>, select current time to reserve
    Then by default user should see the <price> for one hour for parking at nearest <parkingArea>
    Examples:
      | location                             | parkingArea           | price   |
      | "Nassaukade, Amsterdam, Netherlands" | "Van Beuningenstraat" | "€3,90" |

  @Negative-Scenarios
  Scenario Outline: Verify the error message when user select the past dates on date picker
    Given the user on the parkbee.com home page
    When user enter the <location>
    Then select yesterday date to reserve and <error>
    Examples:
      | location                             | error              |
      | "Nassaukade, Amsterdam, Netherlands" | "Date is disabled" |

#//TODO
#    reservation time is only date as of now need to change it to dd-mm-yyyy
#   - check future dates are selected and
#    - boundary check on the dates more than 3000
#    - time,year, change in time need to implement