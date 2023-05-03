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
    private final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.##");
    
    @Override
    public void start(Stage stage) throws IOException {
        
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
            if (sourceBtn.getGraphic() == scenePlayNormal.getSoundOnIMG() || sourceBtn.getGraphic() == scenePlayIce.getSoundOnIMG() || sourceBtn.getGraphic() == scenePlayGrass.getSoundOnIMG() || sourceBtn.getGraphic() == sceneWelcome.getWelcomeSoundOn()) {
                scenePlayNormal.getSoundButton().setGraphic(scenePlayNormal.getSoundOffIMG());
                sceneWelcome.getWelcomeSoundBtn().setGraphic(sceneWelcome.getWelcomeSoundOff());
                scenePlayGrass.getSoundButton().setGraphic(scenePlayGrass.getSoundOffIMG());
                scenePlayIce.getSoundButton().setGraphic(scenePlayIce.getSoundOffIMG());
                Sound.muteSound();
            }
            else {
                scenePlayNormal.getSoundButton().setGraphic(scenePlayNormal.getSoundOnIMG());
                sceneWelcome.getWelcomeSoundBtn().setGraphic(sceneWelcome.getWelcomeSoundOn());
                scenePlayGrass.getSoundButton().setGraphic(scenePlayGrass.getSoundOnIMG());
                scenePlayIce.getSoundButton().setGraphic(scenePlayIce.getSoundOnIMG());
                Sound.unmutesound();
            }
        };
        
//SCENE_WELCOME
        //Button Events
        sceneWelcome.getNormalBtn().setOnAction(goToNormal);
        sceneWelcome.getGrassBtn().setOnAction(goToGrass);
        sceneWelcome.getIceBtn().setOnAction(goToIce);
        
        sceneWelcome.getNormalBtn().setOnMouseEntered(btnOnMouseEntered);
        sceneWelcome.getNormalBtn().setOnMouseExited(btnOnMouseExited);
        sceneWelcome.getGrassBtn().setOnMouseEntered(btnOnMouseEntered);
        sceneWelcome.getGrassBtn().setOnMouseExited(btnOnMouseExited);
        sceneWelcome.getIceBtn().setOnMouseEntered(btnOnMouseEntered);
        sceneWelcome.getIceBtn().setOnMouseExited(btnOnMouseExited);
        //sound button
        sceneWelcome.getWelcomeSoundBtn().setOnMouseEntered(btnOnMouseEntered);
        sceneWelcome.getWelcomeSoundBtn().setOnMouseExited(btnOnMouseExited);
        sceneWelcome.getWelcomeSoundBtn().setOnAction(btnMuteClicked);
        
