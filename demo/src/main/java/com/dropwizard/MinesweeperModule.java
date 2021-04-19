package com.dropwizard;

import com.dropwizard.core.mapper.UserMapper;
import com.dropwizard.core.repositories.UserRepository;
import com.dropwizard.core.repositories.impl.UserRepositoryImpl;
import com.dropwizard.core.services.UserService;
import com.dropwizard.core.services.impl.UserServiceImpl;
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

        //Mappers
        bind(UserMapper.class);

        //Repositories
        bind(UserRepository.class).to(UserRepositoryImpl.class);

        //Services
        bind(UserService.class).to(UserServiceImpl.class);
    }
}
