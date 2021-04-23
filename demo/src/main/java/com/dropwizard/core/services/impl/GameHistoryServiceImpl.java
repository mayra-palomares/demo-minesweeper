package com.dropwizard.core.services.impl;

import com.dropwizard.api.requests.GameHistoryAddRequest;
import com.dropwizard.api.requests.GameMoveRequest;
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
    public GameHistory getGameHistoryById(String gameHistoryId) {
        return gameHistoryRepository.getById(gameHistoryId);
    }

    /**
     * Create a new game and save it in the users game history
     * @param userId
     * @param gameHistoryRequest
     * @return
     * @throws Exception
     */
    @Override
    public GameHistory addGameHistory(String userId, GameHistoryAddRequest gameHistoryRequest) throws Exception {

        if(gameHistoryRequest == null){
            throw new Exception("Error creating a new game");
        }

        Integer numRows = gameHistoryRequest.getNumRows() > Game.MAX_NUM_ROWS? Game.MAX_NUM_ROWS : gameHistoryRequest.getNumRows();
        Integer numColumns = gameHistoryRequest.getNumRows() > Game.MAX_NUM_COLUMNS? Game.MAX_NUM_COLUMNS : gameHistoryRequest.getNumRows();
        Integer numMines = gameHistoryRequest.getNumMines() > Game.MAX_NUM_ROWS? Game.MAX_NUM_ROWS : gameHistoryRequest.getNumRows();
        Integer numFlags = calculateFlagNumber();

        Game game = gameService.generateGame(numRows, numColumns, numMines, numFlags);
        Integer gameNumber = getNextGameNumber();

        GameHistory gameHistory = new GameHistory();
        gameHistory.prepare();
        gameHistory.setUserId(userId);
        gameHistory.setGame(game);
        gameHistory.setGameNumber(gameNumber);

        return gameHistoryRepository.save(gameHistory);
    }

    /**
     * TODO: Calculate flag number with the numRows and numColumns params
     * @return
     */
    private Integer calculateFlagNumber() {
        return Game.MAX_NUM_FLAGS;
    }

    /**
     * TODO: Calculate the game number based on the user game history
     * @return
     */
    private Integer getNextGameNumber() {
        return 1;
    }

    @Override
    public GameHistory updateGameHistory(String userId, String gameHistoryId, GameHistory gameHistory) {
        GameHistory dbGameHistory = getGameHistoryById(gameHistoryId);
        if(dbGameHistory != null){
            gameHistory = gameHistoryRepository.update(gameHistoryId, gameHistory);
        }
        return gameHistory;
    }

    @Override
    public void deleteGameHistory(String userId, String gameHistoryId) {
        gameHistoryRepository.removeById(gameHistoryId);
    }

    /**
     * Make a move in the game
     * @param gameHistoryId
     * @param request
     * @return
     */
    @Override
    public GameHistory makeGameMove(String gameHistoryId, GameMoveRequest request) {
        GameHistory gameHistory = getGameHistoryById(gameHistoryId);
        if(gameHistory != null && request != null){
            Game game = gameHistory.getGame();
            gameService.makeMove(game,request);
            gameHistory.setGame(game);
            gameHistory = gameHistoryRepository.update(gameHistory.getId(), gameHistory);
        }
        return gameHistory;
    }
}
