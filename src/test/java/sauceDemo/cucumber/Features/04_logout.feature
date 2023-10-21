Feature: Logout

  Scenario: Logout
    Given User is already login with correct username and password
    Then User logout
    Then User should see login page