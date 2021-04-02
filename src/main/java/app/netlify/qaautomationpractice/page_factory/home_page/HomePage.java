package app.netlify.qaautomationpractice.page_factory.home_page;

import app.netlify.qaautomationpractice.utility.data_io.PropertyReader;
import app.netlify.qaautomationpractice.utility.data_io.PropertyFile;
import app.netlify.qaautomationpractice.utility.driver.GetDriver;
import app.netlify.qaautomationpractice.utility.page_factory_function_wrappers.PageFactoryBase;
import app.netlify.qaautomationpractice.utility.report_utility.Log;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends PageFactoryBase {
    private final boolean VERBOSE_LOGS = isVerboseLoggingEnabled();
    private final String PAGE_URL = getExpectedPageURL();
    @FindBy(xpath = "//a[@id='home' and @href='index.html']")
    WebElement homeButton;

    public HomePage() {
        PageFactory.initElements(GetDriver.getInstance().getDriver(), this);
    }
    @Override
    protected String getExpectedPageURL() {
        return PropertyReader.getProperty(PropertyFile.HOME_PAGE_PROPERTIES, "url");
    }

    @Override
    protected boolean isVerboseLoggingEnabled() {
        return Boolean.parseBoolean(PropertyReader.getProperty(PropertyFile.HOME_PAGE_PROPERTIES, "verboseLogs"));
    }

    @Override
    public boolean isPageLoaded() {
        if (VERBOSE_LOGS) Log.log(Status.INFO, "Checking if home page is loaded.");
        try {
            isPageURLMatching(PAGE_URL);
            if (VERBOSE_LOGS) Log.log(Status.INFO, "Home page loaded the correct URL.");
            return true;
        } catch (TimeoutException e) {
            Log.log(Status.WARNING, "Home page did not load the correct URL!");
            return false;
        }
    }

    @Override
    public void navigateToPage() {
        navigateToURL(PAGE_URL);
        Log.log(Status.INFO, "Navigated to the home page.");
    }

    public void clickHomeButton() {
        homeButton.click();
        if (VERBOSE_LOGS) Log.log(Status.INFO, "Clicked on the 'Home' button.");
    }

    public boolean isPageTitleValid(String title) {
        if (VERBOSE_LOGS) Log.log(Status.INFO, "Checking if page title matches: " + title);
        try {
            isPageTitleExactMatch(title);
            if (VERBOSE_LOGS) Log.log(Status.INFO, "Title was displayed correctly.");
            return true;
        } catch (TimeoutException e) {
            Log.log(Status.WARNING, "Home page did not load the correct title! Expected: " + title + " | Found: " + getPageTitle());
            return false;
        }
    }
}
