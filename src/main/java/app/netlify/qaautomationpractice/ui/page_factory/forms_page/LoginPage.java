package app.netlify.qaautomationpractice.ui.page_factory.forms_page;

import app.netlify.qaautomationpractice.shared_utilities.data_readers.PropertyReader;
import app.netlify.qaautomationpractice.shared_utilities.data_readers.property_file.PageFactoryPropertyFile;
import app.netlify.qaautomationpractice.shared_utilities.report_utility.Log;
import app.netlify.qaautomationpractice.ui.utility.driver.Driver;
import app.netlify.qaautomationpractice.ui.utility.page_object_utils.*;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public final class LoginPage implements PageObject {
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
        PageFactory.initElements(Driver.getInstance()
                                       .getDriver(), this);
    }

    @Override
    public String getExpectedPageURL() {
        return PropertyReader.getProperty(PageFactoryPropertyFile.LOGIN_PAGE_PROPERTIES, "url");
    }

    @Override
    public boolean isPageLoaded() {
        Log.log(Status.INFO, "Checking if login page is loaded.");
        try {
            Check.PageURL.contains(PAGE_URL);
            Log.log(Status.INFO, "Login page loaded the correct URL.");
            return true;
        } catch (TimeoutException e) {
            Log.log(Status.WARNING, "Login page did not load the correct URL!");
            return false;
        }
    }

    @Override
    public void navigateToPage() {
        Driver.getInstance()
              .navigateToURL(PAGE_URL);
        Log.log(Status.INFO, "Navigated to the login page.");
    }

    public boolean isPageTitleValid(String title) {
        Log.log(Status.INFO, "Checking if page title matches: " + title);
        try {
            Check.PageTitle.matchesExactly(title);
            Log.log(Status.INFO, "Title was displayed correctly.");
            return true;
        } catch (TimeoutException e) {
            Log.log(Status.WARNING, "Login page did not load the correct title! Expected: " + title + " | Found: " + Driver.getInstance()
                                                                                                                           .getDriver()
                                                                                                                           .getTitle());
            return false;
        }
    }

    public void sendKeysToEmailField(String keys) {
        SendKeys.usingElement(email, keys);
        Log.log(Status.INFO, "Entered " + keys + " into the email field.");
    }

    public void sendKeysToPasswordField(String keys) {
        SendKeys.usingElement(password, keys);
        Log.log(Status.INFO, "Entered " + keys + " into the password field.");
    }

    public void clickSubmit() {
        Click.usingElement(submitLogin);
        Log.log(Status.INFO, "Clicked the \"Submit\" button");
    }

    public void clickCheckMeOut() {
        Click.usingElement(checkMeOut);
        Log.log(Status.INFO, "Clicked the \"Check me out\" checkbox.");
    }

    public String getLoginMessageText() {
        String text = Get.ElementText.usingElement(loginMessage);
        Log.log(Status.INFO, "Login message displayed the following after logging in: " + text);
        return text;
    }
}
