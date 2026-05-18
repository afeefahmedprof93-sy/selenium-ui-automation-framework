package com.orange.automation.pageobjects;

import com.orange.automation.base.BasePage;
import com.orange.automation.utils.DriverManager;

import io.qameta.allure.Step;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class MyInfoPage extends BasePage {

    @FindBy(xpath = "//a[contains(@href,'viewMyDetails')]")
    private WebElement myInfoMenu;

    @FindBy(xpath = "//input[@name='middleName']")
    private WebElement middlenameField;

    @FindBy(xpath = "//label[normalize-space()='Other Id']/ancestor::div[contains(@class,'oxd-input-group')]//input")
    private WebElement otherIDField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement saveButton;

    @Step("Navigate to My Info")
    public MyInfoPage goToMyInfo() {
        myInfoMenu.click();
        stepPassed("Navigated to My Info page");
        return this;
    }

    @Step("Update Middle name to: {middlename}")
    public MyInfoPage updateMiddlename(String middlename) {
        middlenameField.sendKeys(
            Keys.chord(Keys.COMMAND, "a"),
            Keys.DELETE
        );
        middlenameField.sendKeys(middlename);
        stepPassed("Middle name updated");
        return this;
    }

    @Step("Update Other Email to: {email}")
    public MyInfoPage updateOtherID(String email) {
        otherIDField.sendKeys(
            Keys.chord(Keys.COMMAND, "a"),
            Keys.DELETE
        );
        otherIDField.sendKeys(email);
        stepPassed("Other Email updated");
        return this;
    }

    @Step("Click Save")
    public MyInfoPage clickSave() {
        saveButton.click();
        stepPassed("Clicked Save on My Info");
        DriverManager.waitForSeconds(1);
        String actualMessage = DriverManager.getDriver()
            .findElement(
                    By.xpath("//p[contains(@class,'oxd-text--toast-message')]")
            )
            .getText();

        Assert.assertTrue(
                actualMessage.contains("Successfully Updated"),
                "Expected popup message to contain: "
                        + "\"Successfully Updated\""
                        + " but found: "
                        + actualMessage
        );

        stepPassed("Verified popup message: " + actualMessage);
        return this;
    }
}