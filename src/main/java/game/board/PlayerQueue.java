package game.board;

import game.player.AIPlayer;
import game.player.HumanPlayer;
import game.player.Player;
import java.util.ArrayList;

public class PlayerQueue {
    
    public ArrayList<Player> listOfPlayers; // TODO: figure out how to make this private
    private final ArrayList<Player> listOfHumansPlayers;
    private final ArrayList<Player> listOfAIPlayers;
    private int indexOfActualPlayer;
    
    public PlayerQueue(Iterable<Player> listOfPlayersToAdd) {
        listOfPlayers = new ArrayList<>();
        listOfHumansPlayers = new ArrayList<>();
        listOfAIPlayers = new ArrayList<>();
        
        for (Player player : listOfPlayersToAdd) {
            listOfPlayers.add(player);
        }
        indexOfActualPlayer = (int) (Math.random() * listOfPlayers.size());
        parsePlayersBetweenAIAndHumans(listOfPlayersToAdd);
    }
    
    
    private void parsePlayersBetweenAIAndHumans(Iterable<Player> listOfPlayersToAdd) {
        for (Player player : listOfPlayersToAdd) {
            if (player instanceof HumanPlayer) { // TODO : apparently, instanceof is a proof of a bad understanding of OOP and indicates abstraction failure
                listOfHumansPlayers.add(player);
            } else if (player instanceof AIPlayer) {
                listOfAIPlayers.add(player);
            } else {
                throw new IllegalArgumentException("Error in PlayerQueue.ParsePlayers(): player is neither a HumanPlayer or AIPlayer");
            }
        }
    }
    
    public ArrayList<Player> getListOfHumanPlayers() {
        return listOfHumansPlayers;
    }
    
    public ArrayList<Player> getListOfAIPlayers() {
        return listOfAIPlayers;
    }
    
    public Player getRightPlayer() {
        int indexToGet = (indexOfActualPlayer + 1) % listOfPlayers.size(); // @Copilot
        return listOfPlayers.get(indexToGet);
    }
    
    public Player getLeftPlayer() {
        int indexToGet = (indexOfActualPlayer - 1) % listOfPlayers.size(); // @Copilot
        return listOfPlayers.get(indexToGet);
    }
    
    public Player getActualPlayer() {
        return listOfPlayers.get(indexOfActualPlayer);
    }
    
    public void nextPlayer() {
        indexOfActualPlayer = (indexOfActualPlayer + 1) % listOfPlayers.size();
    }
    
    public void previousPlayer() {
        indexOfActualPlayer = (indexOfActualPlayer - 1) % listOfPlayers.size();
    }
    
    public void removeTheCatFromAllPlayers() {
        for (Player player : listOfPlayers) {
            player.removeTheCat();
        }
    }
    
    
}
