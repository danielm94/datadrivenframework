package app.netlify.qaautomationpractice.ui.utility.page_object_utils;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public final class Check {
    private Check() {
    }

    public static final class PageURL {
        private PageURL() {
        }

        public static boolean contains(String URL) {
            return Sync.waitUntilURLContains(URL);
        }

        public static boolean contains(String URL, Duration duration) {
            return Sync.waitUntilURLContains(URL, duration);
        }

        public static boolean isExactMatch(String exactURL) {
            return Sync.waitUntilURLMatchesExactly(exactURL);
        }

        public static boolean isExactMatch(String exactURL, Duration duration) {
            return Sync.waitUntilURLMatchesExactly(exactURL, duration);
        }
    }

    public static final class Visibility {
        private Visibility() {
        }

        public static boolean usingLocator(By locator) {
            try {
                Sync.waitUntilVisible(locator);
                return true;
            } catch (TimeoutException e) {
                return false;
            }
        }

        public static boolean usingElement(WebElement element) {
            try {
                Sync.waitUntilVisible(element);
                return true;
            } catch (TimeoutException e) {
                return false;
            }
        }

        public static boolean usingLocator(By locator, Duration duration) {
            try {
                Sync.waitUntilVisible(locator, duration);
                return true;
            } catch (TimeoutException e) {
                return false;
            }
        }

        public static boolean usingElement(WebElement element, Duration duration) {
            try {
                Sync.waitUntilVisible(element, duration);
                return true;
            } catch (TimeoutException e) {
                return false;
            }
        }
    }

    public static final class Invisibility {
        private Invisibility() {
        }

        public static boolean usingLocator(By locator) {
            return Sync.waitUntilInvisible(locator);
        }

        public static boolean usingElement(WebElement element) {
            return Sync.waitUntilInvisible(element);
        }

        public static boolean usingLocator(By locator, Duration duration) {
            return Sync.waitUntilInvisible(locator, duration);
        }

        public static boolean usingElement(WebElement element, Duration duration) {
            return Sync.waitUntilInvisible(element, duration);
        }
    }

    public static final class PageTitle {
        private PageTitle() {
        }

        public static boolean contains(String title) {
            return Sync.waitUntilTitleContains(title);
        }

        public static boolean contains(String title, Duration duration) {
            return Sync.waitUntilTitleContains(title, duration);
        }

        public static boolean matchesExactly(String exactTitle) {
            return Sync.waitUntilTitleMatchesExactly(exactTitle);
        }

        public static boolean matchesExactly(String exactTitle, Duration duration) {
            return Sync.waitUntilTitleMatchesExactly(exactTitle, duration);
        }
    }
}
