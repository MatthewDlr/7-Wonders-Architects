package game.wonders;

import game.cards.Card;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.AssertJUnit.*;

public class RhodesTests extends WondersTests {

    Wonders rhodes = new Rhodes();

    @Test
    public void InitializingTest() {
        assertEquals("Built number of floor is invalid",0, rhodes.GetNumberOfFloorsBuilt());
        assertEquals("Victory points is invalid", 0, rhodes.GetVictoryPoints());
        assertEquals("Floors hasn't been created correctly", 5, rhodes.listOfFloors.size());
        assertEquals("Incorrect buildable floors", new ArrayList<>(List.of(1, 2)), rhodes.GetBuildableFloors());
    }

    @Test
    public void BuildingFloor1Test() {
        rhodes.AddBuiltFloor(2);
        assertEquals(1, rhodes.GetNumberOfFloorsBuilt());
        assertEquals(4, rhodes.GetVictoryPoints());
        assertEquals("2≠",  rhodes.GetFloor(1).GetRessourceRequirement());
        assertTrue(rhodes.GetFloor(1).IsBuildable());
        assertFalse(rhodes.GetFloor(3).IsBuildable());
        assertTrue(rhodes.GetFloor(1).HasSpecialEffect());
        assertEquals(new ArrayList<>(List.of(1)), rhodes.GetBuildableFloors());
    }

    @Test
    public void BuildingFloor4Test() {
        rhodes.AddBuiltFloor(1);
        rhodes.AddBuiltFloor(2);
        rhodes.AddBuiltFloor(3);
        rhodes.AddBuiltFloor(4);

        assertEquals(4, rhodes.GetNumberOfFloorsBuilt());
        assertEquals(19, rhodes.GetVictoryPoints());
        assertFalse(rhodes.GetFloor(4).IsBuildable());
        assertTrue(rhodes.GetFloor(5).IsBuildable());
        assertTrue(rhodes.GetFloor(4).HasSpecialEffect());
        assertEquals(new ArrayList<>(List.of(5)), rhodes.GetBuildableFloors());
    }

    @Test
    public void CardsStackTest() {
        int numberOfGoldCards = 0, numberOfResourceCard = 0, numberOfScienceCard = 0, numberOfVictoryCards = 0, numberOfShieldsCard = 0;
        for (Card card : rhodes.cardsStack) {
            switch (card.GetCardCategory()) {
                case "pièces" -> numberOfGoldCards++;
                case "ressources" -> numberOfResourceCard++;
                case "science" -> numberOfScienceCard++;
                case "points de victoire" -> numberOfVictoryCards++;
                case "bouclier" -> numberOfShieldsCard++;
                default -> fail("Invalid card category");
            }
        }
        int totalNumberOfCards = numberOfGoldCards + numberOfResourceCard + numberOfScienceCard + numberOfVictoryCards + numberOfShieldsCard;
        assertEquals(2, numberOfGoldCards);
        assertEquals(10, numberOfResourceCard);
        assertEquals(4, numberOfScienceCard);
        assertEquals(4, numberOfVictoryCards);
        assertEquals(5, numberOfShieldsCard);
        assertEquals(25, totalNumberOfCards);
    }
}
