package com.sfimonitor.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {

    // Locators using XPaths you provided
    @FindBy(xpath = "/html/body/div[1]/div[2]/div/div/div/div/form/div[1]/input")
    private WebElement usernameField;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div/div/div/div/form/div[2]/input")
    private WebElement passwordField;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div/div/div/div/form/div[3]/div[1]/div/label/label")
    private WebElement rememberMeCheckbox;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div/div/div/div/form/div[4]/input[2]")
    private WebElement signInButton;

    @FindBy(css = ".error-message")
    private WebElement errorMessage;

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void enterUsername(String username) {
        usernameField.clear();
        usernameField.sendKeys(username);
    }

    public void enterPassword(String password) {
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void clickRememberMe() {
        rememberMeCheckbox.click();
    }

    public void clickSignIn() {
        signInButton.click();
    }

    public void login(String username, String password, boolean rememberMe) {
        enterUsername(username);
        enterPassword(password);
        if (rememberMe) {
            clickRememberMe();
        }
        clickSignIn();
    }

    public boolean isErrorMessageDisplayed() {
        try {
            return errorMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getErrorMessage() {
        try {
            return errorMessage.getText();
        } catch (Exception e) {
            return "No error message found";
        }
    }

    public boolean isLoginPageDisplayed() {
        try {
            return usernameField.isDisplayed() && signInButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}