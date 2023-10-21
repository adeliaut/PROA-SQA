Feature: Order product

  Scenario: Order product with complete information
    Given User is already login with correct username and password
    Then User click Add to cart button
    And User go to cart page
    Then User click checkout button
    And User fill shipping information
    And User click continue button
    Then User should see detail order
    Then User click finish button
    Then User should see success order page