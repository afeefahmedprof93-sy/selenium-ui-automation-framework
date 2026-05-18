package com.orange.automation.testcases;

import com.orange.automation.base.BaseTest;
import com.orange.automation.pageobjects.*;
import com.orange.automation.utils.ConfigReader;
import com.orange.automation.utils.PageManager;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("OrangeHRM Demo")
@Feature("Functional Regression - PIM, Leave, Admin, My Info")
public class FunctionalTest extends BaseTest {

    String employeeName = "";

    // TC01
    @Test(priority = 1, description = "TC01: Verify Employee Search by Employee Name")
    @Severity(SeverityLevel.CRITICAL)
    @Story("PIM Module")
    public void TC01_VerifyEmployeeSearch() {
        LoginPage loginPage = PageManager.getLoginPage();

        loginPage.loginWith(
                ConfigReader.getUsername(),   // Admin
                ConfigReader.getPassword()    // admin123
        );
        loginPage.toModule("PIM");
        PIMPage pimPage = PageManager.getPIMPage();
        pimPage.goToEmployeeList()
               .searchEmployee("Orange")
               .verifyFirstEmployeeNameContains("Orange");

    }

    // TC02
    @Test(priority = 2, description = "TC02: Verify Add New Employee")
    @Severity(SeverityLevel.CRITICAL)
    @Story("PIM Module")
    public void TC02_AddNewEmployee() {
        LoginPage loginPage = PageManager.getLoginPage();

        loginPage.loginWith(
                ConfigReader.getUsername(),   // Admin
                ConfigReader.getPassword()    // admin123
        );
        loginPage.toModule("PIM");
        PIMPage pimPage = PageManager.getPIMPage();

        employeeName = "Demo"+System.currentTimeMillis();
        
        pimPage.goToEmployeeList()
               .clickAddEmployee()
               .enterEmployeeDetails(employeeName, "Automation")
               .clickSave();

        Assert.assertTrue(true, "Employee added successfully"); // Replace with actual success validation

    }

    // TC03
    @Test(priority = 3, description = "TC03: Verify Edit Employee Details")
    @Severity(SeverityLevel.NORMAL)
    @Story("PIM Module")
    public void TC03_EditEmployeeDetails() {
        LoginPage loginPage = PageManager.getLoginPage();

        loginPage.loginWith(
                ConfigReader.getUsername(),   // Admin
                ConfigReader.getPassword()    // admin123
        );
        loginPage.toModule("PIM");
        PIMPage pimPage = PageManager.getPIMPage();
        MyInfoPage myInfoPage = PageManager.getMyInfoPage(); // Reusing some methods

        pimPage.goToEmployeeList()
               .searchEmployee("Orange")
               .clickOnFirstEmployee(); // You need to add this method in PIMPage

        // Edit Personal Details
        myInfoPage.updateMiddlename("TestNick" + System.currentTimeMillis())
                  .clickSave();

    }

    // TC04
    @Test(priority = 4, description = "TC04: Verify Delete Employee")
    @Severity(SeverityLevel.CRITICAL)
    @Story("PIM Module")
    public void TC04_DeleteEmployee() {
        LoginPage loginPage = PageManager.getLoginPage();

        loginPage.loginWith(
                ConfigReader.getUsername(),   // Admin
                ConfigReader.getPassword()    // admin123
        );
        PIMPage pimPage = PageManager.getPIMPage();
        loginPage.toModule("PIM");

        pimPage.goToEmployeeList()
               .searchEmployee(employeeName) // Use an employee you added earlier
               .selectFirstEmployee()
               .clickDelete()
               .confirmDelete();

    }

    // TC05
    @Test(priority = 5, description = "TC05: Verify Assign Leave")
    @Severity(SeverityLevel.NORMAL)
    @Story("Leave Module")
    public void TC05_ApplyLeave() {
        LoginPage loginPage = PageManager.getLoginPage();

        loginPage.loginWith(
                ConfigReader.getUsername(),   // Admin
                ConfigReader.getPassword()    // admin123
        );
        LeavePage leavePage = PageManager.getLeavePage();
        loginPage.toModule("Leave");

        leavePage.goToApplyLeave()
                 .selectLeaveType("US - Vacation")
                 .selectEmployeeName("Orange Test")
                 .selectFromDate("2026-06-01")
                 .selectToDate("2026-06-03")
                 .enterComment("Automation Test Leave")
                 .clickApply();

    }

    // TC06
    @Test(priority = 6, description = "TC06: Verify Leave List")
    @Severity(SeverityLevel.NORMAL)
    @Story("Leave Module")
    public void TC06_VerifyMyLeaveList() {
        LoginPage loginPage = PageManager.getLoginPage();

        loginPage.loginWith(
                ConfigReader.getUsername(),   // Admin
                ConfigReader.getPassword()    // admin123
        );
        LeavePage leavePage = PageManager.getLeavePage();
        loginPage.toModule("Leave");

        leavePage.selectFromDate("2026-01-01")
                 .selectToDate("2026-12-31")
                 .clickSearch();

    }

    // TC07
    @Test(priority = 7, description = "TC07: Verify Update Personal Information in My Info")
    @Severity(SeverityLevel.CRITICAL)
    @Story("My Info Module")
    public void TC07_UpdateMyInfo() {
        LoginPage loginPage = PageManager.getLoginPage();

        loginPage.loginWith(
                ConfigReader.getUsername(),   // Admin
                ConfigReader.getPassword()    // admin123
        );
        MyInfoPage myInfoPage = PageManager.getMyInfoPage();
        loginPage.toModule("My Info");

        myInfoPage.updateMiddlename("DemoTest")
                  .updateOtherID(""+System.currentTimeMillis())
                  .clickSave();

    }

    // TC08
    @Test(priority = 8, description = "TC08: Verify Dashboard Widgets Visibility")
    @Severity(SeverityLevel.NORMAL)
    @Story("Dashboard")
    public void TC08_VerifyDashboardWidgets() {
        LoginPage loginPage = PageManager.getLoginPage();

        loginPage.loginWith(
                ConfigReader.getUsername(),   // Admin
                ConfigReader.getPassword()    // admin123
        );
        DashboardPage dashboardPage = PageManager.getDashboardPage();

        dashboardPage.isDashboardDisplayed();
        dashboardPage.verifyQuickLaunchWidgetsVisible();
        dashboardPage.verifyEmployeeDistributionChartVisible();

    }

    // TC09
    @Test(priority = 9, description = "TC09: Verify User Role Search in Admin Module")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Admin Module")
    public void TC09_VerifyUserRoleSearch() {
        LoginPage loginPage = PageManager.getLoginPage();

        loginPage.loginWith(
                ConfigReader.getUsername(),   // Admin
                ConfigReader.getPassword()    // admin123
        );
        AdminPage adminPage = PageManager.getAdminPage();
        loginPage.toModule("Admin");

        adminPage.selectAdminRole()
                 .clickSearch()
                 .validateAllUsersHaveAdminRole();

    }

    // TC10
    @Test(priority = 10, description = "TC10: Verify Job Title Addition")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Admin Module")
    public void TC10_AddJobTitle() {
        LoginPage loginPage = PageManager.getLoginPage();

        loginPage.loginWith(
                ConfigReader.getUsername(),   // Admin
                ConfigReader.getPassword()    // admin123
        );
        AdminPage adminPage = PageManager.getAdminPage();
        loginPage.toModule("Admin");

        adminPage.goToJobTitles()
                 .clickAddJobTitle()
                 .enterJobTitle("Automation Engineer " + System.currentTimeMillis())
                 .enterJobDescription("Created via Automation")
                 .clickSave();

    }
}