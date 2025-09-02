package com.sfimonitor.stepdefinitions;

import com.sfimonitor.pages.DashboardPage;
import com.sfimonitor.pages.LoginPage;
import com.sfimonitor.utils.ConfigReader;
import com.sfimonitor.utils.CustomLogger;
import com.sfimonitor.utils.DriverManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class LoginSteps {
    private WebDriver driver;
    private LoginPage loginPage;
    private DashboardPage dashboardPage;

    public LoginSteps() {
        this.driver = DriverManager.getDriver();
    }

    @Given("I am on the login page")
    public void i_am_on_the_login_page() {
        // Use the actual login URL you provided
        driver.get("https://auth-qa.sfimonitordev.au/realms/ORG-290075/protocol/openid-connect/auth?client_id=monitor-web&redirect_uri=https%3A%2F%2Fmos001-monitor.sfimonitordev.au%2F&state=a7ba9886-76fd-48f3-a596-4e2d51b482c4&response_mode=fragment&response_type=code&scope=openid&nonce=047385c8-c712-499b-8581-be62a38e0e2d");
        loginPage = new LoginPage(driver);

        // Verify we're actually on the login page
        Assert.assertTrue("Should be on login page", loginPage.isLoginPageDisplayed());
        CustomLogger.logSuccess("Navigated to login page");
    }

    @When("I enter username {string} and password {string}")
    public void i_enter_username_and_password(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        CustomLogger.logSuccess("Entered credentials");
    }

    @When("I click the remember me option")
    public void i_click_the_remember_me_option() {
        loginPage.clickRememberMe();
        CustomLogger.logSuccess("Remember me selected");
    }

    @When("I click the login button")
    public void i_click_the_login_button() {
        loginPage.clickSignIn();
        CustomLogger.logSuccess("Login button clicked");
    }

    @Then("I should be redirected to the dashboard")
    public void i_should_be_redirected_to_the_dashboard() {
        dashboardPage = new DashboardPage(driver);

        // Wait for dashboard to load
        try {
            Thread.sleep(3000); // Wait for page load
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        boolean isDashboardDisplayed = dashboardPage.isDashboardDisplayed();
        String headerText = dashboardPage.getDashboardHeaderText();

        Assert.assertTrue("Dashboard should be displayed with header 'Dashboard'. Actual header: '" + headerText + "'",
                isDashboardDisplayed);
        CustomLogger.logSuccess("Redirected to dashboard");
    }

    @Then("I should see an error message")
    public void i_should_see_an_error_message() {
        boolean errorDisplayed = loginPage.isErrorMessageDisplayed();
        String errorText = loginPage.getErrorMessage();

        Assert.assertTrue("Error message should be displayed. Error text: " + errorText,
                errorDisplayed);
        CustomLogger.logError("Error displayed: " + errorText);
    }

    @Then("I should see error message containing {string}")
    public void i_should_see_error_message_containing(String expectedMessage) {
        String actualMessage = loginPage.getErrorMessage();

        Assert.assertTrue("Error message should contain: '" + expectedMessage + "', but was: '" + actualMessage + "'",
                actualMessage.contains(expectedMessage));
        CustomLogger.logError("Error contains: " + expectedMessage);
    }

    @Given("I am logged in to the application")
    public void i_am_logged_in_to_the_application() {
        // Navigate to login page
        i_am_on_the_login_page();

        // Login with valid credentials
        i_enter_username_and_password("superadmin", "admin");

        // Click remember me (optional)
        i_click_the_remember_me_option();

        // Click login button
        i_click_the_login_button();

        // Verify we're on dashboard
        i_should_be_redirected_to_the_dashboard();

        CustomLogger.logSuccess("Successfully logged in to the application");
    }
}