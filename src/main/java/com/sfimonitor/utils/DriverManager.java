package com.sfimonitor.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import java.util.concurrent.TimeUnit;

public class DriverManager {
    private static WebDriver driver;

    private DriverManager() {}

    public static WebDriver getDriver() {
        if (driver == null) {
            initializeDriver();
        }
        return driver;
    }

    private static void initializeDriver() {
        String browser = ConfigReader.getBrowser();
        boolean headless = ConfigReader.isHeadless();

        switch (browser.toLowerCase()) {
            case "chrome":
                // Manual ChromeDriver setup - use the path from environment variable or config
                String chromeDriverPath = System.getenv("CHROME_DRIVER_PATH");
                if (chromeDriverPath == null || chromeDriverPath.isEmpty()) {
                    chromeDriverPath = ConfigReader.getProperty("chrome.driver.path");
                }

                if (chromeDriverPath != null && !chromeDriverPath.isEmpty()) {
                    System.setProperty("webdriver.chrome.driver", chromeDriverPath);
                } else {
                    // Fallback: try to use WebDriverManager as last resort
                    try {
                        io.github.bonigarcia.wdm.WebDriverManager.chromedriver().setup();
                    } catch (Exception e) {
                        throw new RuntimeException("ChromeDriver path not specified and WebDriverManager failed. " +
                                "Please set CHROME_DRIVER_PATH environment variable or chrome.driver.path in config.properties");
                    }
                }

                ChromeOptions chromeOptions = new ChromeOptions();
                if (headless) {
                    chromeOptions.addArguments("--headless");
                }
                chromeOptions.addArguments("--start-maximized");
                chromeOptions.addArguments("--disable-notifications");
                chromeOptions.addArguments("--remote-allow-origins=*");
                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.addArguments("--disable-dev-shm-usage");
                chromeOptions.addArguments("--disable-gpu");
                chromeOptions.addArguments("--window-size=1920,1080");
                driver = new ChromeDriver(chromeOptions);
                break;

            case "firefox":
                io.github.bonigarcia.wdm.WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if (headless) {
                    firefoxOptions.addArguments("--headless");
                }
                driver = new FirefoxDriver(firefoxOptions);
                driver.manage().window().maximize();
                break;

            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

        // Set implicit wait using Java 8 compatible method
        driver.manage().timeouts().implicitlyWait(
                Long.parseLong(ConfigReader.getProperty("implicit.wait")),
                TimeUnit.SECONDS
        );
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}