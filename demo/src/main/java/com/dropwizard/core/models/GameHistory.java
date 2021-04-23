package com.dropwizard.core.models;

public class GameHistory extends BaseModel{
    public static final String COLLECTION_GAME_HISTORY = "game_history";

    private String userId;
    private Integer gameNumber;
    private Game game;

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public String getUserId() {
        return userId;
    }

    public static String getCollectionName() {
        return COLLECTION_GAME_HISTORY;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getGameNumber() {
        return gameNumber;
    }

    public void setGameNumber(Integer gameNumber) {
        this.gameNumber = gameNumber;
    }
}
