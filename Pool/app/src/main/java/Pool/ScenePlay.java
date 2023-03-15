
package Pool;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

//make it extend scene so i dont need a private scene and a get scene method caus its already a scene

    public class ScenePlay{
        private Scene scene;

        public ScenePlay(Stage primaryStage) {
            
            GameStatus.initialize();

            BorderPane borderPane = new BorderPane();
            GridPane gridPaneTop = new GridPane();
            GridPane gridPaneBot = new GridPane();
            gridPaneTop.setAlignment(Pos.TOP_CENTER);
            gridPaneBot.setAlignment(Pos.BOTTOM_LEFT);

            // Create Sliders and Buttons
            Slider forceSlider = new Slider(1, 10, 1);
            Slider angleSlider = new Slider(0.0, 359, 1.0);
            Button playButton = new Button("Play");
            Button menuButton = new Button("Menu");

            // Add Sliders and buttons to GridPane
            gridPaneBot.add(menuButton, 0, 0);
            gridPaneTop.add(forceSlider, 0, 0);
            gridPaneTop.add(angleSlider, 2, 0);
            gridPaneTop.add(playButton, 3, 0);

            // Create Layout
            StackPane layout = new StackPane();
            layout.getChildren().addAll(GameStatus.nets);
            layout.getChildren().addAll(GameStatus.listOfBalls);
            layout.getChildren().addAll(GameStatus.table.getBackground(),GameStatus.table.getBorder());


            borderPane.setCenter(layout);
            borderPane.setTop(gridPaneTop);
            borderPane.setBottom(gridPaneBot);

            GameStatus.setZorder();

            this.scene = new Scene(borderPane);

            playButton.setOnAction((event) -> {
                int force = (int)forceSlider.getValue();
                int angle = (int)angleSlider.getValue();
                System.out.println("Force: " + force + "Angle: " + angle);
                //set values for force and angle
            });

            menuButton.setOnAction((event) -> {
                SceneMenu sceneMenu = new SceneMenu(primaryStage);
                primaryStage.setScene(sceneMenu.getScene());
            });
        }

        public Scene getScene() {
            return this.scene;
        }
    }

