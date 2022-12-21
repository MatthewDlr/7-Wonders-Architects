package game.wonders;

public class WonderFloor {
    private int floorNumber, victoryPoints ;
    private boolean hasSpecialEffect, isBuildable ;
    private String ressourceRequirement;

    public WonderFloor(int floorNumber, int victoryPoints, boolean hasSpecialEffect) {
        this.floorNumber = floorNumber;
        this.victoryPoints = victoryPoints;
        this.hasSpecialEffect = hasSpecialEffect;
        this.isBuildable = floorNumber == 1;
        this.ressourceRequirement = switch (floorNumber) {
            case 1 -> "2≠";
            case 2 -> "2=";
            case 3 -> "3≠";
            case 4 -> "3=";
            case 5 -> "4≠";
            default -> throw new IllegalArgumentException("Unexpected value: " + floorNumber);
        };
    }

    /* Following methods are getters and setters */

    public int GetFloorNumber() {
        return floorNumber;
    }

    public int GetVictoryPoints() {
        return victoryPoints;
    }

    public boolean HasSpecialEffect() {
        return hasSpecialEffect;
    }

    public boolean IsBuildable() {
        return isBuildable;
    }

    public void SetBuildable(boolean isBuildable) {
        this.isBuildable = isBuildable;
    }

    public String GetRessourceRequirement() {
        return ressourceRequirement;
    }






}

