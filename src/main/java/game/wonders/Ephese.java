package game.wonders;

public class Ephese extends Wonders {
    
    public Ephese() {
        super("Ephese",
                "Prenez la première carte de la pioche centrale et posez-la devant vous."
                , new int[]{4, 3, 5, 4, 7}
                , new boolean[]{false, true, true, true, false}
                , new int[]{3, 2, 2, 2, 2, 2, 1, 2, 2, 1, 2, 2, 1, 1}
        );
    }
    
    @Override
    protected void updateBuildableFloors() {
        System.out.println("updateBuildableFloors: " + numberOfFloorsBuilt);
        if (numberOfFloorsBuilt > floor5) {
            return;
        }
        if (numberOfFloorsBuilt == 1) {
            listOfFloors.get(floor2).setBuildable(true);
            listOfFloors.get(floor3).setBuildable(true);
            listOfFloors.get(floor4).setBuildable(true);
        }
        if (numberOfFloorsBuilt == 4) {
            listOfFloors.get(floor5).setBuildable(true);
        }
    }
}
