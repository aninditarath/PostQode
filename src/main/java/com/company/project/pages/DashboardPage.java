package com.company.project.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DashboardPage extends BasePage {

    @FindBy(css = "header .user-name")
    private WebElement userNameLabel;

    @FindBy(linkText = "Settings")
    private WebElement settingsLink;

    @FindBy(linkText = "Products")
    private WebElement productsLink;

    @FindBy(css = ".cart-badge")
    private WebElement cartBadge;

    public String getLoggedUserName() {
        return wait.waitForVisibility(userNameLabel).getText();
    }

    public SettingsPage goToSettings() {
        wait.waitForClickable(settingsLink).click();
        return new SettingsPage();
    }

    public ProductListPage goToProducts() {
        wait.waitForClickable(productsLink).click();
        return new ProductListPage();
    }

    public String getCartBadgeCount() {
        return wait.waitForVisibility(cartBadge).getText();
    }
}
