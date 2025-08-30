package org.lambda.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.bson.types.ObjectId;

import java.io.IOException;

public class ObjectIdDeserializer extends JsonDeserializer<ObjectId> {

    @Override
    public ObjectId deserialize(JsonParser p, DeserializationContext ctx) throws IOException {
        JsonNode node = p.getCodec().readTree(p);
        if(node.has("$oid")) {
            return new ObjectId(node.get("$oid").asText());
        }
        return new ObjectId(node.asText());
    }
}
