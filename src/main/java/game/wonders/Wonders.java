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
    private final String BackCardPath;
    private final String WonderDeckPath;
    
    
    public Wonders(String wonderName, String wonderDescription, int[] victoryPointsPerFloor, boolean[] floorThatHasEffect, int[] cardsStackRepartition) {
        this.wonderName = wonderName;
        this.wonderDescription = wonderDescription;
        BackCardPath = findCardPath();
        WonderDeckPath = findDeckPath();
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
        return BackCardPath;
    }
    
    public String getWonderDeckPath() {
        return WonderDeckPath;
    }
    
    public boolean floorHasSpecialEffect(int floorNumber) {
        return listOfFloors.get(floorNumber - 1).HasSpecialEffect();
    }
    
    public void addBuiltFloor(int floorNumber) {
        listOfFloors.get(floorNumber - 1).SetBuildable(false);
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
            if (floor.IsBuildable()) {
                buildableFloors.add(floor.GetFloorNumber());
            }
        }
        return buildableFloors;
    }
    
    protected void updateBuildableFloors() {
        if (numberOfFloorsBuilt > floor5) {
            return;
        }
        listOfFloors.get(numberOfFloorsBuilt).SetBuildable(true);
    }
    
    public int getVictoryPoints() {
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
    
    public void setFloorAsBuilt(int floorNumber) {
        listOfFloors.get(floorNumber - 1).SetAsBuilt(); // Written by @Copilot
    }
    
}
