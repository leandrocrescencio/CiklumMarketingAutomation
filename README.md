#  Lottoland – QA Automation task
Create a java project using selenium 3 to automate the following scenarios:

This task is split in two parts:
- The first one requires the implementation of two automated tests (the second one is optional), covering the scenarios described below (they should be implemented using Java/Selenium and delivered as a private git repository)
- The second part is about defining and describing test cases (should be documented in the same repository, in the README.md file or in any other file of your choice)

###Part 1 – Automation
#####_**Scenario 1**_
1. Navigate to the booking.com page
2. Execute a new search with the following parameters:
    * a. City: Malaga
    * b. Check in/out dates: next week
    * c. People: 2 adults and 1 child
3. Validate that at least one result is visualized

#####_**Scenario 2 (optional)**_
Same as scenario 1, but once in the results page:
4. Sort the result set by price (lowest first)
5. Assess that the results are sorted correctly

###Part 2 – Test Cases
Imagine we have just implemented the booking search form in the home page: 
do not consider the result page for this task, just the search form (city, dates and number of travellers). 
Please identify and document the test cases you consider relevant for the search form feature.

##Test Cases