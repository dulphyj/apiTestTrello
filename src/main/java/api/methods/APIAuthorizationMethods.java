package api.methods;

import api.APIManager;
import framework.CredentialsManager;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utils.LoggerManager;

import java.util.HashMap;
import java.util.Map;

public class APIAuthorizationMethods {
    private static final LoggerManager log = LoggerManager.getInstance();
    private static final APIManager apiManager = APIManager.getInstance();
    private static final CredentialsManager credentialManager = CredentialsManager.getInstance();

    public static String getAuth(String token){
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("key", credentialManager.getKey());
        jsonAsMap.put("token", credentialManager.getToken());
        String tokenEndpoint = credentialManager.getTokenId().replace("<id>", token);
        Response response = apiManager.getToken(tokenEndpoint, ContentType.JSON, jsonAsMap, token);
        if (response.statusCode() == 200){
            return response.jsonPath().getString("username");
        } else {
            log.error("The Token is not valid");
            return null;
        }
    }
}
