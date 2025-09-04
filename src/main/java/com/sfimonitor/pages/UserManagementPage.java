package com.sfimonitor.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class UserManagementPage extends BasePage {

    // Menu button
    @FindBy(xpath = "/html/body/div[1]/div/div/div[1]/div/div/div/div/div/div[3]/button")
    private WebElement menuButton;

    // User Management option
    @FindBy(xpath = "/html/body/div[3]/div[3]/ul/a[18]")
    private WebElement userManagementOption;

    // Add User button
    @FindBy(xpath = "/html/body/div[1]/div/div/div[1]/main/div/div[1]/div[2]/button[1]")
    private WebElement addUserButton;

    // Add New User header
    @FindBy(xpath = "/html/body/div[5]/div[3]/div/div[1]/h6")
    private WebElement addNewUserHeader;

    // Phone Number field
    @FindBy(xpath = "/html/body/div[5]/div[3]/div/div[2]/div/div/div/form/div/div[1]/div/div[7]/div/div/input")
    private WebElement phoneNumberField;

    // Name field
    @FindBy(xpath = "/html/body/div[5]/div[3]/div/div[2]/div/div/div/form/div/div[1]/div/div[2]/div/div/input")
    private WebElement nameField;

    // Email field
    @FindBy(xpath = "/html/body/div[5]/div[3]/div/div[2]/div/div/div/form/div/div[1]/div/div[3]/div/div/input")
    private WebElement emailField;

    // Role dropdown
    @FindBy(xpath = "/html/body/div[5]/div[3]/div/div[2]/div/div/div/form/div/div[1]/div/div[4]/div/div/div")
    private WebElement roleDropdown;

    // SUPERUSER option
    @FindBy(xpath = "/html/body/div[8]/div[3]/ul/li[1]")
    private WebElement superuserOption;

    // Password field
    @FindBy(xpath = "/html/body/div[5]/div[3]/div/div[2]/div/div/div/form/div/div[1]/div/div[5]/div/div/input")
    private WebElement passwordField;

    // Confirm Password field
    @FindBy(xpath = "/html/body/div[5]/div[3]/div/div[2]/div/div/div/form/div/div[1]/div/div[6]/div/div/input")
    private WebElement confirmPasswordField;

    // Save button
    @FindBy(xpath = "/html/body/div[5]/div[3]/div/div[3]/div[2]/button[2]")
    private WebElement saveButton;

    // Success message - Updated locator for the actual success message
    @FindBy(xpath = "//div[contains(@class, 'MuiAlert-message') and contains(text(), 'User created and registration email sent successfully.')]")
    private WebElement successMessage;

    // Alternative success message locator (if the above doesn't work)
    @FindBy(xpath = "//div[contains(text(), 'User created and registration email sent successfully.')]")
    private WebElement successMessageAlt;

    // Toast notification container (if the success message appears in a toast)
    @FindBy(xpath = "//div[contains(@class, 'Toastify')]//div[contains(text(), 'User created')]")
    private WebElement toastSuccessMessage;

    public UserManagementPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void clickMenuButton() {
        menuButton.click();
        waitForPageToLoad();
    }

    public void clickUserManagementOption() {
        userManagementOption.click();
        waitForPageToLoad();
    }

    public void clickAddUserButton() {
        addUserButton.click();
        waitForPageToLoad();
    }

    public boolean isAddNewUserHeaderDisplayed() {
        try {
            return addNewUserHeader.isDisplayed() &&
                    "Add New User".equals(addNewUserHeader.getText());
        } catch (Exception e) {
            return false;
        }
    }

    public String getPhoneNumberDefaultValue() {
        try {
            return phoneNumberField.getAttribute("value");
        } catch (Exception e) {
            return "Phone number field not found";
        }
    }

    public void enterPhoneNumber(String phoneNumber) {
        phoneNumberField.clear();
        phoneNumberField.sendKeys(phoneNumber);
    }

    public void enterName(String name) {
        nameField.clear();
        nameField.sendKeys(name);
    }

    public void enterEmail(String email) {
        emailField.clear();
        emailField.sendKeys(email);
    }

    public void selectRole(String role) {
        roleDropdown.click();
        // Wait for dropdown to appear
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        if ("SUPERUSER".equals(role)) {
            superuserOption.click();
        }
        // Add more role options as needed
    }

    public void enterPassword(String password) {
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void enterConfirmPassword(String password) {
        confirmPasswordField.clear();
        confirmPasswordField.sendKeys(password);
    }

    public void clickSaveButton() {
        saveButton.click();
        waitForPageToLoad();
    }

    public boolean isSuccessMessageDisplayed() {
        try {
            // Try multiple locators for success message
            WebDriverWait wait = new WebDriverWait(driver, 10);

            // Check first locator
            try {
                return wait.until(ExpectedConditions.visibilityOf(successMessage)).isDisplayed();
            } catch (Exception e) {
                // Try alternative locator
                try {
                    return wait.until(ExpectedConditions.visibilityOf(successMessageAlt)).isDisplayed();
                } catch (Exception ex) {
                    // Try toast notification locator
                    try {
                        return wait.until(ExpectedConditions.visibilityOf(toastSuccessMessage)).isDisplayed();
                    } catch (Exception exc) {
                        // Try generic text search
                        return driver.findElements(By.xpath("//*[contains(text(), 'User created and registration email sent successfully.')]")).size() > 0;
                    }
                }
            }
        } catch (Exception e) {
            return false;
        }
    }

    public String getSuccessMessage() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);

            // Try multiple locators to get the success message text
            try {
                return wait.until(ExpectedConditions.visibilityOf(successMessage)).getText();
            } catch (Exception e) {
                try {
                    return wait.until(ExpectedConditions.visibilityOf(successMessageAlt)).getText();
                } catch (Exception ex) {
                    try {
                        return wait.until(ExpectedConditions.visibilityOf(toastSuccessMessage)).getText();
                    } catch (Exception exc) {
                        // Search for any element containing the success text
                        return driver.findElement(By.xpath("//*[contains(text(), 'User created and registration email sent successfully.')]")).getText();
                    }
                }
            }
        } catch (Exception e) {
            return "Success notification not found";
        }
    }

    public void addNewUser(String name, String email, String role, String phone, String password) {
        enterName(name);
        enterEmail(email);
        selectRole(role);
        enterPhoneNumber(phone);
        enterPassword(password);
        enterConfirmPassword(password);
        clickSaveButton();
    }
}