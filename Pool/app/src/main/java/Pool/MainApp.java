package Pool;

import static Pool.GameStatus.listOfBalls;
import static Pool.GameStatus.table;
import static Pool.GameStatus.tableLines;
import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;
import java.text.DecimalFormat;

import static java.lang.Math.PI;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.concurrent.Task;

public class MainApp extends Application {
    
    private long TIME_OF_TICK = 1000/60;//time in millis
    Stage stagefield;

    @Override
    public void start(Stage stage) throws IOException {
        Sound.initiateSound();

        stagefield = stage;

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
            stage.setScene(scenePlayNormal.getScene());
            stage.setFullScreen(true);
            stage.setFullScreen(false);
            GameStatus.gameState = 0;
            startGame(scenePlayNormal);
            scenePlayNormal.frictionValue.setText(String.valueOf(GameStatus.FRICTION_COEFFICIENT[1])+" F/N");

        };
        EventHandler goToIce = e->{
            stage.setScene(scenePlayIce.getScene());
            stage.setFullScreen(true);
            stage.setFullScreen(false);
            GameStatus.gameState = 1;
            startGame(scenePlayIce);
            scenePlayIce.frictionValue.setText(String.valueOf(GameStatus.FRICTION_COEFFICIENT[0])+" F/N");
        };
        EventHandler goToGrass = e->{
            stage.setScene(scenePlayGrass.getScene());
            stage.setFullScreen(true);
            stage.setFullScreen(false);
            GameStatus.gameState = 2;
            startGame(scenePlayGrass);
            scenePlayGrass.frictionValue.setText(String.valueOf(GameStatus.FRICTION_COEFFICIENT[2])+" F/N");

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
        
//SCENE_WELCOME
        //Button Events
        sceneWelcome.normalBtn.setOnAction(goToNormal);
        sceneWelcome.grassBtn.setOnAction(goToGrass);
        sceneWelcome.iceBtn.setOnAction(goToIce);
        sceneWelcome.welcomeSoundBtn.setOnAction(e->{
            if (sceneWelcome.welcomeSoundBtn.getGraphic() == sceneWelcome.welcomeSoundOn) {
                sceneWelcome.welcomeSoundBtn.setGraphic(sceneWelcome.welcomeSoundOff);
                scenePlayNormal.soundButton.setGraphic(scenePlayNormal.soundOffIMG);
                scenePlayGrass.soundButton.setGraphic(scenePlayGrass.soundOffIMG);
                scenePlayIce.soundButton.setGraphic(scenePlayIce.soundOffIMG);
                Sound.muteSound();
            }
            else {
                sceneWelcome.welcomeSoundBtn.setGraphic(sceneWelcome.welcomeSoundOn);
                scenePlayNormal.soundButton.setGraphic(scenePlayNormal.soundOnIMG);
                scenePlayGrass.soundButton.setGraphic(scenePlayGrass.soundOnIMG);
                scenePlayIce.soundButton.setGraphic(scenePlayIce.soundOnIMG);
                Sound.unmutesound();
            }
        });

        sceneWelcome.welcomeSoundBtn.setOnMouseEntered(btnOnMouseEntered);
        sceneWelcome.welcomeSoundBtn.setOnMouseExited(btnOnMouseExited);
        sceneWelcome.normalBtn.setOnMouseEntered(btnOnMouseEntered);
        sceneWelcome.normalBtn.setOnMouseExited(btnOnMouseExited);
        sceneWelcome.grassBtn.setOnMouseEntered(btnOnMouseEntered);
        sceneWelcome.grassBtn.setOnMouseExited(btnOnMouseExited);
        sceneWelcome.iceBtn.setOnMouseEntered(btnOnMouseEntered);
        sceneWelcome.iceBtn.setOnMouseExited(btnOnMouseExited);
        
//SCENE_PLAY

        DecimalFormat df = new DecimalFormat("#.##");

        scenePlayNormal.playButton.setOnMouseEntered(btnOnMouseEntered);
        scenePlayNormal.playButton.setOnMouseExited(btnOnMouseExited);
        scenePlayNormal.playButton.setOnAction(e->{
            playButtonHit(scenePlayNormal);
            scenePlayNormal.forceValue.setText(Float.valueOf(df.format(scenePlayNormal.forceSlider.getValue()))+" N");
            scenePlayNormal.angleValue.setText(Float.valueOf(df.format(scenePlayNormal.angleSlider.getValue()))+"°");
        });
        scenePlayNormal.menuButton.setOnMouseEntered(btnOnMouseEntered);
        scenePlayNormal.menuButton.setOnMouseExited(btnOnMouseExited);
        scenePlayNormal.menuButton.setOnAction(e->{scenePlayNormal.menuAppears();});

        scenePlayIce.playButton.setOnMouseEntered(btnOnMouseEntered);
        scenePlayIce.playButton.setOnMouseExited(btnOnMouseExited);
        scenePlayIce.playButton.setOnAction(e->{
            playButtonHit(scenePlayIce);
            scenePlayIce.forceValue.setText(Float.valueOf(df.format(scenePlayIce.forceSlider.getValue()))+" N");
            scenePlayIce.angleValue.setText(Float.valueOf(df.format(scenePlayIce.angleSlider.getValue()))+"°");

        });
        scenePlayGrass.menuButton.setOnMouseEntered(btnOnMouseEntered);
        scenePlayGrass.menuButton.setOnMouseExited(btnOnMouseExited);
        scenePlayGrass.menuButton.setOnAction(e-> scenePlayGrass.menuAppears());

        scenePlayGrass.playButton.setOnMouseEntered(btnOnMouseEntered);
        scenePlayGrass.playButton.setOnMouseExited(btnOnMouseExited);
        scenePlayGrass.playButton.setOnAction(e-> {
            playButtonHit(scenePlayGrass);
            scenePlayGrass.forceValue.setText(Float.valueOf(df.format(scenePlayGrass.forceSlider.getValue()))+" N");
            scenePlayGrass.angleValue.setText(Float.valueOf(df.format(scenePlayGrass.angleSlider.getValue()))+"°");
        });

        scenePlayIce.menuButton.setOnMouseEntered(btnOnMouseEntered);
        scenePlayIce.menuButton.setOnMouseExited(btnOnMouseExited);
        scenePlayIce.menuButton.setOnAction(e-> scenePlayIce.menuAppears());

    //SOUND
        scenePlayNormal.soundButton.setOnMouseEntered(btnOnMouseEntered);
        scenePlayNormal.soundButton.setOnMouseExited(btnOnMouseExited);

        scenePlayNormal.soundButton.setOnAction(e->{
            if (scenePlayNormal.soundButton.getGraphic() == scenePlayNormal.soundOnIMG ) {
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
        });

        scenePlayIce.soundButton.setOnMouseEntered(btnOnMouseEntered);
        scenePlayIce.soundButton.setOnMouseExited(btnOnMouseExited);
        scenePlayIce.soundButton.setOnAction(e->{
            if (scenePlayIce.soundButton.getGraphic() == scenePlayIce.soundOnIMG ) {
                scenePlayIce.soundButton.setGraphic(scenePlayIce.soundOffIMG);
                sceneWelcome.welcomeSoundBtn.setGraphic(sceneWelcome.welcomeSoundOff);
                scenePlayNormal.soundButton.setGraphic(scenePlayNormal.soundOffIMG);
                scenePlayGrass.soundButton.setGraphic(scenePlayGrass.soundOffIMG);

                Sound.muteSound();
            }
            else {
                scenePlayIce.soundButton.setGraphic(scenePlayIce.soundOnIMG);
                sceneWelcome.welcomeSoundBtn.setGraphic(sceneWelcome.welcomeSoundOn);
                scenePlayNormal.soundButton.setGraphic(scenePlayNormal.soundOnIMG);
                scenePlayGrass.soundButton.setGraphic(scenePlayGrass.soundOnIMG);
                Sound.unmutesound();
            }
        });

        scenePlayGrass.soundButton.setOnMouseEntered(btnOnMouseEntered);
        scenePlayGrass.soundButton.setOnMouseExited(btnOnMouseExited);
        scenePlayGrass.soundButton.setOnAction(e->{
            if (scenePlayGrass.soundButton.getGraphic() == scenePlayGrass.soundOnIMG ) {
                scenePlayGrass.soundButton.setGraphic(scenePlayGrass.soundOffIMG);
                sceneWelcome.welcomeSoundBtn.setGraphic(sceneWelcome.welcomeSoundOff);
                scenePlayNormal.soundButton.setGraphic(scenePlayNormal.soundOffIMG);
                scenePlayIce.soundButton.setGraphic(scenePlayIce.soundOffIMG);
                Sound.muteSound();
            }
            else {
                scenePlayGrass.soundButton.setGraphic(scenePlayGrass.soundOnIMG);
                sceneWelcome.welcomeSoundBtn.setGraphic(sceneWelcome.welcomeSoundOn);
                scenePlayNormal.soundButton.setGraphic(scenePlayNormal.soundOnIMG);
                scenePlayIce.soundButton.setGraphic(scenePlayIce.soundOnIMG);
                Sound.unmutesound();
            }
        });




//MENU
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
                scenePlayNormal.frictionValue.setStyle("-fx-opacity: 0;");
                scenePlayNormal.angleValue.setStyle("-fx-opacity: 0;");
                scenePlayNormal.forceValue.setStyle("-fx-opacity: 0;");
                scenePlayNormal.frictionTitle.setStyle("-fx-opacity: 0;");
                scenePlayNormal.angleTitle.setStyle("-fx-opacity: 0;");
                scenePlayNormal.forceTitle.setStyle("-fx-opacity: 0;");


            }
            else {
                scenePlayNormal.physicsButton.setGraphic(scenePlayNormal.physicsOn);
                scenePlayNormal.frictionValue.setStyle("-fx-opacity: 1; -fx-font-size: 25px; -fx-font-weight: bold");
                scenePlayNormal.angleValue.setStyle("-fx-opacity: 1;-fx-font-size: 25px; -fx-font-weight: bold");
                scenePlayNormal.forceValue.setStyle("-fx-opacity: 1;-fx-font-size: 25px; -fx-font-weight: bold");
                scenePlayNormal.frictionTitle.setStyle("-fx-opacity: 1;-fx-font-size: 25px; -fx-font-weight: bold");
                scenePlayNormal.angleTitle.setStyle("-fx-opacity: 1;-fx-font-size: 25px; -fx-font-weight: bold");
                scenePlayNormal.forceTitle.setStyle("-fx-opacity: 1;-fx-font-size: 25px; -fx-font-weight: bold");
            }
         });

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
            }
            else {
                scenePlayIce.physicsButton.setGraphic(scenePlayIce.physicsOn);
            }
         });

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

            if (scenePlayGrass.physicsButton.getGraphic() == scenePlayGrass.physicsOn ) {
                scenePlayGrass.physicsButton.setGraphic(scenePlayGrass.physicsOff);
            }
            else {
                scenePlayGrass.physicsButton.setGraphic(scenePlayGrass.physicsOn);
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
        sc.angleSlider.setValue(0);
        sc.forceSlider.setValue(0);
        sc.playButton.setDisable(false);
        GameStatus.cue.appears(sc.angleSlider);
    }

    public void game(ScenePlay sc){

        //GameStatus.listOfBalls[0].setCenterX(0);
        long timeOfStart = System.currentTimeMillis();

        while(0.001 <= GameStatus.listOfBalls[0].getVi().getMagnitude()+GameStatus.listOfBalls[1].getVi().getMagnitude()+GameStatus.listOfBalls[2].getVi().getMagnitude()+GameStatus.listOfBalls[3].getVi().getMagnitude()+GameStatus.listOfBalls[4].getVi().getMagnitude()+GameStatus.listOfBalls[5].getVi().getMagnitude()+GameStatus.listOfBalls[6].getVi().getMagnitude()+GameStatus.listOfBalls[7].getVi().getMagnitude()+GameStatus.listOfBalls[8].getVi().getMagnitude()+GameStatus.listOfBalls[9].getVi().getMagnitude()){
                for(Ball ball : GameStatus.listOfBalls){//calculate position
                    ball.updatePosition();
                    //ball.penetrationFix();

                }
                //CHECK OVERLAP
             for(int ball1=0; ball1< listOfBalls.length; ball1++){
                    for(int ball2=0; ball2< listOfBalls.length; ball2++){
                        if(ball2!=ball1){
                            GameStatus.penetrationFix(listOfBalls[ball1], listOfBalls[ball2]);
                        }
                    }
                }




                GameStatus.checkBallsCollisions();//check if they collide, change x and speed of yes

                GameStatus.updateVisual();//set centerX and y to show the changes to the user

                System.out.print("speed: "+GameStatus.listOfBalls[0].getVi().getMagnitude());

                //MAX- HELP this doesn't work
            //sc.speedValue.setText(GameStatus.listOfBalls[0].getVi().getMagnitude() + "m/s");

            long timeOfEnd;

            do{
                timeOfEnd = System.currentTimeMillis();

            }while(timeOfEnd - timeOfStart < TIME_OF_TICK);//make sure each step is the same amount of time

            System.out.print(" timeOfEnd: "+ (timeOfEnd - timeOfStart));
            System.out.print(" position "+ GameStatus.listOfBalls[0].getCenterX());
            timeOfStart = timeOfEnd;
            System.out.println();
        }


        if(GameStatus.listOfBalls[0].isPocketed){
            whiteInPocket();
        }
        if(GameStatus.listOfBalls[8].isPocketed){

            //win(sc.teamName.getCurrentFrame());

        }

        changeTeam(sc);
    }

    public void playButtonHit(ScenePlay sc){
        
        sc.playButton.setDisable(true);

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

    public void playBtuttonAssign(ScenePlay sc){

    }
    
    public void changeTeam(ScenePlay sc){
        sc.teamName.setCurrentFrame((sc.teamName.getCurrentFrame()+1)%2);
        executeTurn(sc);
    }
    
    public void whiteInPocket(){
        GameStatus.listOfBalls[0].setCenterX(tableLines[0].getStartX());
        GameStatus.listOfBalls[0].setCenterY(table.getY()+0.5*table.getHeight());
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