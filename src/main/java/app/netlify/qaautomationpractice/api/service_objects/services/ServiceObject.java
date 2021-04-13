package app.netlify.qaautomationpractice.api.service_objects.services;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public interface ServiceObject {
    String getEndpoint();

    String getBaseURI();

    RequestSpecification getRequestSpecification();

    void setEndpoint(String endpoint);

    void setBaseURI(String baseURI);

    void setRequestSpecification(RequestSpecification spec);
}
