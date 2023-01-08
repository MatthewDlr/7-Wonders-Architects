package game.wonders;

public class Rhodes extends Wonders {
    
    public Rhodes() {
        super("Rhodes"
                , "Ajoutez 1 Bouclier à votre total de Boucliers."
                , new int[]{4, 4, 5, 6, 7}
                , new boolean[]{true, false, false, true, false}
                , new int[]{2, 2, 2, 2, 2, 2, 2, 1, 1, 2, 2, 2, 1, 2}
        );
        listOfFloors.get(floor2).setBuildable(true);
    }
    
    @Override
    protected void updateBuildableFloors() {
        System.out.println("updateBuildableFloors: " + numberOfFloorsBuilt);
        if (numberOfFloorsBuilt > floor5) {
            return;
        }
        if (numberOfFloorsBuilt >= 2) {
            listOfFloors.get(numberOfFloorsBuilt).setBuildable(true);
        }
    }
    
}

