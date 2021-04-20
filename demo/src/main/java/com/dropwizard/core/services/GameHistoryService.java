package com.dropwizard.core.services;

import com.dropwizard.api.requests.GameHistoryAddRequest;
import com.dropwizard.core.models.GameHistory;

import java.util.List;

public interface GameHistoryService {

    List<GameHistory> getInProgressGameHistoryByUserId(String userId);

    GameHistory getGameHistoryById(String id);

    GameHistory addGameHistory(GameHistoryAddRequest gameHistoryRequest);

    GameHistory updateGameHistory(String id, GameHistory gameHistory);

    void deleteGameHistory(String id);
}
