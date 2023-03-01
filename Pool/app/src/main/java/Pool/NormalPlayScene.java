package Pool;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class NormalPlayScene {
    private Scene scene;

    public NormalPlayScene(Stage primaryStage, Table table) {
        new PlayScene(primaryStage, table);
    }

    public Scene getScene() {
        return this.scene;
    }
}


