package app.netlify.qaautomationpractice.api.service_objects.services.token;

import app.netlify.qaautomationpractice.api.service_objects.pojos.request.UserCredentials;
import app.netlify.qaautomationpractice.api.service_objects.services.ServiceObject;
import app.netlify.qaautomationpractice.shared_utilities.data_readers.PropertyReader;
import app.netlify.qaautomationpractice.shared_utilities.data_readers.property_file.FrameworkPropertyFile;
import app.netlify.qaautomationpractice.shared_utilities.data_readers.property_file.ServiceObjectPropertyFile;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TokenService implements ServiceObject {
    private String baseURI = getBaseURI();
    private String endpoint = getEndpoint();
    private RequestSpecification spec = getRequestSpecification();
    private static Response response;

    @Override
    public String getEndpoint() {
        return PropertyReader.getProperty(
                ServiceObjectPropertyFile.TOKEN_SERVICE_PROPERTIES, "endpoint");
    }

    @Override
    public String getBaseURI() {
        return PropertyReader.getProperty(FrameworkPropertyFile.APPLICATION_PROPERTIES, "api.uri");
    }

    @Override
    public RequestSpecification getRequestSpecification() {
        return new RequestSpecBuilder()
                .setBaseUri(baseURI)
                .setBasePath(endpoint)
                .build();
    }

    @Override
    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    @Override
    public void setBaseURI(String baseURI) {
        this.baseURI = baseURI;
    }

    @Override
    public void setRequestSpecification(RequestSpecification spec) {
        this.spec = spec;
    }

    public void postUserCredentials(String username, String password) {
        UserCredentials credentials = new UserCredentials(username, password);
        response = RestAssured
                .given(spec)
                .contentType(ContentType.JSON)
                .body(credentials)
                .post();
    }

    public void postUserCredentials(UserCredentials credentials) {
        response = RestAssured
                .given(spec)
                .contentType(ContentType.JSON)
                .body(credentials)
                .post();
    }

    public int getStatusCode() {
        return response.getStatusCode();
    }

    public String getToken() {
        return response
                .getBody()
                .asString();
    }
}
