package com.sfimonitor.pages;

import com.sfimonitor.utils.WebDriverUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
    protected WebDriver driver;

    // Common locators
    private By loadingSpinner = By.cssSelector(".loading-spinner");

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    protected void waitForPageToLoad() {
        WebDriverUtils.waitForElementInvisible(driver, loadingSpinner);
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}