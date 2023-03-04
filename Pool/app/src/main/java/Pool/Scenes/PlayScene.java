
package Pool.Scenes;

import Pool.GameStatus;
import Pool.Table;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

    public class PlayScene {
        private Scene scene;

        public PlayScene(Stage primaryStage, Table table) {
            GridPane gridPane = new GridPane();
            gridPane.setAlignment(Pos.TOP_CENTER);
            Slider forceSlider = new Slider(1.0, 5.0, 1.0);
            Slider angleSlider = new Slider(0.0, 359.0, 1.0);
            Button playButton = new Button("Play");
            gridPane.add(forceSlider, 0, 0);
            gridPane.add(angleSlider, 2, 0);
            gridPane.add(playButton, 3, 0);
            StackPane layout = new StackPane();
            layout.getChildren().add(gridPane);
            this.scene = new Scene(layout, 300.0, 250.0);
            playButton.setOnAction((event) -> {
                int force = (int)forceSlider.getValue();
                int angle = (int)angleSlider.getValue();
                System.out.println("Force: " + force + "Angle: " + angle);
            });
        }

        public Scene getScene() {
            return this.scene;
        }
    }

