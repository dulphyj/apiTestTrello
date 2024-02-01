package api.methods;

import api.APIManager;
import framework.CredentialsManager;
import io.restassured.response.Response;
import utils.LoggerManager;

import java.util.HashMap;
import java.util.Map;

public class APIBoardMethod implements APIPostMethod{
    public static final LoggerManager log = LoggerManager.getInstance();
    public static final CredentialsManager credentialsManager = CredentialsManager.getInstance();
    public static final APIManager apiManager = APIManager.getInstance();

    private static String key = credentialsManager.getKey();
    private static String token = credentialsManager.getToken();
    private static final int status = 200;

    public static Response post(String name){
        String postBoardEndpoint = credentialsManager.postBoardEndpoint();
        Map<String, Object> body = new HashMap<>();
        body.put("name", name);
        Response response = apiManager.postBoards(postBoardEndpoint, body, key, token);
        if(response.statusCode() == status){
            log.info("The Board was created");
            return response;
        } else {
            log.error("The Board was not created");
            return null;
        }
    }

    public static Response createList(String name, String idBoard){
        String postBoardEndpoint = credentialsManager.createAListOnABoard().replace("<id>", idBoard);
        Map<String, Object> body = new HashMap<>();
        body.put("name", name);
        Response response = apiManager.postBoards(postBoardEndpoint, body, key, token);
        if (response.statusCode() == status){
            log.info("The List was created");
            return response;
        } else{
            log.error("The List was not created, please check Id Board");
            return null;
        }
    }

    public static String deleteBoard(String idBoard){
        String deleteByIdBoard = credentialsManager.getBoardByIdEndpoint().replace("<id>", idBoard);
        Response response = apiManager.deleteCards(deleteByIdBoard, key, token);
        if(response.statusCode()==status){
            log.info("The Board was deleted");
            return String.valueOf(response.statusCode());
        } else{
            log.error("The Board was not deleted, please check th Id Board");
            return  null;
        }
    }
}
