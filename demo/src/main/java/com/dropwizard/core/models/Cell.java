package com.dropwizard.core.models;

public class Cell {
    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public boolean isHasMine() {
        return hasMine;
    }

    public void setHasMine(boolean hasMine) {
        this.hasMine = hasMine;
    }

    public enum CellStatus {
        EMPTY,
        MINE,
        RED_FLAG
    }

    private CellStatus status = CellStatus.EMPTY;
    private boolean visited = false;
    private boolean hasMine = false;

    public CellStatus getStatus() {
        return status;
    }

    public void setStatus(CellStatus status) {
        this.status = status;
    }
}
