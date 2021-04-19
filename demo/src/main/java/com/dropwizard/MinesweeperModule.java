package com.dropwizard;

import com.dropwizard.db.MongoDBManaged;
import com.dropwizard.db.configuration.MongoDBFactoryConnection;
import ru.vyarus.dropwizard.guice.module.support.DropwizardAwareModule;

public class MinesweeperModule extends DropwizardAwareModule<MinesweeperConfiguration> {

    @Override
    public void configure() {
        MinesweeperConfiguration config = configuration();
        MongoDBFactoryConnection mongoConnection = new MongoDBFactoryConnection(config.getMongoServer());
        MongoDBManaged mongoDBManaged = new MongoDBManaged(mongoConnection.getClient(), config.getMongoServer().getDatabase());
        bind(MongoDBManaged.class).toInstance(mongoDBManaged);
    }
}
