package game.wonders;

public class Olympie extends Wonders {
    
    public Olympie() {
        super("Olympie"
                , "Prenez la première carte de la pioche à votre gauche et de celle à votre droite, et posez-les devant vous."
                , new int[]{3, 5, 2, 5, 7}
                , new boolean[]{false, false, true, true, false}
                , new int[]{3, 2, 2, 1, 2, 2, 1, 1, 2, 1, 3, 2, 1, 2}
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
        }
        if (numberOfFloorsBuilt >= 3) {
            listOfFloors.get(numberOfFloorsBuilt).setBuildable(true);
        }
    }
}

