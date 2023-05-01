 
package Pool;

import javafx.animation.FadeTransition;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import javafx.scene.text.Font;

import java.awt.font.ImageGraphicAttribute;


public class ScenePlay {
    
            private Scene scene;
            public Pane gamePane;
            StackPane layout = new StackPane();
            public GridPane gridPaneMenu = new GridPane();
            ImageView menuBg;
            Rectangle rectangle;

            ImageView forceTitle;
            ImageView angleTitle;
            ImageView frictionTitle;

    //menu
            public Button resumeButton ;
            public Button homeButton ;
            public Button exitButton ;
            public Button physicsButton;
            ImageView physicsOn;
            ImageView physicsOff;
            public FadeTransition fade = new FadeTransition();
            
    //in scene
            public Button playButton;
            public Button menuButton;
            public Slider forceSlider;
            public Slider angleSlider;
            
            public Button soundButton;

            ImageView soundOnIMG;
            ImageView soundOffIMG;

            Label frictionValue;
            Label angleValue;
            Label forceValue;
            
    //decorations        
            public CustomAnimation redBaskets;
            public CustomAnimation orangeBaskets;
            public CustomAnimation teamName;
            
    //winpane
            public Pane winPaneRed;
            public Pane winPaneOrange;
            public Button buttonReplayRed;
            public Button buttonReplayOrange;
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

            
            public void physicsAppears(){
                physicsButton.setGraphic(physicsOn);
                frictionValue.setStyle("-fx-opacity: 1; -fx-font-size: 25px; -fx-font-weight: bold");
                angleValue.setStyle("-fx-opacity: 1;-fx-font-size: 25px; -fx-font-weight: bold");
                forceValue.setStyle("-fx-opacity: 1;-fx-font-size: 25px; -fx-font-weight: bold");
                frictionTitle.setStyle("-fx-opacity: 1;-fx-font-size: 25px; -fx-font-weight: bold");
                angleTitle.setStyle("-fx-opacity: 1;-fx-font-size: 25px; -fx-font-weight: bold");
                forceTitle.setStyle("-fx-opacity: 1;-fx-font-size: 25px; -fx-font-weight: bold");
            }
            public void physicsDisappears(){
                physicsButton.setGraphic(physicsOff);
                frictionValue.setStyle("-fx-opacity: 0;");
                angleValue.setStyle("-fx-opacity: 0;");
                forceValue.setStyle("-fx-opacity: 0;");
                frictionTitle.setStyle("-fx-opacity: 0;");
                angleTitle.setStyle("-fx-opacity: 0;");
                forceTitle.setStyle("-fx-opacity: 0;");
            }
            
