package com.orange.automation.pageobjects;

import com.orange.automation.base.BasePage;
import com.orange.automation.utils.DriverManager;

import io.qameta.allure.Step;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class AdminPage extends BasePage {

    @FindBy(xpath = "//a[contains(@href,'viewSystemUsers')]")
    private WebElement usersMenu;

    @FindBy(xpath = "//span[text()='Job ']")
    private WebElement jobDropdown;

    @FindBy(xpath = "//a[text()='Job Titles']")
    private WebElement jobTitlesMenu;

    @FindBy(xpath = "//button[contains(@class,'oxd-button') and contains(.,'Add')]")
    private WebElement addButton;

    @FindBy(xpath = "//label[text()='Job Title']/parent::div/following-sibling::div/input")
    private WebElement jobTitleField;

    @FindBy(xpath = "//label[text()='Job Description']/parent::div/following-sibling::div/textarea")
    private WebElement jobDescriptionField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement saveButton;

    // ====================== LOCATORS ======================

    @FindBy(xpath = "//label[text()='User Role']/parent::div/following-sibling::div//div[contains(@class,'oxd-select-text')]")
    private WebElement userRoleDropdown;

    @FindBy(xpath = "//div[@role='listbox']//span[text()='Admin']")
    private WebElement adminRoleOption;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement searchButton;

    @FindBy(xpath = "//div[@class='oxd-table-body']//div[@role='row']")
    private List<WebElement> resultRows;

    @FindBy(xpath = "//div[@class='oxd-table-body']//div[@role='row']/div[3]")
    private List<WebElement> roleColumnValues;

    @Step("Navigate to Admin > Users")
    public AdminPage goToUsers() {
        usersMenu.click();
        stepPassed("Navigated to Users page");
        return this;
    }

    @Step("Navigate to Job Titles")
    public AdminPage goToJobTitles() {
        jobDropdown.click();
        jobTitlesMenu.click();
        stepPassed("Navigated to Job Titles");
        return this;
    }

    @Step("Click Add Button")
    public AdminPage clickAddJobTitle() {
        addButton.click();
        stepPassed("Clicked Add for new Job Title");
        return this;
    }

    @Step("Enter Job Title: {title}")
    public AdminPage enterJobTitle(String title) {
        jobTitleField.sendKeys(title);
        stepPassed("Entered Job Title");
        return this;
    }

    @Step("Enter Job Description")
    public AdminPage enterJobDescription(String description) {
        jobDescriptionField.sendKeys(description);
        stepPassed("Entered Job Description");
        return this;
    }

    @Step("Click Save")
    public AdminPage clickSave() {
        saveButton.click();
        stepPassed("Saved new Job Title");
        DriverManager.waitForSeconds(2);
        String actualMessage = DriverManager.getDriver()
            .findElement(
                    By.xpath("//p[contains(@class,'oxd-text--toast-message')]")
            )
            .getText();

        Assert.assertTrue(
                actualMessage.contains("Successfully Saved"),
                "Expected popup message to contain: "
                        + "\"Successfully Saved\""
                        + " but found: "
                        + actualMessage
        );
        return this;
    }

    @Step("Search by User Role: {role}")
    public AdminPage searchByUserRole(String role) {
        // Add search logic as needed
        stepPassed("Searched by User Role: " + role);
        return this;
    }

    @Step("Select Admin role from User Role dropdown")
    public AdminPage selectAdminRole() {
        userRoleDropdown.click();
        adminRoleOption.click();

        stepPassed("Selected Admin role");
        return this;
    }

    @Step("Click Search button")
    public AdminPage clickSearch() {
        searchButton.click();
        DriverManager.waitForSeconds(2);
        stepPassed("Clicked Search button");
        return this;
    }

    @Step("Validate all searched users have Admin role")
    public AdminPage validateAllUsersHaveAdminRole() {

        Assert.assertFalse(
                roleColumnValues.isEmpty(),
                "No search results found"
        );

        for (WebElement role : roleColumnValues) {

            String actualRole = role.getText().trim();

            Assert.assertEquals(
                    actualRole,
                    "Admin",
                    "User role mismatch found"
            );
        }

        stepPassed("Validated all users contain Admin role");

        return this;
    }
}