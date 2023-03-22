
package Pool;

import javafx.animation.FadeTransition;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.File;
import javafx.scene.paint.Color;


public class ScenePlay {

            private Scene scene;

            public ScenePlay(Stage primaryStage)  {

                GameStatus.initialize();
                File btnFile = new File("Pool/app/src/main/resources/sound/tok.mp3");
                MediaPlayer btnSound = new MediaPlayer(new javafx.scene.media.Media(btnFile.toURI().toString()));
                btnSound.setVolume(0.5);

                //Create Play Scene Panes
                Pane gamePane = new Pane();
                BorderPane borderPane = new BorderPane();
                GridPane gridPaneTop = new GridPane();
                GridPane gridPaneBot = new GridPane();
                gridPaneTop.setAlignment(Pos.TOP_CENTER);
                gridPaneBot.setAlignment(Pos.BOTTOM_LEFT);


                //Set Size constraints in top Grid Pane
                ColumnConstraints columnTop1 = new ColumnConstraints(200);
                columnTop1.setHalignment(HPos.CENTER);
                ColumnConstraints columnTop2 = new ColumnConstraints(200);
                columnTop2.setHalignment(HPos.CENTER);
                ColumnConstraints columnTop3 = new ColumnConstraints(100);
                columnTop3.setHalignment(HPos.CENTER);
                RowConstraints rowTop1 = new RowConstraints(100);
                rowTop1.setValignment(VPos.CENTER);

                gridPaneTop.getColumnConstraints().addAll(columnTop1, columnTop2, columnTop3);
                gridPaneTop.getRowConstraints().addAll(rowTop1);

                //Set Size constraints in bottom Grid Pane

                ColumnConstraints columnBot1 = new ColumnConstraints(190);
                columnBot1.setHalignment(HPos.CENTER);
                RowConstraints rowBot1 = new RowConstraints(60);
                rowBot1.setValignment(VPos.CENTER);

                gridPaneBot.getColumnConstraints().addAll(columnBot1);
                gridPaneBot.getRowConstraints().addAll(rowBot1);

                //Play scene Button ImageViews
                ImageView menu = new ImageView("ButtonIMG/MenuBtnIMG.png");
                ImageView redPlay = new ImageView("ButtonIMG/RedPlayBtnIMG.png");

                // Create Sliders and Buttons
                Slider forceSlider = new Slider(1, 10, 1);
                Slider angleSlider = new Slider(0.0, 359, 1.0);
                Button playButton = new Button("", redPlay);
                Button menuButton = new Button("",menu);

                menu.setPreserveRatio(true);
                menu.setFitHeight(45);
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
                gridPaneTop.add(angleSlider, 1, 0);
                gridPaneTop.add(playButton, 2, 0);

                // Create Layout
                StackPane layout = new StackPane();

                //?if I change these values the game Pane disappears
                layout.setMinWidth(900);
                layout.setMinHeight(600);

                //Put table together
                GameStatus.positionObjects(780,1350);
                gamePane.getChildren().addAll(GameStatus.nets);
                gamePane.getChildren().addAll(GameStatus.listOfBalls);
                gamePane.getChildren().addAll(GameStatus.tableLines);
                gamePane.getChildren().addAll(GameStatus.table.getBackground(), GameStatus.table.getBorder());


                //Set background image

                ImageView bg = new ImageView("BackgroundIMG/PlayBgIMG.jpg");

                bg.setPreserveRatio(true);
                bg.setFitWidth(1350);
          //    backgroundPane.getChildren().add(bg);
                FadeTransition fade = new FadeTransition();
                //setting the duration for the Fade transition
                fade.setDuration(Duration.millis(200));
                fade.setNode(layout);
                fade.setFromValue(0.5);
                fade.setToValue(1.0);
                fade.play();

                //Position Panes
               // borderPane.setCenter(gamePane);
                borderPane.setTop(gridPaneTop);
                borderPane.setBottom(gridPaneBot);
                layout.getChildren().addAll(bg,borderPane);

                GameStatus.positionObjects(layout.getWidth(), layout.getHeight());
                this.scene = new Scene(layout);

                playButton.setOnAction((event) -> {
                    int force = (int) forceSlider.getValue();
                    int angle = (int) angleSlider.getValue();
                    System.out.println("Force: " + force + "Angle: " + angle);

                });

                playButton.setOnMouseEntered((event) -> {
                    redPlay.setFitHeight(80);
                    btnSound.play();
                });
                playButton.setOnMouseExited((event) -> {
                    redPlay.setFitHeight(70);
                    btnSound.stop();
                });

                menuButton.setOnAction((event) -> {

                            GridPane gridPane = new GridPane();
                            gridPane.setAlignment(Pos.CENTER);

                            ImageView resume = new ImageView("ButtonIMG/ResumeBtnIMG.png");
                            resume.setPreserveRatio(true);
                            resume.setFitHeight(60);
                            ImageView home = new ImageView("ButtonIMG/HomeBtnIMG.png");
                            home.setPreserveRatio(true);
                            home.setFitHeight(60);
                            ImageView exit = new ImageView("ButtonIMG/ExitBtnIMG.png");
                            exit.setPreserveRatio(true);
                            exit.setFitHeight(60);
                            ImageView physicsOn = new ImageView("ButtonIMG/PhysicsOnBtnIMG.png");
                            physicsOn.setPreserveRatio(true);
                            physicsOn.setFitHeight(120);
                            ImageView physicsOff = new ImageView("ButtonIMG/PhysicsOffBtnIMG.png");
                            physicsOff.setPreserveRatio(true);
                            physicsOff.setFitHeight(120);

                            Button resumeButton = new Button("",resume);
                            Button homeButton = new Button("",home);
                            Button exitButton = new Button("",exit);
                            Button physicsButton = new Button("",physicsOn);

                            homeButton.setBackground(null);
                            homeButton.setBorder(null);
                            resumeButton.setBackground(null);
                            resumeButton.setBorder(null);
                            exitButton.setBackground(null);
                            exitButton.setBorder(null);
                            physicsButton.setBackground(null);
                            physicsButton.setBorder(null);

                            gridPane.setHalignment(resumeButton, HPos.CENTER);
                            gridPane.setHalignment(homeButton, HPos.CENTER);
                            gridPane.setHalignment(exitButton, HPos.CENTER);
                            gridPane.setHalignment(physicsButton, HPos.CENTER);

                            gridPane.setTranslateY(30);

                            gridPane.add(resumeButton, 0, 0);
                            gridPane.add(homeButton, 0, 1);
                            gridPane.add(exitButton, 0, 2);
                            gridPane.add(physicsButton, 0, 3);

                            ColumnConstraints menuColumn = new ColumnConstraints(200);
                            columnTop1.setHalignment(HPos.CENTER);
                            RowConstraints menuRow1 = new RowConstraints(65);
                            menuRow1.setValignment(VPos.CENTER);
                            RowConstraints menuRow2 = new RowConstraints(65);
                            menuRow2.setValignment(VPos.CENTER);
                            RowConstraints menuRow3 = new RowConstraints(65);
                            menuRow3.setValignment(VPos.CENTER);
                            RowConstraints menuRow4 = new RowConstraints(110);
                            menuRow4.setValignment(VPos.CENTER);
                            gridPane.getColumnConstraints().add(menuColumn);
                            gridPane.getRowConstraints().addAll(menuRow1,menuRow2,menuRow3,menuRow4);

                            //ImageViews
                            ImageView menuBg = new ImageView("BackgroundIMG/MenuBgIMG.png");

                            //Set background image
                            menuBg.setPreserveRatio(true);
                            menuBg.setFitWidth(500);
                            Rectangle rectangle = new Rectangle(1350, 700);
                            rectangle.setFill(Color.WHITE);
                            rectangle.setTranslateY(-42);
                            fade.setNode(rectangle);
                            fade.setFromValue(0.0);
                            fade.setToValue(0.4);
                            fade.setDuration(Duration.millis(100));
                            fade.play();

                            layout.getChildren().addAll(rectangle,menuBg,gridPane);

                           // backgroundLayout.getChildren().addAll(backgroundPane, gridPane);

                            resumeButton.setOnAction((menuEvent) -> {
                                fade.setFromValue(0.4);
                                fade.setToValue(0.0);
                                fade.setDuration(Duration.millis(100));
                                fade.play();
                                layout.getChildren().removeAll(rectangle, gridPane, menuBg);

                            });

                            resumeButton.setOnMouseEntered((menuEvent) -> {
                                resume.setFitHeight(70);
                                btnSound.play();
                            });

                            resumeButton.setOnMouseExited((menuEvent) -> {
                                resume.setFitHeight(60);
                                btnSound.stop();
                            });

                            homeButton.setOnMouseEntered((menuEvent) -> {
                                home.setFitHeight(70);
                                btnSound.play();
                            });

                            homeButton.setOnMouseExited((menuEvent) -> {
                                home.setFitHeight(60);
                                btnSound.stop();
                            });

                            exitButton.setOnMouseEntered((menuEvent) -> {
                                exit.setFitHeight(70);
                                btnSound.play();
                            });

                            exitButton.setOnMouseExited((menuEvent) -> {
                                exit.setFitHeight(60);
                                btnSound.stop();
                            });

                            physicsButton.setOnMouseEntered((menuEvent) -> {
                                physicsOn.setFitHeight(130);
                                btnSound.play();
                            });

                            physicsButton.setOnMouseExited((menuEvent) -> {
                                physicsOn.setFitHeight(120);
                                btnSound.stop();
                            });

                            homeButton.setOnAction((menuEvent) -> {
                                SceneWelcome sceneWelcome = new SceneWelcome(primaryStage);
                                primaryStage.setScene(sceneWelcome.getScene());
                            });

                            exitButton.setOnAction((menuEvent) -> {
                                primaryStage.close();
                            });
                        //        togglePhysicsButton(physicsButton);


                        //    public void togglePhysicsButton(Button physicsButton) {
                        //        // Set the initial text of the button
                        //        physicsButton.setText("Physics on");
                        //
                        //        // Add an event handler to the button
                        //        physicsButton.setOnAction((event) -> {
                        //            // Check the current text of the button and toggle it
                        //            if (physicsButton.getText().equals("on")) {
                        //                physicsButton.setText("Physics off");
                        //            } else {
                        //                physicsButton.setText("Physics on");
                        //            }
                        //        });
                        //    }
                });

                menuButton.setOnMouseEntered((event) -> {
                    menu.setFitHeight(50);
                    btnSound.play();
                });
                menuButton.setOnMouseExited((event) -> {
                    menu.setFitHeight(45);
                    btnSound.stop();
                });



            }

            public Scene getScene() {
                return this.scene;
            }
        }
