package controller;

import errorsCenter.DataChecking;
import java.io.File;
import java.util.ArrayList;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public abstract class SoundEngine {
    
    private static ArrayList<MediaPlayer> listOfSoundPlayers = new ArrayList<>();
    
    public static void stopALlSounds() {
        for (MediaPlayer soundPlayer : listOfSoundPlayers) {
            soundPlayer.stop();
        }
    }
    
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
    
    public static MediaPlayer playMainThemeAlternate() {
        MediaPlayer sound = setupMediaPlayer("src/main/resources/musics/7Wonders - Main Theme.mp3");
        sound.play();
        return sound;
    }
    
    public static MediaPlayer playMainTheme() {
        MediaPlayer sound = setupMediaPlayer("src/main/resources/musics/AC - Main Theme.mp3");
        sound.play();
        return sound;
    }
    
    private static MediaPlayer setupMediaPlayer(String path) {
        File soundFile = new File(path);
        DataChecking.checkIfFileIsCorrect(String.valueOf(soundFile));
        Media soundMedia = new Media(soundFile.toURI().toString());
        MediaPlayer sound = new MediaPlayer(soundMedia);
        sound.setOnEndOfMedia(() -> sound.stop());
        listOfSoundPlayers.add(sound);
        return sound;
    }
}
