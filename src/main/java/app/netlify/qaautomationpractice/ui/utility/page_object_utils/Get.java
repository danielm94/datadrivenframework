package app.netlify.qaautomationpractice.ui.utility.page_object_utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.time.Duration;

public final class Get {
    private Get() {
    }

    public static final class Element {
        private Element() {
        }

        /**
         * Finds an element by using a locator.
         *
         * @param locator Locator to find element (E.g., "By.Id")
         * @return WebElement
         */
        public static WebElement usingLocator(By locator) {
            return Sync.waitUntilVisible(locator);
        }

        public static WebElement usingLocator(By locator, Duration duration) {
            return Sync.waitUntilVisible(locator, duration);
        }

        public static WebElement usingExpectedCondition(ExpectedCondition<WebElement> condition) {
            return Sync.waitUntilWebElementCondition(condition);
        }

        public static WebElement usingExpectedCondition(ExpectedCondition<WebElement> condition, Duration duration) {
            return Sync.waitUntilWebElementCondition(condition, duration);
        }
    }

    public static final class ElementText {
        private ElementText() {
        }

        public static String usingLocator(By locator) {
            return Sync.waitUntilVisible(locator)
                       .getText();
        }

        public static String usingElement(WebElement element) {
            return Sync.waitUntilVisible(element)
                       .getText();
        }

        public static String usingLocator(By locator, Duration duration) {
            return Sync.waitUntilVisible(locator, duration)
                       .getText();
        }

        public static String usingElement(WebElement element, Duration duration) {
            return Sync.waitUntilVisible(element, duration)
                       .getText();
        }

        public static String usingBooleanCondition(By locator, ExpectedCondition<Boolean> condition) {
            Sync.waitUntilBooleanCondition(condition);
            return Get.Element.usingLocator(locator)
                              .getText();
        }

        public static String usingBooleanCondition(WebElement element, ExpectedCondition<Boolean> condition) {
            Sync.waitUntilBooleanCondition(condition);
            return element.getText();
        }

        public static String usingBooleanCondition(By locator, ExpectedCondition<Boolean> condition, Duration duration) {
            Sync.waitUntilBooleanCondition(condition, duration);
            return Get.Element.usingLocator(locator)
                              .getText();
        }

        public static String usingBooleanCondition(WebElement element, ExpectedCondition<Boolean> condition, Duration duration) {
            Sync.waitUntilBooleanCondition(condition, duration);
            return element.getText();
        }

        public static String usingElementCondition(ExpectedCondition<WebElement> condition) {
            return Sync.waitUntilWebElementCondition(condition)
                       .getText();
        }

        public static String usingElementCondition(ExpectedCondition<WebElement> condition, Duration duration) {
            return Sync.waitUntilWebElementCondition(condition, duration)
                       .getText();
        }
    }
}
