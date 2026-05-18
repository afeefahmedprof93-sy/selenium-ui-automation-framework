package com.orange.automation.pageobjects;

import com.orange.automation.base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DashboardPage extends BasePage {

    @FindBy(xpath = "//h6[text()='Dashboard']")
    private WebElement dashboardHeader;

    @FindBy(xpath = "//p[normalize-space()='Quick Launch']")
    private WebElement quickLaunchHeader;

    @FindBy(xpath = "//p[normalize-space()='Employee Distribution by Sub Unit']")
    private WebElement employeDistributionHeader;

    @Step("Verify Dashboard is displayed")
    public boolean isDashboardDisplayed() {
        return dashboardHeader.isDisplayed();
    }

    @Step("Verify Quick Launch Widgets are visible")
    public void verifyQuickLaunchWidgetsVisible() {
        if(quickLaunchHeader.isDisplayed()) stepPassed("Quick Launch widgets are visible");
        else stepFailed("Quick Launch widgets are not visible");
    }

    @Step("Verify Employee Distribution Chart is visible")
    public void verifyEmployeeDistributionChartVisible() {
        if(employeDistributionHeader.isDisplayed()) stepPassed("Employee Distribution chart is visible");
        else stepFailed("Employee Distribution chart is not visible");
    }
}