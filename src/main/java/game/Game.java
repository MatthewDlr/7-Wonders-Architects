package game;

import game.board.GameBoard;
import game.player.AIPlayer;
import game.player.HumanPlayer;
import game.player.Player;
import game.wonders.*;

import java.util.ArrayList;

public class Game {
    private final ArrayList<Player> listOfPlayers;
    private final GameBoard gameBoard;
    private ArrayList<Integer> listOfTakenWonders;

    public Game(int numberOfHumanPlayers, int numberOfAIPlayers) {
        this.listOfPlayers = new ArrayList<>();
        this.listOfTakenWonders = new ArrayList<>();

        CreatePlayersInstance(numberOfHumanPlayers, numberOfAIPlayers);

        this.gameBoard = new GameBoard(listOfPlayers);

        this.listOfTakenWonders.clear();
        this.listOfTakenWonders = null;
    }

    private void CreatePlayersInstance(int numberOfHumanPlayers, int numberOfAIPlayers) {
        for (int i = 0; i < numberOfHumanPlayers; i++) {
            Player newPlayer = new HumanPlayer(GetRandomWonders());
            listOfPlayers.add(newPlayer);
        }
        for (int i = 0; i < numberOfAIPlayers; i++) {
            Player newPlayer = new AIPlayer(GetRandomWonders());
            listOfPlayers.add(newPlayer);
        }
    }

    private Wonders GetRandomWonders() {

        int randomIndex;
        do {
            randomIndex = (int) (Math.random() * 7);
            if (listOfTakenWonders.size() > 7) {
                throw new RuntimeException("Error in GetRandomWonders() method : wonders number should never exceed 7");
            }
        } while (listOfTakenWonders.contains(randomIndex));

        listOfTakenWonders.add(randomIndex);
        Wonders returnWonder;
        switch (randomIndex) {
            case 0 -> returnWonder = new Alexandrie();
            case 1 -> returnWonder = new Babylone();
            case 2 -> returnWonder = new Ephese();
            case 3 -> returnWonder = new Gizeh();
            case 4 -> returnWonder = new Halicarnasse();
            case 5 -> returnWonder = new Olympie();
            case 6 -> returnWonder = new Rhodes();
            default ->
                    throw new IllegalStateException("Error in GetRandomWonders() method : randomIndex is not between 0 and 7");
        }
        return returnWonder;
    }

    GameBoard GetGameBoard() {
        return gameBoard;
    }

    ArrayList<Player> GetListOfPlayers() {
        return listOfPlayers;
    }


}
