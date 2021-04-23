package com.dropwizard.core.repositories.impl;

import com.dropwizard.core.mapper.GameHistoryMapper;
import com.dropwizard.core.models.Game;
import com.dropwizard.core.models.GameHistory;
import com.dropwizard.core.repositories.GameHistoryRepository;
import com.dropwizard.db.MongoDBManaged;
import com.google.inject.Inject;
import com.mongodb.client.MongoCursor;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class GameHistoryRepositoryImpl extends MongoBaseRepositoryImpl<GameHistory> implements GameHistoryRepository {

    private MongoDBManaged mongoDBManaged;
    private GameHistoryMapper gameHistoryMapper;

    @Inject
    public GameHistoryRepositoryImpl(MongoDBManaged mongoDBManaged, GameHistoryMapper gameHistoryMapper) {
        super(GameHistory.getCollectionName(), mongoDBManaged, gameHistoryMapper);
        this.mongoDBManaged = mongoDBManaged;
        this.gameHistoryMapper = gameHistoryMapper;
    }

    /**
     * Get the list of the game history which belong to the user and have status IN PROGRESS.
     * @param userId
     * @return
     */
    @Override
    public List<GameHistory> getInProgressGameHistoryByUserId(String userId) {
        Document query = new Document("userId", userId)
                              .append("game.status", Game.GameStatus.IN_PROGRESS.toString())
                              .append("deleted", false);
        final MongoCursor<Document> documents = getCollection().find(query).iterator();
        final List<GameHistory> entities = new ArrayList<>();

        try {
            while (documents.hasNext()) {
                final Document document = documents.next();
                entities.add(gameHistoryMapper.map(document));
            }
        } finally {
            documents.close();
        }
        return entities;
    }
}
