package org.lambda.config;

import java.util.function.UnaryOperator;

public class ConfigLoader {

    public Config loadFrom(UnaryOperator<String> variableSource){
        try {
            return Config.builder()
                    .mongoDatabase(variableSource.apply("MONGO_DATABASE"))
                    .mongoUri(variableSource.apply("MONGO_URI")).build();
        } catch(Exception e){
            throw e;
        }
    }
}
