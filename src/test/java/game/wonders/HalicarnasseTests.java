package game.wonders;

import game.cards.Card;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.AssertJUnit.*;

public class HalicarnasseTests {

    Wonders halicarnasse = new Halicarnasse();

    @Test
    public void InitializingTest() {
        assertEquals("Built number of floor is invalid",0, halicarnasse.GetNumberOfFloorsBuilt());
        assertEquals("Victory points is invalid", 0, halicarnasse.GetVictoryPoints());
        assertEquals("Floors hasn't been created correctly", 5, halicarnasse.listOfFloors.size());
        assertEquals("Incorrect buildable floors", new ArrayList<>(List.of(1)), halicarnasse.GetBuildableFloors());
    }

    @Test
    public void BuildingFloor1Test() {
        halicarnasse.AddBuiltFloor(1);
        assertEquals(1, halicarnasse.GetNumberOfFloorsBuilt());
        assertEquals(3, halicarnasse.GetVictoryPoints());
        assertEquals("2≠",  halicarnasse.GetFloor(1).GetRessourceRequirement());
        assertFalse(halicarnasse.GetFloor(1).IsBuildable());
        assertTrue(halicarnasse.GetFloor(2).IsBuildable());
        assertFalse(halicarnasse.GetFloor(1).HasSpecialEffect());
        assertEquals(new ArrayList<>(List.of(2)), halicarnasse.GetBuildableFloors());
    }

    @Test
    public void BuildingFloor4Test() {
        halicarnasse.AddBuiltFloor(1);
        halicarnasse.AddBuiltFloor(2);

        assertEquals(2, halicarnasse.GetNumberOfFloorsBuilt());
        assertEquals(6, halicarnasse.GetVictoryPoints());
        assertEquals("4≠",  halicarnasse.GetFloor(5).GetRessourceRequirement());
        assertFalse(halicarnasse.GetFloor(5).IsBuildable());
        assertTrue(halicarnasse.GetFloor(4).IsBuildable());
        assertTrue(halicarnasse.GetFloor(3).HasSpecialEffect());
        assertEquals(new ArrayList<>(List.of(3, 4)), halicarnasse.GetBuildableFloors());
    }

    @Test
    public void BuildingFloor5Test() {
        halicarnasse.AddBuiltFloor(1);
        halicarnasse.AddBuiltFloor(2);
        halicarnasse.AddBuiltFloor(4);

        assertEquals(3, halicarnasse.GetNumberOfFloorsBuilt());
        assertEquals(12, halicarnasse.GetVictoryPoints());
        assertFalse(halicarnasse.GetFloor(5).IsBuildable());
        assertTrue(halicarnasse.GetFloor(3).IsBuildable());
        assertEquals(new ArrayList<>(List.of(3)), halicarnasse.GetBuildableFloors());
    }

    @Test
    public void CardsStackTest() {
        int numberOfGoldCards = 0, numberOfResourceCard = 0, numberOfScienceCard = 0, numberOfVictoryCards = 0, numberOfShieldsCard = 0;
        for (Card card : halicarnasse.cardsStack) {
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
        assertEquals(3, numberOfGoldCards);
        assertEquals(9, numberOfResourceCard);
        assertEquals(4, numberOfScienceCard);
        assertEquals(4, numberOfVictoryCards);
        assertEquals(5, numberOfShieldsCard);
        assertEquals(25, totalNumberOfCards);
    }


}
