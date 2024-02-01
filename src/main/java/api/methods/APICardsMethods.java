package api.methods;

import api.APIManager;
import framework.CredentialsManager;
import io.restassured.response.Response;
import utils.LoggerManager;

import java.util.HashMap;
import java.util.Map;

public class APICardsMethods implements APIGetMethod, APIDeleteMethod, APIPostMethod, APIPutMethod{

    public static final LoggerManager log = LoggerManager.getInstance();
    public static final CredentialsManager credentialsManager = CredentialsManager.getInstance();
    public static final APIManager apiManager = APIManager.getInstance();

    private static final int status = 200;

    private static String key = credentialsManager.getKey();
    private static String token = credentialsManager.getToken();

    public static Response getById(String idCard, String key, String token){
        String getByIdEndpoint = credentialsManager.getCardByIdEndpoint().replace("<id>", idCard);
        Response response = apiManager.getCards(getByIdEndpoint, key, token);
        if(response.statusCode() == status){
            log.info("Card found");
            return response;
        } else{
            log.error("ID cannot be found or does not exist");
            return null;
        }
    }

    public static String deleteById(String idCard){
        String deleteByIdEndpoint = credentialsManager.getCardByIdEndpoint().replace("<id>", idCard);
        Response response = apiManager.deleteCards(deleteByIdEndpoint, key, token);
        if(response.statusCode() == status){
            log.info("Deleted Card");
            return String.valueOf(response.statusCode());
        } else {
            log.error("Card cannot found or does no exist");
            return null;
        }
    }

    public static Response post(String idList, String cardName){
        String postCard = credentialsManager.postCardEndpoint();
        Map<String, Object> body = new HashMap<>();
        body.put("name", cardName);
        Response response = apiManager.postCards(postCard, body, key, token, idList);
        if (response.statusCode() == status){
            log.info("Created Card");
            return response;
        } else {
            log.error("Could not create card, please check the ID List.");
            return null;
        }
    }

    public static Response putById(String idCard, String cardName){
        String putCard = credentialsManager.getCardByIdEndpoint().replace("<id>", idCard);
        Map<String, Object> body = new HashMap<>();
        body.put("name", cardName);
        Response response = apiManager.putCards(putCard, body, key, token);
        if(response.statusCode() == status){
            log.info("Put was successfully");
            return response;
        } else {
            log.error("Could not change name, please check the Id Card");
            return null;
        }
    }
}
