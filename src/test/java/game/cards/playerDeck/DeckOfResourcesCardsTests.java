package game.cards.playerDeck;

import game.cards.resources.*;
import game.wonders.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.testng.AssertJUnit.*;

public class DeckOfResourcesCardsTests {

    Alexandrie alexandrie = new Alexandrie();
    DeckOfResourcesCards deckOfResourcesCards = new DeckOfResourcesCards();

    @Test
    public void TestCartsDeckInitialization() {
        for (int i = 0; i < 25; i++) {
            deckOfResourcesCards.AddCardToSet(alexandrie.GetStackTopCard());
        }
        assertEquals(4, deckOfResourcesCards.GetNumberOfShields());
        assertEquals(10, deckOfResourcesCards.GetNumberOfVictoryPointsFromVictoryCards());

    }

    @Test
    public void TestGetListOfResourcesCombinationsFunctions(){
        for (int i = 0; i < 25; i++) {
            deckOfResourcesCards.AddCardToSet(alexandrie.GetStackTopCard());
        }
        int[] numberOfEachResources = deckOfResourcesCards.CountNumberOfEachResources();
        assertArrayEquals(new int[]{4, 2, 2, 2, 1, 2}, numberOfEachResources);

        int numberOfDifferentResources = deckOfResourcesCards.GetNumberOfDifferentResources(numberOfEachResources) ;
        assertEquals(6, numberOfDifferentResources);

        int maxNumberOfTheSameResources = deckOfResourcesCards.GetMaxNumberOfTheSameResources(numberOfEachResources);
        assertEquals(6, maxNumberOfTheSameResources);
    }

    @Test
    public void TestGetListOfResourcesCombinations(){
        deckOfResourcesCards.AddCardToSet(new BrickResourceCard() );
        assertEquals(new ArrayList<>(List.of()), deckOfResourcesCards.GetListOfResourcesCombinations());

        deckOfResourcesCards.AddCardToSet(new BrickResourceCard() );
        assertEquals(new ArrayList<>(List.of("2=")), deckOfResourcesCards.GetListOfResourcesCombinations());

        deckOfResourcesCards.AddCardToSet(new WoodResourceCard() );
        assertEquals(new ArrayList<>(List.of("2=", "2≠")), deckOfResourcesCards.GetListOfResourcesCombinations());

        deckOfResourcesCards.AddCardToSet(new CoinsCard() );
        assertEquals(new ArrayList<>(List.of("2=", "2≠", "3=", "3≠")), deckOfResourcesCards.GetListOfResourcesCombinations());

        deckOfResourcesCards.AddCardToSet(new ExperienceResourceCard());
        assertEquals(new ArrayList<>(List.of("2=", "2≠", "3=", "3≠", "4≠")), deckOfResourcesCards.GetListOfResourcesCombinations());

        deckOfResourcesCards.AddCardToSet(new ExperienceResourceCard());
        assertEquals(new ArrayList<>(List.of("2=", "2≠", "3=", "3≠", "4≠")), deckOfResourcesCards.GetListOfResourcesCombinations());

        deckOfResourcesCards.AddCardToSet(new CoinsCard());
        assertEquals(new ArrayList<>(List.of("2=", "2≠", "3=", "3≠", "4=", "4≠")), deckOfResourcesCards.GetListOfResourcesCombinations());
    }

    @Test
    public void TestRemoveCardsOfDifferentResources(){
        deckOfResourcesCards.AddCardToSet(new BrickResourceCard() );
        deckOfResourcesCards.AddCardToSet(new BrickResourceCard() );
        deckOfResourcesCards.AddCardToSet(new WoodResourceCard() );
        deckOfResourcesCards.RemoveCardsOfDifferentResources(2);
        assertEquals("Brick", deckOfResourcesCards.GetCardFromResourcesCardsSet(0).GetCardName());

        //+ the brick left from the previous test
        deckOfResourcesCards.AddCardToSet(new CoinsCard() );
        deckOfResourcesCards.AddCardToSet(new ExperienceResourceCard());
        deckOfResourcesCards.AddCardToSet(new ExperienceResourceCard());
        deckOfResourcesCards.RemoveCardsOfDifferentResources(3);
        assertEquals("Experience", deckOfResourcesCards.GetCardFromResourcesCardsSet(0).GetCardName());

        //+ the experience left from the previous test
        deckOfResourcesCards.AddCardToSet(new CoinsCard());
        deckOfResourcesCards.AddCardToSet(new CoinsCard());
        deckOfResourcesCards.AddCardToSet(new CoinsCard());
        deckOfResourcesCards.RemoveCardsOfDifferentResources(3);
        assertEquals("Coins", deckOfResourcesCards.GetCardFromResourcesCardsSet(0).GetCardName());

        //+ the coins left from the previous test
        deckOfResourcesCards.AddCardToSet(new WoodResourceCard());
        deckOfResourcesCards.AddCardToSet(new PaperResourceCard());
        deckOfResourcesCards.AddCardToSet(new StoneResourceCard());
        deckOfResourcesCards.RemoveCardsOfDifferentResources(4);
        assertTrue(deckOfResourcesCards.IsResourcesCardsSetEmpty());
    }

