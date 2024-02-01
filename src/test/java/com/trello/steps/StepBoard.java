package com.trello.steps;

import api.methods.http.HttpResponse;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.util.List;
import java.util.Map;

public class StepBoard {
    private final HttpResponse response;
    public StepBoard(HttpResponse response){
        this.response = response;
    }
    @When("The user is on the Trello board BoardName and list ListName")
    public void theUserIsOnTheTrelloBoardAndListWithTheFollowingParams() {
        Assert.assertNotNull(response);
    }
}
