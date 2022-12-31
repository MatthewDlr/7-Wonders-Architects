package game.wonders;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;
import static org.testng.AssertJUnit.fail;

import game.cards.Card;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class EpheseTests {
    
    Wonders ephese = new Ephese();
    
    @Test
    public void InitializingTest() {
        assertEquals("Built number of floor is invalid", 0, ephese.getNumberOfFloorsBuilt());
        assertEquals("Victory points is invalid", 0, ephese.getVictoryPoints());
        assertEquals("Floors hasn't been created correctly", 5, ephese.listOfFloors.size());
        assertEquals("Incorrect buildable floors", new ArrayList<>(List.of(1)), ephese.getBuildableFloors());
    }
    
    @Test
    public void BuildingFloor1Test() {
        ephese.addBuiltFloor(1);
        assertEquals(1, ephese.getNumberOfFloorsBuilt());
        assertEquals(4, ephese.getVictoryPoints());
        assertEquals("2≠", ephese.getFloor(1).GetRessourceRequirement());
        assertFalse(ephese.getFloor(1).IsBuildable());
        assertTrue(ephese.getFloor(4).IsBuildable());
        assertTrue(ephese.getFloor(2).HasSpecialEffect());
        assertEquals(new ArrayList<>(List.of(2, 3, 4)), ephese.getBuildableFloors());
    }
    
    @Test
    public void BuildingFloor4Test() {
        ephese.addBuiltFloor(1);
        ephese.addBuiltFloor(3);
        ephese.addBuiltFloor(4);
        
        assertEquals(3, ephese.getNumberOfFloorsBuilt());
        assertEquals(13, ephese.getVictoryPoints());
        assertEquals("4≠", ephese.getFloor(5).GetRessourceRequirement());
        assertFalse(ephese.getFloor(5).IsBuildable());
        assertTrue(ephese.getFloor(2).IsBuildable());
        assertTrue(ephese.getFloor(4).HasSpecialEffect());
        assertEquals(new ArrayList<>(List.of(2)), ephese.getBuildableFloors());
    }
    
    @Test
    public void CardsStackTest() {
        int numberOfResourceCard = 0, numberOfScienceCard = 0, numberOfVictoryCards = 0, numberOfShieldsCard = 0;
        for (Card card : ephese.cardsStack) {
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
        assertEquals(5, numberOfScienceCard);
        assertEquals(3, numberOfVictoryCards);
        assertEquals(4, numberOfShieldsCard);
        assertEquals(25, totalNumberOfCards);
    }
}
