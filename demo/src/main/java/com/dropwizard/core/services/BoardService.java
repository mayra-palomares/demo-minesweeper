package com.dropwizard.core.services;

import com.dropwizard.api.requests.GameMoveRequest;
import com.dropwizard.core.models.Cell;
import com.dropwizard.core.models.Game;

public interface BoardService {

    Cell[][] generateBoard(Integer numRows, Integer numColumns, Integer numMines);

    void makeMove(Game game, GameMoveRequest moveRequest);
}
