@SignUp
Feature: Sign Up and Login to Magento site

  @SignUpWithValidCredentials
  Scenario: Create a new account
    Given User opens the base website
    When User selects create an account option
    Then User should be navigated to create a new account page
    Then User enters first name as "Tushar" and last name as "Katole"
    And User enters email, password as "Pass@123" and confirm password as "Pass@123"
    And User submits the sign-up form
    Then User should be registered and logged in