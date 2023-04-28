package Pool;

import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;

import static Pool.GameStatus.*;
import static java.lang.Math.PI;
import java.text.DecimalFormat;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.concurrent.Task;

public class MainApp extends Application {
    
    private final long TIME_OF_TICK = 1000/60;//time in millis
    Stage stagefield;
    DecimalFormat df = new DecimalFormat("#.##");
    
    @Override
    public void start(Stage stage) throws IOException {
        
        stagefield = stage;
        
        Sound.initiateSound();
        //create scenes
        SceneWelcome sceneWelcome = new SceneWelcome();
        ScenePlay scenePlayGrass = new ScenePlay();
        ScenePlay scenePlayNormal = new ScenePlay();
        ScenePlay scenePlayIce = new ScenePlay();
        
        stage.setHeight(780);
        stage.setWidth(1350);
        stage.setResizable(false);

        Sound.welcomeBgSound.play();
        stage.setScene(sceneWelcome.getScene());
        stage.show();
        
        scenePlayNormal.getScene().setOnKeyTyped(e->{
            //scenePlayNormal.winAppears(0);
            int randomValue = Character.getNumericValue(e.getCharacter().charAt(0));
            GameStatus.listOfBalls[randomValue].setVectorPosition(new Vector(GameStatus.nets[0].getCenterX(),GameStatus.nets[0].getCenterY()));
            GameStatus.listOfBalls[randomValue].setCenterX(GameStatus.nets[0].getCenterX());
            GameStatus.listOfBalls[randomValue].setCenterY(GameStatus.nets[0].getCenterY());
        });
        scenePlayIce.getScene().setOnKeyTyped(e->{
            //scenePlayNormal.winAppears(0);
            int randomValue = Character.getNumericValue(e.getCharacter().charAt(0));
            GameStatus.listOfBalls[randomValue].setVectorPosition(new Vector(GameStatus.nets[0].getCenterX(),GameStatus.nets[0].getCenterY()));
            GameStatus.listOfBalls[randomValue].setCenterX(GameStatus.nets[0].getCenterX());
            GameStatus.listOfBalls[randomValue].setCenterY(GameStatus.nets[0].getCenterY());
        });
        scenePlayGrass.getScene().setOnKeyTyped(e->{
            //scenePlayNormal.winAppears(0);
            int randomValue = Character.getNumericValue(e.getCharacter().charAt(0));
            GameStatus.listOfBalls[randomValue].setVectorPosition(new Vector(GameStatus.nets[0].getCenterX(),GameStatus.nets[0].getCenterY()));
            GameStatus.listOfBalls[randomValue].setCenterX(GameStatus.nets[0].getCenterX());
            GameStatus.listOfBalls[randomValue].setCenterY(GameStatus.nets[0].getCenterY());
        });
        
        //action handlers for repetitive actions
//GO TO SCENES
        EventHandler goToNormal = e->{
            scenePlayNormal.menuDisappears();
            stage.setScene(scenePlayNormal.getScene());
            stage.setFullScreen(true);
            stage.setFullScreen(false);
            GameStatus.gameState = 0;
            startGame(scenePlayNormal);
        };
        EventHandler goToIce = e->{
            scenePlayIce.menuDisappears();
            stage.setScene(scenePlayIce.getScene());
            stage.setFullScreen(true);
            stage.setFullScreen(false);
            GameStatus.gameState = 1;
            startGame(scenePlayIce);
        };
        EventHandler goToGrass = e->{
            scenePlayGrass.menuDisappears();
            stage.setScene(scenePlayGrass.getScene());
            stage.setFullScreen(true);
            stage.setFullScreen(false);
            GameStatus.gameState = 2;
            startGame(scenePlayGrass);
        };
        EventHandler goToWelcome = e->{
            Sound.playBgSound.stop();
            Sound.welcomeBgSound.play();
            stage.setScene(sceneWelcome.getScene());
            stage.setFullScreen(true);
            stage.setFullScreen(false);
        };
        
//BUTTON ACTIONS
        EventHandler btnOnMouseEntered = e->{
            Button target = (Button)e.getTarget();
            target.setScaleX(1.12);
            target.setScaleY(1.12);
            Sound.btnSound.stop();
            Sound.btnSound.play();
        };
        EventHandler btnOnMouseExited = e->{
            Button target = (Button)e.getTarget();
            target.setScaleX(1);
            target.setScaleY(1);
            Sound.btnSound.stop();
            Sound.btnSound.play();
        };
        
        EventHandler btnMuteClicked = e ->{
            Button sourceBtn = (Button)e.getSource();
            if (sourceBtn.getGraphic() == scenePlayNormal.soundOnIMG || sourceBtn.getGraphic() == scenePlayIce.soundOnIMG || sourceBtn.getGraphic() == scenePlayGrass.soundOnIMG || sourceBtn.getGraphic() == sceneWelcome.welcomeSoundOn) {
                scenePlayNormal.soundButton.setGraphic(scenePlayNormal.soundOffIMG);
                sceneWelcome.welcomeSoundBtn.setGraphic(sceneWelcome.welcomeSoundOff);
                scenePlayGrass.soundButton.setGraphic(scenePlayGrass.soundOffIMG);
                scenePlayIce.soundButton.setGraphic(scenePlayIce.soundOffIMG);
                Sound.muteSound();
            }
            else {
                scenePlayNormal.soundButton.setGraphic(scenePlayNormal.soundOnIMG);
                sceneWelcome.welcomeSoundBtn.setGraphic(sceneWelcome.welcomeSoundOn);
                scenePlayGrass.soundButton.setGraphic(scenePlayGrass.soundOnIMG);
                scenePlayIce.soundButton.setGraphic(scenePlayIce.soundOnIMG);
                Sound.unmutesound();
            }
        };
        
//SCENE_WELCOME
        //Button Events
        sceneWelcome.normalBtn.setOnAction(goToNormal);
        sceneWelcome.grassBtn.setOnAction(goToGrass);
        sceneWelcome.iceBtn.setOnAction(goToIce);
        
        sceneWelcome.normalBtn.setOnMouseEntered(btnOnMouseEntered);
        sceneWelcome.normalBtn.setOnMouseExited(btnOnMouseExited);
        sceneWelcome.grassBtn.setOnMouseEntered(btnOnMouseEntered);
        sceneWelcome.grassBtn.setOnMouseExited(btnOnMouseExited);
        sceneWelcome.iceBtn.setOnMouseEntered(btnOnMouseEntered);
        sceneWelcome.iceBtn.setOnMouseExited(btnOnMouseExited);
        //sound button
        sceneWelcome.welcomeSoundBtn.setOnMouseEntered(btnOnMouseEntered);
        sceneWelcome.welcomeSoundBtn.setOnMouseExited(btnOnMouseExited);
        sceneWelcome.welcomeSoundBtn.setOnAction(btnMuteClicked);
        
//SCENE_PLAY

    //NORMAL
        scenePlayNormal.playButton.setOnMouseEntered(btnOnMouseEntered);
        scenePlayNormal.playButton.setOnMouseExited(btnOnMouseExited);
        scenePlayNormal.playButton.setOnAction(e->{
            playButtonHit(scenePlayNormal);
        });
        scenePlayNormal.menuButton.setOnMouseEntered(btnOnMouseEntered);
        scenePlayNormal.menuButton.setOnMouseExited(btnOnMouseExited);
        scenePlayNormal.menuButton.setOnAction(e->{scenePlayNormal.menuAppears();});
    
        //replay
        scenePlayNormal.buttonReplayRed.setOnMouseEntered(btnOnMouseEntered);
        scenePlayNormal.buttonReplayRed.setOnMouseExited(btnOnMouseExited);
        scenePlayNormal.buttonReplayRed.setOnAction(e->{
            //scenePlayNormal.winDisappears();
            scenePlayNormal.angleSlider.setValue(0);
            scenePlayNormal.forceSlider.setValue(0);
            scenePlayNormal.orangeBaskets.setCurrentFrame(0);
            scenePlayNormal.redBaskets.setCurrentFrame(0);
            
            scenePlayNormal.winPaneRed.setScaleX(0.001);
            scenePlayNormal.winPaneRed.setScaleY(0.001);
            scenePlayNormal.winPaneOrange.setScaleX(0.001);
            scenePlayNormal.winPaneOrange.setScaleY(0.001);
            
            goToWelcome.handle(e);
            Sound.yaySound.stop();
        });
        scenePlayNormal.buttonReplayOrange.setOnMouseEntered(btnOnMouseEntered);
        scenePlayNormal.buttonReplayOrange.setOnMouseExited(btnOnMouseExited);
        scenePlayNormal.buttonReplayOrange.setOnAction(e->{
            //scenePlayNormal.winDisappears();
            scenePlayNormal.angleSlider.setValue(0);
            scenePlayNormal.forceSlider.setValue(0);
            scenePlayNormal.orangeBaskets.setCurrentFrame(0);
            scenePlayNormal.redBaskets.setCurrentFrame(0);
            
            scenePlayNormal.winPaneRed.setScaleX(0.001);
            scenePlayNormal.winPaneRed.setScaleY(0.001);
            scenePlayNormal.winPaneOrange.setScaleX(0.001);
            scenePlayNormal.winPaneOrange.setScaleY(0.001);
            
            goToWelcome.handle(e);
            Sound.yaySound.stop();
        });
    
        //sound button
        scenePlayNormal.soundButton.setOnMouseEntered(btnOnMouseEntered);
        scenePlayNormal.soundButton.setOnMouseExited(btnOnMouseExited);
        scenePlayNormal.soundButton.setOnAction(btnMuteClicked);
        
    //ICE
        scenePlayIce.playButton.setOnMouseEntered(btnOnMouseEntered);
        scenePlayIce.playButton.setOnMouseExited(btnOnMouseExited);
        scenePlayIce.playButton.setOnAction(e->{
            playButtonHit(scenePlayIce);
        });
        scenePlayIce.menuButton.setOnMouseEntered(btnOnMouseEntered);
        scenePlayIce.menuButton.setOnMouseExited(btnOnMouseExited);
        scenePlayIce.menuButton.setOnAction(e->{scenePlayIce.menuAppears();});
        
        //replay
        scenePlayIce.buttonReplayRed.setOnMouseEntered(btnOnMouseEntered);
        scenePlayIce.buttonReplayRed.setOnMouseExited(btnOnMouseExited);
        scenePlayIce.buttonReplayRed.setOnAction(e->{
            //scenePlayIce.winDisappears();
            scenePlayIce.angleSlider.setValue(0);
            scenePlayIce.forceSlider.setValue(0);
            scenePlayIce.orangeBaskets.setCurrentFrame(0);
            scenePlayIce.redBaskets.setCurrentFrame(0);
            
            scenePlayIce.winPaneRed.setScaleX(0.001);
            scenePlayIce.winPaneRed.setScaleY(0.001);
            scenePlayIce.winPaneOrange.setScaleX(0.001);
            scenePlayIce.winPaneOrange.setScaleY(0.001);
            
            goToWelcome.handle(e);
            Sound.yaySound.stop();
        });
        scenePlayIce.buttonReplayOrange.setOnMouseEntered(btnOnMouseEntered);
        scenePlayIce.buttonReplayOrange.setOnMouseExited(btnOnMouseExited);
        scenePlayIce.buttonReplayOrange.setOnAction(e->{
            //scenePlayIce.winDisappears();
            scenePlayIce.angleSlider.setValue(0);
            scenePlayIce.forceSlider.setValue(0);
            scenePlayIce.orangeBaskets.setCurrentFrame(0);
            scenePlayIce.redBaskets.setCurrentFrame(0);
            
            scenePlayIce.winPaneRed.setScaleX(0.001);
            scenePlayIce.winPaneRed.setScaleY(0.001);
            scenePlayIce.winPaneOrange.setScaleX(0.001);
            scenePlayIce.winPaneOrange.setScaleY(0.001);
            
            goToWelcome.handle(e);
            Sound.yaySound.stop();
        });
    
        //sound button
        scenePlayIce.soundButton.setOnMouseEntered(btnOnMouseEntered);
        scenePlayIce.soundButton.setOnMouseExited(btnOnMouseExited);
        scenePlayIce.soundButton.setOnAction(btnMuteClicked);
        
    //GRASS    
        scenePlayGrass.playButton.setOnMouseEntered(btnOnMouseEntered);
        scenePlayGrass.playButton.setOnMouseExited(btnOnMouseExited);
        scenePlayGrass.playButton.setOnAction(e->{
            playButtonHit(scenePlayGrass);
        });
        scenePlayGrass.menuButton.setOnMouseEntered(btnOnMouseEntered);
        scenePlayGrass.menuButton.setOnMouseExited(btnOnMouseExited);
        scenePlayGrass.menuButton.setOnAction(e->{scenePlayGrass.menuAppears();});
        
        //replay
        scenePlayGrass.buttonReplayRed.setOnMouseEntered(btnOnMouseEntered);
        scenePlayGrass.buttonReplayRed.setOnMouseExited(btnOnMouseExited);
        scenePlayGrass.buttonReplayRed.setOnAction(e->{
            //scenePlayGrass.winDisappears();
            scenePlayGrass.angleSlider.setValue(0);
            scenePlayGrass.forceSlider.setValue(0);
            scenePlayGrass.orangeBaskets.setCurrentFrame(0);
            scenePlayGrass.redBaskets.setCurrentFrame(0);
            
            scenePlayGrass.winPaneRed.setScaleX(0.001);
            scenePlayGrass.winPaneRed.setScaleY(0.001);
            scenePlayGrass.winPaneOrange.setScaleX(0.001);
            scenePlayGrass.winPaneOrange.setScaleY(0.001);
            
            goToWelcome.handle(e);
            Sound.yaySound.stop();
        });
        scenePlayGrass.buttonReplayOrange.setOnMouseEntered(btnOnMouseEntered);
        scenePlayGrass.buttonReplayOrange.setOnMouseExited(btnOnMouseExited);
        scenePlayGrass.buttonReplayOrange.setOnAction(e->{
            //scenePlayGrass.winDisappears();
            scenePlayGrass.angleSlider.setValue(0);
            scenePlayGrass.forceSlider.setValue(0);
            scenePlayGrass.orangeBaskets.setCurrentFrame(0);
            scenePlayGrass.redBaskets.setCurrentFrame(0);
            
            scenePlayGrass.winPaneRed.setScaleX(0.001);
            scenePlayGrass.winPaneRed.setScaleY(0.001);
            scenePlayGrass.winPaneOrange.setScaleX(0.001);
            scenePlayGrass.winPaneOrange.setScaleY(0.001);
            
            goToWelcome.handle(e);
            Sound.yaySound.stop();
        });
    
        //sound button
        scenePlayGrass.soundButton.setOnMouseEntered(btnOnMouseEntered);
        scenePlayGrass.soundButton.setOnMouseExited(btnOnMouseExited);
        scenePlayGrass.soundButton.setOnAction(btnMuteClicked);
        
//MENU
    //normal
        scenePlayNormal.resumeButton.setOnMouseEntered(btnOnMouseEntered);
        scenePlayNormal.resumeButton.setOnMouseExited(btnOnMouseExited);
        scenePlayNormal.resumeButton.setOnAction(e->{scenePlayNormal.menuDisappears();});
        
        scenePlayNormal.homeButton.setOnMouseEntered(btnOnMouseEntered);
        scenePlayNormal.homeButton.setOnMouseExited(btnOnMouseExited);
        scenePlayNormal.homeButton.setOnAction(goToWelcome);
        
        scenePlayNormal.exitButton.setOnMouseEntered(btnOnMouseEntered);
        scenePlayNormal.exitButton.setOnMouseExited(btnOnMouseExited);
        scenePlayNormal.exitButton.setOnAction(e->{stage.close();});
        
        scenePlayNormal.physicsButton.setOnMouseEntered(btnOnMouseEntered);
        scenePlayNormal.physicsButton.setOnMouseExited(btnOnMouseExited);
        scenePlayNormal.physicsButton.setOnAction(e->{
        
            if (scenePlayNormal.physicsButton.getGraphic() == scenePlayNormal.physicsOn ) {
                scenePlayNormal.physicsButton.setGraphic(scenePlayNormal.physicsOff);
                scenePlayNormal.physicsDisappears();
            }
            else {
                scenePlayNormal.physicsButton.setGraphic(scenePlayNormal.physicsOn);
                scenePlayNormal.physicsAppears();
            }
         });
        
    //ice    
        scenePlayIce.resumeButton.setOnMouseEntered(btnOnMouseEntered);
        scenePlayIce.resumeButton.setOnMouseExited(btnOnMouseExited);
        scenePlayIce.resumeButton.setOnAction(e->{scenePlayIce.menuDisappears();});
        
        scenePlayIce.homeButton.setOnMouseEntered(btnOnMouseEntered);
        scenePlayIce.homeButton.setOnMouseExited(btnOnMouseExited);
        scenePlayIce.homeButton.setOnAction(goToWelcome);
        
        scenePlayIce.exitButton.setOnMouseEntered(btnOnMouseEntered);
        scenePlayIce.exitButton.setOnMouseExited(btnOnMouseExited);
        scenePlayIce.exitButton.setOnAction(e->{stage.close();});
        
        scenePlayIce.physicsButton.setOnMouseEntered(btnOnMouseEntered);
        scenePlayIce.physicsButton.setOnMouseExited(btnOnMouseExited);
        scenePlayIce.physicsButton.setOnAction(e->{
        
            if (scenePlayIce.physicsButton.getGraphic() == scenePlayIce.physicsOn ) {
                scenePlayIce.physicsButton.setGraphic(scenePlayIce.physicsOff);
                scenePlayIce.physicsDisappears();
            }
            else {
                scenePlayIce.physicsButton.setGraphic(scenePlayIce.physicsOn);
                scenePlayIce.physicsAppears();
            }
         });
      
    //grass    
        scenePlayGrass.resumeButton.setOnMouseEntered(btnOnMouseEntered);
        scenePlayGrass.resumeButton.setOnMouseExited(btnOnMouseExited);
        scenePlayGrass.resumeButton.setOnAction(e->{scenePlayGrass.menuDisappears();});
        
        scenePlayGrass.homeButton.setOnMouseEntered(btnOnMouseEntered);
        scenePlayGrass.homeButton.setOnMouseExited(btnOnMouseExited);
        scenePlayGrass.homeButton.setOnAction(goToWelcome);
        
        scenePlayGrass.exitButton.setOnMouseEntered(btnOnMouseEntered);
        scenePlayGrass.exitButton.setOnMouseExited(btnOnMouseExited);
        scenePlayGrass.exitButton.setOnAction(e->{stage.close();});
        
        scenePlayGrass.physicsButton.setOnMouseEntered(btnOnMouseEntered);
        scenePlayGrass.physicsButton.setOnMouseExited(btnOnMouseExited);
        scenePlayGrass.physicsButton.setOnAction(e->{
        
            if (scenePlayGrass.physicsButton.getGraphic() == scenePlayGrass.physicsOn ) {//turn off
                scenePlayGrass.physicsButton.setGraphic(scenePlayGrass.physicsOff);
                scenePlayGrass.physicsDisappears();
            }
            else { //turn on
                scenePlayGrass.physicsButton.setGraphic(scenePlayGrass.physicsOn);
                scenePlayGrass.physicsAppears();
            }
         });
    }
    
