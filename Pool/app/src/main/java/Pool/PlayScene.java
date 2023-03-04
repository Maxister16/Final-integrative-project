
package Pool;

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

            GridPane gridPaneTop = new GridPane();
            GridPane gridPaneBot = new GridPane();
            gridPaneTop.setAlignment(Pos.TOP_CENTER);
            gridPaneBot.setAlignment(Pos.BOTTOM_LEFT);

            Slider forceSlider = new Slider(1.0, 5.0, 1.0);
            Slider angleSlider = new Slider(0.0, 359.0, 1.0);
            Button playButton = new Button("Play");
            Button menuButton = new Button("Menu");

            gridPaneBot.add(menuButton, 0, 0);

            gridPaneTop.add(forceSlider, 0, 0);
            gridPaneTop.add(angleSlider, 2, 0);
            gridPaneTop.add(playButton, 3, 0);

            StackPane layout = new StackPane();
            layout.setMinWidth(900);
            layout.setMinHeight(600);

            layout.getChildren().addAll(gridPaneTop, gridPaneBot);
            this.scene = new Scene(layout);

            playButton.setOnAction((event) -> {
                int force = (int)forceSlider.getValue();
                int angle = (int)angleSlider.getValue();
                System.out.println("Force: " + force + "Angle: " + angle);
                //set values for force and angle
            });

            menuButton.setOnAction((event) -> {
                MenuScene menuScene = new MenuScene(primaryStage, table);
                primaryStage.setScene(menuScene.getScene());
            });
        }

        public Scene getScene() {
            return this.scene;
        }
    }

