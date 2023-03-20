
package Pool;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.awt.*;


public class ScenePlay {

            private Scene scene;

            public ScenePlay(Stage primaryStage) {

                GameStatus.initialize();
                Pane gamePane = new Pane();

                BorderPane borderPane = new BorderPane();
                GridPane gridPaneTop = new GridPane();
                GridPane gridPaneBot = new GridPane();
                gridPaneTop.setAlignment(Pos.TOP_CENTER);
                gridPaneBot.setAlignment(Pos.BOTTOM_LEFT);

                ImageView menu = new ImageView("ButtonIMG/MenuBtnIMG.png");
                ImageView redPlay = new ImageView("ButtonIMG/RedPlayBtnIMG.png");

                // Create Sliders and Buttons
                Slider forceSlider = new Slider(1, 10, 1);
                Slider angleSlider = new Slider(0.0, 359, 1.0);
                Button playButton = new Button("", redPlay);
                Button menuButton = new Button("",menu);

                menu.setPreserveRatio(true);
                menu.setFitHeight(50);
                redPlay.setPreserveRatio(true);
                redPlay.setFitHeight(70);


                //set button backgrounds and border transparent
                menuButton.setBackground(null);
                menuButton.setBorder(null);
                playButton.setBackground(null);
                playButton.setBorder(null);


                // Add Sliders and buttons to GridPane
                gridPaneBot.add(menuButton, 0, 0);
                gridPaneTop.add(forceSlider, 0, 0);
                gridPaneTop.add(angleSlider, 2, 0);
                gridPaneTop.add(playButton, 3, 0);

                // Create Layout
                StackPane layout = new StackPane();

                //?if I change these values the game Pane disappears
                layout.setMinWidth(900);
                layout.setMinHeight(600);

                //Put table together
                gamePane.getChildren().addAll(GameStatus.nets);
                gamePane.getChildren().addAll(GameStatus.listOfBalls);
                gamePane.getChildren().addAll(GameStatus.tableLines);
                gamePane.getChildren().addAll(GameStatus.table.getBackground(), GameStatus.table.getBorder());

                //Set background image


                ImageView bg = new ImageView("BackgroundIMG/PlayBgIMG.jpg");

                bg.setPreserveRatio(true);
                bg.setFitWidth(1350);
          //      backgroundPane.getChildren().add(bg);


                //Position Panes
                borderPane.getChildren().add(gamePane);
                borderPane.setTop(gridPaneTop);
                borderPane.setBottom(gridPaneBot);
                layout.getChildren().addAll(bg,borderPane);

                GameStatus.positionObjects(layout.getMinWidth(), layout.getMinHeight());
                this.scene = new Scene(layout);

                playButton.setOnAction((event) -> {
                    int force = (int) forceSlider.getValue();
                    int angle = (int) angleSlider.getValue();
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
