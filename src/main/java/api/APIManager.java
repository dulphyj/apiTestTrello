package api;

import framework.CredentialsManager;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utils.LoggerManager;

import java.util.Map;

public class APIManager {
    
    private static final LoggerManager log = LoggerManager.getInstance();
    private static APIManager instance;
    
    private APIManager(){
        initialize();
    }

    public static APIManager getInstance(){
        if(instance == null){
            instance = new APIManager();
        }
        return instance;
    }

    private void initialize() {
        log.info("Initializing API Manager");
        RestAssured.baseURI = CredentialsManager.getInstance().getBaseURL();
        RestAssured.basePath = CredentialsManager.getInstance().getBasePath();
    }

    //token
    public Response getToken(String endpoint, ContentType contentType, Object object, String token){
        log.debug("Sending GET request to: " + endpoint);
        Response response = RestAssured.given()
                .contentType(contentType)
                .queryParam("token", token)
                .body(object)
                .get(endpoint);
        log.logRequestAndResponse("GET", endpoint, response);
        return response;
    }

    //Cards
    public Response getCards(String endpoint, String key, String token) {
        log.debug("Sending GET request to: " + endpoint);
        Response response = RestAssured.given()
                .queryParam("key", key)
                .queryParam("token", token)
                .get(endpoint);
        log.logRequestAndResponse("GET", endpoint, response);
        return response;
    }

    public Response deleteCards(String endpoint, String key, String token) {
        log.debug("Sending DELETE request to: " + endpoint);
        Response response = RestAssured.given()
                .queryParam("key", key)
                .queryParam("token", token)
                .delete(endpoint);
        log.logRequestAndResponse("DELETE", endpoint, response);
        return response;
    }

    public Response postCards(String endpoint, Map<String, Object> jsonBody, String key, String token, String idList) {
        log.debug("Sending POST request to: " + endpoint);
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(jsonBody)
                .queryParam("idList", idList)
                .queryParam("key", key)
                .queryParam("token", token)
                .post(endpoint);
        log.logRequestAndResponse("POST", endpoint, response);
        return response;
    }

    public Response putCards(String endpoint, Map<String, Object> jsonBody, String key, String token){
        log.debug("Sending PUT request to: " + endpoint);
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(jsonBody)
                .queryParam("key", key)
                .queryParam("token", token)
                .put(endpoint);
        log.logRequestAndResponse("PUT", endpoint, response);
        return response;
    }

    //Boards
    public Response postBoards(String endpoint, Map<String, Object> jsonBody, String key, String token) {
        log.debug("Sending POST request to: " + endpoint);
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(jsonBody)
                .queryParam("key", key)
                .queryParam("token", token)
                .post(endpoint);
        log.logRequestAndResponse("POST", endpoint, response);
        return response;
    }
}
