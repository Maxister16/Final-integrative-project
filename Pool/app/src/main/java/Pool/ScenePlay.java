 
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
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;


public class ScenePlay {

            private Scene scene;
            public Pane gamePane;
            StackPane layout = new StackPane();
            
        //menu
            public PaneMenu paneMenu = new PaneMenu();
            public GridPane gridPaneMenu = new GridPane();
            ImageView menuBg;
            Rectangle rectangle;    
            public Button resumeButton ;
            public Button homeButton ;
            public Button exitButton ;
            public Button physicsButton;
            public ImageView physicsOn;
            public ImageView physicsOff;
            public FadeTransition fade = new FadeTransition();
        
        //in game    
            public Button playButton;
            public Button menuButton;
            public Slider forceSlider;
            public Slider angleSlider;
        
        //decorations
            public CustomAnimation redBaskets;
            public CustomAnimation orangeBaskets;
            public CustomAnimation teamName;
            
        //winpane
            public Pane winPane;
            public Button buttonReplay;
            Rectangle winBlur;
            ImageView orangeWin;
            ImageView redWin;
            
            
            public void placeObjectsInGamePane(){
                gamePane.getChildren().clear();
                gamePane.getChildren().addAll(GameStatus.nets);
                gamePane.getChildren().addAll(GameStatus.listOfBalls);
                gamePane.getChildren().addAll(GameStatus.tableLines);
                gamePane.getChildren().addAll(GameStatus.table.getBackground(), GameStatus.table.getBorder(),GameStatus.cue);
            }
            
            public void menuAppears(){
                layout.getChildren().addAll(rectangle,menuBg,gridPaneMenu);
            }
            public void menuDisappears(){
                layout.getChildren().removeAll(rectangle,menuBg,gridPaneMenu);
            }
            
            public void winAppears(int winningTeamIndex){
                winPane.getChildren().clear();
                winPane.getChildren().addAll(winBlur,buttonReplay);
                
                String winningColor;
                if(winningTeamIndex == 0){//if orange Win
                    winPane.getChildren().addAll(orangeWin);
                    winningColor = "orange";
                }
                else{//if red Win
                    winPane.getChildren().addAll(redWin);
                    winningColor = "red";
                }
                
                ImageView buttonReplayIMG = new ImageView("ButtonIMG/"+winningColor+"Replay.png");
                buttonReplayIMG.setPreserveRatio(true);
                buttonReplayIMG.setFitHeight(145);
                buttonReplay.setGraphic(buttonReplayIMG);//set image of button
                buttonReplay.toFront();
                layout.getChildren().add(winPane);
            }
            
            public void winDisappears(){
                layout.getChildren().remove(winPane);
            }
            
            public void physicsAppears(){
                
            }
            public void physicsDisappears(){
                
            }
            
