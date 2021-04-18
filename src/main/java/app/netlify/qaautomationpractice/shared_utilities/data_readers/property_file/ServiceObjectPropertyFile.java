package app.netlify.qaautomationpractice.shared_utilities.data_readers.property_file;

public enum ServiceObjectPropertyFile implements PropertyFile {
    TOKEN_SERVICE_PROPERTIES("/service_object_properties/token_service.properties"),
    EMPLOYEES_SERVICE_PROPERTIES("/service_object_properties/employees_service.properties"),
    EMPLOYEES_AUTH_SERVICE_PROPERTIES("/service_object_properties/employees_service_auth.properties"),
    ERROR_SERVICE_PROPERTIES("/service_object_properties/error_service.properties");
    private final String FILE_PATH;

    ServiceObjectPropertyFile(String filePath) {
        this.FILE_PATH = filePath;
    }

    public String getFilePath() {
        return FILE_PATH;
    }
}
