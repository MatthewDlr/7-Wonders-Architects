package game.cards.playerDeck;

import game.cards.victoryPoints.VictoryPointCardWithCat;
import game.cards.victoryPoints.VictoryPointsCard;
import java.util.ArrayList;

public class DeckOfVictoryPointsCards {
    private ArrayList<VictoryPointsCard> victoryPointsCardsSet;
    private int numberOfVictoryPoints;

    public DeckOfVictoryPointsCards() {
        victoryPointsCardsSet = new ArrayList<>();
        numberOfVictoryPoints = 0;
    }

    void AddCard(VictoryPointsCard cardToAdd) {
        victoryPointsCardsSet.add(cardToAdd);
        numberOfVictoryPoints += cardToAdd.GetNumberOfVictoryPoints();
        if (cardToAdd instanceof VictoryPointCardWithCat){
            //TODO Move the cat to the player’s board
        }
    }

    public int GetNumberOfVictoryPoints() {
        return numberOfVictoryPoints;
    }
}
