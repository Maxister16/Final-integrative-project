
package Pool;

import javafx.animation.FadeTransition;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
                btnSound.setVolume(0.05);

                File songFile = new File("Pool/app/src/main/resources/sound/Song.mp3");
                MediaPlayer playBgSound = new MediaPlayer(new javafx.scene.media.Media(songFile.toURI().toString()));
                playBgSound.play();
                playBgSound.setVolume(0.08);
                playBgSound.setCycleCount(MediaPlayer.INDEFINITE);

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
                gridPaneTop.setTranslateY(130);

                // Create Layout
                StackPane layout = new StackPane();

                //?if I change these values the game Pane disappears
                layout.setMinWidth(900);
                layout.setMinHeight(600);

                //Put table together
                GameStatus.positionObjects(0,0);
                gamePane.getChildren().addAll(GameStatus.nets);
                gamePane.getChildren().addAll(GameStatus.listOfBalls);
                gamePane.getChildren().addAll(GameStatus.tableLines);
                gamePane.getChildren().addAll(GameStatus.table.getBackground(), GameStatus.table.getBorder());



                //Set background image

                ImageView bg = new ImageView("BackgroundIMG/PlayBgIMG.jpg");

                bg.setPreserveRatio(true);
                bg.setFitWidth(1350);
                FadeTransition fade = new FadeTransition();
                //setting the duration for the Fade transition
                fade.setDuration(Duration.millis(200));
                fade.setNode(layout);
                fade.setFromValue(0.5);
                fade.setToValue(1.0);
                fade.play();

                //Basket panes
                Pane redBasketPane = new Pane();
                Pane orangeBasketPane = new Pane();
                CustomAnimation redBaskets = new CustomAnimation("red",5);
                CustomAnimation orangeBaskets = new CustomAnimation("orange",5);
                redBaskets.setPreserveRatio(true);
                redBaskets.setFitHeight(300);
                redBaskets.setTranslateX(10);
                redBaskets.setTranslateY(-40);
                redBaskets.nextFrame();
                redBaskets.nextFrame();
                redBaskets.nextFrame();
                redBaskets.nextFrame();
                redBasketPane.getChildren().add(redBaskets);

                orangeBaskets.setPreserveRatio(true);
                orangeBaskets.setFitHeight(300);

                orangeBaskets.nextFrame();
                orangeBasketPane.getChildren().add(orangeBaskets);

                //Team name pane
                Pane teamNamePane = new Pane();
                CustomAnimation teamName = new CustomAnimation("team",2);
                teamName.setPreserveRatio(true);
                teamName.nextFrame();
                teamName.setFitHeight(165);
                teamNamePane.getChildren().add(teamName);
                teamNamePane.setTranslateX(30);
                teamNamePane.setTranslateY(-140);

                gridPaneTop.getChildren().add(teamNamePane);



                //Position Panes
                borderPane.setCenter(gamePane);
                borderPane.setLeft(redBasketPane);
                borderPane.setRight(orangeBasketPane);
                gamePane.setTranslateY(610);
                gamePane.setTranslateX(500);
                borderPane.setTop(gridPaneTop);

                borderPane.setBottom(gridPaneBot);
                layout.getChildren().addAll(bg,borderPane);

                GameStatus.positionObjects(layout.getWidth(), layout.getHeight());
                this.scene = new Scene(layout);

                playButton.setOnAction((event) -> {
                    double force = forceSlider.getValue();
                    double angle = angleSlider.getValue();
                    System.out.println("Force: " + force + "Angle: " + angle);
                    //CueStick.hitAnim(force);
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
                            GridPane physicsButtonPane = new GridPane();
                            physicsButtonPane.setAlignment(Pos.CENTER);

                            ImageView resume = new ImageView("ButtonIMG/ResumeBtnIMG.png");
                            resume.setPreserveRatio(true);
                            resume.setFitHeight(60);
                            ImageView home = new ImageView("ButtonIMG/HomeBtnIMG.png");
                            home.setPreserveRatio(true);
                            home.setFitHeight(60);
                            ImageView exit = new ImageView("ButtonIMG/ExitBtnIMG.png");
                            exit.setPreserveRatio(true);
                            exit.setFitHeight(60);

                            ImageView physicsLabel = new ImageView("ButtonIMG/PhysicsLableIMG.png");
                            physicsLabel.setPreserveRatio(true);
                            physicsLabel.setFitHeight(60);
                            ImageView physicsOn = new ImageView("ButtonIMG/OnBtnClickedIMG.png");
                            physicsOn.setPreserveRatio(true);
                            physicsOn.setFitHeight(60);
                            ImageView physicsOff = new ImageView("ButtonIMG/OnBtnIMG.png");
                            physicsOff.setPreserveRatio(true);
                            physicsOff.setFitHeight(60);

                            Button resumeButton = new Button("",resume);
                            Button homeButton = new Button("",home);
                            Button exitButton = new Button("",exit);
                            Button physicsOnButton = new Button("",physicsOn);
                            Button physicsOffButton = new Button("",physicsOff);
                            Label physicsLable = new Label("",physicsLabel);

                            homeButton.setBackground(null);
                            homeButton.setBorder(null);
                            resumeButton.setBackground(null);
                            resumeButton.setBorder(null);
                            exitButton.setBackground(null);
                            exitButton.setBorder(null);
                            physicsOnButton.setBackground(null);
                            physicsOffButton.setBackground(null);

                            gridPane.setHalignment(resumeButton, HPos.CENTER);
                            gridPane.setHalignment(homeButton, HPos.CENTER);
                            gridPane.setHalignment(exitButton, HPos.CENTER);
                            physicsButtonPane.setHalignment(physicsOnButton, HPos.CENTER);
                            physicsButtonPane.setHalignment(physicsOffButton, HPos.CENTER);

                            gridPane.setTranslateY(30);
                            physicsButtonPane.setTranslateX(-30);

                            gridPane.add(resumeButton, 0, 0);
                            gridPane.add(homeButton, 0, 1);
                            gridPane.add(exitButton, 0, 2);
                            gridPane.add(physicsLabel, 0, 3);

                            physicsButtonPane.add(physicsOnButton, 1, 1);
                            physicsButtonPane.add(physicsOffButton, 2, 1);
                            gridPane.add(physicsButtonPane,0, 4);


                            ColumnConstraints menuColumn = new ColumnConstraints(200);
                            columnTop1.setHalignment(HPos.CENTER);
                            RowConstraints menuRow1 = new RowConstraints(65);
                            menuRow1.setValignment(VPos.CENTER);
                            RowConstraints menuRow2 = new RowConstraints(65);
                            menuRow2.setValignment(VPos.CENTER);
                            RowConstraints menuRow3 = new RowConstraints(65);
                            menuRow3.setValignment(VPos.CENTER);
                            RowConstraints menuRow4 = new RowConstraints(65);
                            menuRow4.setValignment(VPos.CENTER);
                            RowConstraints menuRow5 = new RowConstraints(65);
                            menuRow5.setValignment(VPos.CENTER);
                            gridPane.getColumnConstraints().add(menuColumn);
                            gridPane.getRowConstraints().addAll(menuRow1,menuRow2,menuRow3,menuRow4,menuRow5);

                            ColumnConstraints physicsColumn1 = new ColumnConstraints(50);
                            physicsColumn1.setHalignment(HPos.CENTER);
                            ColumnConstraints physicsColumn2 = new ColumnConstraints(85);
                            physicsColumn2.setHalignment(HPos.CENTER);
                            RowConstraints physicsRow = new RowConstraints(20);
                            physicsRow.setValignment(VPos.CENTER);
                            physicsButtonPane.getColumnConstraints().addAll(physicsColumn1,physicsColumn2);
                            physicsButtonPane.getRowConstraints().add(physicsRow);

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

                            physicsOnButton.setOnMouseEntered((menuEvent) -> {
                                physicsOn.setFitHeight(70);
                                btnSound.play();
                            });

                            physicsOnButton.setOnMouseExited((menuEvent) -> {
                                physicsOn.setFitHeight(60);
                                btnSound.stop();
                            });

                            physicsOffButton.setOnMouseEntered((menuEvent) -> {
                                physicsOff.setFitHeight(70);
                                btnSound.play();
                            });

                            physicsOffButton.setOnMouseExited((menuEvent) -> {
                                physicsOff.setFitHeight(60);
                                btnSound.stop();
                            });

                            homeButton.setOnAction((menuEvent) -> {
                                SceneWelcome sceneWelcome = new SceneWelcome(primaryStage);
                                primaryStage.setScene(sceneWelcome.getScene());
                                playBgSound.pause();
                            });

                            exitButton.setOnAction((menuEvent) -> {
                                primaryStage.close();
                            });

                            ImageView normal = new ImageView("ButtonIMG/OnBtnIMG.png");
                            physicsOnButton.setOnAction((menuEvent) -> {
                                if (physicsOnButton.getGraphic() == physicsOn ) {
                                    physicsOnButton.setGraphic(normal);
                                }
                            });


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
