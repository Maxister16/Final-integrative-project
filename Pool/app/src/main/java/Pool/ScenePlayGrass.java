package Pool;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class ScenePlayGrass extends ScenePlay{

    //private Scene scene;

    public ScenePlayGrass() {

        GameStatus.table.setImage(new Image("InteractiveObjectIMG/tableGrassIMG.png"));
        //ScenePlay scenePlay = new ScenePlay(primaryStage);
       // GameStatus.positionObjects(scenePlay.getScene().getWidth(), scenePlay.getScene().getHeight());

    }

    /*public Scene getScene() {
        return this.scene;
    }*/
}