            public ScenePlay()  {

                //Create Principal Panes
                gamePane = new Pane();
                //gamePane.setStyle("-fx-background-color: #005555;");
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

                forceSlider = new Slider(0.1, 2, 0.1);
                forceSlider.getStylesheets().add("slider.css");
                angleSlider = new Slider(0.0, 359, 0.1);
                angleSlider.getStylesheets().add("angleSlider.css");
                
                gridPaneTop.setHgap(10);
                
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
                
                //if I change these values the game Pane disappears (This doesn't matter as it won't change)
                layout.setMaxWidth(1350);
                layout.setMaxHeight(780);

                //Set background image
                ImageView bg = new ImageView("BackgroundIMG/PlayBgIMG.jpg");

                bg.setPreserveRatio(true);
                bg.setFitWidth(1350);

                //Basket panes
                Pane redBasketPane = new Pane();
                Pane orangeBasketPane = new Pane();

                //Initialize custom animation for baskets
                redBaskets = new CustomAnimation("red",5);
                orangeBaskets = new CustomAnimation("orange",5);

                redBaskets.setPreserveRatio(true);
                redBaskets.setFitHeight(300);
                redBaskets.setTranslateX(10);
                redBaskets.setTranslateY(-40);

                redBasketPane.getChildren().add(redBaskets);

                orangeBaskets.setPreserveRatio(true);
                orangeBaskets.setFitHeight(280);
                orangeBaskets.setTranslateX(7);

                orangeBasketPane.getChildren().add(orangeBaskets);

                //Team name pane
                Pane teamNamePane = new Pane();

                //Initialize team name custom animation
                teamName = new CustomAnimation("team",2);
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
                borderPane.setTop(gridPaneTop);

                borderPane.setBottom(gridPaneBot);
                layout.getChildren().addAll(bg,borderPane);
                this.scene = new Scene(layout,1350,780);

        //MENU
                
                gridPaneMenu.setAlignment(Pos.CENTER);
//                            gridPaneMenu physicsButtonPane = new gridPaneMenu();
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
                physicsOn = new ImageView("ButtonIMG/OnBtn.png");
                physicsOn.setPreserveRatio(true);
                physicsOn.setFitHeight(60);
                physicsOff = new ImageView("ButtonIMG/OffBtn.png");
                physicsOff.setPreserveRatio(true);
                physicsOff.setFitHeight(60);


                resumeButton = new Button("",resume);
                homeButton = new Button("",home);
                exitButton = new Button("",exit);
                physicsButton = new Button("",physicsOn);

                homeButton.setBackground(null);
                homeButton.setBorder(null);
                resumeButton.setBackground(null);
                resumeButton.setBorder(null);
                exitButton.setBackground(null);
                exitButton.setBorder(null);
                physicsButton.setBackground(null);

                //gridPaneMenu set up
                gridPaneMenu.setHalignment(resumeButton, HPos.CENTER);
                gridPaneMenu.setHalignment(homeButton, HPos.CENTER);
                gridPaneMenu.setHalignment(exitButton, HPos.CENTER);
                gridPaneMenu.setHalignment(physicsLabel, HPos.CENTER);
                gridPaneMenu.setHalignment(physicsButton, HPos.CENTER);
                gridPaneMenu.setTranslateY(30);

                gridPaneMenu.add(resumeButton, 0, 0);
                gridPaneMenu.add(homeButton, 0, 1);
                gridPaneMenu.add(exitButton, 0, 2);
                gridPaneMenu.add(physicsLabel, 0, 3);
                gridPaneMenu.add(physicsButton, 0, 4);

                //gridPaneMenu constraints
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
                gridPaneMenu.getColumnConstraints().add(menuColumn);
                gridPaneMenu.getRowConstraints().addAll(menuRow1,menuRow2,menuRow3,menuRow4,menuRow5);

                //Menu background
                menuBg = new ImageView("BackgroundIMG/MenuBgIMG.png");

                menuBg.setPreserveRatio(true);
                menuBg.setFitWidth(500);
                rectangle = new Rectangle(1350, 700);
                rectangle.setFill(Color.WHITE);
                rectangle.setTranslateY(-42);
                fade.setNode(rectangle);
                fade.setFromValue(0.0);
                fade.setToValue(0.4);
                fade.setDuration(Duration.millis(1000));
                fade.play();

//WINNING PANE
                winPane = new Pane();
                orangeWin = new ImageView("BackgroundIMG/star0.png");
                orangeWin.setPreserveRatio(true);
                orangeWin.setFitHeight(760);
                redWin = new ImageView("BackgroundIMG/redStar.png");
                redWin.setPreserveRatio(true);
                redWin.setFitHeight(760);
                
                /*ImageView apple = new ImageView("ButtonIMG/redReplay.png");
                apple.setPreserveRatio(true);
                apple.setFitHeight(145);
                ImageView orange = new ImageView("ButtonIMG/orangeReplay.png");
                orange.setPreserveRatio(true);
                orange.setFitHeight(145);
                
                /*
                Button redReplay = new Button("", apple);
                Button orangeReplay = new Button("", orange);
                */

                buttonReplay = new Button("");

                buttonReplay.setBackground(null);
                buttonReplay.setBorder(null);
                buttonReplay.setTranslateX(384);
                buttonReplay.setTranslateY(310);

                winPane.setTranslateX(180);
                winPane.setTranslateY(0);
                winBlur = new Rectangle(0,0, 1350, 780);
                //(will add fade transition later)
                winBlur.setFill(Color.WHITE);
                winBlur.setOpacity(0.5);
                winBlur.setTranslateX(-180);
                //layout.getChildren().add(winPane);

                
            }

            public Scene getScene() {
                return this.scene;
            }
        }
