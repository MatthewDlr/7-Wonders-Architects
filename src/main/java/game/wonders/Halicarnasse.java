package game.wonders;

public class Halicarnasse extends Wonders {

    public Halicarnasse() {
        super("Halicarnasse"
                , "Prenez les 5 premières cartes de la pioche à votre gauche ou à votre droite, choisissez-en 1 et posez-la devant vous. Mélangez les cartes restantes dans leur pioche."
                , new int[]{3, 3, 5, 6, 7}
                , new boolean[]{false, true, true, false, false}
                , new int[]{3, 2, 2, 2, 2, 1, 1, 1, 2, 2, 2, 2, 1, 2}
        );
    }

    @Override
    protected void UpdateBuildableFloors() {
        if (numberOfFloorsBuilt > floor5) {
            return ;
        }
        if (numberOfFloorsBuilt == 1) {
            listOfFloors.get(floor2).SetBuildable(true);
        }
        if (numberOfFloorsBuilt == 2) {
            listOfFloors.get(floor3).SetBuildable(true);
            listOfFloors.get(floor4).SetBuildable(true);
        }
        if (numberOfFloorsBuilt == 4) {
            listOfFloors.get(floor5).SetBuildable(true);
        }
    }
}

