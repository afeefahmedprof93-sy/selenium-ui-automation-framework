package com.orange.automation.testcases;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.orange.automation.base.BaseTest;
import com.orange.automation.pageobjects.LoginPage;
import com.orange.automation.utils.ConfigReader;
import com.orange.automation.utils.PageManager;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Authentication Module")
@Feature("Login Feature")
@Listeners({io.qameta.allure.testng.AllureTestNg.class})
public class LoginPageTest extends BaseTest{

    LoginPage loginPage;
    
    @Test
    @Story("Valid Login")
    @Severity(SeverityLevel.CRITICAL)
    @Description("User can login with valid credentials")
    public void verifyLogin() {
        logger.info("[Start]: verifyLogin");

        loginPage = PageManager.getLoginPage();

        loginPage.login(ConfigReader.getUsername(),ConfigReader.getPassword());

    }
}
