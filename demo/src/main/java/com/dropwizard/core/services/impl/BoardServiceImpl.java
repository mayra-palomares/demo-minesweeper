package com.dropwizard.core.services.impl;

import com.dropwizard.api.requests.GameMoveRequest;
import com.dropwizard.core.models.Cell;
import com.dropwizard.core.models.Game;
import com.dropwizard.core.services.BoardService;
import com.google.inject.Inject;

import java.util.Random;

public class BoardServiceImpl implements BoardService {

    @Inject
    public BoardServiceImpl() {
    }

    /**
     * Create a new board with the parameters selected by the user
     */
    @Override
    public Cell[][] generateBoard(Integer numRows, Integer numColumns, Integer numMines) {
        Cell[][] board = initBoard(numRows, numColumns);
        populateWithMines(board, numRows, numColumns, numMines);
        return board;
    }

    private void printBoard(Cell[][] board, int numRows, int numColumns){
        System.out.println("Print board");
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                Cell cell = board[i][j];
                System.out.print(cell.isHasMine());
            }
            System.out.println();
        }
    }

    private Cell[][] initBoard(Integer numRows, Integer numColumns) {
        Cell[][] board = new Cell[numRows][numColumns];
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                board[i][j] = new Cell();
            }
        }
        return board;
    }

    private void populateWithMines(Cell[][] board, int numRows, int numColumns, int numMines) {
        int mines = 0;
        while (mines < numMines) {
            Random random = new Random();
            int randomRow = random.nextInt(numRows);
            int randomColumn = random.nextInt(numColumns);
            Cell cell = board[randomRow][randomColumn];
            if (!cell.isHasMine()) {
                putMineInBoard(board, numRows, numColumns, randomRow, randomColumn);
                mines++;
            }
        }
    }

    private void putMineInBoard(Cell[][] board, int numRows, int numColumns, int rowIndex, int columnIndex) {
        Cell cell = board[rowIndex][columnIndex];
        cell.setHasMine(true);
        board[rowIndex][columnIndex] = cell;
        updateNeighborsMinesCount(board, numRows, numColumns, rowIndex, columnIndex);
    }

    private boolean isValidCell(int numRows, int numColumns, int rowIndex, int columnIndex) {
        return rowIndex <= numRows - 1 && rowIndex >= 0 && columnIndex <= numColumns - 1 && columnIndex >= 0;
    }

    /**
     * Update the calculation of neighbors mines around the cell where a mine is put.
     */
    private void updateNeighborsMinesCount(Cell[][] board, int numRows, int numColumns, int rowIndex, int columnIndex) {
        for (int rowOffset = -1; rowOffset < 2; rowOffset++)
            for (int colOffset = -1; colOffset < 2; colOffset++)
                if (rowOffset != 0 && colOffset != 0) // Skip cell
                    if (isValidCell(numRows, numColumns, rowIndex + rowOffset, columnIndex + colOffset)) {
                        Cell cell = board[rowIndex + rowOffset][columnIndex + colOffset];
                        cell.setNumNeighborsMines(cell.getNumNeighborsMines() + 1);
                        board[rowIndex + rowOffset][columnIndex + colOffset] = cell;
                    }
    }

    /**
     * Choose a cell and validate if we try to put a flag, remove a flag or show its content.
     */
    public void makeMove(Game game, GameMoveRequest moveRequest) {
        Game.GameStatus gameStatus = Game.GameStatus.IN_PROGRESS;
        int rowIndex = moveRequest.getRowIndex();
        int columnIndex = moveRequest.getColumnIndex();
        int numFlags = game.getNumFlags();
        int numRows = game.getNumRows();
        int numColumns = game.getNumColumns();
        Cell[][] board = game.getBoard();

        if(isValidCell(numRows, numColumns, rowIndex, columnIndex)){
            switch(moveRequest.getMove()) {
                case PUT_FLAG:
                    numFlags = putFlagInBoard(board, rowIndex, columnIndex, numFlags);
                    break;
                case REMOVE_FLAG:
                    numFlags = removeFlagInBoard(board, rowIndex, columnIndex, numFlags);
                    break;
                case VISIT:
                    gameStatus = visitCell(board, numRows, numColumns, rowIndex, columnIndex);
                    break;
            }

            game.setStatus(gameStatus);
            game.setNumFlags(numFlags);
            game.setBoard(board);
        }
    }

    private int putFlagInBoard(Cell[][] board, int rowIndex, int columnIndex, int numFlags) {
        Cell cell = board[rowIndex][columnIndex];
        if(cell.getStatus().equals(Cell.CellStatus.EMPTY)){
            cell.setStatus(Cell.CellStatus.RED_FLAG);
            cell.setVisited(true);
            board[rowIndex][columnIndex] = cell;
            numFlags--;
        }
        return numFlags;
    }

    private int removeFlagInBoard(Cell[][] board, int rowIndex, int columnIndex, int numFlags) {
        Cell cell = board[rowIndex][columnIndex];
        if(cell.getStatus().equals(Cell.CellStatus.RED_FLAG)){
            cell.setStatus(Cell.CellStatus.EMPTY);
            cell.setVisited(false);
            board[rowIndex][columnIndex] = cell;
            numFlags++;
        }
        return numFlags;
    }

    /**
     * Visit a cell and show its content. If there is a mine, the use lost the game.
     */
    private Game.GameStatus visitCell(Cell[][] board, int numRows, int numColumns, int rowIndex, int columnIndex) {
        Game.GameStatus gameStatus = Game.GameStatus.IN_PROGRESS;
        Cell cell = board[rowIndex][columnIndex];
        if(!cell.isVisited()){
            if(cell.isHasMine()) {
                cell.setStatus(Cell.CellStatus.MINE);
                cell.setVisited(true);
                board[rowIndex][columnIndex] = cell;
                gameStatus = Game.GameStatus.FAIL;
            }else {
                gameStatus = visitEmptyCell(board, numRows, numColumns, rowIndex, columnIndex);
            }
        }
        return gameStatus;
    }

    private Game.GameStatus visitEmptyCell(Cell[][] board, int numRows, int numColumns, int rowIndex, int columnIndex) {
        Cell cell = board[rowIndex][columnIndex];
        Game.GameStatus gameStatus = Game.GameStatus.IN_PROGRESS;
        if(!cell.isVisited() && !cell.isHasMine()) {
            //Visit cell
            cell.setStatus(Cell.CellStatus.VISITED);
            cell.setVisited(true);
            board[rowIndex][columnIndex] = cell;

            if (isGameCompleted(board, numRows, numColumns)) {
                gameStatus = Game.GameStatus.COMPLETED;
            }else{
                gameStatus = visitNeighbors(board, numRows, numColumns, rowIndex, columnIndex);
            }
        }
        return gameStatus;
    }

    /**
     * Visit neighbors cells which do not have mines
     */
    private Game.GameStatus visitNeighbors(Cell[][] board, int numRows, int numColumns, int rowIndex, int columnIndex) {
        Game.GameStatus gameStatus = Game.GameStatus.IN_PROGRESS;
        for (int rowOffset = -1; rowOffset < 2; rowOffset++)
            for (int colOffset = -1; colOffset < 2; colOffset++)
                if (rowOffset != 0 && colOffset != 0) // Skip cell
                    if (isValidCell(numRows, numColumns, rowIndex + rowOffset, columnIndex + colOffset)) {
                        Cell cell = board[rowIndex + rowOffset][columnIndex + colOffset];
                        if(!cell.isVisited() && !cell.isHasMine()){
                            gameStatus = visitEmptyCell(board, numRows, numColumns, rowIndex + rowOffset, columnIndex + colOffset);
                            if(gameStatus.equals(Game.GameStatus.COMPLETED)){
                                return Game.GameStatus.COMPLETED;
                            }
                        }
                    }
        return gameStatus;
    }

    /**
     * Validate if the user won the game
     */
    private boolean isGameCompleted(Cell[][] board, int numRows, int numColumns) {
        for(int i=0; i < numRows; i++){
            for(int j=0; j < numColumns; j++){
                Cell cell = board[i][j];
                if(!cell.isVisited() && !cell.isHasMine()) {
                    return false;
                }
            }
        }

        return true;
    }
}
