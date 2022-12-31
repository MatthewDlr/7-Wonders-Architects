package game.wonders;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;
import static org.testng.AssertJUnit.fail;

import game.cards.Card;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class AlexandrieTests {
    
    Wonders alexandrie = new Alexandrie();
    
    @Test
    public void InitializingTest() {
        assertEquals("Built number of floor is invalid", 0, alexandrie.getNumberOfFloorsBuilt());
        assertEquals("Victory points is invalid", 0, alexandrie.getVictoryPoints());
        assertEquals("Floors hasn't been created correctly", 5, alexandrie.listOfFloors.size());
        assertEquals("Incorrect buildable floors", new ArrayList<>(List.of(1)), alexandrie.getBuildableFloors());
        assertEquals("Incorrect creation of cards stack", 25, alexandrie.cardsStack.size());
    }
    
    @Test
    public void BuildingFloor1Test() {
        alexandrie.addBuiltFloor(1);
        assertEquals(1, alexandrie.getNumberOfFloorsBuilt());
        assertEquals(4, alexandrie.getVictoryPoints());
        assertEquals("2≠", alexandrie.getFloor(1).GetRessourceRequirement());
        assertFalse(alexandrie.getFloor(1).IsBuildable());
        assertTrue(alexandrie.getFloor(2).IsBuildable());
        assertFalse(alexandrie.getFloor(1).HasSpecialEffect());
        assertEquals(new ArrayList<>(List.of(2)), alexandrie.getBuildableFloors());
    }
    
    @Test
    public void BuildingFloor4Test() {
        alexandrie.addBuiltFloor(1);
        alexandrie.addBuiltFloor(2);
        alexandrie.addBuiltFloor(3);
        alexandrie.addBuiltFloor(4);
        
        assertEquals(4, alexandrie.getNumberOfFloorsBuilt());
        assertEquals(18, alexandrie.getVictoryPoints());
        assertEquals("4≠", alexandrie.getFloor(5).GetRessourceRequirement());
        assertFalse(alexandrie.getFloor(4).IsBuildable());
        assertTrue(alexandrie.getFloor(5).IsBuildable());
        assertTrue(alexandrie.getFloor(4).HasSpecialEffect());
        assertEquals(new ArrayList<>(List.of(5)), alexandrie.getBuildableFloors());
    }
    
    @Test
    public void CardsStackTest() {
        int numberOfResourceCard = 0, numberOfScienceCard = 0, numberOfVictoryCards = 0, numberOfShieldsCard = 0;
        for (Card card : alexandrie.cardsStack) {
            switch (card.getCardCategory()) {
                case "resources" -> numberOfResourceCard++;
                case "science" -> numberOfScienceCard++;
                case "victory" -> numberOfVictoryCards++;
                case "shield" -> numberOfShieldsCard++;
                default -> fail("Invalid card category");
            }
        }
        
        int totalNumberOfCards = numberOfResourceCard + numberOfScienceCard + numberOfVictoryCards + numberOfShieldsCard;
        assertEquals(13, numberOfResourceCard);
        assertEquals(4, numberOfScienceCard);
        assertEquals(4, numberOfVictoryCards);
        assertEquals(4, numberOfShieldsCard);
        assertEquals(25, totalNumberOfCards);
    }
    
}
