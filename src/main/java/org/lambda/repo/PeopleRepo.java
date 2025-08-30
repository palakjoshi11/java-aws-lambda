package org.lambda.repo;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.List;
import java.util.Optional;


@Slf4j
public class PeopleRepo {

    private final MongoCollection<Document> collection;

    public PeopleRepo(MongoDatabase db) {
        this.collection = db.getCollection("people");
    }

    public Optional<Document> getDataById(List<Bson> filters) {
        Bson combinedFilter = Filters.and(filters);
        return Optional.ofNullable(collection.find(combinedFilter).first());
    }

    public Optional<Object> insertData(Document doc) {
        return Optional.of(collection.insertOne(doc));
    }

}
