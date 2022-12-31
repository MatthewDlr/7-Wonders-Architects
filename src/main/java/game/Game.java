package game;

import game.board.GameBoard;
import game.player.AIPlayer;
import game.player.HumanPlayer;
import game.player.Player;
import game.wonders.Alexandrie;
import game.wonders.Babylone;
import game.wonders.Ephese;
import game.wonders.Gizeh;
import game.wonders.Halicarnasse;
import game.wonders.Olympie;
import game.wonders.Rhodes;
import game.wonders.Wonders;
import java.util.ArrayList;

public class Game {
    
    private final ArrayList<Player> listOfPlayers; //TODO : is it necessary to have these attributes in final?
    private final GameBoard gameBoard;
    private ArrayList<Integer> listOfTakenWonders;
    
    public Game(int numberOfHumanPlayers, int numberOfAIPlayers) {
        listOfPlayers = new ArrayList<>();
        listOfTakenWonders = new ArrayList<>();
        createPlayersInstance(numberOfHumanPlayers, numberOfAIPlayers);
        gameBoard = new GameBoard(listOfPlayers);
        
        listOfTakenWonders.clear();
        listOfTakenWonders = null;
    }
    
    private void createPlayersInstance(int numberOfHumanPlayers, int numberOfAIPlayers) {
        for (int i = 0; i < numberOfHumanPlayers; i++) {
            Player newPlayer = new HumanPlayer(getARandomWonder());
            listOfPlayers.add(newPlayer);
        }
        for (int i = 0; i < numberOfAIPlayers; i++) {
            Player newPlayer = new AIPlayer(getARandomWonder());
            listOfPlayers.add(newPlayer);
        }
    }
    
    private Wonders getARandomWonder() {
        
        int randomIndex;
        do {
            randomIndex = (int) (Math.random() * 7);
            if (listOfTakenWonders.size() > 7) {
                throw new RuntimeException("Error in GetARandomWonder() method : wonders number should never exceed 7");
            }
        } while (listOfTakenWonders.contains(randomIndex));
        
        listOfTakenWonders.add(randomIndex);
        return switch (randomIndex) {
            case 0 -> new Alexandrie();
            case 1 -> new Babylone();
            case 2 -> new Ephese();
            case 3 -> new Gizeh();
            case 4 -> new Halicarnasse();
            case 5 -> new Olympie();
            case 6 -> new Rhodes();
            default -> throw new IllegalStateException("Error in GetARandomWonder() method : randomIndex is not between 0 and 7");
        };
    }
    
    public GameBoard getGameBoard() {
        return gameBoard;
    }
    
    ArrayList<Player> getListOfPlayers() {
        return listOfPlayers;
    }
    
    public void launchGame() {
        gameBoard.initializeUI();
    }
}
