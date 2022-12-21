package game.wonders;

import game.cards.Card;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.AssertJUnit.*;

public class BabyloneTests {

    Wonders babylone = new Babylone();

    @Test
    public void InitializingTest() {
        assertEquals("Built number of floor is invalid",0, babylone.GetNumberOfFloorsBuilt());
        assertEquals("Victory points is invalid", 0, babylone.GetVictoryPoints());
        assertEquals("Floors hasn't been created correctly", 5, babylone.listOfFloors.size());
        assertEquals("Incorrect buildable floors", new ArrayList<>(List.of(1)), babylone.GetBuildableFloors());
    }

    @Test
    public void BuildingFloor1Test() {
        babylone.AddBuiltFloor(1);
        assertEquals(1, babylone.GetNumberOfFloorsBuilt());
        assertEquals(3, babylone.GetVictoryPoints());
        assertEquals("2≠",  babylone.GetFloor(1).GetRessourceRequirement());
        assertFalse(babylone.GetFloor(1).IsBuildable());
        assertTrue(babylone.GetFloor(2).IsBuildable());
        assertFalse(babylone.GetFloor(1).HasSpecialEffect());
        assertEquals(new ArrayList<>(List.of(2)), babylone.GetBuildableFloors());
    }

    @Test
    public void BuildingFloor4Test() {
        babylone.AddBuiltFloor(1);
        babylone.AddBuiltFloor(2);
        babylone.AddBuiltFloor(3);

        assertEquals(3, babylone.GetNumberOfFloorsBuilt());
        assertEquals(8, babylone.GetVictoryPoints());
        assertEquals("3≠",  babylone.GetFloor(3).GetRessourceRequirement());
        assertTrue(babylone.GetFloor(4).IsBuildable());
        assertTrue(babylone.GetFloor(5).IsBuildable());
        assertTrue(babylone.GetFloor(5).HasSpecialEffect());
        assertEquals(new ArrayList<>(List.of(4, 5)), babylone.GetBuildableFloors());
    }

    @Test
    public void CardsStackTest() {
        int numberOfGoldCards = 0, numberOfResourceCard = 0, numberOfScienceCard = 0, numberOfVictoryCards = 0, numberOfShieldsCard = 0;
        for (Card card : babylone.cardsStack) {
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
        assertEquals(5, numberOfScienceCard);
        assertEquals(4, numberOfVictoryCards);
        assertEquals(4, numberOfShieldsCard);
        assertEquals(25, totalNumberOfCards);
    }

}
