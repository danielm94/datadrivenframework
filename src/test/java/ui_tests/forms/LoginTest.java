package ui_tests.forms;

import app.netlify.qaautomationpractice.data_providers.forms_pages.LoginData;
import app.netlify.qaautomationpractice.page_factory.forms_page.LoginPage;
import app.netlify.qaautomationpractice.utility.report_utility.TestReportInformation;
import app.netlify.qaautomationpractice.utility.test_base.TestBase;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestBase.class)
public class LoginTest extends TestBase {
    @Test(description = "Login test with valid credentials")
    @TestReportInformation(title = "Login With Valid Credentials",
            description = "Verify that the user can login with valid credentials.")
    public void loginWithValidCredentials() {
        LoginPage loginPage = new LoginPage();
        loginPage.navigateToPage();
        Assert.assertTrue(loginPage.isPageLoaded());
        loginPage.sendKeysToEmailField("admin@admin.com");
        loginPage.sendKeysToPasswordField("admin123");
        loginPage.clickSubmit();
        Assert.assertEquals(loginPage.getLoginMessageText(),
                "You have successfully logged in!", "Login success message was not displayed correctly.");
    }

    @Test(dataProviderClass = LoginData.class, dataProvider = "Invalid Logins")
    @TestReportInformation(title = "Login With Invalid Credentials",
            description = "Verify that the appropriate error message is displayed when invalid credentials are entered.")
    public void loginWithInvalidCredentials(String username, String password) {
        LoginPage loginPage = new LoginPage();
        loginPage.navigateToPage();
        Assert.assertTrue(loginPage.isPageLoaded());
        loginPage.sendKeysToEmailField(username);
        loginPage.sendKeysToPasswordField(password);
        loginPage.clickSubmit();
        Assert.assertEquals(loginPage.getLoginMessageText(),
                "Bad credentials! Please try again! Hint: valid credentials displayed above."
                , "Error message was not displayed correctly when invalid login credentials were entered.");
    }
}
