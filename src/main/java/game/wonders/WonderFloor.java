package game.wonders;

public class WonderFloor {
    
    private final int floorNumber;
    private final int victoryPoints;
    private final boolean hasSpecialEffect;
    private final String ressourceRequirement;
    private boolean isBuildable, isBuilt;
    private String wonderFloorPath;
    
    public WonderFloor(String wonderName, int floorNumber, int victoryPoints, boolean hasSpecialEffect) {
        this.floorNumber = floorNumber;
        this.victoryPoints = victoryPoints;
        this.hasSpecialEffect = hasSpecialEffect;
        isBuildable = floorNumber == 1;
        isBuilt = false;
        ressourceRequirement = switch (floorNumber) {
            case 1 -> "2≠";
            case 2 -> "2=";
            case 3 -> "3≠";
            case 4 -> "3=";
            case 5 -> "4≠";
            default -> throw new IllegalArgumentException("Unexpected value: " + floorNumber);
        };
        wonderFloorPath = FindFloorPath(wonderName, floorNumber);
    }
    
    private String FindFloorPath(String wonderName, int floorNumber) {
        return "src/main/resources/game/wondersFloors/" + wonderName + "/Floor" + floorNumber + ".png";
    }
    
    public String GetFloorPath() {
        return wonderFloorPath;
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
    
    public void setBuildable(boolean isBuildable) {
        this.isBuildable = isBuildable;
    }
    
    public String GetRessourceRequirement() {
        return ressourceRequirement;
    }
    
    public boolean IsBuilt() {
        return isBuilt;
    }
    
    public void setAsBuilt() {
        wonderFloorPath =
                "src/main/resources/game/wondersFloors/" + wonderFloorPath.substring(39, wonderFloorPath.length() - 4) + "Built.png"; //Written By @Copilot
        isBuilt = true;
        isBuildable = false;
    }
}

