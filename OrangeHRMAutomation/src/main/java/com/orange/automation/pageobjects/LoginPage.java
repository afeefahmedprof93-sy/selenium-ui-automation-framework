package com.orange.automation.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.orange.automation.base.BasePage;

public class LoginPage extends BasePage {

    @FindBy(name = "username")
    private static WebElement usernameInput;

    @FindBy(name = "password")
    private static WebElement passwordInput;

    @FindBy(css = "button.orangehrm-login-button")
    private static WebElement loginButton;

    public void enterUsername(String username) {
        try{
            usernameInput.clear();
            usernameInput.sendKeys(username);
            stepPassed("Username is successfully entered");
        } catch(Exception e) {
            stepFailed("Failed to enter Username :" + e.getMessage());
        }
    }

    public void enterPassword(String password) {
        try{
            passwordInput.clear();
            passwordInput.sendKeys(password);
            stepPassed("Username is successfully password");
        } catch(Exception e) {
            stepFailed("Failed to enter password :" + e.getMessage());
        }
    }

    public void clickLogin() {
        try {
            loginButton.click();
            stepPassed("Successfully clicked the login button");
        } catch (Exception e) {
            stepFailed("Failed to click the login button :" + e.getMessage());
        }
    }

    public void login(String username, String password) {
        try {
            enterUsername(username);
            enterPassword(password);
            clickLogin();
            stepPassed("Successfully logged in");
        } catch (Exception e) {
            stepFailed("Failed to log in :" + e.getMessage());
        }
    }  
}
