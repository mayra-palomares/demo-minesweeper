package com.dropwizard.core.services.impl;

import com.dropwizard.core.models.Cell;
import com.dropwizard.core.services.BoardService;
import com.google.inject.Inject;

public class BoardServiceImpl implements BoardService {

    @Inject
    public BoardServiceImpl(){}

    @Override
    public Cell[][] generateBoard(Integer numRows, Integer numColumns, Integer numMines) {
        Cell[][] board = initBoard(numRows, numColumns);
        populateWithMines(board);

        return board;
    }

    private Cell[][] initBoard(Integer numRows, Integer numColumns) {
        Cell[][] board = new Cell[numRows][numColumns];
        for(int i=0; i < numRows; i++){
            for(int j=0; j < numColumns; j++){
                board[i][j] = new Cell();
            }
        }
        return board;
    }

    private void populateWithMines(Cell[][] board){
    }
}
