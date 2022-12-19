package game.player;

public class HumanPlayer extends Player {

    public HumanPlayer(String playerName) {
        this.playerIsAI = false;
        this.setPlayerName(playerName);
    }

}
