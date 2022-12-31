package game.board;

import controller.game.GameController;
import game.player.Player;
import java.util.ArrayList;

public class gameUIBridge {
    
    public static GameController GAMECONTROLLER; // TODO : figure out if encapsulation is really necessary here
    private ArrayList<Player> listOfPlayers;
    private final int[] wondersDispositionX = {-685, -80, 475, 578, 160, -400, -840};
    private final int[] wondersDispositionY = {-85, -85, -85, -508, -725, -725, -550};
    private final int[] wondersDispositionRotation = {0, 0, 0, 270, 180, 180, 90};
    
    static public void setGameController(GameController controller) {
        GAMECONTROLLER = controller;
    }
    
    void setListOfPlayers(ArrayList<Player> listOfPlayers) {
        this.listOfPlayers = listOfPlayers;
    }
    
    void setPlaceOfChosenWonders() {
        int i = 0;
        
        for (Player player : listOfPlayers) {
            int x = wondersDispositionX[i], y = wondersDispositionY[i], rotation = wondersDispositionRotation[i];
            GAMECONTROLLER.setPlaceOfWonders(player.getWonderName(), x, y, rotation);
            i++;
        }
    }
    
    public void displayFirstCardOfWonderCardsStack() {
        for (Player player : listOfPlayers) {
            String cardPath = player.getWonderTopCardPath();
            GAMECONTROLLER.updateWonderTopCard(cardPath, player.getWonderName());
        }
    }
}
