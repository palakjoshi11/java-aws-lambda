package org.lambda.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.lambda.ExecutionContext;
import org.lambda.RequestDispatcher;
import org.lambda.config.Config;
import org.lambda.config.ConfigLoader;
import org.lambda.repo.PeopleRepo;

import java.util.function.UnaryOperator;

@Slf4j
public class DefaultExecutionContext implements ExecutionContext, AutoCloseable {

    private final RequestDispatcher requestDispatcher;

    private final MongoClient mongoClient;

    private final MongoDatabase mongoDatabase;

    private final ObjectMapper objectMapper;
    @Getter
    private final PeopleRepo repo;

    public DefaultExecutionContext()
        {
            this(System::getenv);
        }

    DefaultExecutionContext(UnaryOperator<String> variablesSource){
        log.info("Initializing execution context..");
        // Get all env variables and load it
        ConfigLoader configLoader = new ConfigLoader();
        Config config = configLoader.loadFrom(variablesSource);
        this.mongoClient = MongoClients.create(config.getMongoUri());
        this.mongoDatabase = mongoClient.getDatabase(config.getMongoDatabase());
        this.repo = new PeopleRepo(mongoDatabase);
        this.objectMapper = new ObjectMapper();
        this.requestDispatcher = new DefaultRequestDispatcher(this,objectMapper);
        log.info("Execution context initialization successful..");
    }

    @Override
    public void close(){
        log.info("Closing execution context..");
        if(mongoClient!= null){
            try{
                mongoClient.close();
            } catch(Exception e){
                log.warn("Error while closing mongo client", e);
            }
        }
    }
    @Override
    public RequestDispatcher getRequestDispatch() {
        return requestDispatcher;
    }
}
