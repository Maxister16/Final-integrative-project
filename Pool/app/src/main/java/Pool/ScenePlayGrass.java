package Pool;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;


public class ScenePlayGrass extends ScenePlay{

    //private Scene scene;

    public ScenePlayGrass() throws IOException {

        GameStatus.table.setImage(2);
        //ScenePlay scenePlay = new ScenePlay(primaryStage);
       // GameStatus.positionObjects(scenePlay.getScene().getWidth(), scenePlay.getScene().getHeight());

    }

    /*public Scene getScene() {
        return this.scene;
    }*/
}

