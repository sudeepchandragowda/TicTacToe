import controller.GameController;
import models.Game;
import models.Player;
import models.PlayerType;
import models.Symbol;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
            String playerName = sc.next();

            System.out.println("What is the character symbol of the BOT?");
            String characterSymbol = sc.next();

            players.add(new Player(new Symbol(characterSymbol.charAt(0)), playerName, PlayerType.HUMAN));
        }
        Game game = gameController.createGame(dimension, players);
        }
}
