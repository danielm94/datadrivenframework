package app.netlify.qaautomationpractice.api.utility.test_base;

import app.netlify.qaautomationpractice.shared_utilities.data_readers.PropertyReader;
import app.netlify.qaautomationpractice.shared_utilities.data_readers.property_file.FrameworkPropertyFile;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.TestListenerAdapter;
import org.testng.annotations.BeforeClass;

public class APITestBase extends TestListenerAdapter {
    protected static String baseURI;
    protected static RequestSpecification spec;

    @BeforeClass
    public void beforeClass() {
        baseURI = PropertyReader.getProperty(FrameworkPropertyFile.APPLICATION_PROPERTIES, "api.uri");
        spec = new RequestSpecBuilder()
                .setBaseUri(baseURI)
                .build();
    }
}
