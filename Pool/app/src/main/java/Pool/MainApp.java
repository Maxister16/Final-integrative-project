
package Pool;

import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.PathTransition;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class MainApp extends Application {
    
    @Override
    public void start(Stage stage) throws IOException {
       // Sound.initiateSound();
        //create scenes
        SceneWelcome sceneWelcome = new SceneWelcome();
        ScenePlayGrass scenePlayGrass = new ScenePlayGrass();
        ScenePlayNormal scenePlayNormal = new ScenePlayNormal();
        ScenePlayIce scenePlayIce = new ScenePlayIce();
        
        stage.setHeight(780);
        stage.setWidth(1350);
        //stage.setResizable(false);

        stage.setScene(sceneWelcome.getScene());
        stage.show();
        
        //action handlers for repetitive actions
//GO TO SCENES
        EventHandler goToNormal = e->{
            stage.setScene(scenePlayNormal.getScene());
            stage.setFullScreen(true);
            stage.setFullScreen(false);
            GameStatus.gameState = 0;
            startGame(scenePlayNormal);
        };
        EventHandler goToIce = e->{
            stage.setScene(scenePlayIce.getScene());
            stage.setFullScreen(true);
            stage.setFullScreen(false);
            GameStatus.gameState = 1;
            startGame(scenePlayIce);
        };
        EventHandler goToGrass = e->{
            stage.setScene(scenePlayGrass.getScene());
            stage.setFullScreen(true);
            stage.setFullScreen(false);
            GameStatus.gameState = 2;
            startGame(scenePlayGrass);
        };
        EventHandler goToWelcome = e->{
         //   Sound.playBgSound.stop();
          //  Sound.welcomeBgSound.play();
            stage.setScene(sceneWelcome.getScene());
            stage.setFullScreen(true);
            stage.setFullScreen(false);
        };
        EventHandler menuAppears = e->{
            scenePlayNormal.menuAppears();
        };
        
        
//BUTTON ACTIONS
        EventHandler btnOnMouseEntered = e->{
            Button target = (Button)e.getTarget();
            target.setScaleX(1.2);
            target.setScaleY(1.2);
           // Sound.btnSound.stop();
          //  Sound.btnSound.play();
        };
        EventHandler btnOnMouseExited = e->{
            Button target = (Button)e.getTarget();
            target.setScaleX(1);
            target.setScaleY(1);
          //  Sound.btnSound.stop();
           // Sound.btnSound.play();
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
        
//SCENE_PLAY

    //playButton

        scenePlayNormal.playButton.setOnMouseEntered(btnOnMouseEntered);
        scenePlayNormal.playButton.setOnMouseExited(btnOnMouseExited);
        scenePlayNormal.playButton.setOnAction(e->{
            playButtonHit(scenePlayNormal);
        });
        scenePlayIce.playButton.setOnMouseEntered(btnOnMouseEntered);
        scenePlayIce.playButton.setOnMouseExited(btnOnMouseExited);
        scenePlayIce.playButton.setOnAction(e->{
            playButtonHit(scenePlayIce);
        });
        scenePlayGrass.playButton.setOnMouseEntered(btnOnMouseEntered);
        scenePlayGrass.playButton.setOnMouseExited(btnOnMouseExited);
        scenePlayGrass.playButton.setOnAction(e->{
            playButtonHit(scenePlayGrass);
        });
        
    //Menubutton
        
        scenePlayNormal.menuButton.setOnMouseEntered(btnOnMouseEntered);
        scenePlayNormal.menuButton.setOnMouseExited(btnOnMouseExited);
        scenePlayNormal.menuButton.setOnAction(goToWelcome);
        
        scenePlayIce.menuButton.setOnMouseEntered(btnOnMouseEntered);
        scenePlayIce.menuButton.setOnMouseExited(btnOnMouseExited);
        scenePlayIce.menuButton.setOnAction(goToWelcome);
        
        scenePlayGrass.menuButton.setOnMouseEntered(btnOnMouseEntered);
        scenePlayGrass.menuButton.setOnMouseExited(btnOnMouseExited);
        scenePlayGrass.menuButton.setOnAction(goToWelcome);

    //Sound btn
        scenePlayNormal.soundButton.setOnMouseEntered(btnOnMouseEntered);
        scenePlayNormal.soundButton.setOnMouseExited(btnOnMouseExited);
      //  scenePlayNormal.soundButton.setOnAction(e-> Sound.muteSound());

        scenePlayIce.soundButton.setOnMouseEntered(btnOnMouseEntered);
        scenePlayIce.soundButton.setOnMouseExited(btnOnMouseExited);
       // scenePlayIce.soundButton.setOnAction(e-> Sound.muteSound());

        scenePlayGrass.soundButton.setOnMouseEntered(btnOnMouseEntered);
        scenePlayGrass.soundButton.setOnMouseExited(btnOnMouseExited);
       // scenePlayGrass.soundButton.setOnAction(e-> Sound.muteSound());



    }
    
    public void startGame(ScenePlay sc){
      //  Sound.welcomeBgSound.stop();
      //  Sound.playBgSound.play();
        GameStatus.initialize();
        sc.placeObjectsInGamePane();
        GameStatus.positionObjects(1350, 780, sc.gamePane.getLayoutX(), sc.gamePane.getLayoutY());
        GameStatus.table.setImage(GameStatus.gameState);
        executeTurn(sc);
    }
    
    public void executeTurn(ScenePlay sc){
        GameStatus.cue.appears(sc.angleSlider);
    }
    public void playButtonHit(ScenePlay sc){
        
        GameStatus.cue.hitAnim(sc.angleSlider,sc.forceSlider);

        GameStatus.cue.hitAnim.setOnFinished(e->{
            System.out.println("set opacity to zero");
            GameStatus.cue.setOpacity(0);
            movingTheBalls(sc);
            
            /*try {
                
                Thread.sleep(500);
                GameStatus.cue.setOpacity(0);
            } catch (InterruptedException ex) {}*/
        });
    }
    
    public void changeTeam(ScenePlay sc){
        sc.teamName.setCurrentFrame((sc.teamName.getCurrentFrame()+1)%2);
        executeTurn(sc);
    }
    
    public void movingTheBalls(ScenePlay sc){
        System.out.println("ball are moving method");
        
        //            maxSpeed * percentage of force
        double forceValue = 10 * sc.forceSlider.getValue()/100;
        double angleValue = 180-sc.angleSlider.getValue(); //in deg
        System.out.println(forceValue+" angle:"+angleValue);
        changeTeam(sc);
        /*
        while(GameStatus.listOfBalls[0].getIsMoving()){
            try {
                GameStatus.listOfBalls[0].updatePosition();
                Thread.sleep(1000);
                GameStatus.time+= 1000;
            } catch (InterruptedException ex) {
                Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }*/
        
        
        //when ball dont move

        
        /*while(true){
            while(GameStatus.isGameOn){   
            //balls are doing their thing   
            }
        }*/
    }
    
    public void endGame(ScenePlay sc){
        
    }
    
    
    public static void main(String[] args) {
        Application.launch();
    }/*
    public void Game()
    {
        Time previousFrame = GetTime();
        Time deltaTime = 1/60.;
        while(GameRunning)
        {
            input();//white ball starts moving
            Tick(deltaTime);//set value of x but dont move the ball
            ResolveCollisions();//check with collision for the value of x calculated before
            UpdateGraphic();//update position according to x and collision
            //Time newTime = GetTime();
            //deltaTime = newTime - previousTime;
            //previousTime = newTime;
            Time newTime;
                    
            do
            {
                newTime = GetTime();
            }
            while(newTime - previousTime < 1/60.);
            previousTime = newTime;
                   
        }
    }
    public void Tick(float deltaTime)
    {
        foreach(ball in balls)
        {
            ball.updateposition();
        }
    }*/
}
