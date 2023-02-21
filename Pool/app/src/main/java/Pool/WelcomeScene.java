package Pool;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class WelcomeScene {
    private Scene scene;

    public WelcomeScene (Stage primaryStage) {
        Button button = new Button("Go to Normal Play Scene");
        StackPane layout = new StackPane();
        layout.getChildren().add(button);
        scene = new Scene(layout, 300, 250);

        button.setOnAction(event -> {
            // create instance of Scene1 class
            NormalPlayScene normalPlayScreen = new NormalPlayScene(primaryStage);

            // set primary stage to first scene
            primaryStage.setScene(normalPlayScreen.getScene());
        });
    }

    public Scene getScene() {
        return scene;
    }
}
