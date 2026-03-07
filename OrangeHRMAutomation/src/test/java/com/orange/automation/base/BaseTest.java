package com.orange.automation.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;

import com.orange.automation.utils.AllureReport;
import com.orange.automation.utils.ConfigReader;
import com.orange.automation.utils.DriverManager;


public class BaseTest {
    //Parent class for all Test classes. Setup & teardown live here
    // Logger instance for this class
    protected static final Logger logger = LogManager.getLogger(BaseTest.class);


    @BeforeMethod
    public void startTest() {
        logger.info("Test Started");
        DriverManager.initDriver(ConfigReader.getBrowser());
        DriverManager.goToURL(ConfigReader.getUrl());
    }

    @AfterMethod
    public void endTest() {
        DriverManager.quitDriver();
        logger.info("Test Ended");
    }

    @AfterSuite
    public void teardown() {
        AllureReport.generateAllureReport();
    }

}
