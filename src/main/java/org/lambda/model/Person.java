package org.lambda.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import org.bson.types.ObjectId;
import org.lambda.utils.ObjectIdDeserializer;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Person {

    @JsonProperty("_id")
    @JsonDeserialize(using = ObjectIdDeserializer.class)
    private ObjectId id;
    private String name;
    private String lastName;
    private String city;
    private String state;
    private String country;
    private String phone;
    private String identificationId;
}
