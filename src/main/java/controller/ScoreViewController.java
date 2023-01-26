package controller;

import controller.game.FastSetup;
import game.player.Player;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public abstract class ScoreViewController {
    
    static Scene scoreScene;
    static Parent gameView;
    
    public static void setupScoreView(Collection<Integer> score, List<Player> listOfPlayers, Pane pane) {
        
        Scene scene = pane.getScene();
        Stage stage = (Stage) scene.getWindow();
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(ScoreViewController.class.getResource("/controller/ScoreBoard.fxml"))); // @Copilot
        try {
            gameView = loader.load();
        } catch (IOException e) {
        }
        stage.setScene(gameView.getScene());
        scoreScene = stage.getScene();
        
        List<Player> sortedListOfPlayers = sortPlayersByScore(score, listOfPlayers);
        displayPlayerScore(sortedListOfPlayers);
    }
    
    private static List<Player> sortPlayersByScore(Collection<Integer> score, List<Player> listOfPlayers){
        for (int i = 0; i < listOfPlayers.size(); i++) {
            for (int j = i + 1; j < listOfPlayers.size(); j++) {
                if (score.contains(listOfPlayers.get(i).getNumberOfVictoryPoints()) && score.contains(listOfPlayers.get(j).getNumberOfVictoryPoints())) {
                    if (listOfPlayers.get(i).getNumberOfVictoryPoints() < listOfPlayers.get(j).getNumberOfVictoryPoints()) {
                        Player temp = listOfPlayers.get(i);
                        listOfPlayers.set(i, listOfPlayers.get(j));
                        listOfPlayers.set(j, temp);
                    }
                }
            }
        }
        return listOfPlayers;
    }
    
    private static void displayPlayerScore(Iterable<Player> listOfPlayers) {
        int i = 0;
        for (Player player : listOfPlayers) {
            Text text = FastSetup.createNewText(player.getWonderName() + " : " + player.getNumberOfVictoryPoints(), 100, 400 + 100 * i, 100);
            scoreScene.getRoot().getChildrenUnmodifiable().add(text);
            i++;
        }
    }
    
}
