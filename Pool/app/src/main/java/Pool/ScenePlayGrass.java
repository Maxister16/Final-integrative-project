package Pool;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ScenePlayGrass{

    private Scene scene;

    public ScenePlayGrass(Stage primaryStage) {

        GameStatus.table.setImage(new Image("tableGrassIMG.png"));

        ScenePlay scenePlay = new ScenePlay(primaryStage);
        scene = scenePlay.getScene();
        primaryStage.setScene(scenePlay.getScene());
    }

    public Scene getScene() {
        return this.scene;
    }
}

