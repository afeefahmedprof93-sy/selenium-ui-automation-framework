package com.orange.automation.utils;

import org.openqa.selenium.support.PageFactory;

import com.orange.automation.base.BasePage;
import com.orange.automation.pageobjects.LoginPage;

public class PageManager {
    
    private static <T> T GetPage(Class<T> className){
        return PageFactory.initElements(DriverManager.getDriver(), className);
    }

    public static BasePage getBasePage() {return GetPage(BasePage.class);}

    public static LoginPage getLoginPage() {return GetPage(LoginPage.class);}
}
