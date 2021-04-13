package app.netlify.qaautomationpractice.shared_utilities.report_utility;


import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.model.Media;

//Wrapper class for logging
public class Log {
    public static void log(Status status, String message) {
        TestReport.getInstance()
                .getTest().log(status, message);
    }

    public static void log(Status status, Markup m) {
        TestReport.getInstance()
                .getTest().log(status, m);
    }

    public static void log(Status status, Media media) {
        TestReport.getInstance()
                .getTest().log(status, media);
    }

    public static void log(Status status, String details, Media media) {
        TestReport.getInstance()
                .getTest().log(status, details, media);
    }

    public static void log(Status status, Throwable throwable) {
        TestReport.getInstance()
                .getTest().log(status, throwable);
    }

    public static void log(Status status, Throwable throwable, Media media) {
        TestReport.getInstance()
                .getTest().log(status, throwable, media);
    }

    public static void log(Status status, String details, Throwable throwable, Media media) {
        TestReport.getInstance()
                .getTest().log(status, details, throwable, media);
    }

    public static void addScreenshotFromPath(String path) {
        TestReport.getInstance()
                .getTest().addScreenCaptureFromPath(path);
    }

    public static void addScreenshotFromPath(String path, String title) {
        TestReport.getInstance()
                .getTest().addScreenCaptureFromPath(path, title);
    }
}
