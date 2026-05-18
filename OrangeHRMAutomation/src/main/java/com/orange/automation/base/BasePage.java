package com.orange.automation.base;

import java.io.ByteArrayInputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import com.orange.automation.utils.DriverManager;
import com.orange.automation.utils.ScreenshotUtil;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;

public class BasePage {
    //Parent class for all Page classes. Common browser actions live here
    protected static final Logger logger = LogManager.getLogger(BasePage.class);

    public static void stepFailed(String message) {

        // Log failure with Log4j2
        logger.error("Test Failed: {}", message);

        // Add step to Allure report
        Allure.step("Test Failed: " + message);

        byte[] image = ScreenshotUtil.attachScreenshot();

        // Attach screenshot to Allure
        Allure.addAttachment(message, new ByteArrayInputStream(image));
    }

    public static void stepPassed(String message) {

        // Log failure with Log4j2
        logger.info("Test Passed: {}", message);

        // Add step to Allure report
        Allure.step("Test Passed: " + message);

    }

    // NavigationHelper.java

    @Step("Navigate to: {moduleName}")
    public void toModule(String moduleName) {
        DriverManager.getDriver().findElement(By.xpath(
            "//span[contains(@class,'oxd-main-menu-item--name') and normalize-space()='" + moduleName + "']"))
            .click();
            stepPassed("Navigated to " + moduleName);
    }



}
