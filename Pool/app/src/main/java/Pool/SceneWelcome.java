package Pool;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class SceneWelcome {
    private Scene scene;

    public SceneWelcome(Stage primaryStage) {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        Button normalBtn = new Button("Normal");
        Button grassBtn = new Button("Grass");
        Button iceBtn = new Button("Ice");
        gridPane.add(normalBtn, 0, 0);
        gridPane.add(grassBtn, 2, 0);
        gridPane.add(iceBtn, 3, 0);
        StackPane layout = new StackPane();
        layout.getChildren().addAll(gridPane);
        this.scene = new Scene(layout, 300.0, 250.0);
        normalBtn.setOnAction((event) -> {
            ScenePlayNormal scenePlayNormal = new ScenePlayNormal(primaryStage);
            primaryStage.setScene(scenePlayNormal.getScene());
        });
        grassBtn.setOnAction((event) -> {
            ScenePlayGrass scenePlayGrass = new ScenePlayGrass(primaryStage);
            primaryStage.setScene(scenePlayGrass.getScene());
        });
        iceBtn.setOnAction((event) -> {
            ScenePlayIce scenePlayIce = new ScenePlayIce(primaryStage);
            primaryStage.setScene(scenePlayIce.getScene());
        });
    }

    public Scene getScene() {
        return this.scene;
    }
}
