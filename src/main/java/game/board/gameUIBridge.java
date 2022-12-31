package game.board;

import controller.game.GameController;
import game.player.Player;
import java.util.ArrayList;

public class gameUIBridge {
    
    public static GameController GAMECONTROLLER; // TODO : figure out if encapsulation is really necessary here
    private ArrayList<Player> listOfPlayers;
    
    static public void setGameController(GameController controller) {
        GAMECONTROLLER = controller;
    }
    
    public void setListOfPlayers(ArrayList<Player> listOfPlayers) {
        this.listOfPlayers = listOfPlayers;
    }
    
    public void displayUIOfChosenPlayer() {
        for (Player player : listOfPlayers) {
            GAMECONTROLLER.showWonderElements(player.getWonderName());
        }
    }
}
