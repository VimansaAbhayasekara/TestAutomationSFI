package com.sfimonitor.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.sfimonitor.utils.WebDriverUtils;
import org.junit.Assert;

public class AssetsPage extends BasePage {

    // Locators
    @FindBy(xpath = "//*[@id=\"app\"]/div/div/div[1]/div/div/div/div/div/div[3]/button")
    private WebElement menuIcon;

    @FindBy(xpath = "//a[contains(text(), 'Assets')]")
    private WebElement assetsMenuItem;

    @FindBy(xpath = "//*[@id=\"app\"]/div/div/div[1]/main/div/div[1]/div[1]/h6")
    private WebElement assetsHeader;

    @FindBy(xpath = "//*[@id=\"app\"]/div/div/div[1]/main/div/div[2]/div[2]/header/div/div[2]/div/button[1]")
    private WebElement objectsTab;

    @FindBy(xpath = "//*[@id=\"app\"]/div/div/div[1]/main/div/div[2]/div[2]/header/div/div[2]/div/button[2]")
    private WebElement itemsTab;

    @FindBy(xpath = "//*[@id=\"app\"]/div/div/div[1]/main/div/div[1]/div[2]/button[1]")
    private WebElement addAssetButton;

    // Drawer header - using the XPath you provided
    @FindBy(xpath = "/html/body/div[6]/div[3]/div/div[1]/h6/span")
    private WebElement drawerHeader;

    // Object No input field in the drawer
    @FindBy(xpath = "/html/body/div[6]/div[3]/div/div[2]/div/div/form/div[2]/div[1]/div/div/input")
    private WebElement objectNoInput;

    @FindBy(xpath = "//div[contains(@class, 'MuiDrawer-paper')]//input[contains(@placeholder, 'Object No')]")
    private WebElement objectNoInputByPlaceholder;

    // Object Type field in the drawer
    @FindBy(xpath = "/html/body/div[6]/div[3]/div/div[2]/div/div/form/div[2]/div[2]/div/div/div/input")
    private WebElement objectTypeInput;

    @FindBy(xpath = "//div[contains(@class, 'MuiDrawer-paper')]//input[contains(@placeholder, 'Object Type')]")
    private WebElement objectTypeInputByPlaceholder;

    // Save button in the drawer
    @FindBy(xpath = "/html/body/div[6]/div[3]/div/div[3]/div[2]/button[2]")
    private WebElement saveButton;

    @FindBy(xpath = "//div[contains(@class, 'MuiDrawer-paper')]//button[contains(text(), 'Save')]")
    private WebElement saveButtonByText;

    // Success notification
    @FindBy(xpath = "//div[contains(@class, 'MuiAlert-message') and contains(text(), 'Object added successfully')]")
    private WebElement successNotification;

    public AssetsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void clickMenuIcon() {
        menuIcon.click();
    }

    public void clickAssetsMenuItem() {
        assetsMenuItem.click();
    }

    public boolean isAssetsPageDisplayed() {
        try {
            return assetsHeader.isDisplayed() &&
                    "Assets".equals(assetsHeader.getText());
        } catch (Exception e) {
            return false;
        }
    }

    public String getAssetsHeaderText() {
        try {
            return assetsHeader.getText();
        } catch (Exception e) {
            return "Assets header not found";
        }
    }

    public boolean isObjectsTabActive() {
        try {
            return objectsTab.isDisplayed() && objectsTab.getAttribute("class").contains("Mui-selected");
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isItemsTabActive() {
        try {
            return itemsTab.isDisplayed() && itemsTab.getAttribute("class").contains("Mui-selected");
        } catch (Exception e) {
            return false;
        }
    }

    public void clickObjectsTab() {
        objectsTab.click();
    }

    public void clickItemsTab() {
        itemsTab.click();
    }

    public void clickAddAssetButton() {
        addAssetButton.click();
    }

    public boolean isAddObjectDrawerVisible() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            WebElement header = wait.until(ExpectedConditions.visibilityOf(drawerHeader));
            return header.isDisplayed() && "Add Object".equals(header.getText());
        } catch (Exception e) {
            return false;
        }
    }

    public String getDrawerHeaderText() {
        try {
            return drawerHeader.getText();
        } catch (Exception e) {
            return "Drawer header not found";
        }
    }

