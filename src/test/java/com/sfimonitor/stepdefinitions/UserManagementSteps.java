package com.sfimonitor.stepdefinitions;

import com.sfimonitor.pages.DashboardPage;
import com.sfimonitor.pages.UserManagementPage;
import com.sfimonitor.utils.CustomLogger;
import com.sfimonitor.utils.DriverManager;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import java.util.Random;

public class UserManagementSteps {
    private WebDriver driver;
    private DashboardPage dashboardPage;
    private UserManagementPage userManagementPage;
    private String generatedName;
    private String generatedEmail;

    public UserManagementSteps() {
        this.driver = DriverManager.getDriver();
        this.dashboardPage = new DashboardPage(driver);
    }

    // Helper method to generate random 8-letter string
    private String generateRandomLetters(int length) {
        Random random = new Random();
        StringBuilder randomLetters = new StringBuilder();
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            randomLetters.append(characters.charAt(index));
        }

        return randomLetters.toString();
    }

    @When("I click the menu button")
    public void i_click_the_menu_button() {
        userManagementPage = new UserManagementPage(driver);
        userManagementPage.clickMenuButton();
        CustomLogger.logSuccess("Menu button clicked");
    }

    @When("I select User Management option")
    public void i_select_user_management_option() {
        userManagementPage.clickUserManagementOption();
        CustomLogger.logSuccess("User Management option selected");
    }

    @When("I click the Add User button")
    public void i_click_the_add_user_button() {
        userManagementPage.clickAddUserButton();
        CustomLogger.logSuccess("Add User button clicked");
    }

    @Then("I should see the Add New User header")
    public void i_should_see_the_add_new_user_header() {
        boolean isHeaderDisplayed = userManagementPage.isAddNewUserHeaderDisplayed();
        Assert.assertTrue("Add New User header should be displayed", isHeaderDisplayed);
        CustomLogger.logSuccess("Add New User header is displayed");
    }

    @Then("I verify the default country code is {string} in phone number field")
    public void i_verify_the_default_country_code_is_in_phone_number_field(String expectedCountryCode) {
        String actualPhoneValue = userManagementPage.getPhoneNumberDefaultValue();
        Assert.assertTrue("Phone number field should start with: " + expectedCountryCode +
                        ", but was: " + actualPhoneValue,
                actualPhoneValue.startsWith(expectedCountryCode));
        CustomLogger.logSuccess("Default country code verified: " + expectedCountryCode);
    }

    @When("I add random 8 digits to the phone number field")
    public void i_add_random_8_digits_to_the_phone_number_field() {
        // Generate random 8 digits
        Random random = new Random();
        StringBuilder randomDigits = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            randomDigits.append(random.nextInt(10));
        }

        String fullPhoneNumber = "+61" + randomDigits.toString();
        userManagementPage.enterPhoneNumber(fullPhoneNumber);
        CustomLogger.logSuccess("Random phone number entered: " + fullPhoneNumber);
    }

    @When("I enter random 8-letter name")
    public void i_enter_random_8_letter_name() {
        generatedName = generateRandomLetters(8);
        userManagementPage.enterName(generatedName);
        CustomLogger.logSuccess("Random 8-letter name entered: " + generatedName);
    }

    @When("I enter random 8-letter email")
    public void i_enter_random_8_letter_email() {
        String randomUsername = generateRandomLetters(8);
        generatedEmail = randomUsername + "@gmail.com";
        userManagementPage.enterEmail(generatedEmail);
        CustomLogger.logSuccess("Random 8-letter email entered: " + generatedEmail);
    }

    @When("I select role as {string}")
    public void i_select_role_as(String role) {
        userManagementPage.selectRole(role);
        CustomLogger.logSuccess("Role selected: " + role);
    }

    @When("I enter password as {string}")
    public void i_enter_password_as(String password) {
        userManagementPage.enterPassword(password);
        CustomLogger.logSuccess("Password entered");
    }

    @When("I enter confirm password as {string}")
    public void i_enter_confirm_password_as(String password) {
        userManagementPage.enterConfirmPassword(password);
        CustomLogger.logSuccess("Confirm password entered");
    }

    @When("I click the Save button")
    public void i_click_the_save_button() {
        userManagementPage.clickSaveButton();
        CustomLogger.logSuccess("Save button clicked");
    }

    @Then("I should see the user saved successfully message")
    public void i_should_see_the_user_saved_successfully_message() {
        boolean isSuccessDisplayed = userManagementPage.isSuccessMessageDisplayed();
        String successMessage = userManagementPage.getSuccessMessage();

        Assert.assertTrue("Success message should be displayed. Message: " + successMessage,
                isSuccessDisplayed);
        CustomLogger.logSuccess("User saved successfully: " + successMessage);
    }
}