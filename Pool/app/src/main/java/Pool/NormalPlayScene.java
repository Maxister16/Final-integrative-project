package Pool;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class NormalPlayScene {
    private Scene scene;

    public NormalPlayScene(Stage primaryStage, Table table) {
        PlayScene playScene = new PlayScene(primaryStage, table);
        GameStatus.table.setImage(new Image("InteractiveObjectIMG/tableNormalIMG.png"));
        scene = playScene.getScene();
        primaryStage.setScene(playScene.getScene());
    }

    public Scene getScene() {
        return this.scene;
    }
}


