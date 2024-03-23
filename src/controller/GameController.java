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
}

