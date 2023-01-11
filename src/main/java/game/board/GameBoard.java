package game.board;

import game.cards.GameCardsStack;
import game.player.Player;
import game.tokens.TokensBoard;
import java.util.ArrayList;

public class GameBoard {
    
    private TokensBoard tokensBoard;
    private PlayerQueue playerQueue;
    private gameUIBridge gameUIBridge;
    private ArrayList<Player> listOfPlayers;
    private GameCardsStack gameCardsStack;
    
    public GameBoard(ArrayList<Player> listOfPlayers) {
        tokensBoard = new TokensBoard(listOfPlayers.size());
        playerQueue = new PlayerQueue(listOfPlayers);
        gameUIBridge = new gameUIBridge();
        gameCardsStack = new GameCardsStack();
        this.listOfPlayers = listOfPlayers;
        
        for (Player player : listOfPlayers) {
            player.setGameBoard(this);
        }
        gameUIBridge.setup(listOfPlayers, tokensBoard, playerQueue, gameCardsStack);
    }
    
    public void initialize() {
        gameUIBridge.setupBoard();
        gameUIBridge.setCurrentPlayer(playerQueue.getActualPlayer()); // to be removed
    }
    
    public void nextPlayer() {
        isGameFinished();
        playerQueue.nextPlayer();
        gameUIBridge.setCurrentPlayer(playerQueue.getActualPlayer());
    }
    
    public Player getRightPlayer() {
        return playerQueue.getRightPlayer();
    }
    
    public Player getLeftPlayer() {
        return playerQueue.getLeftPlayer();
    }
    
    public TokensBoard getTokensBoard() {
        if (tokensBoard == null) {
            throw new IllegalStateException("TokensBoard is not initialized");
        }
        return tokensBoard;
    }
    
    public PlayerQueue getPlayerQueue() {
        return playerQueue;
    }
    
    public void addConflictTokenToWarFace() {
        tokensBoard.addConflictTokenToWarFace();
        for (int i = 0; i < tokensBoard.getNumberOfConflictTokens(); i++) {
            String path = tokensBoard.getPathOfConflictToken(i);
            gameUIBridge.updateConflictTokenPath(i, path);
        }
    }
    
    public void setFloorUIasBuilt(int floor) {
        gameUIBridge.setFloorAsBuilt(floor, playerQueue.getActualPlayer().getWonderName());
    }
    
    
    public void getProgressToken() {
        gameUIBridge.UIBridgeAllowUserToTakeAProgressToken();
    }
    
    public void getCatUI() {
        gameUIBridge.UIBridgeTakeTheCat();
    }
    
    public void removeCatForAllPlayers() {
        playerQueue.removeTheCatFromAllPlayers();
    }
    
    
    private void isGameFinished() {
        for (Player player : listOfPlayers) {
            if (player.getWonderLevel() >= 5) {
                findWinner();
            }
            System.out.println("Player wonder level: " + player.getWonderLevel());
        }
    }
    
    private void findWinner() {
        int maxScore = 0;
        Player winner = null;
        for (Player player : listOfPlayers) {
            if (player.getNumberOfVictorypoint() > maxScore) {
                maxScore = player.getNumberOfVictorypoint();
                winner = player;
            }
        }
        //gameUIBridge.UIBridgeShowWinner(winner);
    }
}
