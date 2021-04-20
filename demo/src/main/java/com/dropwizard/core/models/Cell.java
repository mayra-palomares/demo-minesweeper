package com.dropwizard.core.models;

public class Cell {
    public enum CellStatus {
        EMPTY,
        MINE,
        RED_FLAG
    }

    private CellStatus status = CellStatus.EMPTY;

    public CellStatus getStatus() {
        return status;
    }

    public void setStatus(CellStatus status) {
        this.status = status;
    }
}
