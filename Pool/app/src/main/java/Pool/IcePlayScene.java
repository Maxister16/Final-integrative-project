package Pool;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class IcePlayScene {
    private Scene scene;

    public IcePlayScene(Stage primaryStage, Table table) {
        PlayScene playScene = new PlayScene(primaryStage, table);
        GameStatus.table.setImage(1);
        scene = playScene.getScene();
        primaryStage.setScene(playScene.getScene());
    }

    public Scene getScene() {
        return this.scene;
    }
}