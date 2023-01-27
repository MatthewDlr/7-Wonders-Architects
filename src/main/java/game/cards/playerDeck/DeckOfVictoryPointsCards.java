package game.cards.playerDeck;

import controller.game.PlayerActions;
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
        numberOfVictoryPoints += cardToAdd.getNumberOfVictoryPoints();
        if (cardToAdd instanceof VictoryPointCardWithCat) {
            PlayerActions.IS_TOUR_FINISHED = false;
            playerCardsDeck.removeCatFromAllPlayers();
            playerCardsDeck.gotTheCat();
            playerCardsDeck.getCatUI();
        }
    }
    
    public int getNumberOfVictoryPoints() {
        return numberOfVictoryPoints;
    }
    
    public int getNumberOfCards() {
        return victoryPointsCardsSet.size();
    }
    
    public int getNumberOfCatCards() {
        int counter = 0;
        for (VictoryPointsCard victoryPointsCard : victoryPointsCardsSet) {
            if (victoryPointsCard instanceof VictoryPointCardWithCat) {
                counter++;
            }
        }
        return counter;
    }
}
