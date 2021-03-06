package app.netlify.qaautomationpractice.ui.utility.test_base;

import app.netlify.qaautomationpractice.shared_utilities.data_readers.PropertyReader;
import app.netlify.qaautomationpractice.shared_utilities.data_readers.property_file.FrameworkPropertyFile;
import app.netlify.qaautomationpractice.shared_utilities.report_utility.*;
import app.netlify.qaautomationpractice.ui.utility.driver.Driver;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.annotations.*;

import java.io.IOException;

public class UITestBase extends TestListenerAdapter {
    protected static String baseUrl;

    @BeforeClass
    public void beforeClass() {
        baseUrl = PropertyReader.getProperty(FrameworkPropertyFile.APPLICATION_PROPERTIES, "site.url");
    }

    @Parameters(value = {"browser"})
    @BeforeMethod
    public void beforeMethod(@Optional String browser) {
        Driver.getInstance()
              .setDriver(browser);
        Driver.getInstance()
              .getDriver()
              .get(baseUrl);
    }


    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        Driver.getInstance()
              .quit();
    }

    @Override
    public void onFinish(ITestContext context) {
        Reporter.getInstance()
                .flushReport();
    }


    @Override
    public void onStart(ITestContext context) {
        try {
            Reporter.getInstance()
                    .setupReporter(context.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Log.log(Status.FAIL, result.getMethod()
                                   .getMethodName() + " has failed.");
        ThreadLocal<String> screenshotFile = new ThreadLocal<>();
        try {
            screenshotFile.set(Screenshot.captureBrowser(Driver.getInstance()
                                                               .getDriver(),
                    Reporter.getInstance()
                            .getReportDirectory(),
                    result.getMethod()
                          .getMethodName() + "_" + TimeStamp.getTimeStamp() + "_FAILURE"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.addScreenshotFromPath(screenshotFile.get(), "Screenshot of browser on failure");
        if (result.getThrowable() != null) {
            Log.log(Status.FAIL, result.getThrowable());
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
        ThreadLocal<String> testReportTitle = new ThreadLocal<>();
        testReportTitle.set(result.getMethod()
                                  .getConstructorOrMethod()
                                  .getMethod()
                                  .getAnnotation(TestReportInformation.class)
                                  .title());
        ThreadLocal<String> testReportDescription = new ThreadLocal<>();
        testReportDescription.set(result.getMethod()
                                        .getConstructorOrMethod()
                                        .getMethod()
                                        .getAnnotation(TestReportInformation.class)
                                        .description());
        TestReport.getInstance()
                  .createTestReport(testReportTitle.get(), testReportDescription.get());
        TestReport.getInstance()
                  .assignTestCategories(result.getMethod()
                                              .getGroups());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        Log.log(Status.PASS, "Great success!");
    }
}
