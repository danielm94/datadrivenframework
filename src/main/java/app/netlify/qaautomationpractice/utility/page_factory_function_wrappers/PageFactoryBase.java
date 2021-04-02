package app.netlify.qaautomationpractice.utility.page_factory_function_wrappers;

import app.netlify.qaautomationpractice.utility.driver.GetDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.time.Duration;
import java.util.LinkedList;
import java.util.List;

public abstract class PageFactoryBase {
    protected abstract String getExpectedPageURL();

    protected abstract boolean isVerboseLoggingEnabled();

    public abstract boolean isPageLoaded();

    public abstract void navigateToPage();

    public abstract boolean isPageTitleValid(String title);

    /**
     * Finds an element by using a locator.
     *
     * @param locator Locator to find element (E.g., "By.Id")
     * @return WebElement
     */
    protected WebElement find(By locator) {
        return Sync.waitUntilVisible(locator);
    }

    protected WebElement find(By locator, Duration duration) {
        return Sync.waitUntilVisible(locator, duration);
    }

    protected WebElement find(ExpectedCondition<WebElement> condition) {
        return Sync.waitUntilWebElementCondition(condition);
    }

    protected WebElement find(ExpectedCondition<WebElement> condition, Duration duration) {
        return Sync.waitUntilWebElementCondition(condition, duration);
    }

    protected void click(By locator) {
        Sync.waitUntilClickable(locator).click();
    }

    protected void click(WebElement element) {
        Sync.waitUntilClickable(element).click();
    }

    protected void click(By locator, Duration duration) {
        Sync.waitUntilClickable(locator, duration).click();
    }

    protected void click(WebElement element, Duration duration) {
        Sync.waitUntilClickable(element, duration).click();
    }

    protected void click(ExpectedCondition<WebElement> condition) {
        Sync.waitUntilWebElementCondition(condition).click();
    }

    protected void click(ExpectedCondition<WebElement> condition, Duration duration) {
        Sync.waitUntilWebElementCondition(condition, duration).click();
    }

    /**
     * Waits for an element using an ExpectedCondition that returns a boolean before clicking on it using a locator.
     *
     * @param locator   The locator to find the WebElement with.
     * @param condition The desired ExpectedCondition that returns a Boolean.
     */
    protected void click(By locator, ExpectedCondition<Boolean> condition) {
        Sync.waitUntilBooleanCondition(condition);
        find(locator).click();
    }

    /**
     * Waits for an element using an ExpectedCondition that returns a boolean before clicking on it using a locator.
     *
     * @param locator   The locator to find the WebElement with.
     * @param condition The desired ExpectedCondition that returns a Boolean.
     * @param duration  The time to wait for the element before throwing a TimeOutException.
     */
    protected void click(By locator, ExpectedCondition<Boolean> condition, Duration duration) {
        Sync.waitUntilBooleanCondition(condition, duration);
        find(locator, duration).click();
    }

