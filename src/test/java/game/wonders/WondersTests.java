package game.wonders;

import static org.testng.AssertJUnit.assertEquals;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;

public class WondersTests {
    
    
    @Test
    public void WondersWithMaximumFloorsBuilt() {
        ArrayList<Wonders> listOfWonders = new ArrayList<>();
        
        Wonders ephese = new Ephese();
        CreateFloors(ephese, 3);
        listOfWonders.add(ephese);
        
        Wonders halicarnasse = new Halicarnasse();
        CreateFloors(halicarnasse, 3);
        listOfWonders.add(halicarnasse);
        
        Wonders olympie = new Olympie();
        CreateFloors(olympie, 4);
        listOfWonders.add(olympie);
        
        Wonders rhodes = new Rhodes();
        CreateFloors(rhodes, 2);
        listOfWonders.add(rhodes);
        
        int maxNumberOfFloorsBuilt = 0;
        Wonders wonderWithMaxNumberOfFloorsBuilt = null;
        for (Wonders wonder : listOfWonders) {
            if (wonder.getNumberOfFloorsBuilt() > maxNumberOfFloorsBuilt) {
                maxNumberOfFloorsBuilt = wonder.getNumberOfFloorsBuilt();
                wonderWithMaxNumberOfFloorsBuilt = wonder;
            }
        }
        assertEquals(4, maxNumberOfFloorsBuilt);
        assert wonderWithMaxNumberOfFloorsBuilt != null;
        assertEquals("Olympie", wonderWithMaxNumberOfFloorsBuilt.getName());
    }
    
    
    public void CreateFloors(Wonders wonder, int floorNumber) {
        for (int i = 0; i < floorNumber; i++) {
            wonder.addBuiltFloor(i + 1);
        }
    }
}