    public void enterObjectNo(String objectNo) {
        WebDriverWait wait = new WebDriverWait(driver, 10);

        // Wait for drawer to be visible by checking the header
        wait.until(ExpectedConditions.visibilityOf(drawerHeader));
        Assert.assertTrue("Drawer should have 'Add Object' header", "Add Object".equals(drawerHeader.getText()));

        // Try multiple strategies to find and enter Object No
        try {
            WebElement input = wait.until(ExpectedConditions.visibilityOf(objectNoInput));
            input.clear();
            input.sendKeys(objectNo);
        } catch (Exception e1) {
            try {
                WebElement input = wait.until(ExpectedConditions.visibilityOf(objectNoInputByPlaceholder));
                input.clear();
                input.sendKeys(objectNo);
            } catch (Exception e2) {
                // Try to find input by position in the drawer
                WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[contains(@class, 'MuiDrawer-paper')]//input[1]")
                ));
                input.clear();
                input.sendKeys(objectNo);
            }
        }
    }

    public void selectObjectType(String objectTypeValue) {
        WebDriverWait wait = new WebDriverWait(driver, 10);

        // Wait for drawer to be visible by checking the header
        wait.until(ExpectedConditions.visibilityOf(drawerHeader));
        Assert.assertTrue("Drawer should have 'Add Object' header", "Add Object".equals(drawerHeader.getText()));

        // Try multiple strategies to find and select Object Type
        try {
            WebElement typeInput = wait.until(ExpectedConditions.elementToBeClickable(objectTypeInput));
            typeInput.click();
            typeInput.sendKeys(objectTypeValue);
        } catch (Exception e1) {
            try {
                WebElement typeInput = wait.until(ExpectedConditions.elementToBeClickable(objectTypeInputByPlaceholder));
                typeInput.click();
                typeInput.sendKeys(objectTypeValue);
            } catch (Exception e2) {
                WebElement typeInput = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//div[contains(@class, 'MuiDrawer-paper')]//input[2]")
                ));
                typeInput.click();
                typeInput.sendKeys(objectTypeValue);
            }
        }

        // Wait for dropdown options and select the value
        try {
            Thread.sleep(1000); // Wait for options to appear
            WebElement option = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//li[contains(text(), '" + objectTypeValue + "')]")
            ));
            option.click();
        } catch (Exception e) {
            // If no dropdown options, just continue with the entered value
        }
    }

    public void clickSaveButton() {
        WebDriverWait wait = new WebDriverWait(driver, 10);

        // Wait for drawer to be visible by checking the header
        wait.until(ExpectedConditions.visibilityOf(drawerHeader));
        Assert.assertTrue("Drawer should have 'Add Object' header", "Add Object".equals(drawerHeader.getText()));

        // Try multiple strategies to find and click Save button
        try {
            WebElement saveBtn = wait.until(ExpectedConditions.elementToBeClickable(saveButton));
            saveBtn.click();
        } catch (Exception e1) {
            try {
                WebElement saveBtn = wait.until(ExpectedConditions.elementToBeClickable(saveButtonByText));
                saveBtn.click();
            } catch (Exception e2) {
                WebElement saveBtn = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//div[contains(@class, 'MuiDrawer-paper')]//button[contains(@type, 'submit')]")
                ));
                saveBtn.click();
            }
        }
    }

    public boolean isSuccessNotificationDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        try {
            return wait.until(ExpectedConditions.visibilityOf(successNotification)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getSuccessNotificationText() {
        try {
            return successNotification.getText();
        } catch (Exception e) {
            return "Success notification not found";
        }
    }

    // Generate random 6-digit number
    public String generateRandomObjectNo() {
        int randomNum = (int) (Math.random() * 900000) + 100000;
        return String.valueOf(randomNum);
    }

    // Method to debug drawer structure
    public void printDrawerStructure() {
        try {
            if (isAddObjectDrawerVisible()) {
                System.out.println("Drawer is visible with header: " + getDrawerHeaderText());

                // Print all inputs in the drawer
                java.util.List<WebElement> inputs = driver.findElements(By.xpath("/html/body/div[6]/div[3]//input"));
                System.out.println("Inputs found in drawer: " + inputs.size());

                for (WebElement input : inputs) {
                    System.out.println("  Input - Placeholder: " + input.getAttribute("placeholder") +
                            ", Name: " + input.getAttribute("name") +
                            ", Type: " + input.getAttribute("type") +
                            ", Value: " + input.getAttribute("value"));
                }

                // Print all buttons in the drawer
                java.util.List<WebElement> buttons = driver.findElements(By.xpath("/html/body/div[6]/div[3]//button"));
                System.out.println("Buttons found in drawer: " + buttons.size());

                for (WebElement button : buttons) {
                    System.out.println("  Button - Text: " + button.getText() +
                            ", Type: " + button.getAttribute("type"));
                }
            } else {
                System.out.println("Drawer is not visible");
            }
        } catch (Exception e) {
            System.out.println("Error printing drawer structure: " + e.getMessage());
        }
    }
}