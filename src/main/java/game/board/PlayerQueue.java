package game.board;

import game.player.AIPlayer;
import game.player.HumanPlayer;
import game.player.Player;
import java.util.ArrayList;

public class PlayerQueue {
    
    public static ArrayList<Player> playersQueue; // TODO: figure out how to make this private and non static
    private final ArrayList<Player> listOfHumansPlayers;
    private final ArrayList<Player> listOfAIPlayers;
    private int indexOfActualPlayer;
    
    public PlayerQueue(ArrayList<Player> listOfPlayersToAdd) {
        playersQueue = new ArrayList<>();
        listOfHumansPlayers = new ArrayList<>();
        listOfAIPlayers = new ArrayList<>();
        
        for (Player player : listOfPlayersToAdd) {
            playersQueue.add(player);
        }
        indexOfActualPlayer = (int) (Math.random() * playersQueue.size());
        ParsePlayers(listOfPlayersToAdd);
    }
    
    private void ParsePlayers(ArrayList<Player> listOfPlayersToAdd) {
        for (Player player : listOfPlayersToAdd) {
            if (player instanceof HumanPlayer) {
                listOfHumansPlayers.add(player);
            } else if (player instanceof AIPlayer) {
                listOfAIPlayers.add(player);
            } else {
                throw new IllegalArgumentException("Error in PlayerQueue.ParsePlayers: player is not a HumanPlayer or AIPlayer");
            }
        }
    }
    
    public ArrayList<Player> GetListOfHumanPlayers() {
        return listOfHumansPlayers;
    }
    
    public ArrayList<Player> GetListOfAIPlayers() {
        return listOfAIPlayers;
    }
    
    public Player GetRightPlayer() {
        int indexToGet = (indexOfActualPlayer + 1) % playersQueue.size();
        return playersQueue.get(indexToGet);
    }
    
    public Player GetLeftPlayer() {
        int indexToGet = (indexOfActualPlayer - 1) % playersQueue.size();
        return playersQueue.get(indexToGet);
    }
    
    public Player GetActualPlayer() {
        return playersQueue.get(indexOfActualPlayer);
    }
    
    public void NextPlayer() {
        indexOfActualPlayer = (indexOfActualPlayer + 1) % playersQueue.size();
    }
    
    public void PreviousPlayer() {
        indexOfActualPlayer = (indexOfActualPlayer - 1) % playersQueue.size();
    }
    
    public void RemoveTheCatFromAllPlayers() {
        for (Player player : playersQueue) {
            player.RemoveTheCat();
        }
    }
    
}
