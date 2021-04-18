package app.netlify.qaautomationpractice.api.service_objects.services;

import app.netlify.qaautomationpractice.shared_utilities.data_readers.PropertyReader;
import app.netlify.qaautomationpractice.shared_utilities.data_readers.property_file.FrameworkPropertyFile;
import io.restassured.specification.RequestSpecification;

/*
Author: Daniel Martins
Email: daniel.d.martins@outlook.com
*/
public interface ServiceObject {
    String getEndpoint();

    default String getBaseURI() {
        return PropertyReader.getProperty(FrameworkPropertyFile.APPLICATION_PROPERTIES, "api.uri");
    }

    RequestSpecification getRequestSpecification();

    int getStatusCode();

    long getResponseTime();

    void setEndpoint(String endpoint);

    void setBaseURI(String baseURI);

    void setRequestSpecification(RequestSpecification spec);
}
