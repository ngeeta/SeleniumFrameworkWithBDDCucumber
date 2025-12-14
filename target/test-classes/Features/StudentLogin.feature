Feature: Test Login Page
  I want to test Student Login functionality

  Scenario: Valid login with correct credentials
    Given user is on the login page
    When the user enters username "VALID_USER" and password "VALID_PASS"
    And clicks on login button
    Then verify new page URL contains "https://practicetestautomation.com/logged-in-successfully/"
    And verify new page contains expected text "Congratulations student. You successfully logged in!"
    And Verify button Log out is displayed on the new page