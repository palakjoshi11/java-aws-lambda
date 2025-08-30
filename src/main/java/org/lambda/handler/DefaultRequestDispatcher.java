package org.lambda.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.bson.conversions.Bson;
import org.lambda.RequestDispatcher;
import org.lambda.model.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.mongodb.client.model.Filters.eq;

@Slf4j
public class DefaultRequestDispatcher implements RequestDispatcher {

    private final DefaultExecutionContext context;
    private final ObjectMapper objectMapper;

    public DefaultRequestDispatcher(DefaultExecutionContext context, ObjectMapper objectMapper) {
        this.context = context;
        this.objectMapper = objectMapper;
    }

    @Override
    public void handler(Map<String, Object> event, Context awsContext) {

        try {
            Person person = mapEvent(event);
            List<Bson> filters = new ArrayList<>();
            filters.add(eq("identificationId", person.getIdentificationId()));
            filters.add(eq("_id", person.getId()));
            var identificationDoc = context.getRepo().getDataById(filters);
            identificationDoc.ifPresentOrElse( identification -> log.info("identificationDoc: {}", identification),
                    () -> log.error("Identification details are not present"));
        } catch (JsonProcessingException je){
            log.error("Json Parsing error: {}",je.getMessage());
        }
    }

    private Person mapEvent(Map<String, Object> event) throws JsonProcessingException {
        String json = objectMapper.writeValueAsString(event);
        return objectMapper.readValue(json, new TypeReference<>(){});
    }
}
