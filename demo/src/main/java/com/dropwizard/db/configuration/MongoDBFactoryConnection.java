package com.dropwizard.db.configuration;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MongoDBFactoryConnection {

    private static final Logger LOGGER = LoggerFactory.getLogger(MongoDBFactoryConnection.class);
    private MongoConfiguration mongoConfiguration;

    public MongoDBFactoryConnection(final MongoConfiguration mongoConfiguration) {
        this.mongoConfiguration = mongoConfiguration;
    }

    public MongoClient getClient() {
        LOGGER.info("Creating mongoDB client.");
        return MongoClients.create(mongoConfiguration.getUri());
    }
}