    /**
     * Waits for an element using an ExpectedCondition that returns a boolean before clicking on it using a locator.
     *
     * @param element   The element to click.
     * @param condition The desired ExpectedCondition that returns a Boolean.
     */
    protected void click(WebElement element, ExpectedCondition<Boolean> condition) {
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
    protected void click(WebElement element, ExpectedCondition<Boolean> condition, Duration duration) {
        Sync.waitUntilBooleanCondition(condition, duration);
        element.click();
    }

    protected void type(By locator, String keys) {
        WebElement element = Sync.waitUntilVisible(locator);
        element.clear();
        element.sendKeys(keys);
    }

    protected void type(WebElement element, String keys) {
        Sync.waitUntilVisible(element);
        element.clear();
        element.sendKeys(keys);
    }

    protected void type(By locator, String keys, Duration duration) {
        WebElement element = Sync.waitUntilVisible(locator, duration);
        element.clear();
        element.sendKeys(keys);
    }

    protected void type(WebElement element, String keys, Duration duration) {
        Sync.waitUntilVisible(element, duration);
        element.clear();
        element.sendKeys(keys);
    }

    protected void type(ExpectedCondition<WebElement> condition, String keys) {
        WebElement element = Sync.waitUntilWebElementCondition(condition);
        element.clear();
        element.sendKeys(keys);
    }

    protected void type(ExpectedCondition<WebElement> condition, String keys, Duration duration) {
        WebElement element = Sync.waitUntilWebElementCondition(condition, duration);
        element.clear();
        element.sendKeys(keys);
    }

    protected void type(By locator, ExpectedCondition<Boolean> condition, String keys) {
        Sync.waitUntilBooleanCondition(condition);
        WebElement element = find(locator);
        element.clear();
        element.sendKeys(keys);
    }

    protected void type(By locator, ExpectedCondition<Boolean> condition, String keys, Duration duration) {
        Sync.waitUntilBooleanCondition(condition, duration);
        WebElement element = find(locator);
        element.clear();
        element.sendKeys(keys);
    }

    protected void type(WebElement element, ExpectedCondition<Boolean> condition, String keys) {
        Sync.waitUntilBooleanCondition(condition);
        element.clear();
        element.sendKeys(keys);
    }

    protected void type(WebElement element, ExpectedCondition<Boolean> condition, String keys, Duration duration) {
        Sync.waitUntilBooleanCondition(condition, duration);
        element.clear();
        element.sendKeys(keys);
    }

    protected String getText(By locator) {
        return Sync.waitUntilVisible(locator).getText();
    }

    protected String getText(WebElement element) {
        return Sync.waitUntilVisible(element).getText();
    }

    protected String getText(By locator, Duration duration) {
        return Sync.waitUntilVisible(locator, duration).getText();
    }

    protected String getText(WebElement element, Duration duration) {
        return Sync.waitUntilVisible(element, duration).getText();
    }

    protected String getText(By locator, ExpectedCondition<Boolean> condition) {
        Sync.waitUntilBooleanCondition(condition);
        return find(locator).getText();
    }

    protected String getText(WebElement element, ExpectedCondition<Boolean> condition) {
        Sync.waitUntilBooleanCondition(condition);
        return element.getText();
    }

    protected String getText(By locator, ExpectedCondition<Boolean> condition, Duration duration) {
        Sync.waitUntilBooleanCondition(condition, duration);
        return find(locator).getText();
    }

    protected String getText(WebElement element, ExpectedCondition<Boolean> condition, Duration duration) {
        Sync.waitUntilBooleanCondition(condition, duration);
        return element.getText();
    }

    protected String getText(ExpectedCondition<WebElement> condition) {
        return Sync.waitUntilWebElementCondition(condition).getText();
    }

    protected String getText(ExpectedCondition<WebElement> condition, Duration duration) {
        return Sync.waitUntilWebElementCondition(condition, duration).getText();
    }

    public String getCurrentURL() {
        return GetDriver.getInstance().getDriver().getCurrentUrl();
    }

    public String getPageTitle() {
        return GetDriver.getInstance().getDriver().getTitle();
    }

    protected boolean isVisible(By locator) {
        try {
            Sync.waitUntilVisible(locator);
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    protected boolean isVisible(WebElement element) {
        try {
            Sync.waitUntilVisible(element);
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    protected boolean isVisible(By locator, Duration duration) {
        try {
            Sync.waitUntilVisible(locator, duration);
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    protected boolean isVisible(WebElement element, Duration duration) {
        try {
            Sync.waitUntilVisible(element, duration);
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    protected boolean isInvisible(By locator) {
        return Sync.waitUntilInvisible(locator);
    }

    protected boolean isInvisible(WebElement element) {
        return Sync.waitUntilInvisible(element);
    }

    protected boolean isInvisible(By locator, Duration duration) {
        return Sync.waitUntilInvisible(locator, duration);
    }

    protected boolean isInvisible(WebElement element, Duration duration) {
        return Sync.waitUntilInvisible(element, duration);
    }

    protected boolean isPageURLMatching(String URL) {
        return Sync.waitUntilURLContains(URL);
    }

    protected boolean isPageURLMatching(String URL, Duration duration) {
        return Sync.waitUntilURLContains(URL, duration);
    }

    protected boolean isPageURLExactMatch(String exactURL) {
        return Sync.waitUntilURLMatchesExactly(exactURL);
    }

    protected boolean isPageURLExactMatch(String exactURL, Duration duration) {
        return Sync.waitUntilURLMatchesExactly(exactURL, duration);
    }

    protected boolean isPageTitleMatching(String title) {
        return Sync.waitUntilTitleContains(title);
    }

    protected boolean isPageTitleMatching(String title, Duration duration) {
        return Sync.waitUntilTitleContains(title, duration);
    }

    protected boolean isPageTitleExactMatch(String exactTitle) {
        return Sync.waitUntilTitleMatchesExactly(exactTitle);
    }

    protected boolean isPageTitleExactMatch(String exactTitle, Duration duration) {
        return Sync.waitUntilTitleMatchesExactly(exactTitle, duration);
    }

    protected void navigateToURL(String url) {
        GetDriver.getInstance().getDriver().navigate().to(url);
    }
}
