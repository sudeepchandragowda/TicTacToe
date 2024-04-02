import controller.GameController;
import models.*;
import strategies.botPlayingStrategy.BotPlayingStrategy;
import strategies.botPlayingStrategy.BotPlayingStrategyFactory;

import java.util.*;

public class TicTacToeGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GameController gameController = new GameController();

        System.out.println("Please enter the dimension of the game");
        int dimension = sc.nextInt();

        System.out.println("Will there be any bot in the game? Y/N");
        String isBotPresent = sc.next();

        List<Player> players = new ArrayList<>();
        int iteratorNumber = dimension - 1;

        if(isBotPresent.equals("Y")){
            iteratorNumber = dimension - 2;
        }

        for(int i=0; i<iteratorNumber; i++) {
            System.out.println("What is the name of the player number : " + (i+1));
            String playerName = sc.next();

            System.out.println("What is the character symbol of the player number : " + (i+1));
            String characterSymbol = sc.next();

            players.add(new Player(new Symbol(characterSymbol.charAt(0)), playerName, PlayerType.HUMAN));
        }

        if(isBotPresent.equals("Y")){
            System.out.println("What is the name of the BOT?");
            String botName = sc.next();

            System.out.println("What is the character symbol of the BOT?");
            String characterSymbol = sc.next();

            Bot bot = new Bot(new Symbol(characterSymbol.charAt(0)),
                    botName,
                    BotDifficultyLevel.EASY,
                    BotPlayingStrategyFactory.getBotPlayingStrategyForDifficultyLevel(BotDifficultyLevel.EASY));

            players.add(bot);
        }
        // For randomizing the players in the list
        Collections.shuffle(players);

        Game game = gameController.createGame(dimension, players);

        while(game.getGameState().equals(GameState.IN_PROGRESS)) {
            System.out.println("Current board status");
            gameController.displayBoard(game);


            gameController.executeMove(game);
        }

        System.out.println("Game has ended, result was : ");
        if(gameController.getGameState(game).equals(GameState.DRAW)) {
            System.out.println("Game is won by : " + gameController.getWinner(game));
        } else {
            System.out.println("GAME was a Draw");
        }
    }
}
