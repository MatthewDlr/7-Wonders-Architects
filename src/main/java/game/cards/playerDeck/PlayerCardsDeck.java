package game.cards.playerDeck;

import game.board.GameBoard;
import game.cards.Card;
import game.cards.resources.ResourcesCard;
import game.cards.science.ScienceCard;
import game.cards.shields.ShieldsCard;
import game.cards.victoryPoints.VictoryPointsCard;
import game.wonders.Wonders;
import javafx.scene.image.ImageView;

public class PlayerCardsDeck {
    
    DeckOfResourcesCards deckOfResourcesCards;
    DeckOfScienceCards deckOfScienceCards;
    DeckOfVictoryPointsCards deckOfVictoryPointsCards;
    DeckOfShieldsCards deckOfShieldsCards;
    Wonders wonder;
    private boolean hasTheCat;
    private GameBoard gameBoard;
    
    public PlayerCardsDeck(Wonders wonder) {
        deckOfResourcesCards = new DeckOfResourcesCards(this);
        deckOfScienceCards = new DeckOfScienceCards(this);
        deckOfVictoryPointsCards = new DeckOfVictoryPointsCards(this);
        deckOfShieldsCards = new DeckOfShieldsCards(this);
        hasTheCat = false;
        this.wonder = wonder;
    }
    
    public void addCard(Card cardToAdd) {
        if (cardToAdd instanceof ResourcesCard) {
            deckOfResourcesCards.addCard((ResourcesCard) cardToAdd);
            return;
        }
        if (cardToAdd instanceof ScienceCard) {
            deckOfScienceCards.addCard((ScienceCard) cardToAdd);
            return;
        }
        if (cardToAdd instanceof VictoryPointsCard) {
            deckOfVictoryPointsCards.addCard((VictoryPointsCard) cardToAdd);
            return;
        }
        if (cardToAdd instanceof ShieldsCard) {
            deckOfShieldsCards.addCard((ShieldsCard) cardToAdd);
            return;
        }
        throw new IllegalArgumentException("Error in PlayerCardsDeck.AddCard: cardToAdd is not a valid card");
    }
    
    public void gotTheCat() {
        hasTheCat = true;
    }
    
    public boolean hasTheCat() {
        return hasTheCat;
    }
    
    public void removeTheCat() {
        hasTheCat = false;
    }
    
    public void setGameBoard(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }
    
    GameBoard getGameBoard() {
        return gameBoard;
    }
    
    public int getNumberOfResourcesCards() {
        return deckOfResourcesCards.getNumberOfCards();
    }
    
    public int getNumberOfScienceCards() {
        return deckOfScienceCards.getNumberOfCards();
    }
    
    public int getNumberOfVictoryPointsCards() {
        return deckOfVictoryPointsCards.getNumberOfCards();
    }
    
    public int getNumberOfShieldsCards() {
        return deckOfShieldsCards.getNumberOfCards();
    }
    
    public void setFloorUIasBuilt(int floorNumber) {
        gameBoard.setFloorUIasBuilt(floorNumber);
    }
    
    public void addUIResourcesCards(ImageView cardsToAdd) {
        deckOfResourcesCards.addUIcards(cardsToAdd);
    }
    
    public void addUIscienceCards(ImageView cardsToAdd) {
        deckOfScienceCards.addUIscienceCards(cardsToAdd);
    }
    
    public void addUIShieldsCards(ImageView cardsToAdd) {
        deckOfShieldsCards.addCardUI(cardsToAdd);
    }
    
    public void getProgressToken() {
        gameBoard.getProgressToken();
    }
    
    public void getCatUI() {
        gameBoard.getCatUI();
    }
    
    public void removeCatFromAllPlayers() {
        gameBoard.removeCatForAllPlayers();
    }
    
    public int getNumberOfVictoryPoints() {
        return deckOfVictoryPointsCards.getNumberOfVictoryPoints() ;
    }
    
    public void nextPlayer() {
        gameBoard.nextPlayerTurn();
    }
    
    public void removeTrumpetCards() {
        deckOfShieldsCards.removeTrumpetCards();
    }
}
