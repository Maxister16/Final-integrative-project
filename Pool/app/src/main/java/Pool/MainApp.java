package Pool;

import static Pool.GameStatus.listOfBalls;
import static Pool.GameStatus.table;
import static Pool.GameStatus.tableLines;
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
        
        Sound.initiateSound();
        //create scenes
        SceneWelcome sceneWelcome = new SceneWelcome();
        ScenePlayGrass scenePlayGrass = new ScenePlayGrass();
        ScenePlayNormal scenePlayNormal = new ScenePlayNormal();
        ScenePlayIce scenePlayIce = new ScenePlayIce();
        
        stage.setHeight(780);
        stage.setWidth(1350);
        stage.setResizable(false);

        Sound.welcomeBgSound.play();
        stage.setScene(sceneWelcome.getScene());
        stage.show();
        
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
            target.setScaleX(1.15);
            target.setScaleY(1.15);
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
    //normal
        scenePlayNormal.playButton.setOnMouseEntered(btnOnMouseEntered);
        scenePlayNormal.playButton.setOnMouseExited(btnOnMouseExited);
        scenePlayNormal.playButton.setOnAction(e->{
            playButtonHit(scenePlayNormal);
        });
        scenePlayNormal.menuButton.setOnMouseEntered(btnOnMouseEntered);
        scenePlayNormal.menuButton.setOnMouseExited(btnOnMouseExited);
        scenePlayNormal.menuButton.setOnAction(e->{scenePlayNormal.menuAppears();});
        
        scenePlayNormal.buttonReplay.setOnMouseEntered(btnOnMouseEntered);
        scenePlayNormal.buttonReplay.setOnMouseExited(btnOnMouseExited);
        scenePlayNormal.buttonReplay.setOnAction(e->{
            scenePlayNormal.winDisappears();
            scenePlayNormal.angleSlider.setValue(0);
            scenePlayNormal.forceSlider.setValue(0);
            scenePlayNormal.orangeBaskets.setCurrentFrame(0);
            scenePlayNormal.redBaskets.setCurrentFrame(0);
            startGame(scenePlayNormal);
        });
        
    //ice
        scenePlayIce.playButton.setOnMouseEntered(btnOnMouseEntered);
        scenePlayIce.playButton.setOnMouseExited(btnOnMouseExited);
        scenePlayIce.playButton.setOnAction(e->{
            playButtonHit(scenePlayIce);
        });
        scenePlayIce.menuButton.setOnMouseEntered(btnOnMouseEntered);
        scenePlayIce.menuButton.setOnMouseExited(btnOnMouseExited);
        scenePlayIce.menuButton.setOnAction(e->{scenePlayIce.menuAppears();});
    //grass    
        scenePlayGrass.playButton.setOnMouseEntered(btnOnMouseEntered);
        scenePlayGrass.playButton.setOnMouseExited(btnOnMouseExited);
        scenePlayGrass.playButton.setOnAction(e->{
            playButtonHit(scenePlayGrass);
        });
        scenePlayGrass.menuButton.setOnMouseEntered(btnOnMouseEntered);
        scenePlayGrass.menuButton.setOnMouseExited(btnOnMouseExited);
        scenePlayGrass.menuButton.setOnAction(e->{scenePlayGrass.menuAppears();});
        
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
        Sound.playBgSound.play();
        Sound.welcomeBgSound.stop();
        GameStatus.initialize();
        sc.placeObjectsInGamePane();
        GameStatus.positionObjects(1350, 780, sc.gamePane.getLayoutX(), sc.gamePane.getLayoutY());
        GameStatus.table.setImage(GameStatus.gameState);
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
        
        //GameStatus.listOfBalls[0].setCenterX(0);
        long timeOfStart = System.currentTimeMillis();
        
        while(0.001 <= GameStatus.listOfBalls[0].getVi().getMagnitude()+GameStatus.listOfBalls[1].getVi().getMagnitude()+GameStatus.listOfBalls[2].getVi().getMagnitude()+GameStatus.listOfBalls[3].getVi().getMagnitude()+GameStatus.listOfBalls[4].getVi().getMagnitude()+GameStatus.listOfBalls[5].getVi().getMagnitude()+GameStatus.listOfBalls[6].getVi().getMagnitude()+GameStatus.listOfBalls[7].getVi().getMagnitude()+GameStatus.listOfBalls[8].getVi().getMagnitude()+GameStatus.listOfBalls[9].getVi().getMagnitude()){
            for(Ball ball : GameStatus.listOfBalls){//calculate position
                ball.updatePosition();
            }
            GameStatus.checkBallsCollisions(sc);//check if they collide, change x and speed of yes

            GameStatus.updateVisual();//set centerX and y to show the changes to the user

            if (sc.physicsButton.getGraphic() == sc.physicsOn ) {//print physics

            }
                
            System.out.print("speed: "+GameStatus.listOfBalls[0].getVi().getMagnitude());
                
            long timeOfEnd;
            
            do{    
                timeOfEnd = System.currentTimeMillis();
               
            }while(timeOfEnd - timeOfStart < TIME_OF_TICK);//make sure each step is the same amount of time
            
            System.out.print(" timeOfEnd: "+ (timeOfEnd - timeOfStart));
            System.out.print(" position "+ GameStatus.listOfBalls[0].getCenterX());
            timeOfStart = timeOfEnd;
            System.out.println();
        }
        
        for(int i = 0; i<GameStatus.listOfBalls.length; i++){//check which ball isPocketed
            if(GameStatus.listOfBalls[i].isPocketed){
                
                if(i==8){//black ball
                    if(GameStatus.teamsPoints[sc.teamName.getCurrentFrame()] >= 4){
                        sc.winAppears(sc.teamName.getCurrentFrame());
                    }
                    else{
                        sc.winAppears((sc.teamName.getCurrentFrame()==0)? 1: 0);
                    }
                }
                
                else if(i==0){//white ball
                    whiteInPocket();
                }
                
                else if(i<=4){//team 1
                    GameStatus.teamsPoints[0] ++;
                    sc.orangeBaskets.nextFrame();
                }
                
                else if(i>4){ //team 2
                    GameStatus.teamsPoints[1] ++;
                    sc.redBaskets.nextFrame();
                }
            }
        }
        
        changeTeam(sc);
    }

    public void playButtonHit(ScenePlay sc){
        
        sc.playButton.setDisable(true);
        sc.menuButton.setDisable(true);
        sc.forceSlider.setDisable(true);
        sc.angleSlider.setDisable(true);
        
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
    
    public void whiteInPocket(){
        GameStatus.listOfBalls[0].setCenterX(tableLines[0].getStartX());
        GameStatus.listOfBalls[0].setCenterY(table.getY()+0.5*table.getHeight());
        GameStatus.listOfBalls[0].setVectorPosition(new Vector(GameStatus.listOfBalls[0].getCenterX(),GameStatus.listOfBalls[0].getCenterY()));
        GameStatus.listOfBalls[0].setVi(new Vector(0,0));
        GameStatus.listOfBalls[0].isPocketed = false;
        GameStatus.listOfBalls[0].setOpacity(1d);
    }
    
    
    public void movingTheBalls(ScenePlay sc){
        System.out.println("ball are moving method");
        
        //when ball dont move
        changeTeam(sc);

    }
    
    
    public static void main(String[] args) {
        Application.launch();
    }
}