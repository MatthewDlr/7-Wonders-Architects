package game.player;

import game.board.GameBoard;
import game.cards.playerDeck.DeckOfProgressTokens;
import game.cards.playerDeck.PlayerCardsDeck;
import game.tokens.conflict.DeckOfWarToken;
import game.tokens.progress.ProgressToken;
import game.wonders.Wonders;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public abstract class Player {
    
    Wonders wonders;
    PlayerCardsDeck playerCardsDeck;
    DeckOfProgressTokens deckOfProgressTokens;
    DeckOfWarToken deckOfWarToken;
    private GameBoard gameBoard;
    private AnchorPane wonderAnchorPane;
    private double rotation;
    
    public Player(Wonders playerWonder) {
        wonders = playerWonder;
        playerCardsDeck = new PlayerCardsDeck(playerWonder);
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
        int rotation = (int) getWonderGroupRotation();
        System.out.println("rotation: " + rotation);
        return switch (rotation) {
            case 0 -> new double[]{value, 0};
            case 90 -> new double[]{0, value};
            case 180 -> new double[]{-value, 0};
            case 270 -> new double[]{0, -value};
            default -> throw new IllegalStateException("Unexpected value: " + rotation);
        };
    }
    
    public PlayerCardsDeck getPlayerDeck() {
        return playerCardsDeck;
    }
    
    public void setWonderGroup(AnchorPane pane) {
        wonderAnchorPane = pane;
    }
    
    public double getWonderGroupRotation() {
        return rotation;
    }
    
    public void setWonderGroupRotation(double rotation) {
        this.rotation = rotation ;
    }
    
    public AnchorPane getAnchorPane() {
        return wonderAnchorPane;
    }
    
    public void addUIcards(ImageView cardsToAdd) {
        playerCardsDeck.addUIcards(cardsToAdd);
    }
}