package app.netlify.qaautomationpractice.api_tests.employee_controller;

import app.netlify.qaautomationpractice.api.service_objects.services.token.TokenService;
import app.netlify.qaautomationpractice.api.utility.test_base.APITestBase;
import org.testng.annotations.Test;

public class GetJWTToken extends APITestBase {
    @Test
    public void createJWTToken() {
        TokenService tokenService = new TokenService();
        tokenService.postUserCredentials("admin", "admin");
        String token = tokenService.getResponseToken();
        int statusCode = tokenService.getStatusCode();
        System.out.println(token);
        System.out.println(statusCode);
        System.out.println(tokenService.getResponseTime());
    }
}
