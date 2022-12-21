package game.wonders;

import game.cards.Card;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.AssertJUnit.*;

public class EpheseTests {
    
    Wonders ephese = new Ephese();

    @Test
    public void InitializingTest() {
        assertEquals("Built number of floor is invalid",0, ephese.GetNumberOfFloorsBuilt());
        assertEquals("Victory points is invalid", 0, ephese.GetVictoryPoints());
        assertEquals("Floors hasn't been created correctly", 5, ephese.listOfFloors.size());
        assertEquals("Incorrect buildable floors", new ArrayList<>(List.of(1)), ephese.GetBuildableFloors());
    }

    @Test
    public void BuildingFloor1Test() {
        ephese.AddBuiltFloor(1);
        assertEquals(1, ephese.GetNumberOfFloorsBuilt());
        assertEquals(4, ephese.GetVictoryPoints());
        assertEquals("2≠",  ephese.GetFloor(1).GetRessourceRequirement());
        assertFalse(ephese.GetFloor(1).IsBuildable());
        assertTrue(ephese.GetFloor(4).IsBuildable());
        assertTrue(ephese.GetFloor(2).HasSpecialEffect());
        assertEquals(new ArrayList<>(List.of(2, 3, 4)), ephese.GetBuildableFloors());
    }

    @Test
    public void BuildingFloor4Test() {
        ephese.AddBuiltFloor(1);
        ephese.AddBuiltFloor(3);
        ephese.AddBuiltFloor(4);

        assertEquals(3, ephese.GetNumberOfFloorsBuilt());
        assertEquals(13, ephese.GetVictoryPoints());
        assertEquals("4≠",  ephese.GetFloor(5).GetRessourceRequirement());
        assertFalse(ephese.GetFloor(5).IsBuildable());
        assertTrue(ephese.GetFloor(2).IsBuildable());
        assertTrue(ephese.GetFloor(4).HasSpecialEffect());
        assertEquals(new ArrayList<>(List.of(2)), ephese.GetBuildableFloors());
    }

    @Test
    public void CardsStackTest() {
        int numberOfGoldCards = 0, numberOfResourceCard = 0, numberOfScienceCard = 0, numberOfVictoryCards = 0, numberOfShieldsCard = 0;
        for (Card card : ephese.cardsStack) {
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
        assertEquals(10, numberOfResourceCard);
        assertEquals(5, numberOfScienceCard);
        assertEquals(3, numberOfVictoryCards);
        assertEquals(4, numberOfShieldsCard);
        assertEquals(25, totalNumberOfCards);
    }
}
