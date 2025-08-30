package org.lambda.config;

import lombok.Builder;
import lombok.Getter;
/*
The usual Env variables needs to set here
 */
@Builder
@Getter
public class Config {

    private final String mongoDatabase;
    private final String mongoUri;
}
