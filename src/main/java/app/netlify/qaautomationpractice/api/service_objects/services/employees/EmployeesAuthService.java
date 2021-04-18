package app.netlify.qaautomationpractice.api.service_objects.services.employees;

import app.netlify.qaautomationpractice.api.service_objects.pojos.Employee;
import app.netlify.qaautomationpractice.api.service_objects.services.ServiceObject;
import app.netlify.qaautomationpractice.shared_utilities.data_readers.PropertyReader;
import app.netlify.qaautomationpractice.shared_utilities.data_readers.property_file.ServiceObjectPropertyFile;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public final class EmployeesAuthService implements ServiceObject {
    private String endpoint;
    private String baseURI;
    private String authorizationHeaderName;
    private RequestSpecification spec;
    private Response response;

    public EmployeesAuthService() {
        this.endpoint = getEndpoint();
        this.baseURI = getBaseURI();
        this.spec = getRequestSpecification();
        this.authorizationHeaderName = PropertyReader.getProperty
                (ServiceObjectPropertyFile.EMPLOYEES_AUTH_SERVICE_PROPERTIES, "authorization_header");
    }


    public EmployeesAuthService(String baseURI) {
        this.baseURI = baseURI;
        this.endpoint = getEndpoint();
        this.spec = getRequestSpecification();
        this.authorizationHeaderName = PropertyReader.getProperty
                (ServiceObjectPropertyFile.EMPLOYEES_AUTH_SERVICE_PROPERTIES, "authorization_header");
    }

    public EmployeesAuthService(String baseURI, String endpoint) {
        this.baseURI = baseURI;
        this.endpoint = endpoint;
        this.spec = getRequestSpecification();
        this.authorizationHeaderName = PropertyReader.getProperty
                (ServiceObjectPropertyFile.EMPLOYEES_AUTH_SERVICE_PROPERTIES, "authorization_header");
    }

    public EmployeesAuthService(String baseURI, String endpoint, RequestSpecification specification) {
        this.baseURI = baseURI;
        this.endpoint = endpoint;
        this.spec = specification;
        this.authorizationHeaderName = PropertyReader.getProperty
                (ServiceObjectPropertyFile.EMPLOYEES_AUTH_SERVICE_PROPERTIES, "authorization_header");
    }

    public EmployeesAuthService(String baseURI, String endpoint, RequestSpecification specification, String authHeaderName) {
        this.baseURI = baseURI;
        this.endpoint = endpoint;
        this.spec = specification;
        this.authorizationHeaderName = authHeaderName;
    }

    @Override
    public String getEndpoint() {
        return PropertyReader.getProperty(
                ServiceObjectPropertyFile.EMPLOYEES_AUTH_SERVICE_PROPERTIES, "endpoint");
    }

    @Override
    public RequestSpecification getRequestSpecification() {
        return new RequestSpecBuilder()
                .setBaseUri(baseURI)
                .setBasePath(endpoint)
                .build();
    }

    @Override
    public int getStatusCode() {
        return response.statusCode();
    }

    @Override
    public long getResponseTime() {
        return response.getTime();
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

    public void setAuthorizationHeaderName(String authorizationHeaderName) {
        this.authorizationHeaderName = authorizationHeaderName;
    }

    public Response getAllEmployees(String authToken) {
        Header header = new Header(authorizationHeaderName, "Bearer " + authToken);
        response = RestAssured
                .given(spec)
                .header(header)
                .contentType(ContentType.JSON)
                .get();
        return response;
    }

    public Employee[] getAllEmployeesAsPOJO(String authToken) {
        Header header = new Header(authorizationHeaderName, "Bearer " + authToken);
        response = RestAssured
                .given(spec)
                .header(header)
                .contentType(ContentType.JSON)
                .get();
        return response.as(Employee[].class);
    }

    public String getAllEmployeesAsString(String authToken) {
        Header header = new Header(authorizationHeaderName, "Bearer " + authToken);
        response = RestAssured.given(spec)
                              .header(header)
                              .contentType(ContentType.JSON)
                              .get();
        return response.asString();
    }
}
