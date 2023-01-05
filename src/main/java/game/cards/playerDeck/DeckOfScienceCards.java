package game.cards.playerDeck;

import game.cards.science.ScienceCard;
import java.util.ArrayList;

public class DeckOfScienceCards {
    
    private final ArrayList<ScienceCard> scienceCardsSet;
    private final ArrayList<ScienceCard> compasCardsSet;
    private final ArrayList<ScienceCard> gearCardsSet;
    private final ArrayList<ScienceCard> tabletCardsSet;
    private boolean combinationExist;
    private PlayerCardsDeck playerCardsDeck;
    
    public DeckOfScienceCards(PlayerCardsDeck playerCardsDeck) {
        scienceCardsSet = new ArrayList<>();
        compasCardsSet = new ArrayList<>();
        gearCardsSet = new ArrayList<>();
        tabletCardsSet = new ArrayList<>();
        combinationExist = false;
        this.playerCardsDeck = playerCardsDeck;
    }
    
    void addCard(ScienceCard cardToAdd) {
        scienceCardsSet.add(cardToAdd);
        (switch (cardToAdd.getCardName()) {
            case "Compas" -> compasCardsSet;
            case "Gear" -> gearCardsSet;
            case "Tablet" -> tabletCardsSet;
            default -> throw new IllegalArgumentException("Error in ScienceCard.addCard : " + cardToAdd.getCardName() + " is not a valid card");
        }).add(cardToAdd);
        combinationExist = checkForCombinations();
    }
    
    int getNumberOfScienceCards() {
        return scienceCardsSet.size();
    }
    
    public boolean doesCombinationExist() {
        return combinationExist;
    }
    
    private boolean checkForCombinations() {
        
        if (scienceCardsSet.size() < 2) {
            return false;
        }
        if (compasCardsSet.size() == 2) {
            removeFromSet(compasCardsSet);
            compasCardsSet.clear();
            return true;
        }
        if (gearCardsSet.size() == 2) {
            removeFromSet(gearCardsSet);
            gearCardsSet.clear();
            return true;
        }
        if (tabletCardsSet.size() == 2) {
            removeFromSet(tabletCardsSet);
            tabletCardsSet.clear();
            return true;
        }
        if ((compasCardsSet.size() == 1) && (gearCardsSet.size() == 1) && (tabletCardsSet.size() == 1)) {
            removeFromSet(compasCardsSet);
            removeFromSet(gearCardsSet);
            removeFromSet(tabletCardsSet);
            compasCardsSet.clear();
            gearCardsSet.clear();
            tabletCardsSet.clear();
            return true;
        }
        if ((compasCardsSet.size() > 2) || (gearCardsSet.size() > 2) || (tabletCardsSet.size() > 2)) {
            throw new IllegalArgumentException("Unexpected exceed of Science cards in DeckOfScienceCards.CheckForCombinations");
        }
        return false;
    }
    
    private void removeFromSet(Iterable<ScienceCard> setToRemoveFrom) {
        for (ScienceCard cardToRemove : setToRemoveFrom) {
            scienceCardsSet.remove(cardToRemove);
        }
    }
    
    
    public int getNumberOfCards() {
        return scienceCardsSet.size();
    }
}
