package game.wonders;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.fail;

import game.cards.Card;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class OlympieTests {
    
    Wonders olympie = new Olympie();
    
    @Test
    public void InitializingTest() {
        assertEquals("Built number of floor is invalid", 0, olympie.getNumberOfFloorsBuilt());
        assertEquals("Victory points is invalid", 0, olympie.getNumberOfVictoryPoints());
        assertEquals("Floors hasn't been created correctly", 5, olympie.listOfFloors.size());
        assertEquals("Incorrect buildable floors", new ArrayList<>(List.of(1)), olympie.getBuildableFloors());
    }
    
    @Test
    public void BuildingFloor1Test() {
        olympie.addBuiltFloor(1);
        assertEquals(1, olympie.getNumberOfFloorsBuilt());
        assertEquals(3, olympie.getNumberOfVictoryPoints());
        assertEquals("2≠", olympie.getFloor(1).GetRessourceRequirement());
        assertFalse(olympie.getFloor(1).IsBuildable());
        assertFalse(olympie.getFloor(1).HasSpecialEffect());
        assertEquals(new ArrayList<>(List.of(2, 3)), olympie.getBuildableFloors());
    }
    
    @Test
    public void BuildingFloor4Test() {
        olympie.addBuiltFloor(1);
        olympie.addBuiltFloor(3);
        
        assertEquals(2, olympie.getNumberOfFloorsBuilt());
        assertEquals(5, olympie.getNumberOfVictoryPoints());
        assertFalse(olympie.getFloor(4).IsBuildable());
        assertEquals(new ArrayList<>(List.of(2)), olympie.getBuildableFloors());
    }
    
    @Test
    public void BuildingFloor5Test() {
        olympie.addBuiltFloor(1);
        olympie.addBuiltFloor(2);
        olympie.addBuiltFloor(3);
        olympie.addBuiltFloor(4);
        olympie.addBuiltFloor(5);
        
        assertEquals(5, olympie.getNumberOfFloorsBuilt());
        assertEquals(22, olympie.getNumberOfVictoryPoints());
        assertFalse(olympie.getFloor(5).IsBuildable());
        assertEquals(new ArrayList<>(List.of()), olympie.getBuildableFloors());
    }
    
    @Test
    public void CardsStackTest() {
        int numberOfResourceCard = 0, numberOfScienceCard = 0, numberOfVictoryCards = 0, numberOfShieldsCard = 0;
        for (Card card : olympie.cardsStack) {
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