    public void startGame(ScenePlay sc){
        //sc.winAppears(1);
        sc.winPaneRed.setScaleX(0.001);
        sc.winPaneRed.setScaleY(0.001);
        sc.winPaneOrange.setScaleX(0.001);
        sc.winPaneOrange.setScaleY(0.001);
        
        Sound.playBgSound.play();
        Sound.welcomeBgSound.stop();
        GameStatus.initialize();
        sc.placeObjectsInGamePane();
        GameStatus.positionObjects(1350, 780, sc.gamePane.getLayoutX(), sc.gamePane.getLayoutY());
        GameStatus.table.setImage(GameStatus.gameState);
        sc.frictionValue.setText(String.valueOf(GameStatus.FRICTION_COEFFICIENT[gameState])+" F/N");
        executeTurn(sc);
    }
    
    public void executeTurn(ScenePlay sc){
        sc.playButton.setDisable(false);
        sc.menuButton.setDisable(false);
        sc.forceSlider.setDisable(false);
        sc.angleSlider.setDisable(false);
        GameStatus.cue.appears(sc.angleSlider);
    }

    public void game(ScenePlay sc){
        
        long timeOfStart = System.currentTimeMillis();
        
        while(0.001 <= GameStatus.listOfBalls[0].getVi().getMagnitude()+GameStatus.listOfBalls[1].getVi().getMagnitude()+GameStatus.listOfBalls[2].getVi().getMagnitude()+GameStatus.listOfBalls[3].getVi().getMagnitude()+GameStatus.listOfBalls[4].getVi().getMagnitude()+GameStatus.listOfBalls[5].getVi().getMagnitude()+GameStatus.listOfBalls[6].getVi().getMagnitude()+GameStatus.listOfBalls[7].getVi().getMagnitude()+GameStatus.listOfBalls[8].getVi().getMagnitude()+GameStatus.listOfBalls[9].getVi().getMagnitude()){
            for(Ball ball : GameStatus.listOfBalls){//calculate position
                ball.updatePosition();
            }
            
            //CHECK OVERLAP
               for(int ball1=0; ball1< GameStatus.listOfBalls.length; ball1++){
                    for(int ball2=0; ball2< GameStatus.listOfBalls.length; ball2++){
                        if(ball2!=ball1){
                            GameStatus.penetrationFix(GameStatus.listOfBalls[ball1], GameStatus.listOfBalls[ball2]);
                        }
                    }
                }
            GameStatus.checkBallsCollisions(sc);//check if they collide, change x and speed of yes

            GameStatus.updateVisual(sc);//set centerX and y to show the changes to the user

            //System.out.print("speed: "+GameStatus.listOfBalls[0].getVi().getMagnitude());
            
            long timeOfEnd;
            
            do{    
                timeOfEnd = System.currentTimeMillis();
               
            }while(timeOfEnd - timeOfStart < TIME_OF_TICK);//make sure each step is the same amount of time
            
            //System.out.print(" timeOfEnd: "+ (timeOfEnd - timeOfStart));
            //System.out.print(" position "+ GameStatus.listOfBalls[0].getCenterX());
            timeOfStart = timeOfEnd;
            //System.out.println();
        }
        
        for(Ball ball : GameStatus.listOfBalls)
            ball.setVi(new Vector(0,0));
        
        
        System.out.println("ball finish moving");
        //update baskets
        sc.orangeBaskets.setCurrentFrame(GameStatus.teamsPoints[0]);
        sc.redBaskets.setCurrentFrame(GameStatus.teamsPoints[1]);
        
        if(GameStatus.listOfBalls[0].isPocketed){//white ball is in pocket
            whiteInPocket();
        }
        
        if(GameStatus.listOfBalls[8].isPocketed){//black ball is in pocket

            int winningTeam;
            if(GameStatus.teamsPoints[sc.teamName.getCurrentFrame()] >= 4){
                winningTeam = sc.teamName.getCurrentFrame();
                System.out.println("normal win");
            }
            else{
                winningTeam = (sc.teamName.getCurrentFrame()==0)?1:0;
                System.out.println("bomb win");
            }
            if(winningTeam == 0){
                sc.winPaneOrange.setScaleX(1);
                sc.winPaneOrange.setScaleY(1);
                Sound.yaySound.play();
            }else{
                sc.winPaneRed.setScaleX(1);
                sc.winPaneRed.setScaleY(1);
                Sound.yaySound.play();
            }
            
        }
        else
            changeTeam(sc);
    }

