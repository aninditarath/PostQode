package com.company.project.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(id = "email")
    private WebElement emailInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(css = "button.login")
    private WebElement loginBtn;

    @FindBy(css = ".error-msg")
    private WebElement errorMsg;

    @FindBy(linkText = "Forgot Password")
    private WebElement forgotPwdLink;

    public LoginPage navigateTo() {
        driver.get(com.company.project.config.ConfigReader.get("baseUrl") + "/login");
        return this;
    }

    public LoginPage enterEmail(String email) {
        wait.waitForVisibility(emailInput).clear();
        emailInput.sendKeys(email);
        return this;
    }

    public LoginPage enterPassword(String pwd) {
        wait.waitForVisibility(passwordInput).clear();
        passwordInput.sendKeys(pwd);
        return this;
    }

    public LoginPage clickLogin() {
        wait.waitForClickable(loginBtn).click();
        return this;
    }

    public String getErrorMessage() {
        return wait.waitForVisibility(errorMsg).getText();
    }

    public LoginPage clickForgotPassword() {
        wait.waitForClickable(forgotPwdLink).click();
        return this;
    }
}
