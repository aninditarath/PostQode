package com.company.project.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProfilePage extends BasePage {

    @FindBy(css = "button.edit-profile")
    private WebElement editProfileBtn;

    @FindBy(id = "name")
    private WebElement nameInput;

    @FindBy(id = "email")
    private WebElement emailInput;

    @FindBy(id = "phone")
    private WebElement phoneInput;

    @FindBy(css = "button.save-changes")
    private WebElement saveChangesBtn;

    @FindBy(css = ".toast-success")
    private WebElement successToast;

    public ProfilePage clickEditProfile() {
        wait.waitForClickable(editProfileBtn).click();
        return this;
    }

    public ProfilePage enterName(String name) {
        wait.waitForVisibility(nameInput).clear();
        nameInput.sendKeys(name);
        return this;
    }

    public ProfilePage enterEmail(String email) {
        wait.waitForVisibility(emailInput).clear();
        emailInput.sendKeys(email);
        return this;
    }

    public ProfilePage enterPhone(String phone) {
        wait.waitForVisibility(phoneInput).clear();
        phoneInput.sendKeys(phone);
        return this;
    }

    public ProfilePage clickSaveChanges() {
        wait.waitForClickable(saveChangesBtn).click();
        return this;
    }

    public String getSuccessMessage() {
        return wait.waitForVisibility(successToast).getText();
    }
}
