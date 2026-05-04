package com.company.project.tests;

import com.company.project.pages.RegistrationPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class RegistrationTests extends BaseTest {

    @DataProvider(name = "validRegistrations")
    public Object[][] validRegistrations() {
        return new Object[][]{
                {"US-001-1.1", "test@example.com", "TestPass123!"}
        };
    }

    @Test(dataProvider = "validRegistrations")
    public void testValidRegistration(String tcId, String email, String pwd) {
        RegistrationPage page = new RegistrationPage()
                .navigateTo()
                .enterEmail(email)
                .enterPassword(pwd)
                .confirmPassword(pwd)
                .clickRegister();

        String successMsg = page.getSuccessMessage();
        Assert.assertTrue(successMsg.contains("Please check your email to activate your account"),
                "TC " + tcId + " failed – success message not shown");
    }

    @DataProvider(name = "invalidEmails")
    public Object[][] invalidEmails() {
        return new Object[][]{
                {"US-001-1.2", "invalidemail", "TestPass123!"},
                {"US-001-1.2", "test@", "TestPass123!"},
                {"US-001-1.2", "@example.com", "TestPass123!"}
        };
    }

    @Test(dataProvider = "invalidEmails")
    public void testInvalidEmailFormat(String tcId, String email, String pwd) {
        RegistrationPage page = new RegistrationPage()
                .navigateTo()
                .enterEmail(email)
                .enterPassword(pwd)
                .confirmPassword(pwd)
                .clickRegister();

        Assert.assertEquals(page.getErrorMessage(),
                "Please enter a valid email address",
                "TC " + tcId + " – wrong validation message");
    }
}
