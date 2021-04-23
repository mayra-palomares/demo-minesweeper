package com.dropwizard.core.services.impl;

import com.dropwizard.api.requests.GameMoveRequest;
import com.dropwizard.core.models.Cell;
import com.dropwizard.core.models.Game;
import com.dropwizard.core.services.BoardService;
import com.dropwizard.core.services.GameService;
import com.google.inject.Inject;

public class GameServiceImpl implements GameService {

    private BoardService boardService;

    @Inject
    public GameServiceImpl(BoardService boardService){
        this.boardService = boardService;
    }

    @Override
    public Game generateGame(Integer numRows, Integer numColumns, Integer numMines, Integer numFlags) {
        Cell[][] board = boardService.generateBoard(numRows, numColumns, numMines);

        Game game = new Game();
        game.setTimer(new Long(0));
        game.setStatus(Game.GameStatus.CREATED);
        game.setNumRows(numRows);
        game.setNumColumns(numColumns);
        game.setNumMines(numMines);
        game.setNumFlags(numFlags);
        game.setBoard(board);
        return game;
    }

    @Override
    public void makeMove(Game game, GameMoveRequest request) {
        boardService.makeMove(game, request);
    }
}
