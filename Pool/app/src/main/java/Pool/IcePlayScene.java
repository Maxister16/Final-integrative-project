package Pool;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class IcePlayScene {
    private Scene scene;

    public IcePlayScene(Stage primaryStage, Table table) {
        new PlayScene(primaryStage, table);
    }

    public Scene getScene() {
        return this.scene;
    }
}