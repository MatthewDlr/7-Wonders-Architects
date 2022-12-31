package game.cards.playerDeck;

import game.cards.victoryPoints.VictoryPointCardWithCat;
import game.cards.victoryPoints.VictoryPointsCard;
import java.util.ArrayList;

public class DeckOfVictoryPointsCards {
    
    private final ArrayList<VictoryPointsCard> victoryPointsCardsSet;
    private int numberOfVictoryPoints;
    PlayerDeck playerDeck;
    
    public DeckOfVictoryPointsCards(PlayerDeck playerDeck) {
        victoryPointsCardsSet = new ArrayList<>();
        numberOfVictoryPoints = 0;
        this.playerDeck = playerDeck;
    }
    
    void addCard(VictoryPointsCard cardToAdd) {
        victoryPointsCardsSet.add(cardToAdd);
        numberOfVictoryPoints += cardToAdd.GetNumberOfVictoryPoints();
        if (cardToAdd instanceof VictoryPointCardWithCat) {
            //TODO Move the cat to the player’s board
        }
    }
    
    public int getNumberOfVictoryPoints() {
        return numberOfVictoryPoints;
    }
}
