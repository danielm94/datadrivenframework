package app.netlify.qaautomationpractice.utility.data_io;

public enum PropertyFile {
    GLOBAL_PROPERTIES("/framework_properties/global_variables.properties"),
    DEBUG_PROPERTIES("/framework_properties/debug.properties"),
    APPLICATION_PROPERTIES("/framework_properties/application.properties"),
    HOME_PAGE_PROPERTIES("/page_object_properties/home_page.properties"),
    LOGIN_PAGE_PROPERTIES("/page_object_properties/login_page.properties"),
    CHROME_PROPERTIES("/browser_properties/chrome.properties"),
    FIREFOX_PROPERTIES("/browser_properties/firefox.properties"),
    EDGE_PROPERTIES("/browser_properties/edge.properties"),
    IEXPLORER_PROPERTIES("/browser_properties/iexplorer.properties"),
    OPERA_PROPERTIES("/browser_properties/opera.properties");
    private final String FILE_PATH;

    PropertyFile(String filePath) {
        this.FILE_PATH = filePath;
    }

    public String getFilePath() {
        return FILE_PATH;
    }
}
