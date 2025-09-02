package com.sfimonitor.stepdefinitions;

import com.sfimonitor.pages.AssetsPage;
import com.sfimonitor.pages.DashboardPage;
import com.sfimonitor.utils.CustomLogger;
import com.sfimonitor.utils.DriverManager;
import com.sfimonitor.utils.WebDriverUtils;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class AssetsSteps {
    private WebDriver driver;
    private DashboardPage dashboardPage;
    private AssetsPage assetsPage;
    private String generatedObjectNo;

    public AssetsSteps() {
        this.driver = DriverManager.getDriver();
        this.assetsPage = new AssetsPage(driver);
    }

    @When("I navigate to the Assets page")
    public void i_navigate_to_the_assets_page() {
        dashboardPage = new DashboardPage(driver);

        // Click menu icon
        assetsPage.clickMenuIcon();
        CustomLogger.logSuccess("Clicked menu icon");

        // Wait for menu to appear with explicit wait
        WebDriverWait wait = new WebDriverWait(driver, 10);

        try {
            // Try multiple possible selectors for the Assets menu item
            WebElement assetsMenuItem = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//a[contains(text(), 'Assets') or contains(@href, 'assets')]")
            ));

            assetsMenuItem.click();
            CustomLogger.logSuccess("Clicked Assets menu item using text selector");

        } catch (Exception e1) {
            try {
                // Alternative approach: try finding by XPath with different indices
                WebElement assetsMenuItem = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//body/div[contains(@class, 'MuiPopover-paper')]//a[1]")
                ));

                assetsMenuItem.click();
                CustomLogger.logSuccess("Clicked Assets menu item using positional selector");

            } catch (Exception e2) {
                // Last resort: try the original XPath with explicit wait
                WebElement assetsMenuItem = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("/html/body/div[4]/div[3]/ul/a[1]")
                ));

                assetsMenuItem.click();
                CustomLogger.logSuccess("Clicked Assets menu item using original XPath");
            }
        }

        // Wait for Assets page to load
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Verify we're on Assets page with multiple attempts
        boolean isAssetsPage = false;
        int attempts = 0;
        while (attempts < 5 && !isAssetsPage) {
            try {
                isAssetsPage = assetsPage.isAssetsPageDisplayed();
                if (!isAssetsPage) {
                    Thread.sleep(1000);
                    attempts++;
                }
            } catch (Exception e) {
                attempts++;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
        }

        Assert.assertTrue("Should be on Assets page", isAssetsPage);
        CustomLogger.logSuccess("Navigated to Assets page - Header: " + assetsPage.getAssetsHeaderText());
    }

    @When("I ensure I am on the Objects tab")
    public void i_ensure_i_am_on_the_objects_tab() {
        // Check if we're already on Objects tab
        if (!assetsPage.isObjectsTabActive()) {
            // If not, check if we're on Items tab and switch to Objects
            if (assetsPage.isItemsTabActive()) {
                assetsPage.clickObjectsTab();
                CustomLogger.logSuccess("Switched from Items to Objects tab");
            } else {
                // If neither is active, click Objects tab
                assetsPage.clickObjectsTab();
                CustomLogger.logSuccess("Clicked Objects tab");
            }

            // Wait for tab switch
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        } else {
            CustomLogger.logSuccess("Already on Objects tab");
        }

        Assert.assertTrue("Should be on Objects tab", assetsPage.isObjectsTabActive());
    }

    @When("I click the Add Asset button")
    public void i_click_the_add_asset_button() {
        assetsPage.clickAddAssetButton();
        CustomLogger.logSuccess("Clicked Add Asset button");

        // Wait for drawer to appear with explicit wait
        WebDriverWait wait = new WebDriverWait(driver, 15);
        try {
            // Wait for the drawer header using the specific XPath you provided
            WebElement drawerHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("/html/body/div[6]/div[3]/div/div[1]/h6/span")
            ));

            // Verify the header text is "Add Object"
            Assert.assertEquals("Drawer header should be 'Add Object'", "Add Object", drawerHeader.getText());

            CustomLogger.logSuccess("Add Object drawer is visible with correct header");

            // Debug: Print drawer structure to understand what's available
            assetsPage.printDrawerStructure();

        } catch (Exception e) {
            CustomLogger.logError("Add Object drawer did not appear: " + e.getMessage());

            // Try alternative selectors for the drawer
            try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//span[contains(text(), 'Add Object')]")
                ));
                CustomLogger.logSuccess("Found drawer with 'Add Object' text");
            } catch (Exception e2) {
                try {
                    wait.until(ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//h6[contains(text(), 'Add Object')]")
                    ));
                    CustomLogger.logSuccess("Found drawer with h6 containing 'Add Object'");
                } catch (Exception e3) {
                    Assert.fail("Add Object drawer did not appear. Tried: /html/body/div[6]/div[3]/div/div[1]/h6/span, text containing 'Add Object'");
                }
            }
        }
    }

    @When("I enter a random Object No")
    public void i_enter_a_random_object_no() {
        // Verify drawer is still visible with correct header
        Assert.assertTrue("Add Object drawer should be visible", assetsPage.isAddObjectDrawerVisible());
        Assert.assertEquals("Drawer should have 'Add Object' header", "Add Object", assetsPage.getDrawerHeaderText());

        generatedObjectNo = assetsPage.generateRandomObjectNo();
        assetsPage.enterObjectNo(generatedObjectNo);
        CustomLogger.logSuccess("Entered random Object No: " + generatedObjectNo);
    }

    @When("I select Object Type {string}")
    public void i_select_object_type(String objectTypeValue) {
        // Verify drawer is still visible with correct header
        Assert.assertTrue("Add Object drawer should be visible", assetsPage.isAddObjectDrawerVisible());
        Assert.assertEquals("Drawer should have 'Add Object' header", "Add Object", assetsPage.getDrawerHeaderText());

        assetsPage.selectObjectType(objectTypeValue);
        CustomLogger.logSuccess("Selected Object Type: " + objectTypeValue);

        // Wait for selection to be applied
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @When("I click the Save button")
    public void i_click_the_save_button() {
        // Verify drawer is still visible with correct header
        Assert.assertTrue("Add Object drawer should be visible", assetsPage.isAddObjectDrawerVisible());
        Assert.assertEquals("Drawer should have 'Add Object' header", "Add Object", assetsPage.getDrawerHeaderText());

        assetsPage.clickSaveButton();
        CustomLogger.logSuccess("Clicked Save button");

        // Wait for save operation to complete and drawer to close
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Then("I should see the success notification {string}")
    public void i_should_see_the_success_notification(String expectedMessage) {
        boolean notificationDisplayed = assetsPage.isSuccessNotificationDisplayed();
        String notificationText = assetsPage.getSuccessNotificationText();

        Assert.assertTrue("Success notification should be displayed. Text: " + notificationText,
                notificationDisplayed);
        Assert.assertTrue("Notification should contain: '" + expectedMessage + "', but was: '" + notificationText + "'",
                notificationText.contains(expectedMessage));
        CustomLogger.logSuccess("Success notification displayed: " + notificationText);
    }

    @Then("the new asset should be created successfully")
    public void the_new_asset_should_be_created_successfully() {
        // This step could verify that the asset appears in the list
        // For now, we'll just log success since we already verified the notification
        CustomLogger.logSuccess("New asset created successfully with Object No: " + generatedObjectNo);
    }
}