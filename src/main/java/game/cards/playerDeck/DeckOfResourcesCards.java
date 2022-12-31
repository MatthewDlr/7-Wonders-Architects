package game.cards.playerDeck;

import game.cards.resources.ResourcesCard;
import java.util.ArrayList;
import java.util.Collection;

public class DeckOfResourcesCards {
    
    private final ArrayList<ResourcesCard> resourcesCardsSet;
    private PlayerDeck playerDeck;
    
    public DeckOfResourcesCards(PlayerDeck playerDeck) {
        resourcesCardsSet = new ArrayList<>();
        this.playerDeck = playerDeck;
    }
    
    void addCard(ResourcesCard cardToAdd) {
        resourcesCardsSet.add(cardToAdd);
    }
    
    boolean isResourcesCardsSetEmpty() {
        return resourcesCardsSet.isEmpty();
    }
    
    ResourcesCard getCardFromResourcesCardsSet(int index) {
        return resourcesCardsSet.get(index);
    }
    
    public ArrayList<String> getListOfResourcesCombinations() {
        
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
    
    int[] countNumberOfEachResources() {
        int[] numberOfEachRessource = new int[6];
        
        for (ResourcesCard resourcesCard : resourcesCardsSet) {
            numberOfEachRessource[switch (resourcesCard.GetCardName()) {
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
    
    int getNumberOfDifferentResources(int[] numberOfEachRessource) {
        int numberOfDifferentResources = 0;
        for (int j : numberOfEachRessource) {
            if (j > 0) {
                numberOfDifferentResources++;
            }
        }
        return numberOfDifferentResources;
    }
    
    int getMaxNumberOfTheSameResources(int[] numberOfEachRessource) {
        int maxNumberOfTheSameResources = 0;
        int numberOfCoins = numberOfEachRessource[0];
        
        for (int i = 1; i < numberOfEachRessource.length; i++) {
            if (numberOfEachRessource[i] > maxNumberOfTheSameResources) {
                maxNumberOfTheSameResources = numberOfEachRessource[i];
            }
        }
        return Math.max(numberOfCoins, maxNumberOfTheSameResources + numberOfCoins);
    }
    
    public void removeCardsFromDeckWithCombination(String combination) {
        int numberOfCardsToRemove = Integer.parseInt(combination.substring(0, 1));
        String combinationType = combination.substring(1);
        
        if (combinationType.equals("≠")) {
            removeCardsOfDifferentResources(numberOfCardsToRemove);
        }
        if (combinationType.equals("=")) {
            removeCardsOfSameResources(numberOfCardsToRemove);
        }
    }
    
    void removeCardsOfDifferentResources(int numberOfCardsToRemove) {
        int cardsToRemove = numberOfCardsToRemove;
        Collection<String> listOfRemovedCards = new ArrayList<>();
        
        while (cardsToRemove > 0) {
            for (ResourcesCard resourcesCard : resourcesCardsSet) {
                if (!listOfRemovedCards.contains(resourcesCard.GetCardName()) || resourcesCard.GetCardName().equals("Coins")) {
                    resourcesCardsSet.remove(resourcesCard);
                    listOfRemovedCards.add(resourcesCard.GetCardName());
                    cardsToRemove--;
                    break;
                }
            }
            if (isResourcesCardsSetEmpty() && (cardsToRemove > 0)) {
                throw new IllegalArgumentException("Not enough cards to remove in DeckOfResourcesCards.RemoveCardsOfDifferentResources");
            }
        }
    }
    
    void removeCardsOfSameResources(int numberOfCardsToRemove) {
        int cardsToRemove = numberOfCardsToRemove;
        int[] numberOfEachResource = countNumberOfEachResources();
        int numberOfGoldCoins = numberOfEachResource[0];
        
        for (int i = 1; i < numberOfEachResource.length; i++) {
            if ((numberOfEachResource[i] + numberOfGoldCoins) >= cardsToRemove) {
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
                    if (resourcesCard.GetCardName().equals(resourceTypeToRemove) || resourcesCard.GetCardName().equals("Coins")) {
                        resourcesCardsSet.remove(resourcesCard);
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
    
}

