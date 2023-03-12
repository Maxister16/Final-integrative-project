package Pool;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ScenePlayNormal {
    private Scene scene;

    public ScenePlayNormal(Stage primaryStage) {
        
        GameStatus.table.setImage(new Image("InteractiveObjectIMG/tableNormalIMG.png"));
        
        ScenePlay scenePlay = new ScenePlay(primaryStage);
        scene = scenePlay.getScene();
        primaryStage.setScene(scenePlay.getScene());
    }

    public Scene getScene() {
        return this.scene;
    }
}


