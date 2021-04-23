package com.dropwizard.core.repositories.impl;

import com.dropwizard.core.mapper.UserMapper;
import com.dropwizard.core.models.User;
import com.dropwizard.core.repositories.UserRepository;
import com.dropwizard.db.MongoDBManaged;
import com.google.inject.Inject;
import org.bson.Document;

import java.util.Optional;

public class UserRepositoryImpl extends MongoBaseRepositoryImpl<User> implements UserRepository{

    private MongoDBManaged mongoDBManaged;
    private UserMapper userMapper;

    @Inject
    public UserRepositoryImpl(MongoDBManaged mongoDBManaged, UserMapper userMapper) {
        super(User.getCollectionName(), mongoDBManaged, userMapper);
        this.mongoDBManaged = mongoDBManaged;
        this.userMapper = userMapper;
    }

    /**
     * Get a user by username

     */
    @Override
    public User findUserByUsername(String username) {
        Document query = new Document("username", username);
        Optional<Document> document = Optional.ofNullable(getCollection().find(query).first());
        return document.isPresent() ? userMapper.map(document.get()) : null;
    }
}
