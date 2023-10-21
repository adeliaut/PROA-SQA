Feature: Add product to cart

  Scenario: Add product to cart from all products page
    Given User is already login with correct username and password
    Then User click Add to cart button
    And User go to cart page
    Then User should see added product in cart