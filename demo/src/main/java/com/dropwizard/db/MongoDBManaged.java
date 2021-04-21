package com.dropwizard.db;

import com.google.inject.Inject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import io.dropwizard.lifecycle.Managed;
import org.bson.Document;


public class MongoDBManaged implements Managed {
    private MongoClient mongoClient;
    private String database;

    @Inject
    public MongoDBManaged(MongoClient mongoClient, String database) {
        this.setMongoClient(mongoClient);
        this.setDatabase(database);
    }

    @Override
    public void start() throws Exception {
    }

    @Override
    public void stop() throws Exception {
        getMongoClient().close();
    }

    public MongoClient getMongoClient() {
        return mongoClient;
    }

    public void setMongoClient(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    public MongoCollection<Document> getCollectionByName(String collectionName) {
        return mongoClient.getDatabase(this.getDatabase()).getCollection(collectionName);
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }
}
