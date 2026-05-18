package com.orange.automation.pageobjects;

import com.orange.automation.base.BasePage;
import com.orange.automation.utils.DriverManager;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LeavePage extends BasePage {

    // ==================== MENU LINKS ====================

    @FindBy(xpath = "//a[@href='#' and normalize-space()='Assign Leave']")
    private WebElement applyLeaveMenu;

    @FindBy(xpath = "//a[contains(@href,'leave/viewMyLeaveList')]")
    private WebElement myLeaveMenu;

    // ==================== APPLY LEAVE ELEMENTS ====================

    @FindBy(xpath = "//div[@class='oxd-select-wrapper']//div[contains(@class,'oxd-select-text')]")
    private WebElement leaveTypeDropdown;

    @FindBy(xpath = "//label[normalize-space()='From Date']/ancestor::div[contains(@class,'oxd-input-group')]//input")
    private WebElement fromDateField;

    @FindBy(xpath = "//label[normalize-space()='To Date']/ancestor::div[contains(@class,'oxd-input-group')]//input")
    private WebElement toDateField;

    @FindBy(xpath = "//textarea[contains(@class,'oxd-textarea--active')]")
    private WebElement commentField;

    @FindBy(xpath = "//button[@type='submit' and normalize-space()='Assign']")
    private WebElement applyButton;

    @FindBy(xpath = "//button[@type='submit' and contains(.,'Search')]")
    private WebElement searchButton;

    @FindBy(xpath = "//label[normalize-space()='Employee Name']/ancestor::div[contains(@class,'oxd-input-group')]//input")
    private WebElement employeeNameField;

    // ==================== DYNAMIC LOCATORS ====================

    private By leaveTypeOption(String leaveType) {

        return By.xpath(
                "//span[contains(text(),'" + leaveType + "')]"
        );
    }

    // ==================== METHODS ====================

    @Step("Navigate to Apply Leave")
    public LeavePage goToApplyLeave() {

        applyLeaveMenu.click();

        stepPassed("Navigated to Apply Leave page");

        return this;
    }

    @Step("Navigate to My Leave")
    public LeavePage goToMyLeave() {

        myLeaveMenu.click();

        stepPassed("Navigated to My Leave page");

        return this;
    }

    @Step("Select Leave Type: {leaveType}")
    public LeavePage selectLeaveType(String leaveType) {

        leaveTypeDropdown.click();

        DriverManager.getDriver().findElement(leaveTypeOption(leaveType));

        stepPassed("Selected Leave Type: " + leaveType);

        return this;
    }

    @Step("Select From Date: {date}")
    public LeavePage selectFromDate(String date) {

        fromDateField.clear();
        fromDateField.sendKeys(date);

        stepPassed("From Date entered: " + date);

        return this;
    }

    @Step("Select Employee Name: {name}")
    public LeavePage selectEmployeeName(String name) {

        employeeNameField.clear();
        employeeNameField.sendKeys(name);

        stepPassed("From Date entered: " + name);

        return this;
    }

    @Step("Select To Date: {date}")
    public LeavePage selectToDate(String date) {

        toDateField.clear();
        toDateField.sendKeys(date);

        stepPassed("To Date entered: " + date);

        return this;
    }

    @Step("Enter Comment")
    public LeavePage enterComment(String comment) {

        commentField.clear();
        commentField.sendKeys(comment);

        stepPassed("Comment entered");

        return this;
    }

    @Step("Click Apply")
    public LeavePage clickApply() {

        applyButton.click();

        stepPassed("Leave application Assigned");

        return this;
    }

    @Step("Set From Date in My Leave filter")
    public LeavePage setFromDate(String date) {

        fromDateField.clear();
        fromDateField.sendKeys(date);

        stepPassed("From Date set in filter");

        return this;
    }

    @Step("Set To Date in My Leave filter")
    public LeavePage setToDate(String date) {

        toDateField.clear();
        toDateField.sendKeys(date);

        stepPassed("To Date set in filter");

        return this;
    }

    @Step("Click Search on My Leave")
    public LeavePage clickSearch() {

        searchButton.click();

        stepPassed("Search performed on My Leave");

        return this;
    }
}