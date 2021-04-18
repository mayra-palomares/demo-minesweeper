package com.dropwizard;

import com.dropwizard.db.configuration.MongoConfiguration;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

public class MinesweeperConfiguration extends Configuration {
    
    private MongoConfiguration mongoServer;

    @JsonProperty("swagger")
    private SwaggerBundleConfiguration swaggerBundleConfiguration;

    public MongoConfiguration getMongoServer() {
        return mongoServer;
    }

    public void setMongoServer(MongoConfiguration mongoServer) {
        this.mongoServer = mongoServer;
    }

    public SwaggerBundleConfiguration getSwaggerBundleConfiguration() {
        return swaggerBundleConfiguration;
    }

    public void setSwaggerBundleConfiguration(SwaggerBundleConfiguration swaggerBundleConfiguration) {
        this.swaggerBundleConfiguration = swaggerBundleConfiguration;
    }
}
