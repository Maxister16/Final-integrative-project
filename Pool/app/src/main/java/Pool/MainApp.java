
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
        Sound.initiateSound();
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

        scenePlayNormal.playButton.setOnMouseEntered(btnOnMouseEntered);
        scenePlayNormal.playButton.setOnMouseExited(btnOnMouseExited);
        scenePlayNormal.playButton.setOnAction(e->{
            playButtonHit(scenePlayNormal);
            //GameStatus.cue.hitAnim(scenePlayNormal.angleSlider, scenePlayNormal.forceSlider);
        });
        scenePlayNormal.menuButton.setOnMouseEntered(btnOnMouseEntered);
        scenePlayNormal.menuButton.setOnMouseExited(btnOnMouseExited);
        scenePlayNormal.menuButton.setOnAction(goToWelcome);
        
        scenePlayIce.menuButton.setOnAction(goToWelcome);
        scenePlayGrass.menuButton.setOnAction(goToWelcome);
        
    }
    
    public void startGame(ScenePlay sc){
        Sound.playBgSound.play();
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
        
        while(GameStatus.listOfBalls[0].getIsMoving()){
            try {
                GameStatus.listOfBalls[0].updatePosition();
                Thread.sleep(1000);
                GameStatus.time+= 1000;
            } catch (InterruptedException ex) {
                Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        //when ball dont move
        //changeTeam(sc);
        
        
        /*while(true){
            while(GameStatus.isGameOn){   
            //balls are doing their thing   
            }
        }*/
    }
    
    
    public static void main(String[] args) {
        Application.launch();
    }
}
