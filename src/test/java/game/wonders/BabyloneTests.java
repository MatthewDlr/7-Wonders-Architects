package game.wonders;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;
import static org.testng.AssertJUnit.fail;

import game.cards.Card;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class BabyloneTests {
    
    Wonders babylone = new Babylone();
    
    @Test
    public void InitializingTest() {
        assertEquals("Built number of floor is invalid", 0, babylone.getNumberOfFloorsBuilt());
        assertEquals("Victory points is invalid", 0, babylone.getNumberOfVictoryPoints());
        assertEquals("Floors hasn't been created correctly", 5, babylone.listOfFloors.size());
        assertEquals("Incorrect buildable floors", new ArrayList<>(List.of(1)), babylone.getBuildableFloors());
    }
    
    @Test
    public void BuildingFloor1Test() {
        babylone.addBuiltFloor(1);
        assertEquals(1, babylone.getNumberOfFloorsBuilt());
        assertEquals(3, babylone.getNumberOfVictoryPoints());
        assertEquals("2≠", babylone.getFloor(1).GetRessourceRequirement());
        assertFalse(babylone.getFloor(1).IsBuildable());
        assertTrue(babylone.getFloor(2).IsBuildable());
        assertFalse(babylone.getFloor(1).HasSpecialEffect());
        assertEquals(new ArrayList<>(List.of(2)), babylone.getBuildableFloors());
    }
    
    @Test
    public void BuildingFloor4Test() {
        babylone.addBuiltFloor(1);
        babylone.addBuiltFloor(2);
        babylone.addBuiltFloor(3);
        
        assertEquals(3, babylone.getNumberOfFloorsBuilt());
        assertEquals(8, babylone.getNumberOfVictoryPoints());
        assertEquals("3≠", babylone.getFloor(3).GetRessourceRequirement());
        assertTrue(babylone.getFloor(4).IsBuildable());
        assertTrue(babylone.getFloor(5).IsBuildable());
        assertTrue(babylone.getFloor(5).HasSpecialEffect());
        assertEquals(new ArrayList<>(List.of(4, 5)), babylone.getBuildableFloors());
    }
    
    @Test
    public void CardsStackTest() {
        int numberOfResourceCard = 0, numberOfScienceCard = 0, numberOfVictoryCards = 0, numberOfShieldsCard = 0;
        for (Card card : babylone.cardsStack) {
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
        assertEquals(5, numberOfScienceCard);
        assertEquals(4, numberOfVictoryCards);
        assertEquals(4, numberOfShieldsCard);
        assertEquals(25, totalNumberOfCards);
    }
    
}
