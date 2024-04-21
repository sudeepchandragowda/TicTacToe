package strategies.winningStrategy;

public class WinningStrategyFactory {
    public static WinningStrategy getWinningStrategy(int dimension) {
        return new OrderOneWinningStrategy(dimension);
    }
    //TODO : basis of input, return the list of winning strategies.
}
