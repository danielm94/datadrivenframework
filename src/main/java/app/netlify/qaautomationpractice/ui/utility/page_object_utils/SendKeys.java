package app.netlify.qaautomationpractice.ui.utility.page_object_utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.time.Duration;

public final class SendKeys {
    private SendKeys(){}
    public static void usingLocator(By locator, String keys) {
        WebElement element = Sync.waitUntilVisible(locator);
        element.clear();
        element.sendKeys(keys);
    }

    public static void usingElement(WebElement element, String keys) {
        Sync.waitUntilVisible(element);
        element.clear();
        element.sendKeys(keys);
    }

    public static void usingLocator(By locator, String keys, Duration duration) {
        WebElement element = Sync.waitUntilVisible(locator, duration);
        element.clear();
        element.sendKeys(keys);
    }

    public static void usingElement(WebElement element, String keys, Duration duration) {
        Sync.waitUntilVisible(element, duration);
        element.clear();
        element.sendKeys(keys);
    }

    public static void usingElementCondition(ExpectedCondition<WebElement> condition, String keys) {
        WebElement element = Sync.waitUntilWebElementCondition(condition);
        element.clear();
        element.sendKeys(keys);
    }

    public static void usingElementCondition(ExpectedCondition<WebElement> condition, String keys, Duration duration) {
        WebElement element = Sync.waitUntilWebElementCondition(condition, duration);
        element.clear();
        element.sendKeys(keys);
    }

    public static void usingBooleanCondition(By locator, ExpectedCondition<Boolean> condition, String keys) {
        Sync.waitUntilBooleanCondition(condition);
        WebElement element = Get.Element.usingLocator(locator);
        element.clear();
        element.sendKeys(keys);
    }

    public static void usingBooleanCondition(By locator, ExpectedCondition<Boolean> condition, String keys, Duration duration) {
        Sync.waitUntilBooleanCondition(condition, duration);
        WebElement element = Get.Element.usingLocator(locator);
        element.clear();
        element.sendKeys(keys);
    }

    public static void usingBooleanCondition(WebElement element, ExpectedCondition<Boolean> condition, String keys) {
        Sync.waitUntilBooleanCondition(condition);
        element.clear();
        element.sendKeys(keys);
    }

    public static void usingBooleanCondition(WebElement element, ExpectedCondition<Boolean> condition, String keys, Duration duration) {
        Sync.waitUntilBooleanCondition(condition, duration);
        element.clear();
        element.sendKeys(keys);
    }
}