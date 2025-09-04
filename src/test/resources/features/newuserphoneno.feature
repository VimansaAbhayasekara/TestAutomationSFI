Feature: User Management - Phone Number Validation
  As an administrator
  I want to add new users with proper phone number validation
  So that users can be contacted when needed

  @userManagement
  Scenario: Add new user with phone number validation and random credentials
    Given I am logged in to the application
    When I click the menu button
    And I select User Management option
    And I click the Add User button
    Then I should see the Add New User header
    And I verify the default country code is "+61" in phone number field
    When I add random 8 digits to the phone number field
    And I enter random 8-letter name
    And I enter random 8-letter email
    And I select role as "SUPERUSER"
    And I enter password as "Test1234"
    And I enter confirm password as "Test1234"
    And I click the Save button
    Then I should see the user saved successfully message