    public void playButtonHit(ScenePlay sc){
        
        sc.forceValue.setText(Float.valueOf(df.format(sc.forceSlider.getValue()))+" N");
        sc.angleValue.setText(Float.valueOf(df.format(sc.angleSlider.getValue()))+" deg");
        
        sc.playButton.setDisable(true);
        sc.menuButton.setDisable(true);
        sc.forceSlider.setDisable(true);
        sc.angleSlider.setDisable(true);
        
        double vx = sc.forceSlider.getValue()*Math.cos(sc.angleSlider.getValue()*PI/180);
        double vy = sc.forceSlider.getValue()*Math.sin(sc.angleSlider.getValue()*PI/180);
        
        GameStatus.listOfBalls[0].setVi(new Vector(vx,vy));

        GameStatus.cue.hitAnim(sc.angleSlider,sc.forceSlider);
        
        GameStatus.cue.hitAnim.setOnFinished(e-> {
            GameStatus.cue.setOpacity(0d);
            
            Task<Integer> task = new Task<Integer>() {
                @Override protected Integer call() throws Exception {
                    int iterations = 0;
                    game(sc);
                    return iterations;
                }
            };    
            Thread getItemsThread = new Thread(task);
            getItemsThread.setDaemon(true);
            getItemsThread.start();
            
        });

    }
    
    public void changeTeam(ScenePlay sc){
        System.out.println("change team done");
        sc.teamName.setCurrentFrame((sc.teamName.getCurrentFrame()+1)%2);
        executeTurn(sc);
    }
    
    public void whiteInPocket(){
        GameStatus.listOfBalls[0].setCenterX(tableLines[0].getStartX());
        GameStatus.listOfBalls[0].setCenterY(table.getY()+0.5*table.getHeight());
        GameStatus.listOfBalls[0].setVectorPosition(new Vector(GameStatus.listOfBalls[0].getCenterX(),GameStatus.listOfBalls[0].getCenterY()));
        GameStatus.listOfBalls[0].setVi(new Vector(0,0));
        GameStatus.listOfBalls[0].isPocketed = false;
        GameStatus.listOfBalls[0].setOpacity(1d);
    }
    
    public static void main(String[] args) {
        Application.launch();
    }
}