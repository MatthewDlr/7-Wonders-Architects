package game.cards.playerDeck;

import controller.game.PlayerActions;
import game.cards.science.ScienceCard;
import java.util.ArrayList;
import javafx.scene.image.ImageView;

public class DeckOfScienceCards {
    
    private final ArrayList<ScienceCard> scienceCardsSet;
    private final ArrayList<ScienceCard> compasCardsSet;
    private final ArrayList<ScienceCard> gearCardsSet;
    private final ArrayList<ScienceCard> tabletCardsSet;
    private PlayerCardsDeck playerCardsDeck;
    private ArrayList<ScienceCard> scienceCardsCombination;
    private ArrayList<ImageView> scienceCardsSetUI;
    
    public DeckOfScienceCards(PlayerCardsDeck playerCardsDeck) {
        scienceCardsSet = new ArrayList<>();
        compasCardsSet = new ArrayList<>();
        gearCardsSet = new ArrayList<>();
        tabletCardsSet = new ArrayList<>();
        this.playerCardsDeck = playerCardsDeck;
        scienceCardsCombination = new ArrayList<>();
        scienceCardsSetUI = new ArrayList<>();
    }
    
    void addCard(ScienceCard cardToAdd) {
        scienceCardsSet.add(cardToAdd);
        (switch (cardToAdd.getCardName()) {
            case "Compas" -> compasCardsSet;
            case "Gear" -> gearCardsSet;
            case "Tablet" -> tabletCardsSet;
            default -> throw new IllegalArgumentException("Error in ScienceCard.addCard : " + cardToAdd.getCardName() + " is not a valid card");
        }).add(cardToAdd);
        if (checkForCombinations()) {
            PlayerActions.IS_TOUR_FINISHED = false;
            int i = 0;
            for (ScienceCard card : scienceCardsCombination) {
                
                for (ImageView scienceCard : scienceCardsSetUI) {
                    System.out.println("scienceCard: " + scienceCard.getId() + " card: " + card.getCardName());
                    if (scienceCard.getId().contains(card.getCardName())) {
                        scienceCard.setVisible(false);
                        scienceCardsSetUI.remove(scienceCard);
                        scienceCardsSet.remove(card);
                        break;
                    }
                }
            }
            playerCardsDeck.getProgressToken();
        }
    }
    
    private boolean checkForCombinations() {
        
        scienceCardsCombination.clear();
        if (scienceCardsSet.size() < 2) {
            return false;
        }
        if (compasCardsSet.size() == 2) {
            removeFromSet(compasCardsSet);
            scienceCardsCombination.add(compasCardsSet.get(0));
            scienceCardsCombination.add(compasCardsSet.get(1));
            compasCardsSet.clear();
            return true;
        }
        if (gearCardsSet.size() == 2) {
            removeFromSet(gearCardsSet);
            scienceCardsCombination.add(gearCardsSet.get(0));
            scienceCardsCombination.add(gearCardsSet.get(1));
            gearCardsSet.clear();
            return true;
        }
        if (tabletCardsSet.size() == 2) {
            removeFromSet(tabletCardsSet);
            scienceCardsCombination.add(tabletCardsSet.get(0));
            scienceCardsCombination.add(tabletCardsSet.get(1));
            tabletCardsSet.clear();
            return true;
        }
        if (compasCardsSet.size() == 1 && gearCardsSet.size() == 1 && tabletCardsSet.size() == 1) {
            removeFromSet(compasCardsSet);
            removeFromSet(gearCardsSet);
            removeFromSet(tabletCardsSet);
            scienceCardsCombination.add(compasCardsSet.get(0));
            scienceCardsCombination.add(gearCardsSet.get(0));
            scienceCardsCombination.add(tabletCardsSet.get(0));
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
    
    private void removeFromSet(Iterable<ScienceCard> setToRemoveFrom) {
        for (ScienceCard cardToRemove : setToRemoveFrom) {
            scienceCardsSet.remove(cardToRemove);
        }
    }
    
    
    public int getNumberOfCards() {
        return scienceCardsSet.size();
    }
    
    public void addUIscienceCards(ImageView cardsToAdd) {
        scienceCardsSetUI.add(cardsToAdd);
    }
}
