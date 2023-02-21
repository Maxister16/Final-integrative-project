package Pool;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class GrassPlayScene {
    private Scene scene;

    public GrassPlayScene(Stage primaryStage) {
        Button button = new Button("Go to Scene 1");
        StackPane layout = new StackPane();
        layout.getChildren().add(button);
        scene = new Scene(layout, 300, 250);
    }

    public Scene getScene() {
        return scene;
    }
}
