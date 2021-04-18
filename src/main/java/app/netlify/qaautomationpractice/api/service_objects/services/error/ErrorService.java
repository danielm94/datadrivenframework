package app.netlify.qaautomationpractice.api.service_objects.services.error;

import app.netlify.qaautomationpractice.api.service_objects.services.ServiceObject;
import app.netlify.qaautomationpractice.shared_utilities.data_readers.PropertyReader;
import app.netlify.qaautomationpractice.shared_utilities.data_readers.property_file.ServiceObjectPropertyFile;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/*
Author: Daniel Martins
Email: daniel.d.martins@outlook.com
*/
public class ErrorService implements ServiceObject {
    private String baseURI;
    private String endpoint;
    private RequestSpecification spec;
    private Response response;


    public ErrorService() {
        this.baseURI = getBaseURI();
        this.endpoint = getEndpoint();
        this.spec = getRequestSpecification();
    }

    public ErrorService(String baseURI) {
        this.baseURI = baseURI;
        this.endpoint = getEndpoint();
        this.spec = getRequestSpecification();
    }

    public ErrorService(String baseURI, String endpoint) {
        this.baseURI = baseURI;
        this.endpoint = endpoint;
        this.spec = getRequestSpecification();
    }

    public ErrorService(String baseURI, String endpoint, RequestSpecification specification) {
        this.baseURI = baseURI;
        this.endpoint = endpoint;
        this.spec = specification;
    }

    @Override
    public String getEndpoint() {
        return PropertyReader.getProperty(
                ServiceObjectPropertyFile.ERROR_SERVICE_PROPERTIES, "endpoint");
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
}
