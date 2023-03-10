module game {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    
    exports game;
    opens game to javafx.fxml;
    
    exports controller;
    opens controller to javafx.fxml;
    exports game.board;
    opens game.board to javafx.fxml;
    exports controller.game;
    opens controller.game to javafx.fxml;
}