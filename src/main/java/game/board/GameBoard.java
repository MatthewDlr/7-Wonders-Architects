package game.board;

import game.player.Player;
import game.tokens.TokensBoard;
import java.util.ArrayList;

public class GameBoard {
    
    private TokensBoard tokensBoard;
    private PlayerQueue playerQueue;
    private game.board.gameUIBridge gameUIBridge;
    private ArrayList<Player> listOfPlayers;
    
    public GameBoard(ArrayList<Player> listOfPlayers) {
        tokensBoard = new TokensBoard(listOfPlayers.size());
        playerQueue = new PlayerQueue(listOfPlayers);
        gameUIBridge = new gameUIBridge();
        this.listOfPlayers = listOfPlayers;
        
        for (Player player : listOfPlayers) {
            player.setGameBoard(this);
        }
    }
    
    public void initializeUI() {
        gameUIBridge.setListOfPlayers(listOfPlayers);
        gameUIBridge.setPlaceOfChosenWonders();
        gameUIBridge.displayFirstCardOfWonderCardsStack();
        
    }
    
    public TokensBoard getTokensBoard() {
        return tokensBoard;
    }
    
    public PlayerQueue getPlayerQueue() {
        return playerQueue;
    }
    
    public void addConflictTokenToWarFace() {
        tokensBoard.addConflictTokenToWarFace();
    }

}
