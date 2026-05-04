package com.company.project.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationPage extends BasePage {

    @FindBy(id = "email")
    private WebElement emailInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "confirmPassword")
    private WebElement confirmPasswordInput;

    @FindBy(css = "button.register")
    private WebElement registerBtn;

    @FindBy(css = ".error-msg")
    private WebElement errorMsg;

    @FindBy(css = ".success-toast")
    private WebElement successToast;

    public RegistrationPage navigateTo() {
        driver.get(com.company.project.config.ConfigReader.get("baseUrl") + "/register");
        return this;
    }

    public RegistrationPage enterEmail(String email) {
        wait.waitForVisibility(emailInput).clear();
        emailInput.sendKeys(email);
        return this;
    }

    public RegistrationPage enterPassword(String pwd) {
        wait.waitForVisibility(passwordInput).clear();
        passwordInput.sendKeys(pwd);
        return this;
    }

    public RegistrationPage confirmPassword(String pwd) {
        wait.waitForVisibility(confirmPasswordInput).clear();
        confirmPasswordInput.sendKeys(pwd);
        return this;
    }

    public RegistrationPage clickRegister() {
        wait.waitForClickable(registerBtn).click();
        return this;
    }

    public String getErrorMessage() {
        return wait.waitForVisibility(errorMsg).getText();
    }

    public String getSuccessMessage() {
        return wait.waitForVisibility(successToast).getText();
    }
}
