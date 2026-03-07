package com.orange.automation.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.orange.automation.utils.ScreenshotUtil;
import io.qameta.allure.Allure;

public class BasePage {
    //Parent class for all Page classes. Common browser actions live here
    protected static final Logger logger = LogManager.getLogger(BasePage.class);

    public static void stepFailed(String message) {

        // Log failure with Log4j2
        logger.error("Test Failed: {}", message);

        // Add step to Allure report
        Allure.step("Test Failed: " + message);

        // Attach screenshot to Allure
        ScreenshotUtil.attachScreenshot();
    }

    public static void stepPassed(String message) {

        // Log failure with Log4j2
        logger.info("Test Passed: {}", message);

        // Add step to Allure report
        Allure.step("Test Passed: " + message);

    }


}
