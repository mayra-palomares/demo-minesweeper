package com.dropwizard.core.models;

public class Cell {

    public enum CellStatus {
        EMPTY,
        MINE,
        RED_FLAG,
        VISITED
    }

    public enum CellMove {
        VISIT,
        PUT_FLAG,
        REMOVE_FLAG
    }

    private CellStatus status = CellStatus.EMPTY;
    private boolean visited = false;
    private boolean hasMine = false;
    private Integer numNeighborsMines = 0;

    public CellStatus getStatus() {
        return status;
    }

    public void setStatus(CellStatus status) {
        this.status = status;
    }

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

    public Integer getNumNeighborsMines() {
        return numNeighborsMines;
    }

    public void setNumNeighborsMines(Integer numNeighborsMines) {
        this.numNeighborsMines = numNeighborsMines;
    }
}
