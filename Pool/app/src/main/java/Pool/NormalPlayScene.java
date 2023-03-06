package Pool;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class NormalPlayScene {
    private Scene scene;

    public NormalPlayScene(Stage primaryStage, Table table) {
        
        GameStatus.table.setImage(new Image("InteractiveObjectIMG\\tableNormalIMG.png"));
        
        PlayScene playScene = new PlayScene(primaryStage, table);
        scene = playScene.getScene();
        primaryStage.setScene(playScene.getScene());
    }

    public Scene getScene() {
        return this.scene;
    }
}


