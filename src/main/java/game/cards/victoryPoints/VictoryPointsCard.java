package game.cards.victoryPoints;

import game.cards.Card;

public abstract class VictoryPointsCard extends Card {

    protected int numberOfVictoryPoints ;
    protected boolean hasACat ;

    public VictoryPointsCard(String name, int numberOfVictoryPoints, boolean hasACat) {
        this.category = "points de victoire";
        this.name = name;
        this.numberOfVictoryPoints = numberOfVictoryPoints ;
        this.hasACat = hasACat ;
    }

    public int GetNumberOfVictoryPoints() {
        return numberOfVictoryPoints;
    }

    public boolean HasACat() {
        return hasACat;
    }


}
