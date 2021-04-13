package app.netlify.qaautomationpractice.shared_utilities.report_utility;

import app.netlify.qaautomationpractice.shared_utilities.data_readers.PropertyReader;
import app.netlify.qaautomationpractice.shared_utilities.data_readers.property_file.FrameworkPropertyFile;
import app.netlify.qaautomationpractice.shared_utilities.data_readers.property_file.PropertyFile;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.IOException;

public class Reporter {
    private static Reporter instance;
    private static ExtentReports report;
    private static ExtentSparkReporter spark;
    private static String extentReportsDirectory;
    private static String reportDirectory;
    private static String reportFilePath;

    private Reporter() {
    }

    public static Reporter getInstance() {
        if (instance == null) {
            instance = new Reporter();
        }
        return instance;
    }

    public ExtentReports getExtentReport() {
        return report;
    }

    public void setExtentReportsDirectory(String directory) {
        extentReportsDirectory = directory;
    }

    public void flushReport() {
        report.flush();
    }

    public void setupReporter(String reportName) throws IOException {
        if (extentReportsDirectory == null) {
            extentReportsDirectory = PropertyReader.getProperty(FrameworkPropertyFile.GLOBAL_PROPERTIES, "extentReportsDirectory");
        }
        reportDirectory = extentReportsDirectory + reportName + " " + TimeStamp.getTimeStamp() + "\\";
        reportFilePath = reportDirectory + reportName + ".html";
        spark = new ExtentSparkReporter(reportFilePath);
        report = new ExtentReports();
        report.attachReporter(spark);
        report.setSystemInfo("Operating System", System.getProperty("os.name"));
    }

    public void setSystemInfo(String name, String value) {
        if (name != null && value != null)
            report.setSystemInfo(name, value);
    }

    public String getReportDirectory() {
        return reportDirectory;
    }

    public String getReportFilePath() {
        return reportFilePath;
    }

}
