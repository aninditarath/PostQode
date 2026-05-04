package com.company.project.tests;

import com.company.project.pages.DashboardPage;
import com.company.project.pages.LoginPage;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {

    @Test
    public void testSuccessfulLogin() {
        new LoginPage()
                .navigateTo()
                .enterEmail("registered@example.com")
                .enterPassword("ValidPass123!")
                .clickLogin();

        DashboardPage dash = new DashboardPage();
        Assert.assertTrue(dash.getLoggedUserName().contains("John Doe"),
                "TC US-002-2.1 failed – username not displayed");

        // Verify JWT token stored in localStorage
        String token = (String) ((JavascriptExecutor) DriverFactory.getDriver())
                .executeScript("return window.localStorage.getItem('jwt');");
        Assert.assertNotNull(token, "TC US-002-2.1 failed – JWT token not stored");
    }

    @Test
    public void testLoginEmptyEmail() {
        LoginPage page = new LoginPage()
                .navigateTo()
                .enterEmail("")
                .enterPassword("ValidPass123!")
                .clickLogin();

        Assert.assertEquals(page.getErrorMessage(), "Email is required",
                "TC US-002-2.2 failed – wrong validation message");
    }

    @Test
    public void testLoginEmptyPassword() {
        LoginPage page = new LoginPage()
                .navigateTo()
                .enterEmail("registered@example.com")
                .enterPassword("")
                .clickLogin();

        Assert.assertEquals(page.getErrorMessage(), "Password is required",
                "TC US-002-2.3 failed – wrong validation message");
    }

    @Test
    public void testIncorrectPassword() {
        LoginPage page = new LoginPage()
                .navigateTo()
                .enterEmail("registered@example.com")
                .enterPassword("WrongPass!")
                .clickLogin();

        Assert.assertEquals(page.getErrorMessage(), "Invalid email or password",
                "TC US-003-3.1 failed – wrong error message");
    }

    @Test
    public void testNonExistentEmail() {
        LoginPage page = new LoginPage()
                .navigateTo()
                .enterEmail("nonexistent@example.com")
                .enterPassword("AnyPass123!")
                .clickLogin();

        Assert.assertEquals(page.getErrorMessage(), "Invalid email or password",
                "TC US-003-3.2 failed – wrong error message");
    }
}
