package game.wonders;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;
import static org.testng.AssertJUnit.fail;

import game.cards.Card;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class RhodesTests extends WondersTests {
    
    Wonders rhodes = new Rhodes();
    
    @Test
    public void InitializingTest() {
        assertEquals("Built number of floor is invalid", 0, rhodes.getNumberOfFloorsBuilt());
        assertEquals("Victory points is invalid", 0, rhodes.getVictoryPoints());
        assertEquals("Floors hasn't been created correctly", 5, rhodes.listOfFloors.size());
        assertEquals("Incorrect buildable floors", new ArrayList<>(List.of(1, 2)), rhodes.getBuildableFloors());
    }
    
    @Test
    public void BuildingFloor1Test() {
        rhodes.addBuiltFloor(2);
        assertEquals(1, rhodes.getNumberOfFloorsBuilt());
        assertEquals(4, rhodes.getVictoryPoints());
        assertEquals("2≠", rhodes.getFloor(1).GetRessourceRequirement());
        assertTrue(rhodes.getFloor(1).IsBuildable());
        assertFalse(rhodes.getFloor(3).IsBuildable());
        assertTrue(rhodes.getFloor(1).HasSpecialEffect());
        assertEquals(new ArrayList<>(List.of(1)), rhodes.getBuildableFloors());
    }
    
    @Test
    public void BuildingFloor4Test() {
        rhodes.addBuiltFloor(1);
        rhodes.addBuiltFloor(2);
        rhodes.addBuiltFloor(3);
        rhodes.addBuiltFloor(4);
        
        assertEquals(4, rhodes.getNumberOfFloorsBuilt());
        assertEquals(19, rhodes.getVictoryPoints());
        assertFalse(rhodes.getFloor(4).IsBuildable());
        assertTrue(rhodes.getFloor(5).IsBuildable());
        assertTrue(rhodes.getFloor(4).HasSpecialEffect());
        assertEquals(new ArrayList<>(List.of(5)), rhodes.getBuildableFloors());
    }
    
    @Test
    public void CardsStackTest() {
        int numberOfResourceCard = 0, numberOfScienceCard = 0, numberOfVictoryCards = 0, numberOfShieldsCard = 0;
        for (Card card : rhodes.cardsStack) {
            switch (card.getCardCategory()) {
                case "resources" -> numberOfResourceCard++;
                case "science" -> numberOfScienceCard++;
                case "victory" -> numberOfVictoryCards++;
                case "shield" -> numberOfShieldsCard++;
                default -> fail("Invalid card category");
            }
        }
        int totalNumberOfCards = numberOfResourceCard + numberOfScienceCard + numberOfVictoryCards + numberOfShieldsCard;
        assertEquals(12, numberOfResourceCard);
        assertEquals(4, numberOfScienceCard);
        assertEquals(4, numberOfVictoryCards);
        assertEquals(5, numberOfShieldsCard);
        assertEquals(25, totalNumberOfCards);
    }
}
