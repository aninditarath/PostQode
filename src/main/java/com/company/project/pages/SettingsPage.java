package com.company.project.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SettingsPage extends BasePage {

    @FindBy(linkText = "Profile")
    private WebElement profileLink;

    @FindBy(linkText = "Security")
    private WebElement securityLink;

    @FindBy(linkText = "Notifications")
    private WebElement notificationsLink;

    @FindBy(linkText = "Account Activity")
    private WebElement accountActivityLink;

    public ProfilePage openProfile() {
        wait.waitForClickable(profileLink).click();
        return new ProfilePage();
    }

    public SecurityPage openSecurity() {
        wait.waitForClickable(securityLink).click();
        return new SecurityPage();
    }

    public NotificationPage openNotifications() {
        wait.waitForClickable(notificationsLink).click();
        return new NotificationPage();
    }

    public AccountActivityPage openAccountActivity() {
        wait.waitForClickable(accountActivityLink).click();
        return new AccountActivityPage();
    }
}
