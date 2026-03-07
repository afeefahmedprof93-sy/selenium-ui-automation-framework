package com.orange.automation.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class DriverManager {

    // Logger instance for this class
    private static final Logger logger = LogManager.getLogger(DriverManager.class);

    // ThreadLocal makes sure that each thread gets a copy of the driver
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    // we dont want anyone to make a object of this class. We are using static method. we will call them directly
    private DriverManager() {}

    // Returns the current thread's WebDriver instance
    public static WebDriver getDriver() {
        return driver.get();
    }

    // Creates and sets up the browser
    public static void initDriver(String browser) {
        logger.info("Initializing browser: " + browser);
        Allure.step("Initializing browser: " + browser);

        WebDriver webDriver;

        switch (browser.toLowerCase()) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                webDriver = new FirefoxDriver();
                logger.info("Firefox browser launched");
                break;

            case "edge":
                WebDriverManager.edgedriver().setup();
                webDriver = new EdgeDriver();
                logger.info("Edge browser launched");
                break;

            case "chrome":
            default:
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--start-maximized");
                options.addArguments("--disable-notifications");
                webDriver = new ChromeDriver(options);
                webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(ConfigReader.getImplicitWait()));
                logger.info("Chrome browser launched");
                Allure.step("Chrome browser launched");
                break;
        }

        driver.set(webDriver); //populating the threadlocal
    }

    // Closes the browser and cleans up
    public static void quitDriver() {
        if (driver.get() != null) {
            logger.info("Closing browser");
            Allure.step("Closing browser");
            driver.get().quit();
            driver.remove(); // Cleans up ThreadLocal
        }
    }

    public static void goToURL(String url) {
        try {
            driver.get().get(url);
            logger.info("Successfully to navigate to URL: " + url);
            Allure.step("Successfully to navigate to URL: " + url);
        } catch (Exception e){
            logger.error("Failed to navigate to URL: " + url);
            Allure.step("Failed to navigate to URL: " + url);
            throw new RuntimeException(e);
        }
    }
}