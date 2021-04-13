package app.netlify.qaautomationpractice.shared_utilities.report_utility;

import com.aventstack.extentreports.ExtentTest;

public class  TestReport {
    private static TestReport testReport;
    private static final ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    private TestReport() {
    }

    public static TestReport getInstance() {
        if (testReport == null) {
            testReport = new TestReport();
        }
        return testReport;
    }

    public void createTestReport(String testName) {
        test.set(Reporter.getInstance().getExtentReport().createTest(testName));
    }

    public void createTestReport(String testName, String description) {
        test.set(Reporter.getInstance().getExtentReport().createTest(testName, description));
    }

    public void assignTestCategory(String category) {
        test.get().assignCategory(category);
    }

    public void assignTestCategories(String[] categories) {
        for (String category : categories)
            test.get().assignCategory(category);
    }

    public ExtentTest getTest() {
        return test.get();
    }
}
