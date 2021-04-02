package app.netlify.qaautomationpractice.page_factory.forms_page;

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

public class LoginPage extends PageFactoryBase {
    private final boolean VERBOSE_LOGS = isVerboseLoggingEnabled();
    private final String PAGE_URL = getExpectedPageURL();
    @FindBy(id = "email")
    WebElement email;
    @FindBy(id = "password")
    WebElement password;
    @FindBy(id = "exampleCheckbox")
    WebElement checkMeOut;
    @FindBy(id = "submitLoginBtn")
    WebElement submitLogin;
    @FindBy(id = "message")
    WebElement loginMessage;

    public LoginPage() {
        PageFactory.initElements(GetDriver.getInstance().getDriver(), this);
    }

    @Override
    protected String getExpectedPageURL() {
        return PropertyReader.getProperty(PropertyFile.LOGIN_PAGE_PROPERTIES, "url");
    }

    @Override
    protected boolean isVerboseLoggingEnabled() {
        return Boolean.parseBoolean(PropertyReader.getProperty(PropertyFile.LOGIN_PAGE_PROPERTIES, "verboseLogs"));
    }

    @Override
    public boolean isPageLoaded() {
        if (VERBOSE_LOGS) Log.log(Status.INFO, "Checking if login page is loaded.");
        try {
            isPageURLMatching(PAGE_URL);
            if (VERBOSE_LOGS) Log.log(Status.INFO, "Login page loaded the correct URL.");
            return true;
        } catch (TimeoutException e) {
            Log.log(Status.WARNING, "Login page did not load the correct URL!");
            return false;
        }
    }

    @Override
    public void navigateToPage() {
        navigateToURL(PAGE_URL);
        if (VERBOSE_LOGS) Log.log(Status.INFO, "Navigated to the login page.");
    }

    public boolean isPageTitleValid(String title) {
        if (VERBOSE_LOGS) Log.log(Status.INFO, "Checking if page title matches: " + title);
        try {
            isPageTitleExactMatch(title);
            if (VERBOSE_LOGS) Log.log(Status.INFO, "Title was displayed correctly.");
            return true;
        } catch (TimeoutException e) {
            Log.log(Status.WARNING, "Login page did not load the correct title! Expected: " + title + " | Found: " + getPageTitle());
            return false;
        }
    }

    public void sendKeysToEmailField(String keys) {
        type(email, keys);
        if (VERBOSE_LOGS) Log.log(Status.INFO, "Entered " + keys + " into the email field.");
    }

    public void sendKeysToPasswordField(String keys) {
        type(password, keys);
        if (VERBOSE_LOGS) Log.log(Status.INFO, "Entered " + keys + " into the password field.");
    }

    public void clickSubmit() {
        click(submitLogin);
        if (VERBOSE_LOGS) Log.log(Status.INFO, "Clicked the \"Submit\" button");
    }

    public void clickCheckMeOut() {
        click(checkMeOut);
        if (VERBOSE_LOGS) Log.log(Status.INFO, "Clicked the \"Check me out\" checkbox.");
    }

    public String getLoginMessageText() {
        String text = getText(loginMessage);
        if (VERBOSE_LOGS) Log.log(Status.INFO, "Login message displayed the following after logging in: " + text);
        return text;
    }
}
