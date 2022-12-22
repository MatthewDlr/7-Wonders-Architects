package game.player;


import game.wonders.Wonders;

public abstract class Player {
    Wonders wonders;

    public Player(Wonders playerWonder) {
        this.wonders = playerWonder;
    }


}