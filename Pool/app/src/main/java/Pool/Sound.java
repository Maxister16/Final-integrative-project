package Pool;

import javafx.scene.Scene;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class Sound {
    
    public static MediaPlayer btnSound;
    public static MediaPlayer welcomeBgSound;
    public static MediaPlayer playBgSound;
    
    public static void initiateSound(){
        File btnFile = new File(GameStatus.CLIENT_LOCATION_OF_PROJECT+"/src/main/resources/sound/tok.mp3");
        btnSound = new MediaPlayer(new javafx.scene.media.Media(btnFile.toURI().toString()));
        btnSound.setVolume(0.05);

        File natureFile = new File(GameStatus.CLIENT_LOCATION_OF_PROJECT+"/src/main/resources/sound/nature.mp3");
        welcomeBgSound = new MediaPlayer(new javafx.scene.media.Media(natureFile.toURI().toString()));
        welcomeBgSound.setCycleCount(MediaPlayer.INDEFINITE);
        welcomeBgSound.setVolume(3);

        File songFile = new File(GameStatus.CLIENT_LOCATION_OF_PROJECT+"/src/main/resources/sound/Song.mp3");
        playBgSound = new MediaPlayer(new javafx.scene.media.Media(songFile.toURI().toString()));
        playBgSound.setVolume(0.08);
        playBgSound.setCycleCount(MediaPlayer.INDEFINITE);
    }

}
