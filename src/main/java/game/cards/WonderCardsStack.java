package game.cards;

import game.cards.resources.*;
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

public class WonderCardsStack {
    private final ArrayList<Card> cardsStack = new ArrayList<>();

    public WonderCardsStack(int[] cardsRepartition) {
        for (int i = 0; i < cardsRepartition[0]; i++) {
            cardsStack.add(new CoinsCard());
        }
        for (int i = 0; i < cardsRepartition[1]; i++) {
            cardsStack.add(new StoneResourceCard());
        }
        for (int i = 0; i < cardsRepartition[2]; i++) {
            cardsStack.add(new BrickResourceCard());
        }
        for (int i = 0; i < cardsRepartition[3]; i++) {
            cardsStack.add(new WoodResourceCard());
        }
        for (int i = 0; i < cardsRepartition[4]; i++) {
            cardsStack.add(new ExperienceResourceCard());
        }
        for (int i = 0; i < cardsRepartition[5]; i++) {
            cardsStack.add(new PaperResourceCard());
        }
        for (int i = 0; i < cardsRepartition[6]; i++) {
            cardsStack.add(new GearScienceCard());
        }
        for (int i = 0; i < cardsRepartition[7]; i++) {
            cardsStack.add(new CompasScienceCard());
        }
        for (int i = 0; i < cardsRepartition[8]; i++) {
            cardsStack.add(new TabletScienceCard());
        }
        for (int i = 0; i < cardsRepartition[9]; i++) {
            cardsStack.add(new ClassicVictoryPointsCard());
        }
        for (int i = 0; i < cardsRepartition[10]; i++) {
            cardsStack.add(new VictoryPointCardWithCat());
        }
        for (int i = 0; i < cardsRepartition[11]; i++) {
            cardsStack.add(new ClassicShieldCard());
        }
        for (int i = 0; i < cardsRepartition[12]; i++) {
            cardsStack.add(new ShieldCard1Trumpet());
        }
        for (int i = 0; i < cardsRepartition[13]; i++) {
            cardsStack.add(new ShieldCard2Trumpets());
        }
        Collections.shuffle(cardsStack);
    }

    public ArrayList<Card> GetCardsStack() {
        return cardsStack;
    }


}
