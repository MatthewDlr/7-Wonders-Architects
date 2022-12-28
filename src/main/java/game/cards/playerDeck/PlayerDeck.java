package game.cards.playerDeck;

import game.board.PlayerQueue;
import game.cards.Card;
import game.cards.resources.ResourcesCard;
import game.cards.science.ScienceCard;
import game.cards.shields.ShieldsCard;
import game.cards.victoryPoints.VictoryPointsCard;
import game.player.Player;

public class PlayerDeck {
    
    private final String catPath;
    DeckOfResourcesCards deckOfResourcesCards;
    DeckOfScienceCards deckOfScienceCards;
    DeckOfVictoryPointsCards deckOfVictoryPointsCards;
    DeckOfShieldsCards deckOfShieldsCards;
    private boolean hasTheCat;
    
    public PlayerDeck() {
        deckOfResourcesCards = new DeckOfResourcesCards();
        deckOfScienceCards = new DeckOfScienceCards();
        deckOfVictoryPointsCards = new DeckOfVictoryPointsCards();
        deckOfShieldsCards = new DeckOfShieldsCards();
        catPath = "src/main/resources/game/tokens/Cat.png";
        hasTheCat = false;
    }
    
    public void AddCard(Card cardToAdd) {
        if (cardToAdd instanceof ResourcesCard) {
            deckOfResourcesCards.AddCard((ResourcesCard) cardToAdd);
            return;
        }
        if (cardToAdd instanceof ScienceCard) {
            deckOfScienceCards.AddCard((ScienceCard) cardToAdd);
            return;
        }
        if (cardToAdd instanceof VictoryPointsCard) {
            deckOfVictoryPointsCards.AddCard((VictoryPointsCard) cardToAdd);
            return;
        }
        if (cardToAdd instanceof ShieldsCard) {
            deckOfShieldsCards.AddCard((ShieldsCard) cardToAdd);
            return;
        }
        throw new IllegalArgumentException("Error in PlayerDeck.AddCard: cardToAdd is not a valid card");
    }
    
    public String GetCatPath() {
        return catPath;
    }
    
    public void GotTheCat() throws NoSuchFieldException, IllegalAccessException, ClassNotFoundException {
        Class<PlayerQueue> playerQueueClass = (Class<PlayerQueue>) Class.forName("game.board.PlayerQueue");
        Iterable<Player> playersQueue = (Iterable<Player>) playerQueueClass.getField("playersQueue").get(null);
        for (Player player : playersQueue) {
            player.RemoveTheCat();
        }
        hasTheCat = true;
    }
    
    public boolean HasTheCat() {
        return hasTheCat;
    }
    
    public void RemoveTheCat() {
        hasTheCat = false;
    }
    
    
}
