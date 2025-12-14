Feature: Test Login Page
  I want to test Student Login functionality

  Scenario: Valid login with correct credentials
    Given user is on the login page
    When the user enters username "VALID_USER" and password "VALID_PASS"
    And clicks on login button
    Then verify new page URL contains "https://practicetestautomation.com/logged-in-successfully/"
    And verify new page contains expected text "Congratulations student. You successfully logged in!"
    And Verify button Log out is displayed on the new page

  Scenario Outline: Invalid login – <case>
    Given user is on the login page
    When the user enters username "<username>" and password "<password>"
    And clicks on login button
    Then an error message "<errorMessage>" should be displayed

    Examples: 
      | case              | username    | password    | errorMessage              |
      | wrong password    | VALID_USER  | wrongPass   | Your password is invalid! |
      | invalid user/pass | invalidUser | invalidPass | Your username is invalid! |
      | blank credentials |             |             | Your username is invalid! |
