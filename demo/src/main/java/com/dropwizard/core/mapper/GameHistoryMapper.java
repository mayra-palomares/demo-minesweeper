package com.dropwizard.core.mapper;

import com.dropwizard.core.models.GameHistory;
import com.google.inject.Inject;
import org.bson.Document;

public class GameHistoryMapper implements BaseMapper<GameHistory> {

    private GameMapper gameMapper;

    @Inject
    public GameHistoryMapper(GameMapper gameMapper){
        this.gameMapper = gameMapper;
    }

    @Override
    public GameHistory map(Document document) {
        GameHistory gameHistory = new GameHistory();
        gameHistory.setId(document.getObjectId("_id").toString());
        gameHistory.setUserId(document.getString("userId"));
        gameHistory.setCreated(document.getLong("created"));
        gameHistory.setModified(document.getLong("modified"));
        gameHistory.setDeleted(document.getBoolean("deleted"));
        gameHistory.setGame(gameMapper.map((Document) document.get("game")));
        gameHistory.setGameNumber(document.getInteger("gameNumber"));

        return gameHistory;
    }

    @Override
    public Document map(GameHistory gameHistory) {
        Document document = new Document("userId", gameHistory.getUserId())
                                .append("created", gameHistory.getCreated())
                                .append("modified", gameHistory.getModified())
                                .append("deleted", gameHistory.isDeleted())
                                .append("gameNumber", gameHistory.getGameNumber())
                                .append("game", gameMapper.map(gameHistory.getGame()));
        return document;
    }
}
