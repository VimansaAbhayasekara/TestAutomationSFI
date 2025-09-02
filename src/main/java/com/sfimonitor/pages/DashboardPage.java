package com.sfimonitor.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage extends BasePage {

    // Dashboard header using the XPath you provided
    @FindBy(xpath = "/html/body/div[1]/div/div/div[1]/main/div/div[1]/div/h6/span")
    private WebElement dashboardHeader;

    @FindBy(linkText = "Monitoring")
    private WebElement monitoringTab;

    @FindBy(linkText = "Reports")
    private WebElement reportsTab;

    @FindBy(linkText = "Settings")
    private WebElement settingsTab;

    @FindBy(css = ".user-profile")
    private WebElement userProfile;

    public DashboardPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean isDashboardDisplayed() {
        try {
            return dashboardHeader.isDisplayed() &&
                    "Dashboard".equals(dashboardHeader.getText());
        } catch (Exception e) {
            return false;
        }
    }

    public String getDashboardHeaderText() {
        try {
            return dashboardHeader.getText();
        } catch (Exception e) {
            return "Dashboard header not found";
        }
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public void clickMonitoringTab() {
        monitoringTab.click();
    }

    public void clickReportsTab() {
        reportsTab.click();
    }

    public void clickSettingsTab() {
        settingsTab.click();
    }

    public void clickUserProfile() {
        userProfile.click();
    }
}