package com.dropwizard.core.services;

import com.dropwizard.api.requests.GameHistoryAddRequest;
import com.dropwizard.api.requests.GameMoveRequest;
import com.dropwizard.core.models.GameHistory;

import java.util.List;

public interface GameHistoryService {

    List<GameHistory> getInProgressGameHistoryByUserId(String userId);

    GameHistory getGameHistoryById(String gameHistoryId);

    GameHistory addGameHistory(String userId, GameHistoryAddRequest gameHistoryRequest) throws Exception;

    GameHistory updateGameHistory(String userId, String gameHistoryId, GameHistory gameHistory);

    void deleteGameHistory(String userId, String gameHistoryId);

    GameHistory makeGameMove(String gameHistoryId, GameMoveRequest request);
}
