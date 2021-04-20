package com.dropwizard.core.services.impl;

import com.dropwizard.api.requests.GameHistoryAddRequest;
import com.dropwizard.core.models.Game;
import com.dropwizard.core.models.GameHistory;
import com.dropwizard.core.repositories.GameHistoryRepository;
import com.dropwizard.core.services.GameHistoryService;
import com.dropwizard.core.services.GameService;
import com.google.inject.Inject;

import java.util.List;

public class GameHistoryServiceImpl implements GameHistoryService {

    private GameHistoryRepository gameHistoryRepository;
    private GameService gameService;

    @Inject
    public GameHistoryServiceImpl(GameHistoryRepository gameHistoryRepository, GameService gameService) {
        this.gameHistoryRepository = gameHistoryRepository;
        this.gameService = gameService;
    }

    @Override
    public List<GameHistory> getInProgressGameHistoryByUserId(String userId) {
        return gameHistoryRepository.getInProgressGameHistoryByUserId(userId);
    }

    @Override
    public GameHistory getGameHistoryById(String id) {
        return gameHistoryRepository.getById(id);
    }

    @Override
    public GameHistory addGameHistory(GameHistoryAddRequest gameHistoryRequest) {
        Integer numRows = gameHistoryRequest.getNumRows() > Game.MAX_NUM_ROWS? Game.MAX_NUM_ROWS : gameHistoryRequest.getNumRows();
        Integer numColumns = gameHistoryRequest.getNumRows() > Game.MAX_NUM_COLUMNS? Game.MAX_NUM_COLUMNS : gameHistoryRequest.getNumRows();
        Integer numMines = gameHistoryRequest.getNumMines() > Game.MAX_NUM_ROWS? Game.MAX_NUM_ROWS : gameHistoryRequest.getNumRows();
        Integer numFlags = calculateFlagNumber();

        Game game = gameService.generateGame(numRows, numColumns, numMines, numFlags);
        Integer gameNumber = getNextGameNumber();

        GameHistory gameHistory = new GameHistory();
        gameHistory.prepare();
        gameHistory.setUserId(gameHistoryRequest.getUserId());
        gameHistory.setGame(game);
        gameHistory.setGameNumber(gameNumber);

        return gameHistoryRepository.save(gameHistory);
    }

    private Integer calculateFlagNumber() {
        return 40;
    }

    private Integer getNextGameNumber() {
        return 1;
    }

    @Override
    public GameHistory updateGameHistory(String id, GameHistory gameHistory) {
        return gameHistoryRepository.update(id, gameHistory);
    }

    @Override
    public void deleteGameHistory(String id) {
        gameHistoryRepository.removeById(id);
    }
}
