package game.wonders;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;
import static org.testng.AssertJUnit.fail;

import game.cards.Card;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class GizehTests {
    
    Wonders gizeh = new Gizeh();
    
    @Test
    public void InitializingTest() {
        assertEquals("Built number of floor is invalid", 0, gizeh.getNumberOfFloorsBuilt());
        assertEquals("Victory points is invalid", 0, gizeh.getVictoryPoints());
        assertEquals("Floors hasn't been created correctly", 5, gizeh.listOfFloors.size());
        assertEquals("Incorrect buildable floors", new ArrayList<>(List.of(1)), gizeh.getBuildableFloors());
    }
    
    @Test
    public void BuildingFloor1Test() {
        gizeh.addBuiltFloor(1);
        assertEquals(1, gizeh.getNumberOfFloorsBuilt());
        assertEquals(4, gizeh.getVictoryPoints());
        assertEquals("2≠", gizeh.getFloor(1).GetRessourceRequirement());
        assertFalse(gizeh.getFloor(1).IsBuildable());
        assertTrue(gizeh.getFloor(2).IsBuildable());
        assertFalse(gizeh.getFloor(1).HasSpecialEffect());
        assertEquals(new ArrayList<>(List.of(2)), gizeh.getBuildableFloors());
    }
    
    @Test
    public void CardsStackTest() {
        int numberOfResourceCard = 0, numberOfScienceCard = 0, numberOfVictoryCards = 0, numberOfShieldsCard = 0;
        for (Card card : gizeh.cardsStack) {
            switch (card.GetCardCategory()) {
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
        assertEquals(5, numberOfVictoryCards);
        assertEquals(4, numberOfShieldsCard);
        assertEquals(25, totalNumberOfCards);
    }
    
    
}
