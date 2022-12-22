package game.cards.playerDeck;

import game.cards.science.ScienceCard;

import java.util.ArrayList;

public class DeckOfScienceCards {
    private ArrayList<ScienceCard> scienceCardsSet;
    private ArrayList<ScienceCard> compasCardsSet;
    private ArrayList<ScienceCard> gearCardsSet;
    private ArrayList<ScienceCard> tabletCardsSet;

    private boolean CombinationExist;

    public DeckOfScienceCards() {
        scienceCardsSet = new ArrayList<>();
        compasCardsSet = new ArrayList<>();
        gearCardsSet = new ArrayList<>();
        tabletCardsSet = new ArrayList<>();
        CombinationExist = false;
    }

    void AddCard(ScienceCard cardToAdd) {
        scienceCardsSet.add(cardToAdd);
        switch (cardToAdd.GetCardName()) {
            case "Compas" -> compasCardsSet.add(cardToAdd);
            case "Gear" -> gearCardsSet.add(cardToAdd);
            case "Tablet" -> tabletCardsSet.add(cardToAdd);
            default -> throw new IllegalArgumentException("Unrecognized Science Card Symbol in DeckOfScienceCards.AddCard");
        }
        CombinationExist = CheckForCombinations();
    }

    int GetNumberOfScienceCards() {
        return scienceCardsSet.size();
    }

    public boolean GetIfCombinationExist() {
        return CombinationExist;
    }

    boolean CheckForCombinations(){

        if (scienceCardsSet.size() < 2) {
            return false;
        }
        if (compasCardsSet.size() == 2) {
            RemoveFromSet(compasCardsSet);
            compasCardsSet.clear();
            return true;
        }
        if (gearCardsSet.size() == 2) {
            RemoveFromSet(gearCardsSet);
            gearCardsSet.clear();
            return true;
        }
        if (tabletCardsSet.size() == 2) {
            RemoveFromSet(tabletCardsSet);
            tabletCardsSet.clear();
            return true;
        }
        if (compasCardsSet.size() == 1 && gearCardsSet.size() == 1 && tabletCardsSet.size() == 1) {
            RemoveFromSet(compasCardsSet);
            RemoveFromSet(gearCardsSet);
            RemoveFromSet(tabletCardsSet);
            compasCardsSet.clear();
            gearCardsSet.clear();
            tabletCardsSet.clear();
            return true;
        }
        if (compasCardsSet.size() > 2 || gearCardsSet.size() > 2 || tabletCardsSet.size() > 2) {
            throw new IllegalArgumentException("Unexpected exceed of Science cards in DeckOfScienceCards.CheckForCombinations");
        }
    return false;
    }

    private void RemoveFromSet(ArrayList<ScienceCard> setToRemoveFrom) {
        for (ScienceCard cardToRemove : setToRemoveFrom) {
            scienceCardsSet.remove(cardToRemove);
        }
    }




}
