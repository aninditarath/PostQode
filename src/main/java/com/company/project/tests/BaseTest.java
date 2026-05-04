package com.company.project.tests;

import com.company.project.driver.DriverFactory;
import com.company.project.config.ConfigReader;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.testng.ITestResult;
import org.testng.annotations.*;

public class BaseTest {

    protected ExtentReports extent;
    protected ExtentTest test;

    @BeforeSuite
    public void suiteSetup() {
        // Load configuration
        ConfigReader.loadProperties("src/test/resources/config.properties");

        // Initialise ExtentReports
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("reports/AutomationReport.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    @Parameters({"browser"})
    @BeforeMethod
    public void setUp(@Optional("chrome") String browser) {
        DriverFactory.initDriver(browser);
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            test.fail(result.getThrowable());
            // Screenshot utility can be added here
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.pass("Test passed");
        }
        extent.flush();
        DriverFactory.quitDriver();
    }

    @AfterSuite
    public void suiteTearDown() {
        if (extent != null) {
            extent.flush();
        }
    }
}
