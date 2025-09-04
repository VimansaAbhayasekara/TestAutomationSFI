Feature: Vendor Configuration - Primary Currency Verification
  As an administrator
  I want to verify that primary currency (AUD) is displayed correctly in vendor configuration
  So that I can ensure proper currency formatting throughout the application

  @vendorConfig
  Scenario: Verify primary currency AUD in vendor configuration
    Given I am logged in to the application
    When I click the menu button in vendor configuration
    And I select Vendor Configuration option
    Then I should see the Vendor Configuration page with header
    When I click the Manage Asset Catalog icon of the second record
    And I click the dropdown icon
    And I select Add Object Type option
#    Then I should see the slide panel open
    And I verify the Available currency label contains "(AUD)"
    And I verify the Per Day currency label contains "(AUD)"
    And I verify the Per Week currency label contains "(AUD)"
    And I verify the Per Month currency label contains "(AUD)"