//SCENE_PLAY

    //NORMAL
        scenePlayNormal.getPlayButton().setOnMouseEntered(btnOnMouseEntered);
        scenePlayNormal.getPlayButton().setOnMouseExited(btnOnMouseExited);
        scenePlayNormal.getPlayButton().setOnAction(e->{
            playButtonHit(scenePlayNormal);
        });
        scenePlayNormal.getMenuButton().setOnMouseEntered(btnOnMouseEntered);
        scenePlayNormal.getMenuButton().setOnMouseExited(btnOnMouseExited);
        scenePlayNormal.getMenuButton().setOnAction(e->{scenePlayNormal.menuAppears();});
    
        //replay
        scenePlayNormal.getButtonReplayRed().setOnMouseEntered(btnOnMouseEntered);
        scenePlayNormal.getButtonReplayRed().setOnMouseExited(btnOnMouseExited);
        scenePlayNormal.getButtonReplayRed().setOnAction(e->{
            //scenePlayNormal.winDisappears();
            scenePlayNormal.getAngleSliderButton().setValue(0);
            scenePlayNormal.getForceSliderButton().setValue(0);
            scenePlayNormal.getOrangeBaskets().setCurrentFrame(0);
            scenePlayNormal.getRedBaskets().setCurrentFrame(0);
            
            scenePlayNormal.getWinPaneRed().setScaleX(0.001);
            scenePlayNormal.getWinPaneRed().setScaleY(0.001);
            scenePlayNormal.getWinPaneOrange().setScaleX(0.001);
            scenePlayNormal.getWinPaneOrange().setScaleY(0.001);
            
            goToWelcome.handle(e);
            Sound.yaySound.stop();
        });
        scenePlayNormal.getButtonReplayOrange().setOnMouseEntered(btnOnMouseEntered);
        scenePlayNormal.getButtonReplayOrange().setOnMouseExited(btnOnMouseExited);
        scenePlayNormal.getButtonReplayOrange().setOnAction(e->{
            //scenePlayNormal.winDisappears();
            scenePlayNormal.getAngleSliderButton().setValue(0);
            scenePlayNormal.getForceSliderButton().setValue(0);
            scenePlayNormal.getOrangeBaskets().setCurrentFrame(0);
            scenePlayNormal.getRedBaskets().setCurrentFrame(0);
            
            scenePlayNormal.getWinPaneRed().setScaleX(0.001);
            scenePlayNormal.getWinPaneRed().setScaleY(0.001);
            scenePlayNormal.getWinPaneOrange().setScaleX(0.001);
            scenePlayNormal.getWinPaneOrange().setScaleY(0.001);
            
            goToWelcome.handle(e);
            Sound.yaySound.stop();
        });
    
        //sound button
        scenePlayNormal.getSoundButton().setOnMouseEntered(btnOnMouseEntered);
        scenePlayNormal.getSoundButton().setOnMouseExited(btnOnMouseExited);
        scenePlayNormal.getSoundButton().setOnAction(btnMuteClicked);
        
    //ICE
        scenePlayIce.getPlayButton().setOnMouseEntered(btnOnMouseEntered);
        scenePlayIce.getPlayButton().setOnMouseExited(btnOnMouseExited);
        scenePlayIce.getPlayButton().setOnAction(e->{
            playButtonHit(scenePlayIce);
        });
        scenePlayIce.getMenuButton().setOnMouseEntered(btnOnMouseEntered);
        scenePlayIce.getMenuButton().setOnMouseExited(btnOnMouseExited);
        scenePlayIce.getMenuButton().setOnAction(e->{scenePlayIce.menuAppears();});
        
        //replay
        scenePlayIce.getButtonReplayRed().setOnMouseEntered(btnOnMouseEntered);
        scenePlayIce.getButtonReplayRed().setOnMouseExited(btnOnMouseExited);
        scenePlayIce.getButtonReplayRed().setOnAction(e->{
            //scenePlayIce.winDisappears();
            scenePlayIce.getAngleSliderButton().setValue(0);
            scenePlayIce.getForceSliderButton().setValue(0);
            scenePlayIce.getOrangeBaskets().setCurrentFrame(0);
            scenePlayIce.getRedBaskets().setCurrentFrame(0);
            
            scenePlayIce.getWinPaneRed().setScaleX(0.001);
            scenePlayIce.getWinPaneRed().setScaleY(0.001);
            scenePlayIce.getWinPaneOrange().setScaleX(0.001);
            scenePlayIce.getWinPaneOrange().setScaleY(0.001);
            
            goToWelcome.handle(e);
            Sound.yaySound.stop();
        });
        scenePlayIce.getButtonReplayOrange().setOnMouseEntered(btnOnMouseEntered);
        scenePlayIce.getButtonReplayOrange().setOnMouseExited(btnOnMouseExited);
        scenePlayIce.getButtonReplayOrange().setOnAction(e->{
            //scenePlayIce.winDisappears();
            scenePlayIce.getAngleSliderButton().setValue(0);
            scenePlayIce.getForceSliderButton().setValue(0);
            scenePlayIce.getOrangeBaskets().setCurrentFrame(0);
            scenePlayIce.getRedBaskets().setCurrentFrame(0);
            
            scenePlayIce.getWinPaneRed().setScaleX(0.001);
            scenePlayIce.getWinPaneRed().setScaleY(0.001);
            scenePlayIce.getWinPaneOrange().setScaleX(0.001);
            scenePlayIce.getWinPaneOrange().setScaleY(0.001);
            
            goToWelcome.handle(e);
            Sound.yaySound.stop();
        });
    
        //sound button
        scenePlayIce.getSoundButton().setOnMouseEntered(btnOnMouseEntered);
        scenePlayIce.getSoundButton().setOnMouseExited(btnOnMouseExited);
        scenePlayIce.getSoundButton().setOnAction(btnMuteClicked);
        
    //GRASS    
        scenePlayGrass.getPlayButton().setOnMouseEntered(btnOnMouseEntered);
        scenePlayGrass.getPlayButton().setOnMouseExited(btnOnMouseExited);
        scenePlayGrass.getPlayButton().setOnAction(e->{
            playButtonHit(scenePlayGrass);
        });
        scenePlayGrass.getMenuButton().setOnMouseEntered(btnOnMouseEntered);
        scenePlayGrass.getMenuButton().setOnMouseExited(btnOnMouseExited);
        scenePlayGrass.getMenuButton().setOnAction(e->{scenePlayGrass.menuAppears();});
        
        //replay
        scenePlayGrass.getButtonReplayRed().setOnMouseEntered(btnOnMouseEntered);
        scenePlayGrass.getButtonReplayRed().setOnMouseExited(btnOnMouseExited);
        scenePlayGrass.getButtonReplayRed().setOnAction(e->{
            //scenePlayGrass.winDisappears();
            scenePlayGrass.getAngleSliderButton().setValue(0);
            scenePlayGrass.getForceSliderButton().setValue(0);
            scenePlayGrass.getOrangeBaskets().setCurrentFrame(0);
            scenePlayGrass.getRedBaskets().setCurrentFrame(0);
            
            scenePlayGrass.getWinPaneRed().setScaleX(0.001);
            scenePlayGrass.getWinPaneRed().setScaleY(0.001);
            scenePlayGrass.getWinPaneOrange().setScaleX(0.001);
            scenePlayGrass.getWinPaneOrange().setScaleY(0.001);
            
            goToWelcome.handle(e);
            Sound.yaySound.stop();
        });
        scenePlayGrass.getButtonReplayOrange().setOnMouseEntered(btnOnMouseEntered);
        scenePlayGrass.getButtonReplayOrange().setOnMouseExited(btnOnMouseExited);
        scenePlayGrass.getButtonReplayOrange().setOnAction(e->{
            //scenePlayGrass.winDisappears();
            scenePlayGrass.getAngleSliderButton().setValue(0);
            scenePlayGrass.getForceSliderButton().setValue(0);
            scenePlayGrass.getOrangeBaskets().setCurrentFrame(0);
            scenePlayGrass.getRedBaskets().setCurrentFrame(0);
            
            scenePlayGrass.getWinPaneRed().setScaleX(0.001);
            scenePlayGrass.getWinPaneRed().setScaleY(0.001);
            scenePlayGrass.getWinPaneOrange().setScaleX(0.001);
            scenePlayGrass.getWinPaneOrange().setScaleY(0.001);
            
            goToWelcome.handle(e);
            Sound.yaySound.stop();
        });
    
        //sound button
        scenePlayGrass.getSoundButton().setOnMouseEntered(btnOnMouseEntered);
        scenePlayGrass.getSoundButton().setOnMouseExited(btnOnMouseExited);
        scenePlayGrass.getSoundButton().setOnAction(btnMuteClicked);
        
