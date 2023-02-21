package Pool;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class NormalPlayScene {
    private Scene scene;

    public NormalPlayScene (Stage primaryStage) {
        Button button = new Button("Go to Welcome Scene");
        StackPane layout = new StackPane();
        layout.getChildren().add(button);
        scene = new Scene(layout, 300, 250);

        button.setOnAction(event -> {
           WelcomeScene welcomeScene = new WelcomeScene(primaryStage);
            primaryStage.setScene(welcomeScene.getScene());
        });
    }

    public Scene getScene() {
        return scene;
    }
}

