package utils;

import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.Logger;

import java.io.File;

public class LoggerManager {
    private Logger logger;
    private static LoggerManager instance;

    private LoggerManager(){
        initialize();
    }

    public static LoggerManager getInstance(){
        if(instance == null){
            instance = new LoggerManager();
        }
        return instance;
    }

    private void initialize(){
        LoggerContext context = (LoggerContext) LogManager.getContext(false);
        File file = new File("log42.properties");
        context.setConfigLocation(file.toURI());
        logger = LogManager.getLogger(LogManager.class);
    }

    public void logMessage(String type, String message){
        switch (type){
            case "debug": getInstance().logger.debug(message);
            break;
            case "error": getInstance().logger.error(message);
            break;
            case "info": getInstance().logger.info(message);
            break;
            case "warn": getInstance().logger.warn(message);
            break;
        }
    }

    public void debug(String message){
        logMessage("debug", message);
    }
    public void error(String message){
        logMessage("error", message);
    }
    public void info(String message){
        logMessage("info", message);
    }
    public void warn(String message){ logMessage("warn", message);}

    public void logRequestAndResponse(String method, String endpoint, Response response) {
        LoggerManager.getInstance().info(String.format(method + " request to " + endpoint + " Response code: " + response.getStatusCode()));
        LoggerManager.getInstance().debug("Response details: " + response.getBody().asString());
    }
}
