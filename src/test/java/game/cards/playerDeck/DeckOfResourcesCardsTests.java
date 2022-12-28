package game.cards.playerDeck;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;
import static org.testng.internal.junit.ArrayAsserts.assertArrayEquals;

import game.cards.resources.BrickResourceCard;
import game.cards.resources.CoinsCard;
import game.cards.resources.ExperienceResourceCard;
import game.cards.resources.PaperResourceCard;
import game.cards.resources.StoneResourceCard;
import game.cards.resources.WoodResourceCard;
import game.wonders.Alexandrie;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class DeckOfResourcesCardsTests {
    
    Alexandrie alexandrie = new Alexandrie();
    PlayerDeck playerCardsDeck = new PlayerDeck();
    
    @Test
    public void TestCartsDeckInitialization() {
        for (int i = 0; i < 25; i++) {
            playerCardsDeck.AddCard(alexandrie.getStackTopCard());
        }
        assertEquals(4, playerCardsDeck.deckOfShieldsCards.GetNumberOfShields());
        assertEquals(10, playerCardsDeck.deckOfVictoryPointsCards.GetNumberOfVictoryPoints());
        
    }
    
    @Test
    public void TestGetListOfResourcesCombinationsFunctions() {
        for (int i = 0; i < 25; i++) {
            playerCardsDeck.AddCard(alexandrie.getStackTopCard());
        }
        int[] numberOfEachResources = playerCardsDeck.deckOfResourcesCards.CountNumberOfEachResources();
        assertArrayEquals(new int[]{4, 2, 2, 2, 1, 2}, numberOfEachResources);
        
        int numberOfDifferentResources = playerCardsDeck.deckOfResourcesCards.GetNumberOfDifferentResources(numberOfEachResources);
        assertEquals(6, numberOfDifferentResources);
        
        int maxNumberOfTheSameResources = playerCardsDeck.deckOfResourcesCards.GetMaxNumberOfTheSameResources(numberOfEachResources);
        assertEquals(6, maxNumberOfTheSameResources);
    }
    
    @Test
    public void TestGetListOfResourcesCombinations() {
        playerCardsDeck.AddCard(new BrickResourceCard());
        assertEquals(new ArrayList<>(List.of()), playerCardsDeck.deckOfResourcesCards.GetListOfResourcesCombinations());
        
        playerCardsDeck.AddCard(new BrickResourceCard());
        assertEquals(new ArrayList<>(List.of("2=")), playerCardsDeck.deckOfResourcesCards.GetListOfResourcesCombinations());
        
        playerCardsDeck.AddCard(new WoodResourceCard());
        assertEquals(new ArrayList<>(List.of("2=", "2≠")), playerCardsDeck.deckOfResourcesCards.GetListOfResourcesCombinations());
        
        playerCardsDeck.AddCard(new CoinsCard());
        assertEquals(new ArrayList<>(List.of("2=", "2≠", "3=", "3≠")), playerCardsDeck.deckOfResourcesCards.GetListOfResourcesCombinations());
        
        playerCardsDeck.AddCard(new ExperienceResourceCard());
        assertEquals(new ArrayList<>(List.of("2=", "2≠", "3=", "3≠", "4≠")), playerCardsDeck.deckOfResourcesCards.GetListOfResourcesCombinations());
        
        playerCardsDeck.AddCard(new ExperienceResourceCard());
        assertEquals(new ArrayList<>(List.of("2=", "2≠", "3=", "3≠", "4≠")), playerCardsDeck.deckOfResourcesCards.GetListOfResourcesCombinations());
        
        playerCardsDeck.AddCard(new CoinsCard());
        assertEquals(new ArrayList<>(List.of("2=", "2≠", "3=", "3≠", "4=", "4≠")), playerCardsDeck.deckOfResourcesCards.GetListOfResourcesCombinations());
    }
    
    @Test
    public void TestRemoveCardsOfDifferentResources() {
        playerCardsDeck.AddCard(new BrickResourceCard());
        playerCardsDeck.AddCard(new BrickResourceCard());
        playerCardsDeck.AddCard(new WoodResourceCard());
        playerCardsDeck.deckOfResourcesCards.RemoveCardsOfDifferentResources(2);
        assertEquals("Brick", playerCardsDeck.deckOfResourcesCards.GetCardFromResourcesCardsSet(0).GetCardName());
        
        //+ the brick left from the previous test
        playerCardsDeck.AddCard(new CoinsCard());
        playerCardsDeck.AddCard(new ExperienceResourceCard());
        playerCardsDeck.AddCard(new ExperienceResourceCard());
        playerCardsDeck.deckOfResourcesCards.RemoveCardsOfDifferentResources(3);
        assertEquals("Experience", playerCardsDeck.deckOfResourcesCards.GetCardFromResourcesCardsSet(0).GetCardName());
        
        //+ the experience left from the previous test
        playerCardsDeck.AddCard(new CoinsCard());
        playerCardsDeck.AddCard(new CoinsCard());
        playerCardsDeck.AddCard(new CoinsCard());
        playerCardsDeck.deckOfResourcesCards.RemoveCardsOfDifferentResources(3);
        assertEquals("Coins", playerCardsDeck.deckOfResourcesCards.GetCardFromResourcesCardsSet(0).GetCardName());
        
        //+ the coins left from the previous test
        playerCardsDeck.AddCard(new WoodResourceCard());
        playerCardsDeck.AddCard(new PaperResourceCard());
        playerCardsDeck.AddCard(new StoneResourceCard());
        playerCardsDeck.deckOfResourcesCards.RemoveCardsOfDifferentResources(4);
        assertTrue(playerCardsDeck.deckOfResourcesCards.IsResourcesCardsSetEmpty());
    }
    
    @Test
    public void TestRemoveCardsOfSameResources() {
        playerCardsDeck.AddCard(new BrickResourceCard());
        playerCardsDeck.AddCard(new BrickResourceCard());
        playerCardsDeck.AddCard(new WoodResourceCard());
        playerCardsDeck.deckOfResourcesCards.RemoveCardsOfSameResources(2);
        assertEquals("Wood", playerCardsDeck.deckOfResourcesCards.GetCardFromResourcesCardsSet(0).GetCardName());
        
        //+ the wood left from the previous test
        playerCardsDeck.AddCard(new CoinsCard());
        playerCardsDeck.AddCard(new WoodResourceCard());
        playerCardsDeck.AddCard(new ExperienceResourceCard());
        playerCardsDeck.deckOfResourcesCards.RemoveCardsOfSameResources(3);
        assertEquals("Experience", playerCardsDeck.deckOfResourcesCards.GetCardFromResourcesCardsSet(0).GetCardName());
        
        //+ the experience left from the previous test
        playerCardsDeck.AddCard(new CoinsCard());
        playerCardsDeck.AddCard(new CoinsCard());
        playerCardsDeck.AddCard(new CoinsCard());
        playerCardsDeck.deckOfResourcesCards.RemoveCardsOfSameResources(3);
        assertEquals("Experience", playerCardsDeck.deckOfResourcesCards.GetCardFromResourcesCardsSet(0).GetCardName());
        
        //+ the experience left from the previous test
        playerCardsDeck.AddCard(new ExperienceResourceCard());
        playerCardsDeck.AddCard(new StoneResourceCard());
        playerCardsDeck.AddCard(new StoneResourceCard());
        playerCardsDeck.AddCard(new StoneResourceCard());
        playerCardsDeck.AddCard(new PaperResourceCard());
        playerCardsDeck.AddCard(new PaperResourceCard());
        playerCardsDeck.AddCard(new CoinsCard());
        playerCardsDeck.deckOfResourcesCards.RemoveCardsOfDifferentResources(4);
        assertEquals("Experience", playerCardsDeck.deckOfResourcesCards.GetCardFromResourcesCardsSet(0).GetCardName());
    }
    
    @Test
    public void TestRemoveCardsFromDeckWithCombination() {
        
        playerCardsDeck.AddCard(new StoneResourceCard());
        playerCardsDeck.AddCard(new StoneResourceCard());
        playerCardsDeck.AddCard(new StoneResourceCard());
        playerCardsDeck.deckOfResourcesCards.RemoveCardsFromDeckWithCombination("3=");
        assertTrue(playerCardsDeck.deckOfResourcesCards.IsResourcesCardsSetEmpty());
        
        playerCardsDeck.AddCard(new CoinsCard());
        playerCardsDeck.AddCard(new CoinsCard());
        playerCardsDeck.AddCard(new CoinsCard());
        playerCardsDeck.AddCard(new CoinsCard());
        playerCardsDeck.deckOfResourcesCards.RemoveCardsFromDeckWithCombination("4=");
        assertTrue(playerCardsDeck.deckOfResourcesCards.IsResourcesCardsSetEmpty());
        
        playerCardsDeck.AddCard(new CoinsCard());
        playerCardsDeck.AddCard(new CoinsCard());
        playerCardsDeck.AddCard(new CoinsCard());
        playerCardsDeck.AddCard(new CoinsCard());
        playerCardsDeck.AddCard(new CoinsCard());
        playerCardsDeck.deckOfResourcesCards.RemoveCardsFromDeckWithCombination("4≠");
        assertEquals("Coins", playerCardsDeck.deckOfResourcesCards.GetCardFromResourcesCardsSet(0).GetCardName());
        
        //+ the coins left from the previous test
        playerCardsDeck.AddCard(new StoneResourceCard());
        playerCardsDeck.AddCard(new StoneResourceCard());
        playerCardsDeck.AddCard(new WoodResourceCard());
        playerCardsDeck.AddCard(new PaperResourceCard());
        playerCardsDeck.deckOfResourcesCards.RemoveCardsFromDeckWithCombination("3=");
        assertEquals("Wood", playerCardsDeck.deckOfResourcesCards.GetCardFromResourcesCardsSet(0).GetCardName());
        assertEquals("Paper", playerCardsDeck.deckOfResourcesCards.GetCardFromResourcesCardsSet(1).GetCardName());
        
        //+ the wood and paper left from the previous test
        playerCardsDeck.AddCard(new BrickResourceCard());
        playerCardsDeck.AddCard(new CoinsCard());
        playerCardsDeck.deckOfResourcesCards.RemoveCardsFromDeckWithCombination("4≠");
        assertTrue(playerCardsDeck.deckOfResourcesCards.IsResourcesCardsSetEmpty());
        
    }
    
    
}
