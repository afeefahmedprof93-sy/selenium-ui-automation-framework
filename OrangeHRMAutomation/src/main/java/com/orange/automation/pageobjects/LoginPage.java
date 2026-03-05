package com.orange.automation.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.orange.automation.base.BasePage;

import io.qameta.allure.Step;

public class LoginPage extends BasePage {

    @FindBy(name = "username")
    private WebElement usernameInput;

    @FindBy(name = "password")
    private WebElement passwordInput;

    @FindBy(css = "button.orangehrm-login-button")
    private WebElement loginButton;

    @Step("Enter username: {0}")
    public void enterUsername(String username) {
        this.usernameInput.clear();
        this.usernameInput.sendKeys(username);
    }

    @Step("Enter password: {0}")
    public void enterPassword(String password) {
        this.passwordInput.clear();
        this.passwordInput.sendKeys(password);
    }

    @Step("Click login button")
    public void clickLogin() {
        this.loginButton.click();
    }

    @Step("Perform login with username: {0} and password: {1}")
    public void login(String username, String password) {
        this.enterUsername(username);
        this.enterPassword(password);
        this.clickLogin();
    }  
}
