package Pool;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class WelcomeScene {
    private Scene scene;

    public WelcomeScene(Stage primaryStage, Table table) {
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
            NormalPlayScene normalPlayScene = new NormalPlayScene(primaryStage, table);
            primaryStage.setScene(normalPlayScene.getScene());
        });
        grassBtn.setOnAction((event) -> {
            GrassPlayScene grassPlayScene = new GrassPlayScene(primaryStage, table);
            primaryStage.setScene(grassPlayScene.getScene());
        });
        iceBtn.setOnAction((event) -> {
            IcePlayScene icePlayScene = new IcePlayScene(primaryStage, table);
            primaryStage.setScene(icePlayScene.getScene());
        });
    }

    public Scene getScene() {
        return this.scene;
    }
}
