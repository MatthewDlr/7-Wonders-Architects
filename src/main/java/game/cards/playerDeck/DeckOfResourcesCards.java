package game.cards.playerDeck;

import game.cards.Card;
import game.cards.resources.ResourcesCard;
import java.util.ArrayList;
import java.util.Collection;
import javafx.scene.image.ImageView;

public class DeckOfResourcesCards {
    
    private final ArrayList<ResourcesCard> resourcesCardsSet;
    public ArrayList<ImageView> resourcesCardsSetUI;
    private PlayerCardsDeck playerCardsDeck;
    
    public DeckOfResourcesCards(PlayerCardsDeck playerCardsDeck) {
        resourcesCardsSet = new ArrayList<>();
        this.playerCardsDeck = playerCardsDeck;
        resourcesCardsSetUI = new ArrayList<>();
    }
    
    void addCard(ResourcesCard cardToAdd) {
        resourcesCardsSet.add(cardToAdd);
        checkIfNextFloorCanBeBuilt();
    }
    
    private void checkIfNextFloorCanBeBuilt() {
        ArrayList<Integer> listOfBuildableFloor = playerCardsDeck.wonder.getBuildableFloors();
        System.out.println("listOfBuildableFloor: " + listOfBuildableFloor);
        if (listOfBuildableFloor.isEmpty() || isResourcesCardsSetEmpty()) {
            return;
        }
        ArrayList<String> listOfResourcesCombinations = getListOfResourcesCombinations();
        for (int i : listOfBuildableFloor) {
            Collection<String> listOfResourcesRequired = new ArrayList<>();
            listOfResourcesRequired.add(playerCardsDeck.wonder.getFloor(i).GetRessourceRequirement());
            
            for (String resourcesCombination : listOfResourcesCombinations) {
                if (listOfResourcesRequired.contains(resourcesCombination)) {
                    playerCardsDeck.wonder.addBuiltFloor(i);
                    removeCardsFromDeckWithCombination(resourcesCombination);
                    playerCardsDeck.setFloorUIasBuilt(i);
                }
            }
        }
        
    }
    
    boolean isResourcesCardsSetEmpty() {
        return resourcesCardsSet.isEmpty();
    }
    
    ResourcesCard getCardFromResourcesCardsSet(int index) {
        return resourcesCardsSet.get(index);
    }
    
    private ArrayList<String> getListOfResourcesCombinations() {
        
        ArrayList<String> listOfResourcesCombinations = new ArrayList<>();
        int[] numberOfEachResource = countNumberOfEachResources();
        int numberOfResourcesCards = resourcesCardsSet.size();
        int numberOfDifferentResources = getNumberOfDifferentResources(numberOfEachResource);
        int maxNumberOfSameResources = getMaxNumberOfTheSameResources(numberOfEachResource);
        
        for (int i = 2; i <= numberOfResourcesCards; i++) {
            
            if (i <= maxNumberOfSameResources) {
                listOfResourcesCombinations.add(i + "=");
            }
            if (i <= numberOfDifferentResources) {
                listOfResourcesCombinations.add(i + "≠");
            }
        }
        return listOfResourcesCombinations;
    }
    
    private int[] countNumberOfEachResources() {
        int[] numberOfEachRessource = new int[6];
        
        for (ResourcesCard resourcesCard : resourcesCardsSet) {
            numberOfEachRessource[switch (resourcesCard.getCardName()) {
                case "Coins" -> 0;
                case "Stone" -> 1;
                case "Brick" -> 2;
                case "Wood" -> 3;
                case "Experience" -> 4;
                case "Paper" -> 5;
                default -> throw new IllegalArgumentException("Unrecognized resource type in DeckOfResourcesCards.resourcesCardsSet ");
            }]++;
        }
        return numberOfEachRessource;
    }
    
    private int getNumberOfDifferentResources(int[] numberOfEachRessource) {
        int numberOfDifferentResources = 0;
        for (int j : numberOfEachRessource) {
            if (j > 0) {
                numberOfDifferentResources++;
            }
        }
        return numberOfDifferentResources;
    }
    
