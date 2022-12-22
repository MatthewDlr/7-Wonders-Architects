package game.wonders;

import game.cards.Card;
import game.cards.WonderCardsStack;

import java.util.ArrayList;

public abstract class Wonders {

    String wonderName, wonderDescription;
    ArrayList<WonderFloor> listOfFloors = new ArrayList<>();
    int numberOfFloorsBuilt = 0, victoryPoints = 0, floor2 = 1, floor3 = 2, floor4 = 3, floor5 = 4;
    ArrayList<Card> cardsStack ;


    public Wonders(String wonderName, String wonderDescription, int[] victoryPointsPerFloor, boolean[] floorThatHasEffect, int[] cardsTypeRepartition ) {
        this.wonderName = wonderName;
        this.wonderDescription = wonderDescription;
        for (int i = 0; i < 5; i++) {
            WonderFloor floor = new WonderFloor(i+1 , victoryPointsPerFloor[i], floorThatHasEffect[i] );
            listOfFloors.add(floor);
        }
        WonderCardsStack stackOfCards = new WonderCardsStack(cardsTypeRepartition);
        this.cardsStack = stackOfCards.GetCardsStack();
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
        if (numberOfFloorsBuilt > floor5){
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

}
