package controller;

import errorsCenter.DataChecking;
import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public abstract class SoundEngine {
    
    public static MediaPlayer playWarCorSound() {
        MediaPlayer sound = setupMediaPlayer("src/main/resources/musics/WarMusic2.mp3");
        sound.play();
        return sound;
    }
    
    public static MediaPlayer playWarBattleSound() {
        MediaPlayer sound = setupMediaPlayer("src/main/resources/musics/WarMusic1.mp3");
        sound.play();
        return sound;
    }
    
    public static MediaPlayer playCatSound() {
        MediaPlayer sound = setupMediaPlayer("src/main/resources/musics/Cat.mp3");
        sound.play();
        return sound;
    }
    
    private static MediaPlayer setupMediaPlayer(String path) {
        File soundFile = new File(path);
        DataChecking.checkIfFileIsCorrect(String.valueOf(soundFile));
        Media soundMedia = new Media(soundFile.toURI().toString());
        MediaPlayer sound = new MediaPlayer(soundMedia);
        sound.setOnEndOfMedia(() -> sound.stop());
        return sound;
    }
}
