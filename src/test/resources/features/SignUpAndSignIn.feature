@SignUp
Feature: Sign Up and Login to Magento site

  # Email id is randomly generated on each test run
  @SignUpWithValidCredentials
  Scenario: Create a new account
    Given User opens the base website
    When User selects create an account option
    Then User should be navigated to create a new account page
    Then User enters first name as "Tushar" and last name as "Katole"
    And User enters email, password as "Pass@123" and confirm password as "Pass@123"
    And User submits the sign-up form
    Then User should be registered and logged in

  @SignUpWithValidCredentialsAndLogoutAndSignIn
  Scenario: Create a new account, logout and sign in
    Given User opens the base website
    When User selects create an account option
    Then User should be navigated to create a new account page
    Then User enters first name as "Tushar" and last name as "Katole"
    And User enters email, password as "Pass@123" and confirm password as "Pass@123"
    And User submits the sign-up form
    Then User should be registered and logged in
    When User Signs out
    Then User is redirected to Home Page
    When User selects Sign In option
    Then User is redirected to Sign In Page
    When User enters email and password as "Pass@123" and submits the form
    Then User is logged in successfully

  @SignUpWithAllBlankFields
  Scenario: Sign up with all blank fields
    Given User opens the base website
    When User selects create an account option
    Then User should be navigated to create a new account page
    Then User enters first name as "" and last name as ""
    And User enters blank email, password as "" and confirm password as ""
    And User submits the sign-up form
    Then User faces error message

  @SignUpWithNameFieldsBlank
  Scenario: Sign up with name fields blank
    Given User opens the base website
    When User selects create an account option
    Then User should be navigated to create a new account page
    Then User enters first name as "" and last name as ""
    And User enters email, password as "Pass@123" and confirm password as "Pass@123"
    And User submits the sign-up form
    Then User faces error message

  @SignUpWithEmailFieldsBlank
  Scenario: Sign up with email field blank
    Given User opens the base website
    When User selects create an account option
    Then User should be navigated to create a new account page
    Then User enters first name as "" and last name as ""
    And User enters blank email, password as "Pass@123" and confirm password as "Pass@123"
    And User submits the sign-up form
    Then User faces error message

  @SignUpWithDifferentPasswordAndConfirmPassword
  Scenario: Sign up using different password and confirm password
    Given User opens the base website
    When User selects create an account option
    Then User should be navigated to create a new account page
    Then User enters first name as "Tushar" and last name as "Katole"
    And User enters email, password as "Pass@123" and confirm password as "Pass@1234"
    And User submits the sign-up form
    Then User faces error message