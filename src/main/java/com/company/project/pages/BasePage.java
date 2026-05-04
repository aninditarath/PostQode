package com.company.project.pages;

import com.company.project.driver.DriverFactory;
import com.company.project.utils.WaitHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Base class for all page objects.
 * Provides driver instance, explicit wait helper and common actions.
 */
public abstract class BasePage {
    protected WebDriver driver;
    protected WaitHelper wait;

    public BasePage() {
        this.driver = DriverFactory.getDriver();
        PageFactory.initElements(driver, this);
        this.wait = new WaitHelper(driver);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public String getTitle() {
        return driver.getTitle();
    }
}
