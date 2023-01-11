package errorsCenter;

import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;


public abstract class ErrorsHandler {
    
    public static void handleErrorsInVideo(MediaPlayer mediaPlayer, String path, MediaView mediaView) {
        
        Runnable event;
        if (mediaPlayer.getOnEndOfMedia() != null) {
            event = mediaPlayer.getOnEndOfMedia(); // @OpenAI
        } else {
            event = null;
        }
        
        mediaPlayer.setOnError(() -> {
            System.out.println("Video Error avoided : " + path);
            
            File file = new File(path);
            Media media = new Media(file.toURI().toString());
            MediaPlayer mediaPlayer2 = new MediaPlayer(media);
            mediaView.setMediaPlayer(mediaPlayer2);
            
            mediaPlayer2.setOnEndOfMedia(() -> {
                if (event != null) {
                    event.run(); // @OpenAI
                }
            });
            handleErrorsInVideo(mediaPlayer2, path, mediaView);
            mediaPlayer2.play();
        });
    }
    
    
}

