package game.cards.playerDeck;

import game.cards.victoryPoints.VictoryPointCardWithCat;
import game.cards.victoryPoints.VictoryPointsCard;
import java.util.ArrayList;

public class DeckOfVictoryPointsCards {
    
    private final ArrayList<VictoryPointsCard> victoryPointsCardsSet;
    private int numberOfVictoryPoints;
    PlayerCardsDeck playerCardsDeck;
    
    public DeckOfVictoryPointsCards(PlayerCardsDeck playerCardsDeck) {
        victoryPointsCardsSet = new ArrayList<>();
        numberOfVictoryPoints = 0;
        this.playerCardsDeck = playerCardsDeck;
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
    
    public int getNumberOfCards() {
        return victoryPointsCardsSet.size();
    }
}
