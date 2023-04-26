package Pool;

import javafx.scene.Scene;
import javafx.scene.media.MediaPlayer;
import javafx.application.HostServices;
import java.io.File;

public class Sound {
    
    public static MediaPlayer btnSound;
    public static MediaPlayer welcomeBgSound;
    public static MediaPlayer playBgSound;
    public static MediaPlayer yaySound;
    
    public static void initiateSound(){
        File btnFile = new File("Pool/app/src/main/resources/sound/tok.mp3");
        btnSound = new MediaPlayer(new javafx.scene.media.Media(btnFile.toURI().toString()));
        btnSound.setVolume(0.05);

        File natureFile = new File("Pool/app/src/main/resources/sound/nature.mp3");
        welcomeBgSound = new MediaPlayer(new javafx.scene.media.Media(natureFile.toURI().toString()));
        welcomeBgSound.setCycleCount(MediaPlayer.INDEFINITE);
        welcomeBgSound.setVolume(5);

        File songFile = new File("Pool/app/src/main/resources/sound/song.mp3");
        playBgSound = new MediaPlayer(new javafx.scene.media.Media(songFile.toURI().toString()));
        playBgSound.setVolume(0.08);
        playBgSound.setCycleCount(MediaPlayer.INDEFINITE);

        File yayFile = new File("Pool/app/src/main/resources/sound/yay.mp3");
        yaySound = new MediaPlayer(new javafx.scene.media.Media(yayFile.toURI().toString()));
        yaySound.setVolume(0.2);
        yaySound.setCycleCount(1);

        //in Main, when win pane is shown, play yaySound
        //in Main, on click of winpane replay btn (for orange and red), stop yaySound
    }

    public static void muteSound(){
        btnSound.setVolume(0);
        welcomeBgSound.setVolume(0);
        playBgSound.setVolume(0);
        yaySound.setVolume(0);
    }
    public static void unmutesound(){
        btnSound.setVolume(0.05);
        welcomeBgSound.setVolume(3);
        playBgSound.setVolume(0.08);
        yaySound.setVolume(0.2);
    }
}
