package com.dropwizard.api.requests;

public class GameHistoryAddRequest {  
    private Integer numRows;
    private Integer numColumns;
    private Integer numMines;

    public Integer getNumRows() {
        return numRows;

    public void setNumRows(Integer numRows) {
        this.numRows = numRows;
    }

    public Integer getNumColumns() {
        return numColumns;
    }

    public void setNumColumns(Integer numColumns) {
        this.numColumns = numColumns;
    }

    public Integer getNumMines() {
        return numMines;
    }

    public void setNumMines(Integer numMines) {
        this.numMines = numMines;
    }
}