//MENU
    //normal
        scenePlayNormal.getResumeButton().setOnMouseEntered(btnOnMouseEntered);
        scenePlayNormal.getResumeButton().setOnMouseExited(btnOnMouseExited);
        scenePlayNormal.getResumeButton().setOnAction(e->{scenePlayNormal.menuDisappears();});
        
        scenePlayNormal.getHomeButton().setOnMouseEntered(btnOnMouseEntered);
        scenePlayNormal.getHomeButton().setOnMouseExited(btnOnMouseExited);
        scenePlayNormal.getHomeButton().setOnAction(goToWelcome);
        
        scenePlayNormal.getExitButton().setOnMouseEntered(btnOnMouseEntered);
        scenePlayNormal.getExitButton().setOnMouseExited(btnOnMouseExited);
        scenePlayNormal.getExitButton().setOnAction(e->{stage.close();});
        
        scenePlayNormal.getPhysicsButton().setOnMouseEntered(btnOnMouseEntered);
        scenePlayNormal.getPhysicsButton().setOnMouseExited(btnOnMouseExited);
        scenePlayNormal.getPhysicsButton().setOnAction(e->{
        
            if (scenePlayNormal.getPhysicsButton().getGraphic() == scenePlayNormal.getPhysicsOn() ) {
                scenePlayNormal.getPhysicsButton().setGraphic(scenePlayNormal.getPhysicsOff());
                scenePlayNormal.physicsDisappears();
            }
            else {
                scenePlayNormal.getPhysicsButton().setGraphic(scenePlayNormal.getPhysicsOn());
                scenePlayNormal.physicsAppears();
            }
         });
        
    //ice    
        scenePlayIce.getResumeButton().setOnMouseEntered(btnOnMouseEntered);
        scenePlayIce.getResumeButton().setOnMouseExited(btnOnMouseExited);
        scenePlayIce.getResumeButton().setOnAction(e->{scenePlayIce.menuDisappears();});
        
        scenePlayIce.getHomeButton().setOnMouseEntered(btnOnMouseEntered);
        scenePlayIce.getHomeButton().setOnMouseExited(btnOnMouseExited);
        scenePlayIce.getHomeButton().setOnAction(goToWelcome);
        
        scenePlayIce.getExitButton().setOnMouseEntered(btnOnMouseEntered);
        scenePlayIce.getExitButton().setOnMouseExited(btnOnMouseExited);
        scenePlayIce.getExitButton().setOnAction(e->{stage.close();});
        
        scenePlayIce.getPhysicsButton().setOnMouseEntered(btnOnMouseEntered);
        scenePlayIce.getPhysicsButton().setOnMouseExited(btnOnMouseExited);
        scenePlayIce.getPhysicsButton().setOnAction(e->{
        
            if (scenePlayIce.getPhysicsButton().getGraphic() == scenePlayIce.getPhysicsOn() ) {
                scenePlayIce.getPhysicsButton().setGraphic(scenePlayIce.getPhysicsOff());
                scenePlayIce.physicsDisappears();
            }
            else {
                scenePlayIce.getPhysicsButton().setGraphic(scenePlayIce.getPhysicsOn());
                scenePlayIce.physicsAppears();
            }
         });
      
    //grass    
        scenePlayGrass.getResumeButton().setOnMouseEntered(btnOnMouseEntered);
        scenePlayGrass.getResumeButton().setOnMouseExited(btnOnMouseExited);
        scenePlayGrass.getResumeButton().setOnAction(e->{scenePlayGrass.menuDisappears();});
        
        scenePlayGrass.getHomeButton().setOnMouseEntered(btnOnMouseEntered);
        scenePlayGrass.getHomeButton().setOnMouseExited(btnOnMouseExited);
        scenePlayGrass.getHomeButton().setOnAction(goToWelcome);
        
        scenePlayGrass.getExitButton().setOnMouseEntered(btnOnMouseEntered);
        scenePlayGrass.getExitButton().setOnMouseExited(btnOnMouseExited);
        scenePlayGrass.getExitButton().setOnAction(e->{stage.close();});
        
        scenePlayGrass.getPhysicsButton().setOnMouseEntered(btnOnMouseEntered);
        scenePlayGrass.getPhysicsButton().setOnMouseExited(btnOnMouseExited);
        scenePlayGrass.getPhysicsButton().setOnAction(e->{
        
            if (scenePlayGrass.getPhysicsButton().getGraphic() == scenePlayGrass.getPhysicsOn() ) {//turn off
                scenePlayGrass.getPhysicsButton().setGraphic(scenePlayGrass.getPhysicsOff());
                scenePlayGrass.physicsDisappears();
            }
            else { //turn on
                scenePlayGrass.getPhysicsButton().setGraphic(scenePlayGrass.getPhysicsOn());
                scenePlayGrass.physicsAppears();
            }
         });
    }
    
    public void startGame(ScenePlay sc){
        //sc.winAppears(1);
        sc.getWinPaneRed().setScaleX(0.001);
        sc.getWinPaneRed().setScaleY(0.001);
        sc.getWinPaneOrange().setScaleX(0.001);
        sc.getWinPaneOrange().setScaleY(0.001);
        
        Sound.playBgSound.play();
        Sound.welcomeBgSound.stop();
        GameStatus.initialize();
        sc.placeObjectsInGamePane();
        GameStatus.positionObjects(1350, 780, sc.getGamePane().getLayoutX(), sc.getGamePane().getLayoutY());
        GameStatus.table.setImage(GameStatus.gameState);
        sc.getFrictionValueLabel().setText(String.valueOf(GameStatus.FRICTION_COEFFICIENT[gameState])+" F/N");
        executeTurn(sc);
    }
    
    public void executeTurn(ScenePlay sc){
        sc.getPlayButton().setDisable(false);
        sc.getMenuButton().setDisable(false);
        sc.getForceSliderButton().setDisable(false);
        sc.getAngleSliderButton().setDisable(false);
        GameStatus.cue.appears(sc.getAngleSliderButton());
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
        sc.getOrangeBaskets().setCurrentFrame(GameStatus.teamsPoints[0]);
        sc.getRedBaskets().setCurrentFrame(GameStatus.teamsPoints[1]);
        
        if(GameStatus.listOfBalls[0].isPocketed){//white ball is in pocket
            whiteInPocket();
        }
        
        if(GameStatus.listOfBalls[8].isPocketed){//black ball is in pocket

            int winningTeam;
            if(GameStatus.teamsPoints[sc.getTeamNameBaskets().getCurrentFrame()] >= 4){
                winningTeam = sc.getTeamNameBaskets().getCurrentFrame();
                System.out.println("normal win");
            }
            else{
                winningTeam = (sc.getTeamNameBaskets().getCurrentFrame()==0)?1:0;
                System.out.println("bomb win");
            }
            if(winningTeam == 0){
                sc.getWinPaneOrange().setScaleX(1);
                sc.getWinPaneOrange().setScaleY(1);
                Sound.yaySound.play();
            }else{
                sc.getWinPaneRed().setScaleX(1);
                sc.getWinPaneRed().setScaleY(1);
                Sound.yaySound.play();
            }
            
        }
        else
            changeTeam(sc);
    }

    public void playButtonHit(ScenePlay sc){
        
        sc.getForceValueLabel().setText(Float.valueOf(DECIMAL_FORMAT.format(sc.getForceSliderButton().getValue()))+" N");
        sc.getAngleValueLabel().setText(Float.valueOf(DECIMAL_FORMAT.format(sc.getAngleSliderButton().getValue()))+"°");
        
        sc.getPlayButton().setDisable(true);
        sc.getMenuButton().setDisable(true);
        sc.getForceSliderButton().setDisable(true);
        sc.getAngleSliderButton().setDisable(true);
        
        double vx = sc.getForceSliderButton().getValue()*Math.cos(sc.getAngleSliderButton().getValue()*PI/180);
        double vy = sc.getForceSliderButton().getValue()*Math.sin(sc.getAngleSliderButton().getValue()*PI/180);
        
        GameStatus.listOfBalls[0].setVi(new Vector(vx,vy));

        GameStatus.cue.hitAnim(sc.getAngleSliderButton(),sc.getForceSliderButton());
        
        GameStatus.cue.getHitAnim().setOnFinished(e-> {
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
        sc.getTeamNameBaskets().setCurrentFrame((sc.getTeamNameBaskets().getCurrentFrame()+1)%2);
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