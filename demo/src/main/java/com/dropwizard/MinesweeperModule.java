package com.dropwizard;

import com.dropwizard.core.mapper.*;
import com.dropwizard.core.repositories.*;
import com.dropwizard.core.repositories.impl.*;
import com.dropwizard.core.services.*;
import com.dropwizard.core.services.impl.*;
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
        bind(GameHistoryMapper.class);
        bind(GameMapper.class);

        //Repositories
        bind(UserRepository.class).to(UserRepositoryImpl.class);
        bind(GameHistoryRepository.class).to(GameHistoryRepositoryImpl.class);

        //Services
        bind(UserService.class).to(UserServiceImpl.class);
        bind(GameHistoryService.class).to(GameHistoryServiceImpl.class);
        bind(GameService.class).to(GameServiceImpl.class);
        bind(BoardService.class).to(BoardServiceImpl.class);
    }
}
