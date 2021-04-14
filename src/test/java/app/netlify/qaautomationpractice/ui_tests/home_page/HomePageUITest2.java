package app.netlify.qaautomationpractice.ui_tests.home_page;

import app.netlify.qaautomationpractice.ui.page_factory.home_page.HomePage;
import app.netlify.qaautomationpractice.shared_utilities.report_utility.TestReportInformation;
import app.netlify.qaautomationpractice.ui.utility.test_base.UITestBase;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(UITestBase.class)
public class HomePageUITest2 extends UITestBase {
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
