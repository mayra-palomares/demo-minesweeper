package com.dropwizard.api.requests;

import com.dropwizard.core.models.Cell;

public class GameMoveRequest { 
    private int rowIndex;
    private int columnIndex;
    private Cell.CellMove move;
    private Long timer;

    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public void setColumnIndex(int columnIndex) {
        this.columnIndex = columnIndex;
    }

    public Cell.CellMove getMove() {
        return move;
    }

    public void setMove(Cell.CellMove move) {
        this.move = move;
    }

    public Long getTimer() {
        return timer;
    }

    public void setTimer(Long timer) {
        this.timer = timer;
    }
}
