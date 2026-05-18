package com.orange.automation.pageobjects;

import com.orange.automation.base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Page Object for the OrangeHRM Login Page.
 * All locators and actions related to login are encapsulated here.
 */
public class LoginPage extends BasePage {

    // ─────────────────────────────────────────────
    // Locators
    // ─────────────────────────────────────────────

    @FindBy(name = "username")
    private WebElement usernameField;

    @FindBy(name = "password")
    private WebElement passwordField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement loginButton;

    /** Error alert shown for invalid credentials */
    @FindBy(xpath = "//div[contains(@class,'oxd-alert-content')]//p")
    private WebElement invalidCredentialsError;

    /** Inline required-field validation message under username */
    @FindBy(xpath = "(//span[contains(@class,'oxd-input-field-error-message')])[1]")
    private WebElement usernameRequiredError;

    /** Inline required-field validation message under password */
    @FindBy(xpath = "(//span[contains(@class,'oxd-input-field-error-message')])[2]")
    private WebElement passwordRequiredError;

    /** Element that confirms a successful login / dashboard load */
    @FindBy(xpath = "//h6[contains(@class,'oxd-topbar-header-breadcrumb-module')]")
    private WebElement dashboardHeader;


    // ─────────────────────────────────────────────
    // Actions
    // ─────────────────────────────────────────────

    @Step("Enter username: {username}")
    public LoginPage enterUsername(String username) {
        try {
            usernameField.clear();
            usernameField.sendKeys(username);
            stepPassed("Entered username: " + username);
        } catch (Exception e) {
            stepFailed("Failed to enter username: " + e.getMessage());
        }
        return this;
    }

    @Step("Enter password")
    public LoginPage enterPassword(String password) {
        try {
            passwordField.clear();
            passwordField.sendKeys(password);
            stepPassed("Entered password successfully");
        } catch (Exception e) {
            stepFailed("Failed to enter password: " + e.getMessage());
        }
        return this;
    }

    @Step("Click Login button")
    public LoginPage clickLoginButton() {
        try {
            loginButton.click();
            stepPassed("Clicked Login button");
        } catch (Exception e) {
            stepFailed("Failed to click Login button: " + e.getMessage());
        }
        return this;
    }

    // ─────────────────────────────────────────────
    // Composite Actions (reusable flows)
    // ─────────────────────────────────────────────

    @Step("Login with username: {username}")
    public LoginPage loginWith(String username, String password) {
        return enterUsername(username)
                .enterPassword(password)
                .clickLoginButton();
    }

    @Step("Submit login form with empty fields")
    public LoginPage clickLoginWithoutCredentials() {
        return clickLoginButton();
    }

    // ─────────────────────────────────────────────
    // Getters — for assertions in test layer
    // ─────────────────────────────────────────────

    @Step("Get dashboard header text")
    public String getDashboardHeaderText() {
        try {
            String text = dashboardHeader.getText();
            stepPassed("Dashboard header text retrieved: " + text);
            return text;
        } catch (Exception e) {
            stepFailed("Dashboard header not found: " + e.getMessage());
            return "";
        }
    }

    @Step("Get invalid credentials error message")
    public String getInvalidCredentialsErrorText() {
        try {
            String text = invalidCredentialsError.getText();
            stepPassed("Error message retrieved: " + text);
            return text;
        } catch (Exception e) {
            stepFailed("Error message element not found: " + e.getMessage());
            return "";
        }
    }

    @Step("Get username required field error text")
    public String getUsernameRequiredErrorText() {
        try {
            String text = usernameRequiredError.getText();
            stepPassed("Username required error retrieved: " + text);
            return text;
        } catch (Exception e) {
            stepFailed("Username required error not found: " + e.getMessage());
            return "";
        }
    }

    @Step("Get password required field error text")
    public String getPasswordRequiredErrorText() {
        try {
            String text = passwordRequiredError.getText();
            stepPassed("Password required error retrieved: " + text);
            return text;
        } catch (Exception e) {
            stepFailed("Password required error not found: " + e.getMessage());
            return "";
        }
    }

    @Step("Check if dashboard is displayed")
    public boolean isDashboardDisplayed() {
        try {
            boolean displayed = dashboardHeader.isDisplayed();
            stepPassed("Dashboard displayed: " + displayed);
            return displayed;
        } catch (Exception e) {
            stepFailed("Dashboard not displayed: " + e.getMessage());
            return false;
        }
    }

    @Step("Check if invalid credentials error is displayed")
    public boolean isInvalidCredentialsErrorDisplayed() {
        try {
            boolean displayed = invalidCredentialsError.isDisplayed();
            stepPassed("Invalid credentials error displayed: " + displayed);
            return displayed;
        } catch (Exception e) {
            stepFailed("Invalid credentials error not visible: " + e.getMessage());
            return false;
        }
    }
}