package com.dropwizard.core.services;

import com.dropwizard.api.requests.GameMoveRequest;
import com.dropwizard.core.models.Game;

public interface GameService {

    Game generateGame(Integer numRows, Integer numColumns, Integer numMines, Integer numFlags);

    void makeMove(Game game, GameMoveRequest request);
}
