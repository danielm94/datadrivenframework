package app.netlify.qaautomationpractice.ui.utility.page_object_utils;

public interface PageObject {
    String getExpectedPageURL();

    boolean isPageLoaded();

    void navigateToPage();

    boolean isPageTitleValid(String title);

}
