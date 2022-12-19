package game.player;

import game.Game;


public abstract class Player extends Game {

    protected boolean playerIsAI;
    private String playerName;

    public String getPlayerName() {
        return playerName;
    }
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

}