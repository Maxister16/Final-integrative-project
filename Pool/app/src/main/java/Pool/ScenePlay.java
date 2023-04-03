 
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


public abstract class ScenePlay {

            private Scene scene;
            Slider forceSlider;
            Slider angleSlider;
            Button playButton;
            Button menuButton;

            public ScenePlay()  {

                //Initialize Game Status
                GameStatus.initialize();

                //Music and Sound set up
                /*File btnFile = new File(GameStatus.CLIENT_LOCATION_OF_PROJECT+"/src/main/resources/sound/tok.mp3");
                MediaPlayer btnSound = new MediaPlayer(new javafx.scene.media.Media(btnFile.toURI().toString()));
                btnSound.setVolume(0.05);

                File songFile = new File(GameStatus.CLIENT_LOCATION_OF_PROJECT+"/src/main/resources/sound/Song.mp3");
                MediaPlayer playBgSound = new MediaPlayer(new javafx.scene.media.Media(songFile.toURI().toString()));
                playBgSound.play();
                playBgSound.setVolume(0.08);
                playBgSound.setCycleCount(MediaPlayer.INDEFINITE);*/

                //Create Principal Panes
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


                //Play screen Sliders and Buttons
                ImageView menu = new ImageView("ButtonIMG/MenuBtnIMG.png");
                ImageView play = new ImageView("ButtonIMG/playBtnIMG.png");

                forceSlider = new Slider(1, 10, 1);
                angleSlider = new Slider(0.0, 359, 1.0);

                //Not working attempt at styling sliders
                // Set the background of the track node
                //forceSlider.setStyle("-fx-background-color: linear-gradient(to right, yellow, red); -fx-border-color: linear-gradient(to right, yellow, red); ");
                //forceSlider.getStylesheets().add("slider.css");

                playButton = new Button("", play);
                menuButton = new Button("",menu);

                menu.setPreserveRatio(true);
                menu.setFitHeight(45);
                play.setPreserveRatio(true);
                play.setFitHeight(70);

                //set button backgrounds and border transparent
                menuButton.setBackground(null);
                menuButton.setBorder(null);
                playButton.setBackground(null);
                playButton.setBorder(null);

                // Add Sliders and buttons to GridPanes
                gridPaneBot.add(menuButton, 0, 0);
                gridPaneTop.add(forceSlider, 0, 0);
                gridPaneTop.add(angleSlider, 1, 0);
                gridPaneTop.add(playButton, 2, 0);
                gridPaneTop.setTranslateY(130);

                // Create Layout
                StackPane layout = new StackPane();

                //if I change these values the game Pane disappears (This doesn't matter as it won't change)
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

                //Initialize custom animation for baskets
                CustomAnimation redBaskets = new CustomAnimation("red",5);
                CustomAnimation orangeBaskets = new CustomAnimation("orange",5);

                redBaskets.setPreserveRatio(true);
                redBaskets.setFitHeight(300);
                redBaskets.setTranslateX(10);
                redBaskets.setTranslateY(-40);

                //.nextFrame for testing
                redBaskets.nextFrame();
                redBaskets.nextFrame();
                redBaskets.nextFrame();
                redBaskets.nextFrame();
                redBasketPane.getChildren().add(redBaskets);

                orangeBaskets.setPreserveRatio(true);
                orangeBaskets.setFitHeight(280);
                orangeBaskets.setTranslateX(7);

                orangeBaskets.nextFrame();
                orangeBasketPane.getChildren().add(orangeBaskets);

                //Team name pane
                Pane teamNamePane = new Pane();

                //Initialize team name custom animation
                CustomAnimation teamName = new CustomAnimation("team",2);
                teamName.setPreserveRatio(true);
                teamName.nextFrame();
                teamName.setFitHeight(165);
                teamNamePane.getChildren().add(teamName);
                teamNamePane.setTranslateX(30);
                teamNamePane.setTranslateY(-140);

                gridPaneTop.getChildren().add(teamNamePane);

                //Position Panes in Border Pane
                borderPane.setCenter(gamePane);
                borderPane.setLeft(redBasketPane);
                borderPane.setRight(orangeBasketPane);
                gamePane.setTranslateY(610);
                gamePane.setTranslateX(480);
                borderPane.setTop(gridPaneTop);

                borderPane.setBottom(gridPaneBot);
                layout.getChildren().addAll(bg,borderPane);

                GameStatus.positionObjects(layout.getWidth(), layout.getHeight());
                this.scene = new Scene(layout);

                // Play button events
                   //MAX-Player can't press play button if balls are still moving (setOnAction, setOnMouseEntered, setOnMouseExited events should be temporarily disabled)
                /*
                   playButton.setOnAction((event) -> {
                    double force = forceSlider.getValue();
                    double angle = angleSlider.getValue();
                    System.out.println("Force: " + force + "Angle: " + angle);
                    // MAX-you get force and angle from here, +game starts
                });

                playButton.setOnMouseEntered((event) -> {
                    play.setFitHeight(80);
                    btnSound.play();

                });
                playButton.setOnMouseExited((event) -> {
                    play.setFitHeight(70);
                    btnSound.stop();
                });

                //Winning Screen
                Pane winPane = new Pane();
                ImageView orangeWin = new ImageView("BackgroundIMG/star0.png");
                orangeWin.setPreserveRatio(true);
                orangeWin.setFitHeight(760);
                ImageView redWin = new ImageView("BackgroundIMG/redStar.png");
                redWin.setPreserveRatio(true);
                redWin.setFitHeight(760);
                ImageView apple =new ImageView("ButtonIMG/redReplay.png");
                apple.setPreserveRatio(true);
                apple.setFitHeight(145);
                ImageView orange =new ImageView("ButtonIMG/orangeReplay.png");
                orange.setPreserveRatio(true);
                orange.setFitHeight(145);

                Button redReplay = new Button("", apple);
                Button orangeReplay = new Button("", orange);

                redReplay.setBackground(null);
                orangeReplay.setBackground(null);
                redReplay.setBorder(null);
                orangeReplay.setBorder(null);
                redReplay.setTranslateX(384);
                redReplay.setTranslateY(310);
                orangeReplay.setTranslateX(391);
                orangeReplay.setTranslateY(327);

                winPane.setTranslateX(180);
                winPane.setTranslateY(0);
                Rectangle winBlur = new Rectangle(0,0, 1350, 780);
                //(will add fade transition later)
                winBlur.setFill(Color.WHITE);
                winBlur.setOpacity(0.5);
                winBlur.setTranslateX(-180);


                //MAX TO ADD -- Do same as below if opposite team sends black ball into hole

                // GameStatus.team1Points=4; // Uncomment if you want to see winPane (Orange= Team 1, Red= Team 2)
                if (GameStatus.team1Points==4){
                    winPane.getChildren().addAll(winBlur,orangeWin,orangeReplay);
                    layout.getChildren().add(winPane);
                }

                if (GameStatus.team2Points==4){
                    winPane.getChildren().addAll(winBlur,redWin,redReplay);
                    layout.getChildren().add(winPane);
                }
/*
                //Replay button events
                orangeReplay.setOnAction((event) -> {
                    winPane.getChildren().clear();
                    layout.getChildren().remove(winPane);
                    SceneWelcome sceneWelcome = new SceneWelcome(primaryStage);
                    primaryStage.setScene(sceneWelcome.getScene());
                    playBgSound.pause();
                });

                redReplay.setOnAction((event) -> {
                    winPane.getChildren().clear();
                    layout.getChildren().remove(winPane);
                    SceneWelcome sceneWelcome = new SceneWelcome(primaryStage);
                    primaryStage.setScene(sceneWelcome.getScene());

                    playBgSound.pause();
                });

                redReplay.setOnMouseEntered((event) -> {
                    apple.setFitHeight(155);
                    btnSound.play();
                });

                redReplay.setOnMouseExited((event) -> {
                    apple.setFitHeight(145);
                    btnSound.stop();
                });

                orangeReplay.setOnMouseEntered((event) -> {
                    orange.setFitHeight(155);
                    btnSound.play();
                });

                orangeReplay.setOnMouseExited((event) -> {
                    orange.setFitHeight(145);
                    btnSound.stop();
                });

                //Menu button event
                menuButton.setOnAction((event) -> {
                            //Create Panes
                            GridPane gridPane = new GridPane();
                            gridPane.setAlignment(Pos.CENTER);
//                            GridPane physicsButtonPane = new GridPane();
//                            physicsButtonPane.setAlignment(Pos.CENTER);

                            //Images
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
                            ImageView physicsOn = new ImageView("ButtonIMG/OnBtn.png");
                            physicsOn.setPreserveRatio(true);
                            physicsOn.setFitHeight(60);
                            ImageView physicsOff = new ImageView("ButtonIMG/OffBtn.png");
                            physicsOff.setPreserveRatio(true);
                            physicsOff.setFitHeight(60);


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

                            //GridPane set up
                            gridPane.setHalignment(resumeButton, HPos.CENTER);
                            gridPane.setHalignment(homeButton, HPos.CENTER);
                            gridPane.setHalignment(exitButton, HPos.CENTER);
                            gridPane.setHalignment(physicsLabel, HPos.CENTER);
                            gridPane.setHalignment(physicsButton, HPos.CENTER);
                            gridPane.setTranslateY(30);

                            gridPane.add(resumeButton, 0, 0);
                            gridPane.add(homeButton, 0, 1);
                            gridPane.add(exitButton, 0, 2);
                            gridPane.add(physicsLabel, 0, 3);
                            gridPane.add(physicsButton, 0, 4);

                            //GridPane constraints
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

                            //Menu background
                            ImageView menuBg = new ImageView("BackgroundIMG/MenuBgIMG.png");

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

                            //Add to layout (this is the principal pane in ScenePaly)
                            layout.getChildren().addAll(rectangle,menuBg,gridPane);

                            //Button events
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
                                physicsOn.setFitHeight(70);
                                physicsOff.setFitHeight(70);
                                btnSound.play();
                            });

                            physicsButton.setOnMouseExited((menuEvent) -> {
                                physicsOn.setFitHeight(60);
                                physicsOff.setFitHeight(60);
                                btnSound.stop();
                            });

//                            physicsOffButton.setOnMouseEntered((menuEvent) -> {
//                                physicsOff.setFitHeight(70);
//                                btnSound.play();
//                            });
//
//                            physicsOffButton.setOnMouseExited((menuEvent) -> {
//                                physicsOff.setFitHeight(60);
//                                btnSound.stop();
//                            });

                            homeButton.setOnAction((menuEvent) -> {
                                SceneWelcome sceneWelcome = new SceneWelcome(primaryStage);
                                primaryStage.setScene(sceneWelcome.getScene());
                                playBgSound.pause();
                                //MAX-game ends
                            });

                            exitButton.setOnAction((menuEvent) -> {
                                primaryStage.close();
                                //MAX-game ends
                            });

                            //Eszter- doesn't save changes to physics button in menu (bc when button is created it has on, I will try to make it dependent on if the pane physics Pane is present
                            physicsButton.setOnAction((menuEvent) -> {
                                if (physicsButton.getGraphic() == physicsOn ) {
                                    physicsButton.setGraphic(physicsOff);
                                    //Eszter-once physics pane is created remove here from layout
                                }
                                else {
                                    physicsButton.setGraphic(physicsOn);
                                    //Eszter-once physics pane is created add here to layout
                                }
                            });


                });

                //Menu button zoom when mouse on it
                menuButton.setOnMouseEntered((event) -> {
                    menu.setFitHeight(50);
                    btnSound.play();
                });
                menuButton.setOnMouseExited((event) -> {
                    menu.setFitHeight(45);
                    btnSound.stop();
                });
*/
            }

            public Scene getScene() {
                return this.scene;
            }
        }
