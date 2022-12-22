package game.wonders;

import game.cards.Card;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.AssertJUnit.*;

public class OlympieTests {

    Wonders olympie = new Olympie();

    @Test
    public void InitializingTest() {
        assertEquals("Built number of floor is invalid",0, olympie.GetNumberOfFloorsBuilt());
        assertEquals("Victory points is invalid", 0, olympie.GetVictoryPoints());
        assertEquals("Floors hasn't been created correctly", 5, olympie.listOfFloors.size());
        assertEquals("Incorrect buildable floors", new ArrayList<>(List.of(1)), olympie.GetBuildableFloors());
    }

    @Test
    public void BuildingFloor1Test() {
        olympie.AddBuiltFloor(1);
        assertEquals(1, olympie.GetNumberOfFloorsBuilt());
        assertEquals(3, olympie.GetVictoryPoints());
        assertEquals("2≠",  olympie.GetFloor(1).GetRessourceRequirement());
        assertFalse(olympie.GetFloor(1).IsBuildable());
        assertFalse(olympie.GetFloor(1).HasSpecialEffect());
        assertEquals(new ArrayList<>(List.of(2, 3)), olympie.GetBuildableFloors());
    }

    @Test
    public void BuildingFloor4Test() {
        olympie.AddBuiltFloor(1);
        olympie.AddBuiltFloor(3);

        assertEquals(2, olympie.GetNumberOfFloorsBuilt());
        assertEquals(5, olympie.GetVictoryPoints());
        assertFalse(olympie.GetFloor(4).IsBuildable());
        assertEquals(new ArrayList<>(List.of(2)), olympie.GetBuildableFloors());
    }

    @Test
    public void BuildingFloor5Test() {
        olympie.AddBuiltFloor(1);
        olympie.AddBuiltFloor(2);
        olympie.AddBuiltFloor(3);
        olympie.AddBuiltFloor(4);
        olympie.AddBuiltFloor(5);

        assertEquals(5, olympie.GetNumberOfFloorsBuilt());
        assertEquals(22, olympie.GetVictoryPoints());
        assertFalse(olympie.GetFloor(5).IsBuildable());
        assertEquals(new ArrayList<>(List.of()), olympie.GetBuildableFloors());
    }

    @Test
    public void CardsStackTest() {
        int numberOfResourceCard = 0, numberOfScienceCard = 0, numberOfVictoryCards = 0, numberOfShieldsCard = 0;
        for (Card card : olympie.cardsStack) {
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
        assertEquals(4, numberOfVictoryCards);
        assertEquals(5, numberOfShieldsCard);
        assertEquals(25, totalNumberOfCards);
    }

}