            public ScenePlay()  {

                //Create Principal Panes
                gamePane = new Pane();
                gamePane.setTranslateY(25);
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
                RowConstraints rowTop1 = new RowConstraints(52);
                rowTop1.setValignment(VPos.CENTER);

                gridPaneTop.getColumnConstraints().addAll(columnTop1, columnTop2, columnTop3);
                gridPaneTop.getRowConstraints().addAll(rowTop1);

                //Set Size constraints in bottom Grid Pane
                ColumnConstraints columnBot1 = new ColumnConstraints(280);
                columnBot1.setHalignment(HPos.LEFT);
                ColumnConstraints columnBot2 = new ColumnConstraints(150);
                columnBot2.setHalignment(HPos.CENTER);
                ColumnConstraints columnBot3 = new ColumnConstraints(160);
                columnBot3.setHalignment(HPos.LEFT);
                ColumnConstraints columnBot4 = new ColumnConstraints(110);
                columnBot4.setHalignment(HPos.CENTER);
                ColumnConstraints columnBot5 = new ColumnConstraints(150);
                columnBot5.setHalignment(HPos.LEFT);
                ColumnConstraints columnBot6 = new ColumnConstraints(120);
                columnBot6.setHalignment(HPos.CENTER);
                ColumnConstraints columnBot7 = new ColumnConstraints(110);
                columnBot7.setHalignment(HPos.LEFT);
                ColumnConstraints columnBot8 = new ColumnConstraints(100);
                columnBot8.setHalignment(HPos.CENTER);
                RowConstraints rowBot1 = new RowConstraints(60);
                rowBot1.setValignment(VPos.CENTER);

                gridPaneBot.getColumnConstraints().addAll(columnBot1, columnBot2, columnBot3, columnBot4, columnBot5, columnBot6, columnBot7, columnBot8);
                gridPaneBot.getRowConstraints().addAll(rowBot1);

                //Play screen Sliders and Buttons
                ImageView menu = new ImageView("ButtonIMG/MenuBtnIMG.png");
                ImageView play = new ImageView("ButtonIMG/playBtnIMG.png");

                forceSlider = new Slider(0.1, 2, 0.01);
                forceSlider.getStylesheets().add("slider.css");
                angleSlider = new Slider(0.0, 359, 0.001);
                angleSlider.getStylesheets().add("angleSlider.css");

                gridPaneTop.setHgap(20);


                playButton = new Button("", play);
                menuButton = new Button("",menu);

                menu.setPreserveRatio(true);
                menu.setFitHeight(40);
                play.setPreserveRatio(true);
                play.setFitHeight(70);

                //set button backgrounds and border transparent
                menuButton.setBackground(null);
                menuButton.setBorder(null);
                menuButton.setTranslateX(3);
                playButton.setBackground(null);
                playButton.setBorder(null);

                //Physics text
                frictionTitle = new ImageView("LabelIMG/FrictionWordIMG.png");
                frictionTitle.setRotate(2);
                frictionTitle.setTranslateY(2);
                angleTitle = new ImageView("LabelIMG/AngleWordIMG.png");
                forceTitle = new ImageView("LabelIMG/ForceWordIMG.png");
                forceTitle.setTranslateY(1);
                frictionTitle.setPreserveRatio(true);
                frictionTitle.setFitHeight(30);
                angleTitle.setPreserveRatio(true);
                angleTitle.setFitHeight(30);
                forceTitle.setPreserveRatio(true);
                forceTitle.setFitHeight(30);

                frictionValue = new Label("");
                angleValue = new Label("");
                forceValue = new Label("");

                frictionValue.setStyle("-fx-font-size: 25px; -fx-font-weight: bold;");
                angleValue.setStyle("-fx-font-size: 25px; -fx-font-weight: bold;");
                forceValue.setStyle("-fx-font-size: 25px; -fx-font-weight: bold;");

                //Volume Button
                soundOnIMG = new ImageView("ButtonIMG/VolumeBtnOnIMG.png");
                soundOnIMG.setPreserveRatio(true);
                soundOnIMG.setFitHeight(60);
                soundButton = new Button("",soundOnIMG);
                soundButton.setBackground(null);
                soundButton.setBorder(null);
                soundButton.setTranslateY(-3);
                soundButton.setTranslateX(150);
                soundOffIMG = new ImageView("ButtonIMG/VolumeBtnOffIMG.png");
                soundOffIMG.setPreserveRatio(true);
                soundOffIMG.setFitHeight(60);

                // Add Sliders and buttons to GridPanes
                 gridPaneBot.add(menuButton, 0, 0);
                 gridPaneBot.add(frictionTitle, 1, 0);
                 gridPaneBot.add(frictionValue, 2, 0);
                 gridPaneBot.add(forceTitle, 3, 0);
                 gridPaneBot.add(forceValue, 4, 0);
                 gridPaneBot.add(angleTitle, 5, 0);
                 gridPaneBot.add(angleValue, 6, 0);
                 gridPaneBot.add(soundButton, 7, 0);

                ImageView forceLabel = new ImageView("LabelIMG/ForceSliderIMG.jpg");
                ImageView angleLabel = new ImageView("LabelIMG/AngleSliderIMG.jpg");
                forceLabel.setPreserveRatio(true);
                forceLabel.setFitHeight(32);
                forceLabel.setTranslateY(-7);
                angleLabel.setPreserveRatio(true);
                angleLabel.setFitHeight(32);
                angleLabel.setTranslateY(-7);

                gridPaneTop.add(forceSlider, 0, 0);
                gridPaneTop.add(angleSlider, 1, 0);
                gridPaneTop.add(forceLabel, 0,1);
                gridPaneTop.add(angleLabel, 1,1);

                gridPaneTop.add(playButton, 2, 0);
                gridPaneTop.setTranslateY(160);
                
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
                teamName.setFitHeight(130);
                teamNamePane.getChildren().add(teamName);
                teamNamePane.setTranslateX(10);
                teamNamePane.setTranslateY(-160);

                gridPaneTop.getChildren().add(teamNamePane);

                //Position Panes in Border Pane
                borderPane.setCenter(gamePane);
                borderPane.setLeft(redBasketPane);
                borderPane.setRight(orangeBasketPane);
                borderPane.setTop(gridPaneTop);

                borderPane.setBottom(gridPaneBot);
                layout.getChildren().addAll(bg,borderPane);
                this.scene = new Scene(layout,1350,780);

//MENU PANE
                
                gridPaneMenu.setAlignment(Pos.CENTER);
                //gridPaneMenu physicsButtonPane = new gridPaneMenu();
                //physicsButtonPane.setAlignment(Pos.CENTER);

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
                winPaneRed = new Pane();
                winPaneOrange = new Pane();
                
                orangeWin = new ImageView("BackgroundIMG/star0.png");
                orangeWin.setPreserveRatio(true);
                orangeWin.setFitHeight(760);
                redWin = new ImageView("BackgroundIMG/redStar.png");
                redWin.setPreserveRatio(true);
                redWin.setFitHeight(760);
                ImageView apple =new ImageView("ButtonIMG/redReplay.png");
                apple.setPreserveRatio(true);
                apple.setFitHeight(145);
                ImageView orange = new ImageView("ButtonIMG/orangeReplay.png");
                orange.setPreserveRatio(true);
                orange.setFitHeight(145);
                
                buttonReplayRed = new Button("",apple);
                buttonReplayRed.setBackground(null);
                buttonReplayRed.setBorder(null);
                buttonReplayRed.setTranslateX(384);
                buttonReplayRed.setTranslateY(310);
                
                buttonReplayOrange = new Button("",orange);
                buttonReplayOrange.setBackground(null);
                buttonReplayOrange.setBorder(null);
                buttonReplayOrange.setTranslateX(384);
                buttonReplayOrange.setTranslateY(310);

                winPaneRed.setTranslateX(180);
                winPaneRed.setTranslateY(0);
                winPaneOrange.setTranslateX(180);
                winPaneOrange.setTranslateY(0);
                Rectangle winBlurRed = new Rectangle(0,0, 1350, 780);
                winBlurRed.setFill(Color.WHITE);
                winBlurRed.setOpacity(0.5);
                winBlurRed.setTranslateX(-180);
                Rectangle winBlurOrange = new Rectangle(0,0, 1350, 780);
                winBlurOrange.setFill(Color.WHITE);
                winBlurOrange.setOpacity(0.5);
                winBlurOrange.setTranslateX(-180);
                
                winPaneOrange.getChildren().addAll(winBlurOrange,orangeWin,buttonReplayOrange);
                winPaneRed.getChildren().addAll(winBlurRed,redWin,buttonReplayRed);
                
                winPaneRed.setScaleX(0.001);
                winPaneRed.setScaleY(0.001);
                winPaneOrange.setScaleX(0.001);
                winPaneOrange.setScaleY(0.001);
                
                layout.getChildren().addAll(winPaneOrange,winPaneRed);
                
            }

            public Scene getScene() {
                return this.scene;
            }
        }
