package com.trello.steps;

import api.APIManager;
import api.methods.http.HttpResponse;
import entities.Card;
import framework.CredentialsManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.HashMap;
import java.util.Map;

public class StepCards {

    private static final CredentialsManager credentialsManager = CredentialsManager.getInstance();
    private static  final APIManager apiManager = APIManager.getInstance();

    static String key = credentialsManager.getKey();
    static String token = credentialsManager.getToken();

    private final HttpResponse httpResponse;
    private Card card;

    public StepCards(HttpResponse httpResponse){
        this.httpResponse = httpResponse;
    }

    //POST
    @And("The user creates a card with the name {string}")
    public void theUserCreatesACardWithTheName(String nameCard) {
        String createCard = credentialsManager.postCardEndpoint();
        String idList = httpResponse.getResponse().jsonPath().getString("id");
        Map<String, Object> name = new HashMap<>();
        name.put("name", nameCard);
        Response response = apiManager.postCards(createCard, name, key, token, idList);
        httpResponse.setResponse(response);
        card = httpResponse.getResponse().as(Card.class);
        String nameCardResponse = httpResponse.getResponse().jsonPath().getString("name");
        Assert.assertNotNull(nameCardResponse, "Name not found!");
        Assert.assertEquals(nameCardResponse, nameCard, "The Name is wrong!");
    }

    @Then("response should be {string}")
    public void responseShouldBe(String status) {
        String statusResponse = String.valueOf(httpResponse.getResponse().statusCode());
        Assert.assertNotNull(statusResponse, "Status Code not found!");
        Assert.assertEquals(statusResponse, status, "The Status Code is not 200 Ok!");
    }

    @And("response must be valid and has a body")
    public void responseMustBeValidAndHasABody() {
        Assert.assertNotNull(httpResponse.getResponse(), "Response not found!");
        Assert.assertNotNull(httpResponse.getResponse().getBody(), "Response has not body");
    }

    @And("name should be correct")
    public void nameShouldBeCorrect() {
        String expectedName = card.getName();
        String actualName = httpResponse.getResponse().jsonPath().getString("name");
        Assert.assertNotNull(actualName, "Name not found!");
        Assert.assertEquals(actualName, expectedName, "The names do not match!");
    }

    //GET
    @And("The user looks at a card with the name TestCard")
    public void theUserLooksAtACardWithTheNameTestCard() {
        String name = httpResponse.getResponse().jsonPath().getString("name");
        card = httpResponse.getResponse().as(Card.class);
        Assert.assertEquals(name, card.getName(), "It is not the TestCard");
    }

    @And("The user gets the card")
    public void theUserGetsTheCard() {
        String id = card.getId();
        String endpoint = credentialsManager.getCardByIdEndpoint().replace("<id>", id);
        Response response = apiManager.getCards(endpoint, key, token);
        httpResponse.setResponse(response);
        Assert.assertNotNull(httpResponse.getResponse(), "Could not GET card");
    }

    //UPDATE
    @And("The user update the name of the card to {string}")
    public void theUserUpdateTheNameOfTheCardTo(String newName) {
        String id = card.getId();
        String endpoint = credentialsManager.getCardByIdEndpoint().replace("<id>", id);
        Map<String, Object> body = new HashMap<>();
        body.put("name", newName);
        Response response = apiManager.putCards(endpoint, body, key, token);
        httpResponse.setResponse(response);
        card = httpResponse.getResponse().as(Card.class);
        Assert.assertNotNull(httpResponse, "Failed Update!");
    }

    @And("the user delete the card")
    public void theUserDeleteTheCard() {
        String id = card.getId();
        String endpoint = credentialsManager.getCardByIdEndpoint().replace("<id>", id);
        Response response = apiManager.deleteCards(endpoint, key, token);
        httpResponse.setResponse(response);
        card = httpResponse.getResponse().as(Card.class);
        Assert.assertNotNull(httpResponse, "Response not found!");
    }
}