    private int getMaxNumberOfTheSameResources(int[] numberOfEachRessource) {
        int maxNumberOfTheSameResources = 0;
        int numberOfCoins = numberOfEachRessource[0];
        
        for (int i = 1; i < numberOfEachRessource.length; i++) {
            if (numberOfEachRessource[i] > maxNumberOfTheSameResources) {
                maxNumberOfTheSameResources = numberOfEachRessource[i];
            }
        }
        return Math.max(numberOfCoins, maxNumberOfTheSameResources + numberOfCoins);
    }
    
    private void removeCardsFromDeckWithCombination(String combination) {
        int numberOfCardsToRemove = Integer.parseInt(combination.substring(0, 1));
        String combinationType = combination.substring(1);
        
        if (combinationType.equals("≠")) {
            removeCardsOfDifferentResources(numberOfCardsToRemove);
        }
        if (combinationType.equals("=")) {
            removeCardsOfSameResources(numberOfCardsToRemove);
        }
    }
    
    private void removeCardsOfDifferentResources(int numberOfCardsToRemove) {
        int cardsToRemove = numberOfCardsToRemove;
        Collection<String> listOfRemovedCards = new ArrayList<>();
        
        while (cardsToRemove > 0) {
            for (ResourcesCard resourcesCard : resourcesCardsSet) {
                if (!listOfRemovedCards.contains(resourcesCard.getCardName()) || resourcesCard.getCardName().equals("Coins")) {
                    resourcesCardsSet.remove(resourcesCard);
                    removeUICards(resourcesCard);
                    listOfRemovedCards.add(resourcesCard.getCardName());
                    cardsToRemove--;
                    break;
                }
            }
            if (isResourcesCardsSetEmpty() && cardsToRemove > 0) {
                throw new IllegalArgumentException("Not enough cards to remove in DeckOfResourcesCards.RemoveCardsOfDifferentResources");
            }
        }
    }
    
    private void removeCardsOfSameResources(int numberOfCardsToRemove) {
        int cardsToRemove = numberOfCardsToRemove;
        int[] numberOfEachResource = countNumberOfEachResources();
        int numberOfGoldCoins = numberOfEachResource[0];
        
        for (int i = 1; i < numberOfEachResource.length; i++) {
            if (numberOfEachResource[i] + numberOfGoldCoins >= cardsToRemove) {
                String resourceTypeToRemove;
                resourceTypeToRemove = switch (i) {
                    case 1 -> "Stone";
                    case 2 -> "Brick";
                    case 3 -> "Wood";
                    case 4 -> "Experience";
                    case 5 -> "Paper";
                    default -> throw new IllegalArgumentException(
                            "Unable to retrieve back the resource name from the index in DeckOfResourcesCards.RemoveCardsOfSameResources");
                };
                
                Iterable<ResourcesCard> resourcesCardsCopy = (ArrayList<ResourcesCard>) resourcesCardsSet.clone();
                for (ResourcesCard resourcesCard : resourcesCardsCopy) {
                    if (resourcesCard.getCardName().equals(resourceTypeToRemove) || resourcesCard.getCardName().equals("Coins")) {
                        resourcesCardsSet.remove(resourcesCard);
                        removeUICards(resourcesCard);
                        cardsToRemove--;
                        if (cardsToRemove == 0) {
                            return;
                        }
                    }
                }
            }
        }
        if (cardsToRemove > 0) {
            throw new IllegalArgumentException("Not enough cards to remove in DeckOfResourcesCards.RemoveCardsOfSameResources");
        }
    }
    
    public int getNumberOfCards() {
        return resourcesCardsSet.size();
    }
    
    public void addUIcards(ImageView cardsToAdd) {
        resourcesCardsSetUI.add(cardsToAdd);
    }
    
    private void removeUICards(Card cardToRemove) {
        for (ImageView card : resourcesCardsSetUI) {
            System.out.println(card.getId());
            if (card.getId().contains(cardToRemove.getCardName())) {
                card.setOpacity(0);
                resourcesCardsSetUI.remove(card);
                return;
            }
        }
    }
}

