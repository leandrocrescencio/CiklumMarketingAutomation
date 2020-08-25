#  Lottoland – QA Automation task
Create a java project using selenium 3 to automate the following scenarios:

This task is split in two parts:
 - The first one requires the implementation of two automated tests (the second one is optional), covering the scenarios described below (they should be implemented using Java/Selenium and delivered as a private git repository)
 - The second part is about defining and describing test cases (should be documented in the same repository, in the README.md file or in any other file of your choice)

## Part 1 – Automation
##### _**Scenario 1**_
1. Navigate to the booking.com page
2. Execute a new search with the following parameters:
    * a. City: Malaga
    * b. Check in/out dates: next week
    * c. People: 2 adults and 1 child
3. Validate that at least one result is visualized

##### _**Scenario 2 (optional)**_
Same as scenario 1, but once in the results page:
4. Sort the result set by price (lowest first)
5. Assess that the results are sorted correctly

## Part 2 – Test Cases
Imagine we have just implemented the booking search form in the home page: 
do not consider the result page for this task, just the search form (city, dates and number of travellers). 
Please identify and document the test cases you consider relevant for the search form feature.

### Test Scenarios :
* - All tests were based on the smoke testing manually executed to support the automation and also identify possible business rules for the search form. 
They are divided into positive and negative scenarios below.  
#### Positives
  ```
  - Suggestion places should be displayed for the wording the user is typing for the place.
  - Suggestion places should be displayed if the user click the city input.
  - Selecting one suggestion should populate the city name, and trigger the date-picker exhibit. 
  - Search providing city, number of guests and rooms but without set a date-range.
  - Search providing city, number of guests and rooms and set a date-range of one day.
  - Search providing city, number of guests and rooms and set a date-range between 1 to 30 days.
  - Clicking on the dates should display the date selection modal with the current date highlighted
  - Clicking on the guests inputs should display the guests/room selector.
  - Guests/Room modal should display the default values of 2 Adults, 0 Children and 1 Room. 
  - Search providing a valid city, a valid date-range and the number of guests and rooms with maximum values.
  - Search providing a valid city, a valid date-range and the number of guests and rooms with minimum values.
  - The add Children should enable a optional field to select the Children's Age.
  - Typing the City and using the TAB to navigate to the date selection, should display dropdown for the date selection.
  - The dropdown for the date selection should accept mouse and keyboard interactions.
  ```

#### Negative
  ```
  - Warning message should be displayed if an numeric city name is provided and the user click the search button.
  - Warning message should be displayed if none city name is provided and the user click the search button.
  - Warning message should be displayed if a date-rage longer than 30 nights is provided and the user click in the search button.
  - Dates before the current day should not be clickable.
  - The remove Adults button on the Guests container should be unclickable when reaching 1 Adult.
  - The remove Children button on the Guests container should be unclickable when reaching 0 Children.
  - The remove Rooms button on the Guests container should be unclickable when reaching 1 Room.
  - The add Adults button on the Guests container should be unclickable when reaching 30 Adult.
  - The add Children button on the Guests container should be unclickable when reaching 10 Children.
  - The add Rooms button on the Guests container should be unclickable when reaching 30 Room.
  ```