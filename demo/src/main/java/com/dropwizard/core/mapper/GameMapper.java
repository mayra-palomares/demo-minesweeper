package com.dropwizard.core.mapper;

import com.dropwizard.core.models.Cell;
import com.dropwizard.core.models.Game;
import com.mongodb.BasicDBObject;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class GameMapper implements BaseMapper<Game>{
    @Override
    public Game map(Document document) {
        Game game = new Game();
        game.setNumColumns(document.getInteger("numColumns"));
        game.setNumRows(document.getInteger("numRows"));
        game.setNumMines(document.getInteger("numMines"));
        game.setNumFlags(document.getInteger("numFlags"));
        Game.GameStatus gameStatus = Game.GameStatus.valueOf(document.getString("status"));
        game.setStatus(gameStatus);
        game.setTimer(document.getLong("timer"));
        Cell[][] board = mapBoard(document, game.getNumRows(), game.getNumColumns());
        game.setBoard(board);
        return game;
    }

    private Cell[][] mapBoard(Document document, Integer numRows, Integer numColumns){
        List<List<Document>> matrix = document.get("board", ArrayList.class);
        Cell[][] board = new Cell[numRows][numColumns];
        for(int i=0; i < numRows; i++){
            List<Document> row = matrix.get(i);
            for(int j=0; j < numColumns; j++){
                Document dbObject = row.get(j);
                Cell.CellStatus cellStatus = Cell.CellStatus.valueOf(dbObject.getString("status"));
                Cell cell = new Cell();
                cell.setStatus(cellStatus);
                board[i][j] = cell;
            }
        }
        return board;
    }

    @Override
    public Document map(Game game) {
        Document document = new Document("numColumns",game.getNumColumns())
                                .append("numRows",game.getNumRows())
                                .append("numMines",game.getNumMines())
                                .append("numFlags", game.getNumFlags())
                                .append("status",game.getStatus().toString())
                                .append("timer",game.getTimer())
                                .append("board", mapBoard(game.getBoard(), game.getNumRows(), game.getNumColumns()));
        return document;
    }

    private List<List<BasicDBObject>> mapBoard(Cell[][] board, Integer numRows, Integer numColumns){
        List<List<BasicDBObject>> matrix = new ArrayList<>();
        for(int i=0; i< numRows; i++){
            List<BasicDBObject> row = new ArrayList<>();
            for(int j=0; j<numColumns; j++){
                BasicDBObject cell = mapCell(board[i][j]);
                row.add(cell);
            }
            matrix.add(row);
        }

        return matrix;
    }

    private BasicDBObject mapCell(Cell cell){
        return new BasicDBObject("status", cell.getStatus().toString());
    }
}
