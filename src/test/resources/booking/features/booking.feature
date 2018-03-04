@test
Feature: Testing search functionality on Booking.com

  Scenario: It is possible to search for hotels for specific criteria
    Given client is at a http://booking.com main page
    And currency is set to Euro
    And interface language is set to English (UK)
    When client chooses "Málaga, Andalucía, Spain" as a destination
    And he sets checkin date as a last day of current month
    And he sets checkout date as a first day of next month
    And he sets number of adults to 1
    And he sets number of children to 1
    And he sets child age to 5 years
    And he sets number of rooms to 2
    And he sets he's travelling for work
    And he submits search form
    And he chooses only available properties
    And he chooses only properties with review rate above 8
    Then search results contain items with price less than 200.00 and rating more than 8.0