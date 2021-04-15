package app.netlify.qaautomationpractice.ui.utility.page_object_utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.time.Duration;

public final class Click {
    private Click() {
    }

    public static void usingLocator(By locator) {
        Sync.waitUntilClickable(locator)
            .click();
    }

    public static void usingElement(WebElement element) {
        Sync.waitUntilClickable(element)
            .click();
    }

    public static void usingLocator(By locator, Duration duration) {
        Sync.waitUntilClickable(locator, duration)
            .click();
    }

    public static void usingElement(WebElement element, Duration duration) {
        Sync.waitUntilClickable(element, duration)
            .click();
    }

    public static void usingElementCondition(ExpectedCondition<WebElement> condition) {
        Sync.waitUntilWebElementCondition(condition)
            .click();
    }

    public static void usingElementCondition(ExpectedCondition<WebElement> condition, Duration duration) {
        Sync.waitUntilWebElementCondition(condition, duration)
            .click();
    }

    /**
     * Waits for an element using an ExpectedCondition that returns a boolean before clicking on it using a locator.
     *
     * @param locator   The locator to find the WebElement with.
     * @param condition The desired ExpectedCondition that returns a Boolean.
     */
    public static void usingBooleanCondition(By locator, ExpectedCondition<Boolean> condition) {
        Sync.waitUntilBooleanCondition(condition);
        Get.Element.usingLocator(locator)
                   .click();
    }

    /**
     * Waits for an element using an ExpectedCondition that returns a boolean before clicking on it using a locator.
     *
     * @param locator   The locator to find the WebElement with.
     * @param condition The desired ExpectedCondition that returns a Boolean.
     * @param duration  The time to wait for the element before throwing a TimeOutException.
     */
    public static void usingBooleanCondition(By locator, ExpectedCondition<Boolean> condition, Duration duration) {
        Sync.waitUntilBooleanCondition(condition, duration);
        Get.Element.usingLocator(locator, duration)
            .click();
    }

    /**
     * Waits for an element using an ExpectedCondition that returns a boolean before clicking on it using a locator.
     *
     * @param element   The element to click.
     * @param condition The desired ExpectedCondition that returns a Boolean.
     */
    public static void usingBooleanCondition(WebElement element, ExpectedCondition<Boolean> condition) {
        Sync.waitUntilBooleanCondition(condition);
        element.click();
    }

    /**
     * Waits for an element using an ExpectedCondition that returns a boolean before clicking on it using a locator.
     *
     * @param element   The element to click.
     * @param condition The desired ExpectedCondition that returns a Boolean.
     * @param duration  The time to wait for the element before throwing a TimeOutException.
     */
    public static void usingBooleanCondition(WebElement element, ExpectedCondition<Boolean> condition, Duration duration) {
        Sync.waitUntilBooleanCondition(condition, duration);
        element.click();
    }
}