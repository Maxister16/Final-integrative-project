package Pool;

import javafx.scene.Scene;
import javafx.scene.media.MediaPlayer;
import javafx.application.HostServices;
import java.io.File;

public class Sound {
    
    public static MediaPlayer btnSound;
    public static MediaPlayer welcomeBgSound;
    public static MediaPlayer playBgSound;
    
    public static void initiateSound(){
//        File btnFile = new File(GameStatus.CLIENT_LOCATION_OF_PROJECT+"/src/main/resources/sound/tok.mp3");
//        btnSound = new MediaPlayer(new javafx.scene.media.Media(btnFile.toURI().toString()));
//        btnSound.setVolume(0.05);
//
//        File natureFile = new File(GameStatus.CLIENT_LOCATION_OF_PROJECT+"/src/main/resources/sound/nature.mp3");
//        welcomeBgSound = new MediaPlayer(new javafx.scene.media.Media(natureFile.toURI().toString()));
//        welcomeBgSound.setCycleCount(MediaPlayer.INDEFINITE);
//        welcomeBgSound.setVolume(3);
//
//        File songFile = new File(GameStatus.CLIENT_LOCATION_OF_PROJECT+"/src/main/resources/sound/Song.mp3");
//        playBgSound = new MediaPlayer(new javafx.scene.media.Media(songFile.toURI().toString()));
//        playBgSound.setVolume(0.08);
//        playBgSound.setCycleCount(MediaPlayer.INDEFINITE);
    }

    public static void muteSound(){
    //    btnSound.setVolume(0);
    //    welcomeBgSound.setVolume(0);
    //    playBgSound.setVolume(0);
    }
    public static void unmutesound(){
    //    btnSound.setVolume(0.05);
    //    welcomeBgSound.setVolume(3);
    //    playBgSound.setVolume(0.08);

    }

}
