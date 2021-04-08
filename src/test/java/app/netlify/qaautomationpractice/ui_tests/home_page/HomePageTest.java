package app.netlify.qaautomationpractice.ui_tests.home_page;

import app.netlify.qaautomationpractice.page_factory.home_page.HomePage;
import app.netlify.qaautomationpractice.utility.report_utility.TestReportInformation;
import app.netlify.qaautomationpractice.utility.test_base.TestBase;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestBase.class)
public class HomePageTest extends TestBase {
    @TestReportInformation(title = "Is Home Page Loaded", description = "Verify home page is loaded")
    @Test()
    public void homePageIsLoaded() {
        HomePage homePage = new HomePage();
        Assert.assertTrue(homePage.isPageLoaded(), "Home page was not loaded correctly/in time.");
    }

    @TestReportInformation(title = "Home Page Title is Correct", description = "Verify home page title is correct")
    @Test()
    public void homePageTitleIsCorrect() {
        HomePage homePage = new HomePage();
        Assert.assertTrue(homePage.isPageLoaded());
        Assert.assertTrue(homePage.isPageTitleValid("Learn with RV | Automation Practice"), "Title was not loaded correctly.");

    }

    @TestReportInformation(title = "Home Button Takes User To Home Page", description = "Verify home page button takes user to the home page.")
    @Test(groups = {"UI Tests", "Regression", "Unit Test"})
    public void homeButtonTakesUserToHomePage() {
        HomePage homePage = new HomePage();
        Assert.assertTrue(homePage.isPageLoaded(), "Home page was not loaded correctly/in time.");
        homePage.clickHomeButton();
        Assert.assertTrue(homePage.isPageLoaded(), "Home page was not loaded correctly/in time.");
        Assert.assertTrue(homePage.isPageTitleValid("Learn with RV | Automation Practice"), "Title was not loaded correctly.");
    }
}
