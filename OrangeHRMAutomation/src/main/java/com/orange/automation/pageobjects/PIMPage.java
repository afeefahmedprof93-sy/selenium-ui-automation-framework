package com.orange.automation.pageobjects;

import com.orange.automation.base.BasePage;
import com.orange.automation.utils.DriverManager;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class PIMPage extends BasePage {

    @FindBy(xpath = "//a[@href='#' and normalize-space()='Employee List']")
    private WebElement employeeListMenu;

    @FindBy(xpath = "//button[contains(@class,'oxd-button') and contains(.,'Add')]")
    private WebElement addButton;

    @FindBy(name = "firstName")
    private WebElement firstNameField;

    @FindBy(name = "lastName")
    private WebElement lastNameField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement saveButton;

    @FindBy(xpath = "//label[normalize-space()='Employee Name']/ancestor::div[contains(@class,'oxd-input-group')]//input")
    private WebElement employeeNameSearchField;

    @FindBy(xpath = "//button[@type='submit' and contains(.,'Search')]")
    private WebElement searchButton;

    @FindBy(xpath = "//i[contains(@class,'oxd-checkbox-input-icon') and contains(@class,'bi-check')]")
    private WebElement firstCheckbox;

    @FindBy(xpath = "//button[contains(@class,'oxd-button--label-danger')]")
    private WebElement deleteButton;

    @FindBy(xpath = "//button[contains(@class,'oxd-button--label-danger') and contains(.,'Yes')]")
    private WebElement confirmDeleteButton;

    @Step("Navigate to Employee List")
    public PIMPage goToEmployeeList() {
        employeeListMenu.click();
        stepPassed("Navigated to Employee List");
        return this;
    }

    @Step("Click Add Employee")
    public PIMPage clickAddEmployee() {
        addButton.click();
        stepPassed("Clicked Add Employee");
        return this;
    }

    @Step("Enter Employee Details - FirstName: {firstName}, LastName: {lastName}")
    public PIMPage enterEmployeeDetails(String firstName, String lastName) {
        firstNameField.sendKeys(firstName);
        lastNameField.sendKeys(lastName);
        stepPassed("Entered employee personal details");
        return this;
    }

    @Step("Click Save")
    public PIMPage clickSave() {
        saveButton.click();
        DriverManager.waitForSeconds(2);
        stepPassed("Clicked Save button");
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

        stepPassed("Verified popup message: " + actualMessage);
        return this;
    }

    @Step("Search Employee: {name}")
    public PIMPage searchEmployee(String name) {
        employeeNameSearchField.clear();
        employeeNameSearchField.sendKeys(name);
        searchButton.click();
        DriverManager.waitForSeconds(2);
        stepPassed("Searched for employee: " + name);
        return this;
    }

    @Step("Verify first employee name contains: {expectedText}")
    public PIMPage verifyFirstEmployeeNameContains(String expectedText) {

        String employeeName = DriverManager.getDriver()
                .findElement(
                        By.xpath("(//div[@class='oxd-table-body']//div[@role='row'])[1]//div[@role='cell'][3]")
                )
                .getText();

        Assert.assertTrue(
                employeeName.contains(expectedText),
                "Expected employee name to contain: "
                        + expectedText
                        + " but found: "
                        + employeeName
        );

        stepPassed("Verified first employee name contains: " + expectedText);

        return this;
    }

    @Step("Click on First Employee in List")
    public PIMPage clickOnFirstEmployee() {
        DriverManager.getDriver().findElement(By.xpath("//div[@class='oxd-table-body']//div[@role='row'][1]//i[contains(@class,'bi-pencil-fill')]/parent::button")).click();
        stepPassed("Opened first employee details");
        DriverManager.waitForSeconds(2);
        return this;
    }

    @Step("Select First Employee Checkbox")
    public PIMPage selectFirstEmployee() {
        firstCheckbox.click();
        stepPassed("Selected first employee");
        return this;
    }

    @Step("Click Delete Button")
    public PIMPage clickDelete() {
        deleteButton.click();
        stepPassed("Clicked Delete button");
        return this;
    }

    @Step("Confirm Delete")
    public PIMPage confirmDelete() {
        confirmDeleteButton.click();
        stepPassed("Confirmed employee deletion");
        String actualMessage = DriverManager.getDriver()
            .findElement(
                    By.xpath("//p[contains(@class,'oxd-text--toast-message')]")
            )
            .getText();

        Assert.assertTrue(
                actualMessage.contains("Successfully Deleted"),
                "Expected popup message to contain: "
                        + "\"Successfully Deleted\""
                        + " but found: "
                        + actualMessage
        );

        stepPassed("Verified popup message: " + actualMessage);
        return this;
    }
}