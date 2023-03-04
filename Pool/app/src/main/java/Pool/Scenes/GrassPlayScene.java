package Pool.Scenes;

import Pool.Table;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GrassPlayScene {
    private Scene scene;

    public GrassPlayScene(Stage primaryStage, Table table) {
        new PlayScene(primaryStage, table);
    }

    public Scene getScene() {
        return this.scene;
    }
}

