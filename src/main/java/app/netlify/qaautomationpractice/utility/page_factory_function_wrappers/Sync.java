package app.netlify.qaautomationpractice.utility.page_factory_function_wrappers;

import app.netlify.qaautomationpractice.utility.data_io.PropertyReader;
import app.netlify.qaautomationpractice.utility.data_io.PropertyFile;
import app.netlify.qaautomationpractice.utility.driver.GetDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Sync {
    private static final long DEFAULT_TIME_OUT = getDefaultTimeout();

    private static long getDefaultTimeout() {
        long timeout = 0L;
        try {
            timeout = Long.parseLong(PropertyReader.getProperty(PropertyFile.GLOBAL_PROPERTIES, "timeOutInSeconds"));
        } catch (NumberFormatException e){
            System.out.println("\"timeOutInSeconds\" property in "+PropertyFile.GLOBAL_PROPERTIES.getFilePath()+" did not contain a valid long value.");
        }
        return timeout;
    }

    /*--Wait until visible--*/
    public static WebElement waitUntilVisible(By locator) {
        WebDriverWait wait = new WebDriverWait(GetDriver.getInstance().getDriver(), Duration.ofSeconds(DEFAULT_TIME_OUT));
        return wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfElementLocated(locator)));
    }

    public static WebElement waitUntilVisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(GetDriver.getInstance().getDriver(), Duration.ofSeconds(DEFAULT_TIME_OUT));
        return wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(element)));
    }

    public static WebElement waitUntilVisible(By locator, Duration duration) {
        WebDriverWait wait = new WebDriverWait(GetDriver.getInstance().getDriver(), duration);
        return wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfElementLocated(locator)));
    }

    public static WebElement waitUntilVisible(WebElement element, Duration duration) {
        WebDriverWait wait = new WebDriverWait(GetDriver.getInstance().getDriver(), duration);
        return wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(element)));
    }

    /*--Wait until clickable--*/
    public static WebElement waitUntilClickable(By locator) {
        WebDriverWait wait = new WebDriverWait(GetDriver.getInstance().getDriver(), Duration.ofSeconds(DEFAULT_TIME_OUT));
        return wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(locator)));
    }

    public static WebElement waitUntilClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(GetDriver.getInstance().getDriver(), Duration.ofSeconds(DEFAULT_TIME_OUT));
        return wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(element)));
    }

    public static WebElement waitUntilClickable(By locator, Duration duration) {
        WebDriverWait wait = new WebDriverWait(GetDriver.getInstance().getDriver(), duration);
        return wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(locator)));
    }

    public static WebElement waitUntilClickable(WebElement element, Duration duration) {
        WebDriverWait wait = new WebDriverWait(GetDriver.getInstance().getDriver(), duration);
        return wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(element)));
    }

    /*--Wait until invisible--*/
    public static boolean waitUntilInvisible(By locator) {
        WebDriverWait wait = new WebDriverWait(GetDriver.getInstance().getDriver(), Duration.ofSeconds(DEFAULT_TIME_OUT));
        return wait.until(ExpectedConditions.refreshed(ExpectedConditions.invisibilityOfElementLocated(locator)));
    }

    public static boolean waitUntilInvisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(GetDriver.getInstance().getDriver(), Duration.ofSeconds(DEFAULT_TIME_OUT));
        return wait.until(ExpectedConditions.refreshed(ExpectedConditions.invisibilityOf(element)));
    }

    public static boolean waitUntilInvisible(By locator, Duration duration) {
        WebDriverWait wait = new WebDriverWait(GetDriver.getInstance().getDriver(), duration);
        return wait.until(ExpectedConditions.refreshed(ExpectedConditions.invisibilityOfElementLocated(locator)));
    }

    public static boolean waitUntilInvisible(WebElement element, Duration duration) {
        WebDriverWait wait = new WebDriverWait(GetDriver.getInstance().getDriver(), duration);
        return wait.until(ExpectedConditions.refreshed(ExpectedConditions.invisibilityOf(element)));
    }

    /*--Wait until element is present on DOM--*/
    public static WebElement waitUntilPresentOnDOM(By locator) {
        WebDriverWait wait = new WebDriverWait(GetDriver.getInstance().getDriver(), Duration.ofSeconds(DEFAULT_TIME_OUT));
        return wait.until(ExpectedConditions.refreshed(ExpectedConditions.presenceOfElementLocated(locator)));
    }

    public static WebElement waitUntilPresentOnDOM(By locator, Duration duration) {
        WebDriverWait wait = new WebDriverWait(GetDriver.getInstance().getDriver(), duration);
        return wait.until(ExpectedConditions.refreshed(ExpectedConditions.presenceOfElementLocated(locator)));
    }

    /*--Wait until URL contains--*/
    public static boolean waitUntilURLContains(String URL) {
        WebDriverWait wait = new WebDriverWait(GetDriver.getInstance().getDriver(), Duration.ofSeconds(DEFAULT_TIME_OUT));
        return wait.until(ExpectedConditions.refreshed(ExpectedConditions.urlContains(URL)));
    }

    public static boolean waitUntilURLContains(String URL, Duration duration) {
        WebDriverWait wait = new WebDriverWait(GetDriver.getInstance().getDriver(), duration);
        return wait.until(ExpectedConditions.refreshed(ExpectedConditions.urlContains(URL)));
    }

    /*--Wait until URL matches exactly--*/
    public static boolean waitUntilURLMatchesExactly(String URL) {
        WebDriverWait wait = new WebDriverWait(GetDriver.getInstance().getDriver(), Duration.ofSeconds(DEFAULT_TIME_OUT));
        return wait.until(ExpectedConditions.refreshed(ExpectedConditions.urlToBe(URL)));
    }

    public static boolean waitUntilURLMatchesExactly(String URL, Duration duration) {
        WebDriverWait wait = new WebDriverWait(GetDriver.getInstance().getDriver(), duration);
        return wait.until(ExpectedConditions.refreshed(ExpectedConditions.urlToBe(URL)));
    }

    /*--Wait until title contains--*/
    public static boolean waitUntilTitleContains(String title) {
        WebDriverWait wait = new WebDriverWait(GetDriver.getInstance().getDriver(), Duration.ofSeconds(DEFAULT_TIME_OUT));
        return wait.until(ExpectedConditions.refreshed(ExpectedConditions.titleContains(title)));
    }

    public static boolean waitUntilTitleContains(String title, Duration duration) {
        WebDriverWait wait = new WebDriverWait(GetDriver.getInstance().getDriver(), duration);
        return wait.until(ExpectedConditions.refreshed(ExpectedConditions.titleContains(title)));
    }

    /*--Wait until title matches exactly--*/
    public static boolean waitUntilTitleMatchesExactly(String title) {
        WebDriverWait wait = new WebDriverWait(GetDriver.getInstance().getDriver(), Duration.ofSeconds(DEFAULT_TIME_OUT));
        return wait.until(ExpectedConditions.refreshed(ExpectedConditions.titleIs(title)));
    }

    public static boolean waitUntilTitleMatchesExactly(String title, Duration duration) {
        WebDriverWait wait = new WebDriverWait(GetDriver.getInstance().getDriver(), duration);
        return wait.until(ExpectedConditions.refreshed(ExpectedConditions.titleIs(title)));
    }

    public static WebElement waitUntilWebElementCondition(ExpectedCondition<WebElement> condition) {
        WebDriverWait wait = new WebDriverWait(GetDriver.getInstance().getDriver(), Duration.ofSeconds(DEFAULT_TIME_OUT));
        return wait.until(condition);
    }

    public static WebElement waitUntilWebElementCondition(ExpectedCondition<WebElement> condition, Duration duration) {
        WebDriverWait wait = new WebDriverWait(GetDriver.getInstance().getDriver(), duration);
        return wait.until(condition);
    }

    public static boolean waitUntilBooleanCondition(ExpectedCondition<Boolean> condition) {
        WebDriverWait wait = new WebDriverWait(GetDriver.getInstance().getDriver(), Duration.ofSeconds(DEFAULT_TIME_OUT));
        return wait.until(condition);
    }

    public static boolean waitUntilBooleanCondition(ExpectedCondition<Boolean> condition, Duration duration) {
        WebDriverWait wait = new WebDriverWait(GetDriver.getInstance().getDriver(), duration);
        return wait.until(condition);
    }
}
