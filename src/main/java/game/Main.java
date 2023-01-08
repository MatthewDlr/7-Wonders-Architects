package game;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {
    
    public static void main(String[] args) {
        launch();
    }
    
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/controller/FirstView.fxml")));
        Font font = Font.loadFont(getClass().getResource("/fonts/JetBrainsMono-Medium.ttf").toExternalForm(), 12);
        URL url = getClass().getResource("/fonts/font.css");
        root.getStylesheets().add(url.toExternalForm());
        Scene scene = new Scene(root, 1276, 718);
        stage.setTitle("7 Wonders Architect Legends Edition");
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/Setup/7WondersLogo.png"))));
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.setScene(scene);
        stage.show();
    }
}