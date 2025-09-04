package com.sfimonitor.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class WebDriverUtils {
    private static final int EXPLICIT_WAIT = Integer.parseInt(ConfigReader.getProperty("explicit.wait"));

    public static WebElement waitForElementVisible(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, EXPLICIT_WAIT);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement waitForElementClickable(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, EXPLICIT_WAIT);
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static boolean waitForElementInvisible(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, EXPLICIT_WAIT);
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public static boolean isElementPresent(WebDriver driver, By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public static void clickElement(WebDriver driver, By locator) {
        waitForElementClickable(driver, locator).click();
    }

    public static void sendKeys(WebDriver driver, By locator, String text) {
        WebElement element = waitForElementVisible(driver, locator);
        element.clear();
        element.sendKeys(text);
    }

    public static String getText(WebDriver driver, By locator) {
        return waitForElementVisible(driver, locator).getText();
    }

    public static void takeScreenshot(WebDriver driver, String testName) {
        // Screenshot implementation would go here
        System.out.println("Screenshot taken for test: " + testName);
    }

    public static void setImplicitWait(WebDriver driver, long timeout, TimeUnit unit) {
        driver.manage().timeouts().implicitlyWait(timeout, unit);
    }

    public static void selectDropdownOption(WebDriver driver, By dropdownLocator, String optionText) {
        WebElement dropdown = waitForElementClickable(driver, dropdownLocator);
        dropdown.click();

        // Wait for options to appear
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Select the option (you might need to adjust this based on your actual dropdown structure)
        WebElement option = driver.findElement(By.xpath("//li[contains(text(), '" + optionText + "')]"));
        option.click();
    }
}