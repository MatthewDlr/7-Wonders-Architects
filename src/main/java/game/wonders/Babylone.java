package game.wonders;

public class Babylone extends Wonders {
    
    
    public Babylone() {
        super("Babylone"
                , "Choisissez 1 jeton Progrès parmi les 4 disponibles, et posez-le devant vous."
                , new int[]{3, 0, 5, 7, 5}
                , new boolean[]{false, true, false, false, true}
                , new int[]{3, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 1, 1}
        );
    }
    
    @Override
    protected void updateBuildableFloors() {
        System.out.println("updateBuildableFloors: " + numberOfFloorsBuilt);
        if (numberOfFloorsBuilt > floor5) {
            return;
        }
        if (numberOfFloorsBuilt < 3) {
            listOfFloors.get(numberOfFloorsBuilt).setBuildable(true);
        }
        if (numberOfFloorsBuilt == 3) {
            listOfFloors.get(floor4).setBuildable(true);
            listOfFloors.get(floor5).setBuildable(true);
        }
    }
    
}

