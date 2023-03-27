package Pool;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class GrassPlayScene {
    private Scene scene;

    public GrassPlayScene(Stage primaryStage, Table table) {
        PlayScene playScene = new PlayScene(primaryStage, table);
        GameStatus.table.setImage(2);
        scene = playScene.getScene();
        primaryStage.setScene(playScene.getScene());
    }

    public Scene getScene() {
        return this.scene;
    }
}

