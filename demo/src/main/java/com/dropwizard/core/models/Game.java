package com.dropwizard.core.models;

public class Game {
    public final static Integer MAX_NUM_ROWS = 100;
    public final static Integer MAX_NUM_COLUMNS = 100;
    public final static Integer MAX_NUM_FLAGS = 40;
    public final static Integer MAX_NUM_MINES = 100;

    public Integer getNumFlags() {
        return numFlags;
    }

    public void setNumFlags(Integer numFlags) {
        this.numFlags = numFlags;
    }

    public enum GameStatus {
        CREATED,
        IN_PROGRESS,
        COMPLETED,
        FAIL
    }

    private Integer numRows;
    private Integer numColumns;
    private Integer numMines;
    private Integer numFlags;
    private Cell[][] board;
    private GameStatus status = GameStatus.CREATED;
    private Long timer;

    public Integer getNumRows() {
        return numRows;
    }

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

    public Cell[][] getBoard() {
        return board;
    }

    public void setBoard(Cell[][] board) {
        this.board = board;
    }

    public GameStatus getStatus() {
        return status;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }

    public Long getTimer() {
        return timer;
    }

    public void setTimer(Long timer) {
        this.timer = timer;
    }
}
