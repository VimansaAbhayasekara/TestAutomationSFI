package com.sfimonitor.stepdefinitions;

import com.sfimonitor.pages.DashboardPage;
import com.sfimonitor.pages.VendorConfigurationPage;
import com.sfimonitor.utils.CustomLogger;
import com.sfimonitor.utils.DriverManager;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class VendorConfigurationSteps {
    private WebDriver driver;
    private DashboardPage dashboardPage;
    private VendorConfigurationPage vendorConfigurationPage;

    public VendorConfigurationSteps() {
        this.driver = DriverManager.getDriver();
        this.vendorConfigurationPage = new VendorConfigurationPage(driver);
    }

    @When("I click the menu button in vendor configuration")
    public void i_click_the_menu_button_in_vendor_configuration() {
        vendorConfigurationPage.clickMenuButton();
        CustomLogger.logSuccess("Menu button clicked");
    }

    @When("I select Vendor Configuration option")
    public void i_select_vendor_configuration_option() {
        vendorConfigurationPage.clickVendorConfigurationOption();
        CustomLogger.logSuccess("Vendor Configuration option selected");

        // Wait for page to load
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Then("I should see the Vendor Configuration page with header")
    public void i_should_see_the_vendor_configuration_page_with_header() {
        boolean isPageDisplayed = vendorConfigurationPage.isVendorConfigurationPageDisplayed();
        String headerText = vendorConfigurationPage.getVendorConfigurationHeaderText();

        Assert.assertTrue("Vendor Configuration page should be displayed. Header: " + headerText,
                isPageDisplayed);
        CustomLogger.logSuccess("Vendor Configuration page displayed - Header: " + headerText);
    }

    @When("I click the Manage Asset Catalog icon of the second record")
    public void i_click_the_manage_asset_catalog_icon_of_the_second_record() {
        vendorConfigurationPage.clickManageAssetCatalogIcon();
        CustomLogger.logSuccess("Manage Asset Catalog icon clicked");

        // Wait for action to complete
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @When("I click the dropdown icon")
    public void i_click_the_dropdown_icon() {
        vendorConfigurationPage.clickDropdownIcon();
        CustomLogger.logSuccess("Dropdown icon clicked");

        // Wait for dropdown to appear
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @When("I select Add Object Type option")
    public void i_select_add_object_type_option() {
        vendorConfigurationPage.clickAddObjectTypeOption();
        CustomLogger.logSuccess("Add Object Type option selected");

        // Wait for slide panel to appear
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Then("I should see the slide panel open")
    public void i_should_see_the_slide_panel_open() {
        boolean isPanelVisible = vendorConfigurationPage.isSlidePanelVisible();
        Assert.assertTrue("Slide panel should be visible", isPanelVisible);
        CustomLogger.logSuccess("Slide panel is visible");


        // Wait for panel to fully load
        vendorConfigurationPage.waitForSlidePanelToLoad();
    }

    @Then("I verify the Available currency label contains {string}")
    public void i_verify_the_available_currency_label_contains(String expectedCurrency) {


        String availableLabelText = vendorConfigurationPage.getAvailableCurrencyLabelText();
        boolean containsAUD = vendorConfigurationPage.verifyCurrencyLabelContainsAUD(availableLabelText);

        Assert.assertTrue("Available currency label should contain '" + expectedCurrency +
                "'. Actual: '" + availableLabelText + "'", containsAUD);
        CustomLogger.logSuccess("Available currency label verified: " + availableLabelText);
    }

    @Then("I verify the Per Day currency label contains {string}")
    public void i_verify_the_per_day_currency_label_contains(String expectedCurrency) {
        String perDayLabelText = vendorConfigurationPage.getPerDayCurrencyLabelText();
        boolean containsAUD = vendorConfigurationPage.verifyCurrencyLabelContainsAUD(perDayLabelText);

        Assert.assertTrue("Per Day currency label should contain '" + expectedCurrency +
                "'. Actual: '" + perDayLabelText + "'", containsAUD);
        CustomLogger.logSuccess("Per Day currency label verified: " + perDayLabelText);
    }

    @Then("I verify the Per Week currency label contains {string}")
    public void i_verify_the_per_week_currency_label_contains(String expectedCurrency) {
        String perWeekLabelText = vendorConfigurationPage.getPerWeekCurrencyLabelText();
        boolean containsAUD = vendorConfigurationPage.verifyCurrencyLabelContainsAUD(perWeekLabelText);

        Assert.assertTrue("Per Week currency label should contain '" + expectedCurrency +
                "'. Actual: '" + perWeekLabelText + "'", containsAUD);
        CustomLogger.logSuccess("Per Week currency label verified: " + perWeekLabelText);
    }

    @Then("I verify the Per Month currency label contains {string}")
    public void i_verify_the_per_month_currency_label_contains(String expectedCurrency) {
        String perMonthLabelText = vendorConfigurationPage.getPerMonthCurrencyLabelText();
        boolean containsAUD = vendorConfigurationPage.verifyCurrencyLabelContainsAUD(perMonthLabelText);

        Assert.assertTrue("Per Month currency label should contain '" + expectedCurrency +
                "'. Actual: '" + perMonthLabelText + "'", containsAUD);
        CustomLogger.logSuccess("Per Month currency label verified: " + perMonthLabelText);
    }
}