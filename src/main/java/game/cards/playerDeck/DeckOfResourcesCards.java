package game.cards.playerDeck;

import game.cards.Card;
import game.cards.resources.ResourcesCard;

import java.util.ArrayList;

public class DeckOfResourcesCards {
    private ArrayList<ResourcesCard> resourcesCardsSet;

    public DeckOfResourcesCards() {
        resourcesCardsSet = new ArrayList<>();
    }

    void AddCard(ResourcesCard cardToAdd) {
        resourcesCardsSet.add(  cardToAdd);
    }

    boolean IsResourcesCardsSetEmpty(){
        return resourcesCardsSet.isEmpty();
    }

    ResourcesCard GetCardFromResourcesCardsSet(int index){
        return resourcesCardsSet.get(index);
    }

    public ArrayList<String> GetListOfResourcesCombinations(){

        ArrayList<String> listOfResourcesCombinations = new ArrayList<>();
        int[] numberOfEachResource = CountNumberOfEachResources();
        int numberOfResourcesCards = resourcesCardsSet.size();
        int numberOfDifferentResources = GetNumberOfDifferentResources(numberOfEachResource) ;
        int maxNumberOfSameResources = GetMaxNumberOfTheSameResources(numberOfEachResource) ;

        for (int i = 2 ; i <= numberOfResourcesCards ; i++){

            if (i <= maxNumberOfSameResources){
                listOfResourcesCombinations.add(i + "=");
            }
            if (i <= numberOfDifferentResources){
                listOfResourcesCombinations.add(i + "≠");
            }
        }
        return listOfResourcesCombinations;
    }

    int[] CountNumberOfEachResources(){
        int[] numberOfEachRessource = new int[6];

        for (ResourcesCard resourcesCard : resourcesCardsSet) {
            switch (resourcesCard.GetCardName()) {
                case "Coins" -> numberOfEachRessource[0]++;
                case "Stone" -> numberOfEachRessource[1]++;
                case "Brick" -> numberOfEachRessource[2]++;
                case "Wood" -> numberOfEachRessource[3]++;
                case "Experience" -> numberOfEachRessource[4]++;
                case "Paper" -> numberOfEachRessource[5]++;
                default -> throw new IllegalArgumentException("Unrecognized resource type in DeckOfResourcesCards.resourcesCardsSet ");
            }
        }
        return numberOfEachRessource;
    }

    int GetNumberOfDifferentResources(int[] numberOfEachRessource){
        int numberOfDifferentResources = 0;
        for (int j : numberOfEachRessource) {
            if (j > 0) {
                numberOfDifferentResources++;
            }
        }
        return numberOfDifferentResources;
    }

    int GetMaxNumberOfTheSameResources(int[] numberOfEachRessource){
        int maxNumberOfTheSameResources = 0;
        int numberOfCoins = numberOfEachRessource[0];

        for (int i = 1; i < numberOfEachRessource.length; i++) {
            if (numberOfEachRessource[i] > maxNumberOfTheSameResources){
                maxNumberOfTheSameResources = numberOfEachRessource[i];
            }
        }
        return Math.max(numberOfCoins, maxNumberOfTheSameResources + numberOfCoins);
    }

    public void RemoveCardsFromDeckWithCombination(String combination){
        int numberOfCardsToRemove =  Integer.parseInt(combination.substring(0, 1));
        String combinationType = combination.substring(1);

        if (combinationType.equals("≠")){
            RemoveCardsOfDifferentResources(numberOfCardsToRemove);
        }
        if (combinationType.equals("=")){
            RemoveCardsOfSameResources(numberOfCardsToRemove);
        }
    }

    void RemoveCardsOfDifferentResources(int numberOfCardsToRemove) {
        ArrayList<String> listOfRemovedCards = new ArrayList<>();

        while (numberOfCardsToRemove > 0){
            for (ResourcesCard resourcesCard : resourcesCardsSet) {
                if (!listOfRemovedCards.contains(resourcesCard.GetCardName()) || resourcesCard.GetCardName().equals("Coins")){
                    resourcesCardsSet.remove(resourcesCard);
                    listOfRemovedCards.add(resourcesCard.GetCardName());
                    numberOfCardsToRemove--;
                    break;
                }
            }
            if (IsResourcesCardsSetEmpty() && numberOfCardsToRemove > 0){
                throw new IllegalArgumentException("Not enough cards to remove in DeckOfResourcesCards.RemoveCardsOfDifferentResources");
            }
        }
    }

    void RemoveCardsOfSameResources(int numberOfCardsToRemove) {
        int[] numberOfEachResource = CountNumberOfEachResources();
        int numberOfGoldCoins = numberOfEachResource[0];

        for (int i = 1; i < numberOfEachResource.length; i++) {
            if (numberOfEachResource[i] + numberOfGoldCoins >= numberOfCardsToRemove){
                String resourceTypeToRemove ;
                switch (i) {
                    case 1 -> resourceTypeToRemove = "Stone";
                    case 2 -> resourceTypeToRemove = "Brick";
                    case 3 -> resourceTypeToRemove = "Wood";
                    case 4 -> resourceTypeToRemove = "Experience";
                    case 5 -> resourceTypeToRemove = "Paper";
                    default -> throw new IllegalArgumentException("Unable to retrieve back the resource name from the index in DeckOfResourcesCards.RemoveCardsOfSameResources");
                }

                ArrayList<ResourcesCard> resourcesCardsCopy = (ArrayList<ResourcesCard>) resourcesCardsSet.clone();
                for (ResourcesCard resourcesCard : resourcesCardsCopy) {
                    if (resourcesCard.GetCardName().equals(resourceTypeToRemove) || resourcesCard.GetCardName().equals("Coins")){
                        resourcesCardsSet.remove(resourcesCard);
                        numberOfCardsToRemove--;
                        if (numberOfCardsToRemove == 0){
                            return;
                        }
                    }
                }
            }
        }
        if (numberOfCardsToRemove > 0){
            throw new IllegalArgumentException("Not enough cards to remove in DeckOfResourcesCards.RemoveCardsOfSameResources");
        }
    }

}

