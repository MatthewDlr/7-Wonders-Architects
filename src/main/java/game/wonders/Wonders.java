package game.wonders;

import game.cards.Card;
import game.cards.WonderCardsStack;

import java.io.File;
import java.util.ArrayList;

public abstract class Wonders {

    String wonderName, wonderDescription;
    ArrayList<WonderFloor> listOfFloors = new ArrayList<>();
    int numberOfFloorsBuilt = 0, victoryPoints = 0, floor2 = 1, floor3 = 2, floor4 = 3, floor5 = 4;
    ArrayList<Card> cardsStack;
    private String BackCardPath ;
    private String WonderDeckPath;


    public Wonders(String wonderName, String wonderDescription, int[] victoryPointsPerFloor, boolean[] floorThatHasEffect, int[] cardsStackRepartition) {
        this.wonderName = wonderName;
        this.wonderDescription = wonderDescription;
        this.BackCardPath = FindCardPath();
        this.WonderDeckPath = FindDeckPath();
        for (int i = 0; i < 5; i++) {
            WonderFloor floor = new WonderFloor(wonderName ,i + 1, victoryPointsPerFloor[i], floorThatHasEffect[i]);
            listOfFloors.add(floor);
        }
        WonderCardsStack stackOfCards = new WonderCardsStack(cardsStackRepartition);
        this.cardsStack = stackOfCards.GetCardsStack();
    }

    private String FindCardPath() {
        String path = "src/main/resources/game/cards/Back/" + wonderName + "Card.png"; // @Copilot
        CheckIfFileExist(path);
        return path ;
    }

    private String FindDeckPath() {
        String path = "src/main/resources/game/wondersDeck/" + wonderName + "Deck.png"; // @Copilot
        CheckIfFileExist(path);
        return path;
    }

    private void CheckIfFileExist(String path) {
        if (!new File(path).exists()) {
            throw new IllegalArgumentException("Error in Wonders cards Files Check : Failed to load " + path );
        }
    }

    public String GetBackCardPath() {
        return BackCardPath;
    }

    public String GetWonderDeckPath() {
        return WonderDeckPath;
    }

    public boolean FloorHasSpecialEffect(int floorNumber) {
        return listOfFloors.get(floorNumber - 1).HasSpecialEffect();
    }

    public void AddBuiltFloor(int floorNumber) {
        listOfFloors.get(floorNumber - 1).SetBuildable(false);
        victoryPoints += listOfFloors.get(floorNumber - 1).GetVictoryPoints();
        numberOfFloorsBuilt++;
        UpdateBuildableFloors();
    }

    public WonderFloor GetFloor(int floorNumber) {
        return listOfFloors.get(floorNumber - 1);
    }

    public ArrayList<Integer> GetBuildableFloors() {
        ArrayList<Integer> buildableFloors = new ArrayList<>();
        for (WonderFloor floor : listOfFloors) {
            if (floor.IsBuildable()) {
                buildableFloors.add(floor.GetFloorNumber());
            }
        }
        return buildableFloors;
    }

    protected void UpdateBuildableFloors() {
        if (numberOfFloorsBuilt > floor5) {
            return;
        }
        listOfFloors.get(numberOfFloorsBuilt).SetBuildable(true);
    }

    public int GetVictoryPoints() {
        return victoryPoints;
    }

    public String GetName() {
        return wonderName;
    }

    public int GetNumberOfFloorsBuilt() {
        return numberOfFloorsBuilt;
    }

    public Card GetStackTopCard() {
        return cardsStack.remove(0);
    }

    public void SetFloorAsBuilt(int floorNumber) {
        listOfFloors.get(floorNumber - 1).SetAsBuilt(); // Written by @Copilot
    }

}
