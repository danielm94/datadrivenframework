package app.netlify.qaautomationpractice.api.service_objects.services.employees;

import app.netlify.qaautomationpractice.api.service_objects.pojos.Employee;
import app.netlify.qaautomationpractice.api.service_objects.services.ServiceObject;
import app.netlify.qaautomationpractice.api.service_objects.services.token.TokenService;
import app.netlify.qaautomationpractice.shared_utilities.data_readers.PropertyReader;
import app.netlify.qaautomationpractice.shared_utilities.data_readers.property_file.ServiceObjectPropertyFile;
import io.restassured.RestAssured;
import io.restassured.authentication.OAuthScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
/*
Author: Daniel Martins
Email: daniel.d.martins@outlook.com
*/

/**
 * Employees Service Object for /api/v1/employees/ endpoint.
 */
public final class EmployeesService implements ServiceObject {
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
        return response.getStatusCode();
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

    public Response getAllEmployees() {
        response = RestAssured
                .given(spec)
                .contentType(ContentType.JSON)
                .get();
        return response;
    }

    public Employee[] getAllEmployeesAsPOJO() {
        response = RestAssured
                .given(spec)
                .contentType(ContentType.JSON)
                .get();
        return response.as(Employee[].class);
    }

    public String getAllEmployeesAsString() {
        response = RestAssured.given(spec)
                              .contentType(ContentType.JSON)
                              .get();
        return response.asString();
    }

    public Response getEmployee(int employeeID) {
        response = RestAssured.given(spec)
                              .contentType(ContentType.JSON)
                              .get(String.valueOf(employeeID));
        return response;
    }

    public Employee getEmployeeAsPOJO(int employeeID) {
        response = RestAssured.given(spec)
                              .contentType(ContentType.JSON)
                              .get(String.valueOf(employeeID));
        return response.as(Employee.class);
    }

    public String getEmployeeAsString(int employeeID) {
        response = RestAssured.given(spec)
                              .contentType(ContentType.JSON)
                              .get(String.valueOf(employeeID));
        return response.asString();
    }

    public Response createEmployee(Employee employee) {
        return response = RestAssured.given(spec)
                                     .contentType(ContentType.JSON)
                                     .body(employee)
                                     .post();
    }

    public Response updateEmployee(int employeeID, Employee employee) {
        return response = RestAssured.given(spec)
                                     .contentType(ContentType.JSON)
                                     .body(employee)
                                     .put(String.valueOf(employeeID));
    }

    public Response deleteEmployee(int employeeID) {
        return response = RestAssured.given(spec)
                                     .contentType(ContentType.JSON)
                                     .delete(String.valueOf(employeeID));
    }

    public static void main(String[] args) {
        EmployeesService service = new EmployeesService();
        service.deleteEmployee(14)
               .prettyPrint();
        service.getAllEmployees()
               .prettyPrint();

    }
}
