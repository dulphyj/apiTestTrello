package com.trello.steps;

import api.APIManager;
import api.methods.APIAuthorizationMethods;
import framework.CredentialsManager;
import io.cucumber.java.en.Given;
import io.restassured.response.Response;
import org.testng.Assert;

public class AuthStepdefs {
    private final CredentialsManager credentialsManager = CredentialsManager.getInstance();


    @Given("The user {string} is authorized to make a request.")
    public void theUserIsAuthorizedToMakeARequest(String username) {
        String token = credentialsManager.getToken();
        String response = APIAuthorizationMethods.getAuth(token);
        Assert.assertEquals(response, username, "The user is not the same");
    }
}
