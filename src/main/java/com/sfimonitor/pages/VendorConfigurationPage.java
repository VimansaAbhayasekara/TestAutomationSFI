package com.sfimonitor.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class VendorConfigurationPage extends BasePage {

    // Menu button
    @FindBy(xpath = "/html/body/div[1]/div/div/div[1]/div/div/div/div/div/div[3]/button")
    private WebElement menuButton;

    // Vendor Configuration option
    @FindBy(xpath = "/html/body/div[3]/div[3]/ul/a[17]")
    private WebElement vendorConfigurationOption;

    // Vendor Configuration header
    @FindBy(xpath = "/html/body/div[1]/div/div/div[1]/main/div/div[1]/div/h6")
    private WebElement vendorConfigurationHeader;

    // Manage Asset Catalog icon for the second record
    @FindBy(xpath = "/html/body/div[1]/div/div/div[1]/main/div/div[2]/div/div/div[2]/div/div/div/div/div/div/table/tbody/tr[2]/td[7]/div/button[1]")
    private WebElement manageAssetCatalogIcon;

    // Dropdown icon
    @FindBy(xpath = "/html/body/div[1]/div/div/div[1]/main/div/div[1]/div/div/button[2]")
    private WebElement dropdownIcon;

    // Add Object Type option
    @FindBy(xpath = "/html/body/div[1]/div/div/div[1]/main/div/div[1]/div/div[2]/div/ul/li[1]")
    private WebElement addObjectTypeOption;

    // Slide panel container
    @FindBy(xpath = "/html/body/div[6]/div[3]")
    private WebElement slidePanel;

    // Available currency label - Primary XPath
    @FindBy(xpath = "/html/body/div[6]/div[3]/div/div[2]/div/form/div[2]/div[1]/div[2]/div/div[2]/div/label")
    private WebElement availableCurrencyLabel;

    // Alternative Available currency label locators
    @FindBy(xpath = "//label[contains(text(), 'Price (AUD)')]")
    private WebElement availableCurrencyLabelByText;

    @FindBy(xpath = "//label[contains(., 'AUD')]")
    private WebElement availableCurrencyLabelByAUD;

    // Per Day currency label - Primary XPath
    @FindBy(xpath = "/html/body/div[6]/div[3]/div/div[2]/div/form/div[2]/div[2]/div[2]/div[2]/div[2]/div/label")
    private WebElement perDayCurrencyLabel;

    // Alternative Per Day currency label locators
    @FindBy(xpath = "(//label[contains(text(), 'Price (AUD)')])[2]")
    private WebElement perDayCurrencyLabelByText;

    @FindBy(xpath = "(//label[contains(., 'AUD')])[2]")
    private WebElement perDayCurrencyLabelByAUD;

    // Per Week currency label - Primary XPath
    @FindBy(xpath = "/html/body/div[6]/div[3]/div/div[2]/div/form/div[2]/div[2]/div[2]/div[3]/div[2]/div/label")
    private WebElement perWeekCurrencyLabel;

    // Alternative Per Week currency label locators
    @FindBy(xpath = "(//label[contains(text(), 'Price (AUD)')])[3]")
    private WebElement perWeekCurrencyLabelByText;

    @FindBy(xpath = "(//label[contains(., 'AUD')])[3]")
    private WebElement perWeekCurrencyLabelByAUD;

    // Per Month currency label - Primary XPath
    @FindBy(xpath = "/html/body/div[6]/div[3]/div/div[2]/div/form/div[2]/div[2]/div[2]/div[4]/div[2]/div/label")
    private WebElement perMonthCurrencyLabel;

    // Alternative Per Month currency label locators
    @FindBy(xpath = "(//label[contains(text(), 'Price (AUD)')])[4]")
    private WebElement perMonthCurrencyLabelByText;

    @FindBy(xpath = "(//label[contains(., 'AUD')])[4]")
    private WebElement perMonthCurrencyLabelByAUD;

    public VendorConfigurationPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void clickMenuButton() {
        menuButton.click();
        waitForPageToLoad();
    }

    public void clickVendorConfigurationOption() {
        vendorConfigurationOption.click();
        waitForPageToLoad();
    }

    public boolean isVendorConfigurationPageDisplayed() {
        try {
            return vendorConfigurationHeader.isDisplayed() &&
                    "Vendor Configuration".equals(vendorConfigurationHeader.getText());
        } catch (Exception e) {
            return false;
        }
    }

    public String getVendorConfigurationHeaderText() {
        try {
            return vendorConfigurationHeader.getText();
        } catch (Exception e) {
            return "Vendor Configuration header not found";
        }
    }

    public void clickManageAssetCatalogIcon() {
        manageAssetCatalogIcon.click();
        waitForPageToLoad();
    }

    public void clickDropdownIcon() {
        dropdownIcon.click();
        waitForPageToLoad();
    }

    public void clickAddObjectTypeOption() {
        addObjectTypeOption.click();
        waitForPageToLoad();
    }

    public boolean isSlidePanelVisible() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            return wait.until(ExpectedConditions.visibilityOf(slidePanel)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getAvailableCurrencyLabelText() {
        WebDriverWait wait = new WebDriverWait(driver, 10);

        // Try multiple strategies to find the Available currency label
        try {
            return wait.until(ExpectedConditions.visibilityOf(availableCurrencyLabel)).getText();
        } catch (Exception e1) {
            try {
                return wait.until(ExpectedConditions.visibilityOf(availableCurrencyLabelByText)).getText();
            } catch (Exception e2) {
                try {
                    return wait.until(ExpectedConditions.visibilityOf(availableCurrencyLabelByAUD)).getText();
                } catch (Exception e3) {
                    // Final fallback: search for any label containing AUD
                    try {
                        return driver.findElement(By.xpath("(//label[contains(., 'AUD')])[1]")).getText();
                    } catch (Exception e4) {
                        return "Available currency label not found";
                    }
                }
            }
        }
    }

    public String getPerDayCurrencyLabelText() {
        WebDriverWait wait = new WebDriverWait(driver, 10);

        // Try multiple strategies to find the Per Day currency label
        try {
            return wait.until(ExpectedConditions.visibilityOf(perDayCurrencyLabel)).getText();
        } catch (Exception e1) {
            try {
                return wait.until(ExpectedConditions.visibilityOf(perDayCurrencyLabelByText)).getText();
            } catch (Exception e2) {
                try {
                    return wait.until(ExpectedConditions.visibilityOf(perDayCurrencyLabelByAUD)).getText();
                } catch (Exception e3) {
                    // Final fallback: search for any label containing AUD
                    try {
                        return driver.findElement(By.xpath("(//label[contains(., 'AUD')])[2]")).getText();
                    } catch (Exception e4) {
                        return "Per Day currency label not found";
                    }
                }
            }
        }
    }

    public String getPerWeekCurrencyLabelText() {
        WebDriverWait wait = new WebDriverWait(driver, 10);

        // Try multiple strategies to find the Per Week currency label
        try {
            return wait.until(ExpectedConditions.visibilityOf(perWeekCurrencyLabel)).getText();
        } catch (Exception e1) {
            try {
                return wait.until(ExpectedConditions.visibilityOf(perWeekCurrencyLabelByText)).getText();
            } catch (Exception e2) {
                try {
                    return wait.until(ExpectedConditions.visibilityOf(perWeekCurrencyLabelByAUD)).getText();
                } catch (Exception e3) {
                    // Final fallback: search for any label containing AUD
                    try {
                        return driver.findElement(By.xpath("(//label[contains(., 'AUD')])[3]")).getText();
                    } catch (Exception e4) {
                        return "Per Week currency label not found";
                    }
                }
            }
        }
    }

    public String getPerMonthCurrencyLabelText() {
        WebDriverWait wait = new WebDriverWait(driver, 10);

        // Try multiple strategies to find the Per Month currency label
        try {
            return wait.until(ExpectedConditions.visibilityOf(perMonthCurrencyLabel)).getText();
        } catch (Exception e1) {
            try {
                return wait.until(ExpectedConditions.visibilityOf(perMonthCurrencyLabelByText)).getText();
            } catch (Exception e2) {
                try {
                    return wait.until(ExpectedConditions.visibilityOf(perMonthCurrencyLabelByAUD)).getText();
                } catch (Exception e3) {
                    // Final fallback: search for any label containing AUD
                    try {
                        return driver.findElement(By.xpath("(//label[contains(., 'AUD')])[4]")).getText();
                    } catch (Exception e4) {
                        return "Per Month currency label not found";
                    }
                }
            }
        }
    }

    public boolean verifyCurrencyLabelContainsAUD(String labelText) {
        return labelText != null && labelText.contains("(AUD)");
    }

    // Method to wait for slide panel to be fully loaded
    public void waitForSlidePanelToLoad() {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        try {
            wait.until(ExpectedConditions.visibilityOf(slidePanel));
            // Wait a bit more for all elements to be loaded
            Thread.sleep(2000);
        } catch (Exception e) {
            // Continue even if wait fails
        }
    }

}