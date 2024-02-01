package com.trello.steps.hooks.features;

import api.methods.APIBoardMethod;

import api.methods.APICardsMethods;
import api.methods.http.HttpResponse;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.restassured.response.Response;
import org.testng.Assert;
import utils.LoggerManager;

import java.util.List;
import java.util.Map;
import java.util.Objects;


public class CardsFeatureHook {

    private final HttpResponse response;
    private static final LoggerManager log = LoggerManager.getInstance();
    private static String statusOk = "200";

    private static String idList;
    private static String idBoard;

    public CardsFeatureHook(HttpResponse response){
        this.response = response;
    }



    @Before("@CreateCard or @GetCard or @UpdateCardName or @DeleteCard")
    public void createBoardAndList(){
        String boardName = "BoardName";
        String listName = "ListName";
        Response board= APIBoardMethod.post(boardName);
        idBoard = board.jsonPath().getString("id");
        Response list = APIBoardMethod.createList(listName, idBoard);
        idList = list.jsonPath().getString("id");
        if (list != null){
            log.info("Card and List Created");
            response.setResponse(list);
        } else {
            Assert.fail("Something went Wrong, Board or List was not created");
            log.error("Board or List was not created");
        }
    }

    @Before("@GetCard or @UpdateCardName or @DeleteCard")
    public void createCard(){
        String name = "TestCard";
        Response card = APICardsMethods.post(idList, name);
        if(card != null){
            log.info("The Card was created");
            response.setResponse(card);
        } else{
            Assert.fail("Error to Create a Card");
            log.error("The Card was not created");
        }
    }


    @After("@CreateCard or @GetCard or @UpdateCardName or @DeleteCard")
    public void deleteBoardWitCard(){
        String board = APIBoardMethod.deleteBoard(idBoard);
        if (Objects.equals(board, statusOk)){
            log.info("The Board was deleted");
        } else {
            Assert.fail("Something went Wrong");
            log.error("The Board was not deleted");
        }
    }
}