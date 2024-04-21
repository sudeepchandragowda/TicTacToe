package strategies.winningStrategy;

import exception.GameDrawnException;
import models.Board;
import models.Move;
import models.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderOneWinningStrategy implements WinningStrategy {
    private int dimension;
    private int symbolsAdded;
    private List<HashMap<Character, Integer>> rowSymbolCount = new ArrayList<>();
    private List<HashMap<Character, Integer>> columnSymbolCount = new ArrayList<>();
    private HashMap<Character, Integer> topLeftDiagonalSymbolCount = new HashMap<>();
    private HashMap<Character, Integer> bottomLeftDiagonalSymbolCount = new HashMap<>();
    private HashMap<Character, Integer> cornerSymbolCount = new HashMap<>();

    public OrderOneWinningStrategy(int dimension) {
        this.dimension = dimension;
        for (int i = 0; i < dimension; i++) {
            rowSymbolCount.add(new HashMap<>());
            columnSymbolCount.add(new HashMap<>());
        }
    }

    public boolean isCellTopLeftDiagonal(int row, int column) {
        return row == column;
    }

    public boolean isCellBottomLeftDiagonal(int row, int column) {
        return (row + column) == dimension - 1;
    }

    public boolean isCornerCell(int row, int column) {
        if (row == 0 || row == dimension - 1) {
            return (column == 0 || column == dimension - 1);
        }
        return false;
    }

    @Override
    public Player checkWinner(Board board, Move lastMove) {
        symbolsAdded++;
        char symbol = lastMove.getPlayer().getSymbol().getSymbolChar();
        int row = lastMove.getCell().getRow();
        int column = lastMove.getCell().getColumn();
        int dimension = board.getSize();

        if(checkForRowWins(row, column, symbol, lastMove) != null)
            return lastMove.getPlayer();
        else if ((checkForColumnWins(row, column, symbol, lastMove) != null))
            return lastMove.getPlayer();
        else if((checkForDiagonalWins(row, column, symbol, lastMove) != null))
            return lastMove.getPlayer();
        else if((checkForCornerWin(row, column, symbol, lastMove) != null))
            return lastMove.getPlayer();

        if(symbolsAdded == (dimension*dimension)) {
            board.printBoard();
            throw new GameDrawnException("Game is drawn as cells are full");
        }
        return null;
    }
        private Player checkForRowWins ( int row, int column, char symbol, Move lastMove){
            if (!rowSymbolCount.get(row).containsKey(symbol)) {
                rowSymbolCount.get(row).put(symbol, 0);
            }
            rowSymbolCount.get(row).put(
                    symbol, rowSymbolCount.get(row).get(symbol) + 1
            );
            //winning by same symbol across the row
            if (rowSymbolCount.get(row).get(symbol) == dimension)
                return lastMove.getPlayer();
            return null;
        }
    private Player checkForColumnWins ( int row, int column, char symbol, Move lastMove) {
            if (!columnSymbolCount.get(column).containsKey(symbol)) {
                columnSymbolCount.get(column).put(symbol, 0);
            }
            columnSymbolCount.get(column).put(
                    symbol, columnSymbolCount.get(column).get(symbol) + 1
            );
            //winning by same symbol across the column
            if (columnSymbolCount.get(column).get(symbol) == dimension)
                return lastMove.getPlayer();
            return null;
    }
    private Player checkForDiagonalWins ( int row, int column, char symbol, Move lastMove){
            if (isCellTopLeftDiagonal(row, column)) {
                if (!topLeftDiagonalSymbolCount.containsKey(symbol)) {
                    topLeftDiagonalSymbolCount.put(symbol, 0);
                }
                topLeftDiagonalSymbolCount.put(
                        symbol, topLeftDiagonalSymbolCount.get(symbol) + 1
                );
                //winning by same symbol across the top left diagonal
                if (topLeftDiagonalSymbolCount.get(symbol) == dimension)
                    return lastMove.getPlayer();
            }
            if (isCellBottomLeftDiagonal(row, column)) {
                if (!bottomLeftDiagonalSymbolCount.containsKey(symbol)) {
                    bottomLeftDiagonalSymbolCount.put(symbol, 0);
                }
                bottomLeftDiagonalSymbolCount.put(
                        symbol, bottomLeftDiagonalSymbolCount.get(symbol) + 1
                );
                //winning by same symbol across the bottom left diagonal
                if (bottomLeftDiagonalSymbolCount.get(symbol) == dimension)
                    return lastMove.getPlayer();
            }
            return null;
    }
    private Player checkForCornerWin ( int row, int column, char symbol, Move lastMove){
            if (isCornerCell(row, column)) {
                if (!cornerSymbolCount.containsKey(symbol)) {
                    cornerSymbolCount.put(symbol, 0);
                }
                cornerSymbolCount.put(
                        symbol, cornerSymbolCount.get(symbol) + 1
                );
                //winning by same symbol across the bottom left diagonal
                if (cornerSymbolCount.get(symbol) == dimension)
                    return lastMove.getPlayer();
            }
            return null;
    }
}


// TODO :
/*
    Implement 4 classes for Winning Strategy
    1. RowWinningStrategy
    2. ColumnWinningStrategy
    3. DiagonalWinningStrategy
    4. CornerWinningStrategy
 */