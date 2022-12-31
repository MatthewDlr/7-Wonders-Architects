package game.cards.playerDeck;

import game.board.GameBoard;
import game.board.PlayerQueue;
import game.cards.Card;
import game.cards.resources.ResourcesCard;
import game.cards.science.ScienceCard;
import game.cards.shields.ShieldsCard;
import game.cards.victoryPoints.VictoryPointsCard;
import game.player.Player;

public class PlayerDeck {
    
    DeckOfResourcesCards deckOfResourcesCards;
    DeckOfScienceCards deckOfScienceCards;
    DeckOfVictoryPointsCards deckOfVictoryPointsCards;
    DeckOfShieldsCards deckOfShieldsCards;
    private boolean hasTheCat;
    private GameBoard gameBoard;
    
    public PlayerDeck() {
        deckOfResourcesCards = new DeckOfResourcesCards(this);
        deckOfScienceCards = new DeckOfScienceCards(this);
        deckOfVictoryPointsCards = new DeckOfVictoryPointsCards(this);
        deckOfShieldsCards = new DeckOfShieldsCards(this);
        hasTheCat = false;
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
        throw new IllegalArgumentException("Error in PlayerDeck.AddCard: cardToAdd is not a valid card");
    }
    
    public void gotTheCat() throws NoSuchFieldException, IllegalAccessException, ClassNotFoundException {
        
        Class<PlayerQueue> playerQueueClass = (Class<PlayerQueue>) Class.forName("game.board.PlayerQueue");
        Iterable<Player> playersQueue = (Iterable<Player>) playerQueueClass.getField("playersQueue").get(null);
        for (Player player : playersQueue) {
            player.removeTheCat();
        }
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
    
    
}
