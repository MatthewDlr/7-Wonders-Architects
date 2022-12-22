package game.cards.playerDeck;

import game.cards.Card;
import game.cards.resources.ResourcesCard;
import game.cards.science.ScienceCard;
import game.cards.shields.ShieldsCard;
import game.cards.victoryPoints.VictoryPointsCard;

public class PlayerDeck {

    DeckOfResourcesCards deckOfResourcesCards;
    DeckOfScienceCards deckOfScienceCards;
    DeckOfVictoryPointsCards deckOfVictoryPointsCards;
    DeckOfShieldsCards deckOfShieldsCards;


    public PlayerDeck() {
        deckOfResourcesCards = new DeckOfResourcesCards();
        deckOfScienceCards = new DeckOfScienceCards();
        deckOfVictoryPointsCards = new DeckOfVictoryPointsCards();
        deckOfShieldsCards = new DeckOfShieldsCards();
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
        throw new IllegalArgumentException("Unrecognized card type in PlayerDeck.AddCardToSet");
    }


}
