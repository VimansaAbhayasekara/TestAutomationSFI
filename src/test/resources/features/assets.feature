Feature: Assets Management
  As a user
  I want to manage assets in the SFI Monitor application
  So that I can track and organize objects and items

  @assets
  Scenario: Add new object to Assets
    Given I am logged in to the application
    When I navigate to the Assets page
    And I ensure I am on the Objects tab
    And I click the Add Asset button
    And I enter a random Object No
    And I select Object Type "2089"
    And I click the Save button
    Then I should see the success notification "Object added successfully"
    And the new asset should be created successfully