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
    
    //initiate all the mediaplayers to the correct volume and file
    public static void initiateSound(){
        File btnFile = new File(GameStatus.CLIENT_LOCATION_OF_PROJECT+"/src/main/resources/sound/tok.mp3");
        btnSound = new MediaPlayer(new javafx.scene.media.Media(btnFile.toURI().toString()));
        btnSound.setVolume(0.5);

        File natureFile = new File(GameStatus.CLIENT_LOCATION_OF_PROJECT+"/src/main/resources/sound/nature.mp3");
        welcomeBgSound = new MediaPlayer(new javafx.scene.media.Media(natureFile.toURI().toString()));
        welcomeBgSound.setCycleCount(MediaPlayer.INDEFINITE);
        welcomeBgSound.setVolume(100);

        File songFile = new File(GameStatus.CLIENT_LOCATION_OF_PROJECT+"/src/main/resources/sound/song.mp3");
        playBgSound = new MediaPlayer(new javafx.scene.media.Media(songFile.toURI().toString()));
        playBgSound.setVolume(0.5);
        playBgSound.setCycleCount(MediaPlayer.INDEFINITE);

        File yayFile = new File(GameStatus.CLIENT_LOCATION_OF_PROJECT+"/src/main/resources/sound/yay.mp3");
        yaySound = new MediaPlayer(new javafx.scene.media.Media(yayFile.toURI().toString()));
        yaySound.setVolume(0.2);
        yaySound.setCycleCount(1);
        
    }

    public static void muteSound(){
        btnSound.setVolume(0);
        welcomeBgSound.setVolume(0);
        playBgSound.setVolume(0);
        yaySound.setVolume(0);
    }
    public static void unmutesound(){
        btnSound.setVolume(0.5);
        welcomeBgSound.setVolume(100);
        playBgSound.setVolume(0.5);
        yaySound.setVolume(0.2);
    }
}
