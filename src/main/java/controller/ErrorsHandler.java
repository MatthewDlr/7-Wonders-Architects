package controller;

import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public abstract class ErrorsHandler {

    public static void CheckIfFileIsCorrect(String path) {
        if (!new File(path).exists()) {
            throw new IllegalArgumentException("Error in UI Files Check : Failed to load " + path);
        }

        if (!Files.isReadable(Paths.get(path))) {
            throw new IllegalArgumentException("Error in UI Files Check : " + path + " is not readable");
        }

    }

    public static void CheckForMediaErrors(MediaPlayer mediaPlayer) {

        if (mediaPlayer.getError() != null) {
            throw new IllegalArgumentException("Error in Media Files Check : " + mediaPlayer.getError().getMessage());
        }
    }




}

