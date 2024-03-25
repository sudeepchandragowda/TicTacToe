package strategies.winningStrategy;

import models.Board;
import models.Move;
import models.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderOneWinningStrategy implements WinningStrategy {
    private int dimension;
    private List<HashMap<Character,Integer>> rowSymbolCount = new ArrayList<>();
    private List<HashMap<Character,Integer>> columnSymbolCount = new ArrayList<>();
    private HashMap<Character, Integer> topLeftDiagonalSymbolCount  = new HashMap<>();
    private HashMap<Character, Integer> bottomLeftDiagonalSymbolCount  = new HashMap<>();
    private HashMap<Character, Integer> cornerSymbolCount = new HashMap<>();

    public OrderOneWinningStrategy(int dimension) {
        this.dimension = dimension;
        for (int i=0; i<dimension; i++) {
            rowSymbolCount.add(new HashMap<>());
            columnSymbolCount.add(new HashMap<>());
        }
    }

    public boolean isCellTopLeftDiagonal(int row, int column) {
        return row == column;
    }

    public boolean isCellBottomLeftDiagonal (int row, int column) {
        return (row+column) == dimension-1;
    }

    public boolean isCornerCell(int row, int column) {
        if(row == 0 || row == dimension-1) {
            return (column == 0 || column == dimension-1);
        }
        return false;
    }

    @Override
    public Player checkWinner(Board board, Move lastMove) {
        char symbol = lastMove.getPlayer().getSymbol().getSymbolChar();
        int row = lastMove.getCell().getRow();
        int column = lastMove.getCell().getColumn();
        int dimension = board.getSize();

        if(!rowSymbolCount.get(row).containsKey(symbol)) {
            rowSymbolCount.get(row).put(symbol, 0);
        }
        return null;
    }
}
