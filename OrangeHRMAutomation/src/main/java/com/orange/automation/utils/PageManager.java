package com.orange.automation.utils;

import org.openqa.selenium.support.PageFactory;

import com.orange.automation.base.BasePage;
import com.orange.automation.pageobjects.AdminPage;
import com.orange.automation.pageobjects.DashboardPage;
import com.orange.automation.pageobjects.LeavePage;
import com.orange.automation.pageobjects.LoginPage;
import com.orange.automation.pageobjects.MyInfoPage;
import com.orange.automation.pageobjects.PIMPage;

public class PageManager {
    
    private static <T> T GetPage(Class<T> className){
        return PageFactory.initElements(DriverManager.getDriver(), className);
    }

    public static BasePage getBasePage() {return GetPage(BasePage.class);}

    public static LoginPage getLoginPage() {return GetPage(LoginPage.class);}

    public static PIMPage getPIMPage() { return GetPage(PIMPage.class); }
    public static LeavePage getLeavePage() { return GetPage(LeavePage.class); }
    public static MyInfoPage getMyInfoPage() { return GetPage(MyInfoPage.class);}
    public static AdminPage getAdminPage() { return GetPage(AdminPage.class); }
    public static DashboardPage getDashboardPage() { return GetPage(DashboardPage.class); }
}
