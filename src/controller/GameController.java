package controller;

import models.Game;
import models.Player;
import strategies.winningStrategy.WinningStrategy;

import java.util.List;

public class GameController {
    public Game createGame(int dimension, List<Player> players, List<WinningStrategy> winningStrategies) {
        try {
            return Game.builder()
                    .setDimension(dimension)
                    .setPlayers(players)
                    .setWinningStrategies(winningStrategies)
                    .build();
        } catch (Exception e) {
            System.out.println("Could not start the game, something went wrong");
        }
        return null;
    }
    public void displayBoard (Game game) {
        game.getBoard().printBoard();
    }
    public GameState getGameState(Game game){
        return game.getGameState();
    }
    public void executeMove(Game game) {
        int nextPlayerIndex = game.getNextplayerIndex();
        Player nextPlayerToPlay = game.getPlayers().get(nextPlayerIndex);
        nextPlayerToPlay.makeMove(game.getBoard());
    }
    public String getWinner(Game game) {
        return game.getWinner().getName();
    }
}

