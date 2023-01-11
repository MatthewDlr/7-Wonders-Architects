package game.wonders;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;
import static org.testng.AssertJUnit.fail;

import game.cards.Card;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class HalicarnasseTests {
    
    Wonders halicarnasse = new Halicarnasse();
    
    @Test
    public void InitializingTest() {
        assertEquals("Built number of floor is invalid", 0, halicarnasse.getNumberOfFloorsBuilt());
        assertEquals("Victory points is invalid", 0, halicarnasse.getNumberOfVictoryPoints());
        assertEquals("Floors hasn't been created correctly", 5, halicarnasse.listOfFloors.size());
        assertEquals("Incorrect buildable floors", new ArrayList<>(List.of(1)), halicarnasse.getBuildableFloors());
    }
    
    @Test
    public void BuildingFloor1Test() {
        halicarnasse.addBuiltFloor(1);
        assertEquals(1, halicarnasse.getNumberOfFloorsBuilt());
        assertEquals(3, halicarnasse.getNumberOfVictoryPoints());
        assertEquals("2≠", halicarnasse.getFloor(1).GetRessourceRequirement());
        assertFalse(halicarnasse.getFloor(1).IsBuildable());
        assertTrue(halicarnasse.getFloor(2).IsBuildable());
        assertFalse(halicarnasse.getFloor(1).HasSpecialEffect());
        assertEquals(new ArrayList<>(List.of(2)), halicarnasse.getBuildableFloors());
    }
    
    @Test
    public void BuildingFloor4Test() {
        halicarnasse.addBuiltFloor(1);
        halicarnasse.addBuiltFloor(2);
        
        assertEquals(2, halicarnasse.getNumberOfFloorsBuilt());
        assertEquals(6, halicarnasse.getNumberOfVictoryPoints());
        assertEquals("4≠", halicarnasse.getFloor(5).GetRessourceRequirement());
        assertFalse(halicarnasse.getFloor(5).IsBuildable());
        assertTrue(halicarnasse.getFloor(4).IsBuildable());
        assertTrue(halicarnasse.getFloor(3).HasSpecialEffect());
        assertEquals(new ArrayList<>(List.of(3, 4)), halicarnasse.getBuildableFloors());
    }
    
    @Test
    public void BuildingFloor5Test() {
        halicarnasse.addBuiltFloor(1);
        halicarnasse.addBuiltFloor(2);
        halicarnasse.addBuiltFloor(4);
        
        assertEquals(3, halicarnasse.getNumberOfFloorsBuilt());
        assertEquals(12, halicarnasse.getNumberOfVictoryPoints());
        assertFalse(halicarnasse.getFloor(5).IsBuildable());
        assertTrue(halicarnasse.getFloor(3).IsBuildable());
        assertEquals(new ArrayList<>(List.of(3)), halicarnasse.getBuildableFloors());
    }
    
    @Test
    public void CardsStackTest() {
        int numberOfResourceCard = 0, numberOfScienceCard = 0, numberOfVictoryCards = 0, numberOfShieldsCard = 0;
        for (Card card : halicarnasse.cardsStack) {
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
