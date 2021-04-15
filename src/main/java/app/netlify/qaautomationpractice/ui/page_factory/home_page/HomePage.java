package app.netlify.qaautomationpractice.ui.page_factory.home_page;

import app.netlify.qaautomationpractice.shared_utilities.data_readers.PropertyReader;
import app.netlify.qaautomationpractice.shared_utilities.data_readers.property_file.PageFactoryPropertyFile;
import app.netlify.qaautomationpractice.shared_utilities.report_utility.Log;
import app.netlify.qaautomationpractice.ui.utility.driver.Driver;
import app.netlify.qaautomationpractice.ui.utility.page_object_utils.Check;
import app.netlify.qaautomationpractice.ui.utility.page_object_utils.PageObject;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage implements PageObject {
    private final boolean VERBOSE_LOGS = isVerboseLoggingEnabled();
    private final String PAGE_URL = getExpectedPageURL();
    @FindBy(xpath = "//a[@id='home' and @href='index.html']")
    WebElement homeButton;

    public HomePage() {
        PageFactory.initElements(Driver.getInstance()
                                       .getDriver(), this);
    }

    @Override
    public String getExpectedPageURL() {
        return PropertyReader.getProperty(PageFactoryPropertyFile.HOME_PAGE_PROPERTIES, "url");
    }

    @Override
    public boolean isVerboseLoggingEnabled() {
        return Boolean.parseBoolean(PropertyReader.getProperty(PageFactoryPropertyFile.HOME_PAGE_PROPERTIES, "verboseLogs"));
    }

    @Override
    public boolean isPageLoaded() {
        if (VERBOSE_LOGS) Log.log(Status.INFO, "Checking if home page is loaded.");
        try {
            Check.PageURL.contains(PAGE_URL);
            if (VERBOSE_LOGS) Log.log(Status.INFO, "Home page loaded the correct URL.");
            return true;
        } catch (TimeoutException e) {
            Log.log(Status.WARNING, "Home page did not load the correct URL!");
            return false;
        }
    }

    @Override
    public void navigateToPage() {
        Driver.getInstance()
              .navigateToURL(PAGE_URL);
        Log.log(Status.INFO, "Navigated to the home page.");
    }

    public void clickHomeButton() {
        homeButton.click();
        if (VERBOSE_LOGS) Log.log(Status.INFO, "Clicked on the 'Home' button.");
    }

    public boolean isPageTitleValid(String title) {
        if (VERBOSE_LOGS) Log.log(Status.INFO, "Checking if page title matches: " + title);
        try {
            Check.PageTitle.matchesExactly(title);
            if (VERBOSE_LOGS) Log.log(Status.INFO, "Title was displayed correctly.");
            return true;
        } catch (TimeoutException e) {
            Log.log(Status.WARNING, "Home page did not load the correct title! Expected: " + title + " | Found: " + Driver.getInstance()
                                                                                                                          .getDriver()
                                                                                                                          .getTitle());
            return false;
        }
    }
}
