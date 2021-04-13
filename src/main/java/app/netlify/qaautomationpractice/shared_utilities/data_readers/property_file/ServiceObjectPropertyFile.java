package app.netlify.qaautomationpractice.shared_utilities.data_readers.property_file;

public enum ServiceObjectPropertyFile implements PropertyFile {
    TOKEN_SERVICE_PROPERTIES("/service_object_properties/token_service.properties");
    private final String FILE_PATH;

    ServiceObjectPropertyFile(String filePath) {
        this.FILE_PATH = filePath;
    }

    public String getFilePath() {
        return FILE_PATH;
    }
}
