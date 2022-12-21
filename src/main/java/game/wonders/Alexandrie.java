package game.wonders;

public class Alexandrie extends Wonders {

    public Alexandrie() {
        super("Alexandrie"
                , "Prenez la première carte d'une pioche au choix, n'importe où sur la table, et posez-la devant vous."
                , new int[]{4, 3, 6, 5, 7}
                , new boolean[]{false, false, true, true, false}
                , new int[]{4, 2, 2, 2, 1, 2, 2, 1, 1, 2, 2, 2, 1, 1}
        );
    }


}

