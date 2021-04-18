package app.netlify.qaautomationpractice.api.utility.test_base;

import app.netlify.qaautomationpractice.shared_utilities.report_utility.Log;
import app.netlify.qaautomationpractice.shared_utilities.report_utility.Reporter;
import app.netlify.qaautomationpractice.shared_utilities.report_utility.TestReport;
import app.netlify.qaautomationpractice.shared_utilities.report_utility.TestReportInformation;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.IOException;

public class APITestBase extends TestListenerAdapter {
    @Override
    public void onFinish(ITestContext context) {
        Reporter
                .getInstance()
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