    @Test
    public void TestRemoveCardsOfSameResources(){
        deckOfResourcesCards.AddCardToSet(new BrickResourceCard() );
        deckOfResourcesCards.AddCardToSet(new BrickResourceCard() );
        deckOfResourcesCards.AddCardToSet(new WoodResourceCard() );
        deckOfResourcesCards.RemoveCardsOfSameResources(2);
        assertEquals("Wood", deckOfResourcesCards.GetCardFromResourcesCardsSet(0).GetCardName());

        //+ the wood left from the previous test
        deckOfResourcesCards.AddCardToSet(new CoinsCard() );
        deckOfResourcesCards.AddCardToSet(new WoodResourceCard());
        deckOfResourcesCards.AddCardToSet(new ExperienceResourceCard());
        deckOfResourcesCards.RemoveCardsOfSameResources(3);
        assertEquals("Experience", deckOfResourcesCards.GetCardFromResourcesCardsSet(0).GetCardName());

        //+ the experience left from the previous test
        deckOfResourcesCards.AddCardToSet(new CoinsCard());
        deckOfResourcesCards.AddCardToSet(new CoinsCard());
        deckOfResourcesCards.AddCardToSet(new CoinsCard());
        deckOfResourcesCards.RemoveCardsOfSameResources(3);
        assertEquals("Experience", deckOfResourcesCards.GetCardFromResourcesCardsSet(0).GetCardName());

        //+ the experience left from the previous test
        deckOfResourcesCards.AddCardToSet(new ExperienceResourceCard());
        deckOfResourcesCards.AddCardToSet(new StoneResourceCard());
        deckOfResourcesCards.AddCardToSet(new StoneResourceCard());
        deckOfResourcesCards.AddCardToSet(new StoneResourceCard());
        deckOfResourcesCards.AddCardToSet(new PaperResourceCard());
        deckOfResourcesCards.AddCardToSet(new PaperResourceCard());
        deckOfResourcesCards.AddCardToSet(new CoinsCard());
        deckOfResourcesCards.RemoveCardsOfDifferentResources(4);
        assertEquals("Experience", deckOfResourcesCards.GetCardFromResourcesCardsSet(0).GetCardName());
    }

    @Test
    public void TestRemoveCardsFromDeckWithCombination(){

        deckOfResourcesCards.AddCardToSet(new StoneResourceCard());
        deckOfResourcesCards.AddCardToSet(new StoneResourceCard());
        deckOfResourcesCards.AddCardToSet(new StoneResourceCard());
        deckOfResourcesCards.RemoveCardsFromDeckWithCombination("3=");
        assertTrue(deckOfResourcesCards.IsResourcesCardsSetEmpty());

        deckOfResourcesCards.AddCardToSet(new CoinsCard());
        deckOfResourcesCards.AddCardToSet(new CoinsCard());
        deckOfResourcesCards.AddCardToSet(new CoinsCard());
        deckOfResourcesCards.AddCardToSet(new CoinsCard());
        deckOfResourcesCards.RemoveCardsFromDeckWithCombination("4=");
        assertTrue(deckOfResourcesCards.IsResourcesCardsSetEmpty());

        deckOfResourcesCards.AddCardToSet(new CoinsCard());
        deckOfResourcesCards.AddCardToSet(new CoinsCard());
        deckOfResourcesCards.AddCardToSet(new CoinsCard());
        deckOfResourcesCards.AddCardToSet(new CoinsCard());
        deckOfResourcesCards.AddCardToSet(new CoinsCard());
        deckOfResourcesCards.RemoveCardsFromDeckWithCombination("4≠");
        assertEquals("Coins", deckOfResourcesCards.GetCardFromResourcesCardsSet(0).GetCardName());

        //+ the coins left from the previous test
        deckOfResourcesCards.AddCardToSet(new StoneResourceCard());
        deckOfResourcesCards.AddCardToSet(new StoneResourceCard());
        deckOfResourcesCards.AddCardToSet(new WoodResourceCard());
        deckOfResourcesCards.AddCardToSet(new PaperResourceCard());
        deckOfResourcesCards.RemoveCardsFromDeckWithCombination("3=");
        assertEquals("Wood", deckOfResourcesCards.GetCardFromResourcesCardsSet(0).GetCardName());
        assertEquals("Paper", deckOfResourcesCards.GetCardFromResourcesCardsSet(1).GetCardName());

        //+ the wood and paper left from the previous test
        deckOfResourcesCards.AddCardToSet(new BrickResourceCard());
        deckOfResourcesCards.AddCardToSet(new CoinsCard());
        deckOfResourcesCards.RemoveCardsFromDeckWithCombination("4≠");
        assertTrue(deckOfResourcesCards.IsResourcesCardsSetEmpty());

    }


}
