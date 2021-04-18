package com.dropwizard.db;

import com.mongodb.client.MongoClient;
import io.dropwizard.lifecycle.Managed;

public class MongoDBManaged implements Managed {
    private MongoClient mongoClient;

    public MongoDBManaged(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    @Override
    public void start() throws Exception {
    }

    @Override
    public void stop() throws Exception {
        mongoClient.close();
    }
}
