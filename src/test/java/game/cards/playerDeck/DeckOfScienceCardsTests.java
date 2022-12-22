package game.cards.playerDeck;

import game.cards.science.CompasScienceCard;
import game.cards.science.GearScienceCard;
import game.cards.science.ScienceCard;
import game.cards.science.TabletScienceCard;
import org.junit.jupiter.api.Test;
import static org.testng.AssertJUnit.*;

public class DeckOfScienceCardsTests {

    PlayerDeck playerCardsDeck = new PlayerDeck();
    ScienceCard gearCards1 = new GearScienceCard();
    ScienceCard gearCards2 = new GearScienceCard();
    ScienceCard compasCard1 = new CompasScienceCard();
    ScienceCard tabletCard1 = new TabletScienceCard();


    @Test
    public void TestDeckOfScienceCardsInitialization() {
        assertEquals(0, playerCardsDeck.deckOfScienceCards.GetNumberOfScienceCards());
        assertFalse(playerCardsDeck.deckOfScienceCards.GetIfCombinationExist());
    }

    @Test
    public void TestGetIfCombinationExist(){
        playerCardsDeck.AddCard(gearCards1);
        playerCardsDeck.AddCard(gearCards2);
        assertEquals(0, playerCardsDeck.deckOfScienceCards.GetNumberOfScienceCards());
        assertTrue(playerCardsDeck.deckOfScienceCards.GetIfCombinationExist());

        playerCardsDeck.AddCard(compasCard1);
        assertEquals(1, playerCardsDeck.deckOfScienceCards.GetNumberOfScienceCards());
        assertFalse(playerCardsDeck.deckOfScienceCards.GetIfCombinationExist());

        playerCardsDeck.AddCard(tabletCard1);
        assertEquals(2, playerCardsDeck.deckOfScienceCards.GetNumberOfScienceCards());
        assertFalse(playerCardsDeck.deckOfScienceCards.GetIfCombinationExist());

        playerCardsDeck.AddCard(gearCards1);
        assertEquals(0, playerCardsDeck.deckOfScienceCards.GetNumberOfScienceCards());
        assertTrue(playerCardsDeck.deckOfScienceCards.GetIfCombinationExist());

        playerCardsDeck.AddCard(gearCards1);
        playerCardsDeck.AddCard(tabletCard1);
        playerCardsDeck.AddCard(gearCards2);
        assertEquals(1, playerCardsDeck.deckOfScienceCards.GetNumberOfScienceCards());
        assertTrue(playerCardsDeck.deckOfScienceCards.GetIfCombinationExist());
    }

}
