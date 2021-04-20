package com.dropwizard.core.services;

import com.dropwizard.core.models.Cell;

public interface BoardService {

    Cell[][] generateBoard(Integer numRows, Integer numColumns, Integer numMines);
}
