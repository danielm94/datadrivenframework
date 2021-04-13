package app.netlify.qaautomationpractice.shared_utilities.data_readers.property_file;

public enum BrowserPropertyFile implements PropertyFile {
    CHROME_PROPERTIES("/browser_properties/chrome.properties"),
    FIREFOX_PROPERTIES("/browser_properties/firefox.properties"),
    EDGE_PROPERTIES("/browser_properties/edge.properties"),
    IEXPLORER_PROPERTIES("/browser_properties/iexplorer.properties"),
    OPERA_PROPERTIES("/browser_properties/opera.properties");
    private final String FILE_PATH;

    BrowserPropertyFile(String filePath) {
        this.FILE_PATH = filePath;
    }

    public String getFilePath() {
        return FILE_PATH;
    }
}
