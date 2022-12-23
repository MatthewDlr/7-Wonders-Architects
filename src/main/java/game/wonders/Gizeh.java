package game.wonders;

public class Gizeh extends Wonders {

    public Gizeh() {
        super("Gizeh"
                , "Cette Merveille n'a pas d'effet particulier, mais rapporte plus de Points de victoire que les autres Merveilles."
                , new int[]{4, 5, 6, 7, 8}
                , new boolean[]{false, false, false, false, false}
                , new int[]{3, 2, 1, 2, 2, 2, 1, 2, 1, 2, 3, 2, 0, 2}
        );
    }

}
