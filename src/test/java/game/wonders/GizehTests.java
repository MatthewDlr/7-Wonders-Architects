package game.wonders;

import game.cards.Card;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.AssertJUnit.*;

public class GizehTests {

    Wonders gizeh = new Gizeh();

    @Test
    public void InitializingTest() {
        assertEquals("Built number of floor is invalid",0, gizeh.GetNumberOfFloorsBuilt());
        assertEquals("Victory points is invalid", 0, gizeh.GetVictoryPoints());
        assertEquals("Floors hasn't been created correctly", 5, gizeh.listOfFloors.size());
        assertEquals("Incorrect buildable floors", new ArrayList<>(List.of(1)), gizeh.GetBuildableFloors());
    }

    @Test
    public void BuildingFloor1Test() {
        gizeh.AddBuiltFloor(1);
        assertEquals(1, gizeh.GetNumberOfFloorsBuilt());
        assertEquals(4, gizeh.GetVictoryPoints());
        assertEquals("2≠",  gizeh.GetFloor(1).GetRessourceRequirement());
        assertFalse(gizeh.GetFloor(1).IsBuildable());
        assertTrue(gizeh.GetFloor(2).IsBuildable());
        assertFalse(gizeh.GetFloor(1).HasSpecialEffect());
        assertEquals(new ArrayList<>(List.of(2)), gizeh.GetBuildableFloors());
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
