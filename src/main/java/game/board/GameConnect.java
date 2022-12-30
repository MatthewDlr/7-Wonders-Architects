package game.board;

import controller.game.GameController;

public class GameConnect {

    public static GameController gameController;

    static public void setGameController(GameController controller) {
        gameController = controller;
    }

}
