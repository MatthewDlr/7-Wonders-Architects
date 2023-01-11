package game.wonders;

import errorsCenter.DataChecking;
import game.cards.Card;
import game.cards.WonderCardsStack;
import java.util.ArrayList;

public abstract class Wonders {
    
    String wonderName, wonderDescription;
    ArrayList<WonderFloor> listOfFloors = new ArrayList<>();
    int numberOfFloorsBuilt, victoryPoints, floor2 = 1, floor3 = 2, floor4 = 3, floor5 = 4;
    ArrayList<Card> cardsStack;
    private final String backCardPath;
    private final String wonderDeckPath;
    
    
    public Wonders(String wonderName, String wonderDescription, int[] victoryPointsPerFloor, boolean[] floorThatHasEffect, int[] cardsStackRepartition) {
        this.wonderName = wonderName;
        this.wonderDescription = wonderDescription;
        backCardPath = findCardPath();
        wonderDeckPath = findDeckPath();
        for (int i = 0; i < 5; i++) {
            WonderFloor floor = new WonderFloor(wonderName, i + 1, victoryPointsPerFloor[i], floorThatHasEffect[i]);
            listOfFloors.add(floor);
        }
        WonderCardsStack stackOfCards = new WonderCardsStack(cardsStackRepartition);
        cardsStack = stackOfCards.GetCardsStack();
    }
    
    private String findCardPath() {
        String path = "src/main/resources/game/cards/Back/" + wonderName + "Card.png"; // @Copilot
        DataChecking.checkIfFileIsCorrect(path);
        return path;
    }
    
    private String findDeckPath() {
        String path = "src/main/resources/game/wondersDeck/" + wonderName + "Deck.png"; // @Copilot
        DataChecking.checkIfFileIsCorrect(path);
        return path;
    }
    
    public String getBackCardPath() {
        return backCardPath;
    }
    
    public String getWonderDeckPath() {
        return wonderDeckPath;
    }
    
    public boolean floorHasSpecialEffect(int floorNumber) {
        return listOfFloors.get(floorNumber - 1).HasSpecialEffect();
    }
    
    public void addBuiltFloor(int floorNumber) {
        listOfFloors.get(floorNumber - 1).setBuildable(false);
        listOfFloors.get(floorNumber - 1).setAsBuilt();
        victoryPoints += listOfFloors.get(floorNumber - 1).GetVictoryPoints();
        numberOfFloorsBuilt++;
        updateBuildableFloors();
    }
    
    public WonderFloor getFloor(int floorNumber) {
        return listOfFloors.get(floorNumber - 1);
    }
    
    public ArrayList<Integer> getBuildableFloors() {
        ArrayList<Integer> buildableFloors = new ArrayList<>();
        for (WonderFloor floor : listOfFloors) {
            if (floor.IsBuildable() && !floor.IsBuilt()) {
                buildableFloors.add(floor.GetFloorNumber());
            }
        }
        return buildableFloors;
    }
    
    protected void updateBuildableFloors() {
        System.out.println("updateBuildableFloors: " + numberOfFloorsBuilt);
        if (numberOfFloorsBuilt > floor5) {
            return;
        }
        listOfFloors.get(numberOfFloorsBuilt).setBuildable(true);
    }
    
    public int getNumberOfVictoryPoints() {
        return victoryPoints;
    }
    
    public String getName() {
        return wonderName;
    }
    
    public int getNumberOfFloorsBuilt() {
        return numberOfFloorsBuilt;
    }
    
    public Card getStackTopCard() {
        return cardsStack.remove(0);
    }
    
    public String getStackTopCardPath() {
        return cardsStack.get(0).getCardPath();
    }
    
    public void setFloorAsBuilt(int floorNumber) {
        listOfFloors.get(floorNumber - 1).setAsBuilt(); // Written by @Copilot
    }
    
}
