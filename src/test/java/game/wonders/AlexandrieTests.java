package game.wonders;

import game.cards.Card;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.testng.AssertJUnit.*;

public class AlexandrieTests {

    Wonders alexandrie = new Alexandrie();

    @Test
    public void InitializingTest() {
        assertEquals("Built number of floor is invalid",0, alexandrie.GetNumberOfFloorsBuilt());
        assertEquals("Victory points is invalid", 0, alexandrie.GetVictoryPoints());
        assertEquals("Floors hasn't been created correctly", 5, alexandrie.listOfFloors.size());
        assertEquals("Incorrect buildable floors", new ArrayList<>(List.of(1)), alexandrie.GetBuildableFloors());
        assertEquals("Incorrect creation of cards stack", 25, alexandrie.cardsStack.size());
    }

    @Test
    public void BuildingFloor1Test() {
        alexandrie.AddBuiltFloor(1);
        assertEquals(1, alexandrie.GetNumberOfFloorsBuilt());
        assertEquals(4, alexandrie.GetVictoryPoints());
        assertEquals("2≠",  alexandrie.GetFloor(1).GetRessourceRequirement());
        assertFalse(alexandrie.GetFloor(1).IsBuildable());
        assertTrue(alexandrie.GetFloor(2).IsBuildable());
        assertFalse(alexandrie.GetFloor(1).HasSpecialEffect());
        assertEquals(new ArrayList<>(List.of(2)), alexandrie.GetBuildableFloors());
    }

    @Test
    public void BuildingFloor4Test() {
        alexandrie.AddBuiltFloor(1);
        alexandrie.AddBuiltFloor(2);
        alexandrie.AddBuiltFloor(3);
        alexandrie.AddBuiltFloor(4);

        assertEquals(4, alexandrie.GetNumberOfFloorsBuilt());
        assertEquals(18, alexandrie.GetVictoryPoints());
        assertEquals("4≠",  alexandrie.GetFloor(5).GetRessourceRequirement());
        assertFalse(alexandrie.GetFloor(4).IsBuildable());
        assertTrue(alexandrie.GetFloor(5).IsBuildable());
        assertTrue(alexandrie.GetFloor(4).HasSpecialEffect());
        assertEquals(new ArrayList<>(List.of(5)), alexandrie.GetBuildableFloors());
    }

    @Test
    public void CardsStackTest() {
        int numberOfGoldCards = 0, numberOfResourceCard = 0, numberOfScienceCard = 0, numberOfVictoryCards = 0, numberOfShieldsCard = 0;
        for (Card card : alexandrie.cardsStack) {
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
        assertEquals(4, numberOfGoldCards);
        assertEquals(9, numberOfResourceCard);
        assertEquals(4, numberOfScienceCard);
        assertEquals(4, numberOfVictoryCards);
        assertEquals(4, numberOfShieldsCard);
        assertEquals(25, totalNumberOfCards);
    }

}
