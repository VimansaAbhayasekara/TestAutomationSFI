Feature: Login Functionality
  As a user
  I want to login to the SFI Monitor application
  So that I can access the dashboard and monitoring features

  @smoke
  Scenario: Successful sign in with valid credentials and remember me
    Given I am on the login page
    When I enter username "superadmin" and password "admin"
    And I click the remember me option
    And I click the login button
    Then I should be redirected to the dashboard

  Scenario: Successful login with valid credentials without remember me
    Given I am on the login page
    When I enter username "superadmin" and password "admin"
    And I click the login button
    Then I should be redirected to the dashboard

  Scenario: Failed login with invalid credentials
    Given I am on the login page
    When I enter username "ADMIN" and password "ADMIN"
    And I click the login button
    Then I should see an error message
    And I should see error message containing "Invalid username or password."

  Scenario: Failed login with empty credentials
    Given I am on the login page
    When I enter username "" and password ""
    And I click the login button
    Then I should see an error message
    And I should see error message containing "Invalid username or password."

