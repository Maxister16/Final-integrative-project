package Pool;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;

public class ScenePlayIce {
    private Scene scene;

    public ScenePlayIce(Stage primaryStage) {

        GameStatus.table.setImage(new Image("InteractiveObjectIMG/tableIceIMG.png"));

        ScenePlay scenePlay = new ScenePlay(primaryStage);
        scene = scenePlay.getScene();
        primaryStage.setScene(scenePlay.getScene());


    }

    public Scene getScene() {
        return this.scene;
    }
}