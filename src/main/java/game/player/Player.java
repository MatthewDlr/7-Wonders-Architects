package game.player;

import game.board.GameBoard;
import game.cards.Card;
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
    
    public int getWonderLevel() {
        return wonders.getNumberOfFloorsBuilt();
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
    
    public void addUIcard(ImageView cardsToAdd) {
        if (cardsToAdd.getId().contains("Brick") || cardsToAdd.getId().contains("Coins") || cardsToAdd.getId().contains("Experience") || cardsToAdd.getId().contains("Paper") || cardsToAdd.getId().contains("Stone") || cardsToAdd.getId().contains("Wood")) {
            playerCardsDeck.addUIResourcesCards(cardsToAdd);
            return;
        }
        if (cardsToAdd.getId().contains("Science")) {
            playerCardsDeck.addUIscienceCards(cardsToAdd);
            return;
        }
        System.out.println("Error in add new UI card: " + cardsToAdd.getId() + " is not a valid card");
    }
    
    public int getNumberOfVictorypoint() {
        int numberOfVictoryPoint = 0;
        numberOfVictoryPoint += wonders.getNumberOfVictoryPoints();
        numberOfVictoryPoint += playerCardsDeck.getNumberOfVictoryPoints();
        numberOfVictoryPoint += deckOfWarToken.getNumberOfVictoryPoints();
        if (hasTheCat()) {
            numberOfVictoryPoint += 2;
        }
        return numberOfVictoryPoint;
    }
    
    public Card popWonderCard() {
        return wonders.popCard();
    }
    
    public int getWonderCardsStackSize() {
        return wonders.getStackSize();
    }
    
    public int getNumberOfShields() {
        return getPlayerDeck().getNumberOfShieldsCards();
    }
    
    public void addWarToken() {
        deckOfWarToken.addWarToken();
    }
    
    public double getCoordinatesForNextWarToken() {
        return deckOfWarToken.getNumberOfWarTokens() * 70;
    }
}