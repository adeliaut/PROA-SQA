Feature: Login to shop

  Scenario Outline: Login to saucedemo.com
    Given User open login page
    When User input <username> and <password> info
    And User click login button
    Then Login has <status> status

    Examples:
    | username      | password        | status  |
    | standard_user | secret_sauce    | success |
    | standard_user | secret_ketchup  | fail    |