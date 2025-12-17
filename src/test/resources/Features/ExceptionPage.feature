Feature: Test Exception Page

  Scenario: Test No Such Element Exception 
    Given user is on the exception page
    When the user add row2
    #And enter details in row2
    Then row2 is displayed