package app.netlify.qaautomationpractice.shared_utilities.data_readers.property_file;

public enum FrameworkPropertyFile implements PropertyFile {
    GLOBAL_PROPERTIES("/framework_properties/global_variables.properties"),
    DEBUG_PROPERTIES("/framework_properties/debug.properties"),
    APPLICATION_PROPERTIES("/framework_properties/application.properties");
    private final String FILE_PATH;

    FrameworkPropertyFile(String filePath) {
        this.FILE_PATH = filePath;
    }

    public String getFilePath() {
        return FILE_PATH;
    }
}
