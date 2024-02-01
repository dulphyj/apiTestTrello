package framework;

import utils.LoggerManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class CredentialsManager {

    private Properties properties;
    private static final LoggerManager log = LoggerManager.getInstance();
    private static final String envFilePath = System.getProperty("user.dir") + File.separator + "environments.properties";
    private static final String keyPath = System.getProperty("user.dir") + File.separator + "key.properties";
    private static final String apiPath = System.getProperty("user.dir") + File.separator + "api.properties";
    private static CredentialsManager instance;

    private String envId;

    private CredentialsManager(){
        initialize();
    }

    private void initialize() {
        log.info("Reading credentials");
        String trelloEnvironmentId = System.getProperty("envId");
        if ((trelloEnvironmentId == null) || (trelloEnvironmentId.isEmpty())) {
            envId = "qa";
        } else {
            envId = trelloEnvironmentId;
        }
        log.info("Trello Environment Id --> " + envId);

        properties = new Properties();
        try ( FileInputStream envFileInputStream = new FileInputStream(envFilePath);
                FileInputStream keyFileInputStream = new FileInputStream(keyPath);
                FileInputStream apiFileInputStream = new FileInputStream(apiPath)) {
                Properties envProperties = new Properties();
                Properties keyProperties = new Properties();
                Properties apiProperties = new Properties();
                envProperties.load(envFileInputStream);
                keyProperties.load(keyFileInputStream);
                apiProperties.load(apiFileInputStream);
                properties.putAll(envProperties);
                properties.putAll(keyProperties);
                properties.putAll(apiProperties);
        } catch (IOException e){
            log.error("unable to load properties file");
        }
    }

    public static CredentialsManager getInstance() {
        if(instance == null){
            instance = new CredentialsManager();
        }
        return instance;
    }

    public String getEnvId() {return envId;}
    private String getEnvironmentSetting(String setting){return (String) getInstance().properties.get(setting);}

    public String getBaseURL() {
        return getEnvironmentSetting("envId.baseURL".replace("envId", getEnvId().toLowerCase()));
    }
    public String getBasePath() {return getEnvironmentSetting("qa.basePath");}
    public String getKey(){return getEnvironmentSetting("api.key");}
    public String getToken(){return getEnvironmentSetting("api.token");}
    public String getCardByIdEndpoint(){return getEnvironmentSetting("api.endpoint.cardById");}
    public String postCardEndpoint(){return getEnvironmentSetting("api.endpoint.postsCard");}
    public String postBoardEndpoint(){return getEnvironmentSetting("api.endpoint.postsBoard");}
    public String createAListOnABoard(){return getEnvironmentSetting("api.endpoint.postListOnABoard");}
    public String getListByIdEndpoint(){return getEnvironmentSetting("api.endpoint.listById");}
    public String getBoardByIdEndpoint(){return getEnvironmentSetting("api.endpoint.boardById");}
    public String getTokenId(){return  getEnvironmentSetting("api.endpoint.tokenById");}
}
