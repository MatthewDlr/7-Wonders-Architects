package game.cards;

import game.cards.resources.BrickResourceCard;
import game.cards.resources.CoinsCard;
import game.cards.resources.ExperienceResourceCard;
import game.cards.resources.PaperResourceCard;
import game.cards.resources.StoneResourceCard;
import game.cards.resources.WoodResourceCard;
import game.cards.science.CompasScienceCard;
import game.cards.science.GearScienceCard;
import game.cards.science.TabletScienceCard;
import game.cards.shields.ClassicShieldCard;
import game.cards.shields.ShieldCard1Trumpet;
import game.cards.shields.ShieldCard2Trumpets;
import game.cards.victoryPoints.ClassicVictoryPointsCard;
import game.cards.victoryPoints.VictoryPointCardWithCat;
import java.util.ArrayList;
import java.util.Collections;

public class GameCardsStack {
    
    private final ArrayList<Card> gameCardsStack;
    
    public GameCardsStack() {
        gameCardsStack = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            gameCardsStack.add(new CoinsCard());
            gameCardsStack.add(new ShieldCard2Trumpets());
        }
        for (int i = 0; i < 4; i++) {
            gameCardsStack.add(new PaperResourceCard());
            gameCardsStack.add(new StoneResourceCard());
            gameCardsStack.add(new BrickResourceCard());
            gameCardsStack.add(new ExperienceResourceCard());
            gameCardsStack.add(new WoodResourceCard());
            gameCardsStack.add(new CoinsCard());
            gameCardsStack.add(new ClassicVictoryPointsCard());
            gameCardsStack.add(new VictoryPointCardWithCat());
            gameCardsStack.add(new VictoryPointCardWithCat());
            gameCardsStack.add(new ShieldCard1Trumpet());
            gameCardsStack.add(new ClassicShieldCard());
            gameCardsStack.add(new CompasScienceCard());
            gameCardsStack.add(new GearScienceCard());
            gameCardsStack.add(new TabletScienceCard());
        }
        Collections.shuffle(gameCardsStack);
        
    }
    
    public Card popCard() {
        return gameCardsStack.remove(0);
    }
    
    public boolean isEmpty() {
        return gameCardsStack.isEmpty();
    }
    
    public int getSize() {
        return gameCardsStack.size();
    }
    
    public String getTopCardPath() {
        return gameCardsStack.get(0).getCardPath();
    }
}


