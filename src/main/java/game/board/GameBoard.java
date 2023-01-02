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
        gameUIBridge.setTokensBoard(tokensBoard);
        gameUIBridge.setPlaceOfChosenWonders();
        gameUIBridge.displayFirstCardOfWonderCardsStack();
        gameUIBridge.setPlaceOfConflictTokens(tokensBoard.getNumberOfConflictTokens()); //@Copilot
        for (int i = 1; i < 4; i++) {
            gameUIBridge.setPlaceOfProgressTokens(tokensBoard.getProgressToken(), i);
        }
        gameUIBridge.associatePlayersWithWonders();
        gameUIBridge.setCurrentPlayer(playerQueue.getActualPlayer()); // to be removed
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
