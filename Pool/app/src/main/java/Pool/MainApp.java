package Pool;

import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;
import static java.lang.Math.PI;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.concurrent.Task;

public class MainApp extends Application {
    
    private long TIME_OF_TICK = 1000/60;//time in millis
    Stage stagefield;
    
    @Override
    public void start(Stage stage) throws IOException {
        
        stagefield = stage;
        
        //Sound.initiateSound();
        //create scenes
        SceneWelcome sceneWelcome = new SceneWelcome();
        ScenePlayGrass scenePlayGrass = new ScenePlayGrass();
        ScenePlayNormal scenePlayNormal = new ScenePlayNormal();
        ScenePlayIce scenePlayIce = new ScenePlayIce();
        
        stage.setHeight(780);
        stage.setWidth(1350);
        stage.setResizable(false);

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
            target.setScaleX(1.5);
            target.setScaleY(1.5);
            //Sound.btnSound.stop();
            //Sound.btnSound.play();
        };
        EventHandler btnOnMouseExited = e->{
            Button target = (Button)e.getTarget();
            target.setScaleX(1);
            target.setScaleY(1);
            //Sound.btnSound.stop();
            //Sound.btnSound.play();
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
        });
        scenePlayNormal.menuButton.setOnMouseEntered(btnOnMouseEntered);
        scenePlayNormal.menuButton.setOnMouseExited(btnOnMouseExited);
        scenePlayNormal.menuButton.setOnAction(goToWelcome);
        
        scenePlayIce.menuButton.setOnAction(goToWelcome);
        scenePlayGrass.menuButton.setOnAction(goToWelcome);
        
    }
    
    public void startGame(ScenePlay sc){
        //Sound.playBgSound.play();
        GameStatus.initialize();
        sc.placeObjectsInGamePane();
        GameStatus.positionObjects(1350, 780, sc.gamePane.getLayoutX(), sc.gamePane.getLayoutY());
        GameStatus.table.setImage(GameStatus.gameState);
        executeTurn(sc);
    }
    
    public void executeTurn(ScenePlay sc){
        GameStatus.cue.appears(sc.angleSlider);
    }

    public void game(ScenePlay sc){
        
        //GameStatus.listOfBalls[0].setCenterX(0);
        long timeOfStart = System.currentTimeMillis();
        
        while(GameStatus.listOfBalls[0].getVi().getMagnitude() >= 0.001) {
            for (Ball ball : GameStatus.listOfBalls) {//calculate position
                ball.updatePosition();
            }
            GameStatus.checkBallsCollisions(sc);//check if they collide, change x and speed of yes

            GameStatus.updateVisual();//set centerX and y to show the changes to the user

            // GameStatus.listOfBalls[0].setCenterX(252);
            System.out.print("speed: " + GameStatus.listOfBalls[0].getVi().getMagnitude());
            long timeOfEnd;

            do {
                timeOfEnd = System.currentTimeMillis();

            } while (timeOfEnd - timeOfStart < TIME_OF_TICK);//make sure each step is the same amount of time

            System.out.print(" timeOfEnd: " + (timeOfEnd - timeOfStart));
            System.out.print(" position " + GameStatus.listOfBalls[0].getCenterX());
            timeOfStart = timeOfEnd;
            System.out.println();
        }

        changeTeam(sc);
        
    }

    public void playButtonHit(ScenePlay sc){
        
        double vx = sc.forceSlider.getValue()*Math.cos(sc.angleSlider.getValue()*PI/180);
        double vy = sc.forceSlider.getValue()*Math.sin(sc.angleSlider.getValue()*PI/180);
        
        GameStatus.listOfBalls[0].setVi(new Vector(vx,vy));

        GameStatus.cue.hitAnim(sc.angleSlider,sc.forceSlider);
        
 /*       GameStatus.cue.hitAnim.setOnFinished(e-> {
       game(sc);    
        });
*/
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
        sc.teamName.setCurrentFrame((sc.teamName.getCurrentFrame()+1)%2);
        executeTurn(sc);
    }
    
    public void movingTheBalls(ScenePlay sc){
        System.out.println("ball are moving method");
        
        //when ball dont move
        changeTeam(sc);
        
        
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