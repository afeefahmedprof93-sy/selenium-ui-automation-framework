package com.orange.automation.testcases;

import com.orange.automation.base.BaseTest;
import com.orange.automation.pageobjects.LoginPage;
import com.orange.automation.utils.ConfigReader;
import com.orange.automation.utils.PageManager;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test class covering Authentication module test cases:
 *  TC-001 — Login with valid credentials
 *  TC-002 — Login with invalid credentials
 *  TC-003 — Login with empty fields
 */
@Epic("OrangeHRM")
@Feature("Authentication")
public class LoginPageTest extends BaseTest {

    // ─────────────────────────────────────────────────────────────
    // TC-001: Login with valid credentials
    // ─────────────────────────────────────────────────────────────

    @Test(priority = 1, description = "TC-001: Login with valid credentials")
    @Story("Valid Login")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Enter valid credentials Admin/admin123 and verify redirect to Dashboard")
    public void TC_001_LoginWithValidCredentials() {

        LoginPage loginPage = PageManager.getLoginPage();

        loginPage.loginWith(
                ConfigReader.getUsername(),   // Admin
                ConfigReader.getPassword()    // admin123
        );

        Assert.assertTrue(
                loginPage.isDashboardDisplayed(),
                "Dashboard should be displayed after successful login"
        );

        Assert.assertEquals(
                loginPage.getDashboardHeaderText(),
                "Dashboard",
                "Page header should read 'Dashboard' after login"
        );
    }

    // ─────────────────────────────────────────────────────────────
    // TC-002: Login with invalid credentials
    // ─────────────────────────────────────────────────────────────

    @Test(priority = 2, description = "TC-002: Login with invalid credentials")
    @Story("Invalid Login")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Enter wrong username/password and verify the error message is displayed")
    public void TC_002_LoginWithInvalidCredentials() {

        LoginPage loginPage = PageManager.getLoginPage();

        loginPage.loginWith("invalidUser", "wrongPassword123");

        Assert.assertTrue(
                loginPage.isInvalidCredentialsErrorDisplayed(),
                "Error alert should be visible for invalid credentials"
        );

        Assert.assertEquals(
                loginPage.getInvalidCredentialsErrorText(),
                "Invalid credentials",
                "Error message text should be 'Invalid credentials'"
        );
    }

    // ─────────────────────────────────────────────────────────────
    // TC-003: Login with empty fields
    // ─────────────────────────────────────────────────────────────

    @Test(priority = 3, description = "TC-003: Login with empty fields")
    @Story("Empty Field Validation")
    @Severity(SeverityLevel.NORMAL)
    @Description("Submit blank login form and verify required field validation messages appear")
    public void TC_003_LoginWithEmptyFields() {

        LoginPage loginPage = PageManager.getLoginPage();

        loginPage.clickLoginWithoutCredentials();

        Assert.assertEquals(
                loginPage.getUsernameRequiredErrorText(),
                "Required",
                "Username field should show 'Required' validation message"
        );

        Assert.assertEquals(
                loginPage.getPasswordRequiredErrorText(),
                "Required",
                "Password field should show 'Required' validation message"
        );
    }
}