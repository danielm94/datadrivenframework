package app.netlify.qaautomationpractice.shared_utilities.data_readers.property_file;

public enum PageFactoryPropertyFile implements PropertyFile {
    HOME_PAGE_PROPERTIES("/page_object_properties/home_page.properties"),
    LOGIN_PAGE_PROPERTIES("/page_object_properties/login_page.properties");
    private final String FILE_PATH;

    PageFactoryPropertyFile(String filePath) {
        this.FILE_PATH = filePath;
    }

    public String getFilePath() {
        return FILE_PATH;
    }
}
