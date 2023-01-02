package game.player;

import game.board.GameBoard;
import game.cards.playerDeck.DeckOfProgressTokens;
import game.cards.playerDeck.PlayerCardsDeck;
import game.tokens.conflict.DeckOfWarToken;
import game.tokens.progress.ProgressToken;
import game.wonders.Wonders;
import javafx.scene.Group;

public abstract class Player {
    
    Wonders wonders;
    PlayerCardsDeck playerCardsDeck;
    DeckOfProgressTokens deckOfProgressTokens;
    DeckOfWarToken deckOfWarToken;
    private GameBoard gameBoard;
    private Group javaFXGroup;
    
    public Player(Wonders playerWonder) {
        wonders = playerWonder;
        playerCardsDeck = new PlayerCardsDeck();
        deckOfProgressTokens = new DeckOfProgressTokens();
        deckOfWarToken = new DeckOfWarToken();
    }
    
    public boolean hasTheCat() {
        return playerCardsDeck.hasTheCat();
    }
    
    public void removeTheCat() {
        playerCardsDeck.removeTheCat();
    }
    
    public void gotTheCat() throws NoSuchFieldException, IllegalAccessException, ClassNotFoundException {
        playerCardsDeck.gotTheCat();
    }
    
    public String getWonderName() {
        return wonders.getName();
    }
    
    public String getWonderTopCardPath() {
        return wonders.getStackTopCardPath();
    }
    
    public void setGameBoard(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
        playerCardsDeck.setGameBoard(gameBoard);
    }
    
    public void addProgressToken(ProgressToken progressToken) {
        deckOfProgressTokens.addProgressToken(progressToken);
    }
    
    public double[] getCoordinatesForNextProgressToken(){
        int value = deckOfProgressTokens.getCoordinatesForNextProgressToken();
        int rotation = (int) javaFXGroup.getRotate();
        return switch (rotation) {
            case 0 -> new double[]{value, 0};
            case 90 -> new double[]{0, value};
            case 180 -> new double[]{-value, 0};
            case 270 -> new double[]{0, -value};
            default -> new double[]{0, 0};
        };
    }
    
    public int getCoordinatesForNextProgressTokenNoRotation(){
        return deckOfProgressTokens.getCoordinatesForNextProgressToken();
    }
    
    public PlayerCardsDeck getPlayerDeck() {
        return playerCardsDeck;
    }
    
    public void setWonderGroup(Group javafxGroup) {
        javaFXGroup = javafxGroup;
    }
    
    public double getWonderGroupRotation() {
        return javaFXGroup.getRotate();
    }
    
    public Group getWonderGroup() {
        return javaFXGroup;
    }
    
}