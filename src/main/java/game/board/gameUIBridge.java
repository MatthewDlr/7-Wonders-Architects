package game.board;

import controller.game.GameController;
import game.cards.GameCardsStack;
import game.player.Player;
import game.tokens.TokensBoard;
import java.util.ArrayList;
import javafx.scene.layout.Pane;

public class gameUIBridge {
    
    private final int[] wondersDispositionX = {5, 595, 1175, 1275, 925, 345, -60};
    private final int[] wondersDispositionY = {550, 550, 550, 130, 5, 5, 130};
    private final int[] wondersDispositionRotation = {0, 0, 0, 270, 180, 180, 90};
    
    public static GameController GAME_CONTROLLER; // TODO : figure out if encapsulation is really necessary here
    private static ArrayList<Player> LIST_OF_PLAYER;
    private static TokensBoard TOKENS_BOARD;
    private static PlayerQueue PLAYER_QUEUE;
    private static GameCardsStack GAME_CARDS_STACK;
    
    public void setup(ArrayList<Player> listOfPlayers, TokensBoard tokensBoard, PlayerQueue playerQueue, GameCardsStack gameCardsStack) {
        gameUIBridge.LIST_OF_PLAYER = listOfPlayers;
        gameUIBridge.TOKENS_BOARD = tokensBoard;
        gameUIBridge.PLAYER_QUEUE = playerQueue;
        gameUIBridge.GAME_CARDS_STACK = gameCardsStack;
    }
    
    public void setGameController(GameController controller) {
        GAME_CONTROLLER = controller;
    }
    
    protected ArrayList<Player> getListOfPlayers() {
        return LIST_OF_PLAYER;
    }
    
    protected TokensBoard getTokensBoard() {
        return TOKENS_BOARD;
    }
    
    protected PlayerQueue getPlayerQueue() {
        return PLAYER_QUEUE;
    }
    
    protected GameCardsStack getGameCardsStack() {
        return GAME_CARDS_STACK;
    }
    
    public void setCurrentPlayer(Player player) {
        GAME_CONTROLLER.setCurrentPlayer(player);
    }
    
    public void setupBoard() {
        GAME_CONTROLLER.linkPlayersWithUIComponents();
        setupChosenWonders();
        setPlaceOfProgressTokens();
        setPlaceOfConflictTokens();
        setCurrentPlayer(PLAYER_QUEUE.getActualPlayer());
    }
    
    private void setupChosenWonders() {
        int i = 0;
        for (Player player : LIST_OF_PLAYER) {
            int x = wondersDispositionX[i], y = wondersDispositionY[i], rotation = wondersDispositionRotation[i];
            GAME_CONTROLLER.setPlaceOfWonders(player.getWonderName(), x, y, rotation);
            player.setWonderGroupRotation(rotation);
            player.getAnchorPane().setDisable(false);
            player.getAnchorPane().setFocusTraversable(false);
            String cardPath = player.getWonderTopCardPath();
            GAME_CONTROLLER.updateWonderTopCard(cardPath, player.getWonderName());
            i++;
        }
    }
    
    void setPlaceOfProgressTokens() {
        for (int i = 1; i < 4; i++) {
            GAME_CONTROLLER.setupProgressToken(TOKENS_BOARD.popProgressToken(), i);
        }
    }
    
    void setPlaceOfConflictTokens() {
        for (int tokenNumber = 0; tokenNumber < TOKENS_BOARD.getNumberOfConflictTokens(); tokenNumber++) {
            GAME_CONTROLLER.setupConflictToken(tokenNumber);
        }
    }
    
    public void updateConflictTokenPath(int index, String path) {
        GAME_CONTROLLER.updateConflictTokenImage(index, path);
    }
    
    public void setFloorAsBuilt(int floor, String wonderName) {
        GAME_CONTROLLER.setFloorUIasBuilt(floor, wonderName);
    }
    
    public void UIBridgeAllowUserToTakeAProgressToken() {
        GAME_CONTROLLER.allowUserToTakeAProgressToken();
    }
    
    public void UIBridgeTakeTheCat() {
        GAME_CONTROLLER.getCatUI();
    }
    
    public Player getRightPlayer(){
        return PLAYER_QUEUE.getRightPlayer();
    }
    
    public Player getLeftPlayer(){
        return PLAYER_QUEUE.getLeftPlayer();
    }
    
    public void addWarTokenToPlayer(Player player) {
        GAME_CONTROLLER.addWarTokenUIToPlayer(player);
    }
    
    public void aIplayTakeMainStackCard() {
        GAME_CONTROLLER.takeMainStackCard();
    }
    
    public void aIplayTakeRightStackCard(Player rightPlayer) {
        GAME_CONTROLLER.takeRightStackCard(rightPlayer);
    }
    
    public void aIplayTakeLeftStackCard(Player leftPlayer) {
        GAME_CONTROLLER.takeLeftStackCard(leftPlayer);
    }
    
    public Pane getPaneUI() {
        return GAME_CONTROLLER.getPane();
    }
}
