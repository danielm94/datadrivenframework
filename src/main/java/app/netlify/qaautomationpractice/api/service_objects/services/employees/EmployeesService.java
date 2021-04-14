package app.netlify.qaautomationpractice.api.service_objects.services.employees;

import app.netlify.qaautomationpractice.api.service_objects.services.ServiceObject;
import app.netlify.qaautomationpractice.api.service_objects.services.token.TokenService;
import app.netlify.qaautomationpractice.shared_utilities.data_readers.PropertyReader;
import app.netlify.qaautomationpractice.shared_utilities.data_readers.property_file.FrameworkPropertyFile;
import app.netlify.qaautomationpractice.shared_utilities.data_readers.property_file.ServiceObjectPropertyFile;
import io.restassured.authentication.OAuthScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class EmployeesService implements ServiceObject {
    private String endpoint = getEndpoint();
    private String baseURI = getBaseURI();
    private RequestSpecification spec = getRequestSpecification();
    private Response response;

    @Override
    public String getEndpoint() {
        return PropertyReader.getProperty(
                ServiceObjectPropertyFile.EMPLOYEES_SERVICE_PROPERTIES, "endpoint");
    }

    @Override
    public String getBaseURI() {
        return PropertyReader.getProperty(FrameworkPropertyFile.APPLICATION_PROPERTIES, "api.uri");
    }

    @Override
    public RequestSpecification getRequestSpecification() {
        TokenService tokenService = new TokenService();
        tokenService.postUserCredentials("admin", "admin");
        String authorization = tokenService.getResponseToken();
        OAuthScheme auth = new OAuthScheme();
        auth.setAccessToken(authorization);
        return new RequestSpecBuilder()
                .setBaseUri(baseURI)
                .setBasePath(endpoint)
                .setAuth(auth)
                .build();
    }

    @Override
    public int getStatusCode() {
        return 0;
    }

    @Override
    public long getResponseTime() {
        return 0;
    }

    @Override
    public void setEndpoint(String endpoint) {

    }

    @Override
    public void setBaseURI(String baseURI) {

    }

    @Override
    public void setRequestSpecification(RequestSpecification spec) {

    }
}
