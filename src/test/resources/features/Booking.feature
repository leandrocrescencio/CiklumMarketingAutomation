 @Booking
 Feature: Booking Scenario 01
  As a customer I want to search in the Booking website with my parameters

  @scenario01
  Scenario Outline: Booking Scenario 01
    Given I type "<city>" to start my search
    And I select "4" days in the next "1" week as date range
    And I click to open the guest selector and choose "<adult>" adults, "<child>" child, in "<rooms>" room
    Then I click in the search icon
    And I open the first result from the search
    Examples:
      | city   | adult | child | rooms |
      | Málaga | 2     | 1     | 1     |


 @scenario02
 Scenario Outline: Booking Scenario 02 - Same as 01 but sorting the result by the lowest prices
   Given I type "<city>" to start my search
   And I select "4" days in the next "1" week as date range
   And I click to open the guest selector and choose "<adult>" adults, "<child>" child, in "<rooms>" room
   Then I click in the search icon
   And I sort the result by price
   And I open the first result from the search
   Examples:
     | city   | adult | child | rooms |
     | Málaga | 2     | 1     | 1     |