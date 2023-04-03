
package Pool;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.animation.PathTransition;
import javafx.scene.control.Slider;
import javafx.util.Duration;

public class TestClassCueStick extends Application {
    public static Slider angleSlider = new Slider(0.0, 360.0, 1.0);
    public static Slider forceSlider = new Slider(0.0, 100, 1.0);
    
    @Override
    public void start(Stage stage) throws IOException {
        //WelcomeScene welcomeScene = new WelcomeScene(stage, new Table());
        
        stage.setMinHeight(600);
        stage.setMinWidth(900);
        Pane pane = new Pane();
        
        GameStatus.initialize();
        
        pane.getChildren().addAll(GameStatus.listOfBalls[0],GameStatus.cue, angleSlider, forceSlider);
        GameStatus.listOfBalls[0].setCenterY(200);
        GameStatus.listOfBalls[0].setCenterX(400);
        
        forceSlider.setTranslateX(200);
        
        GameStatus.cue.appears();
        
        Scene sc = new Scene(pane,200,300);
        stage.setScene(sc);
        //stage.setScene(welcomeScene.getScene());
        stage.show();
        
        sc.setOnMouseClicked(e->{
            GameStatus.cue.hitAnim(forceSlider.getValue());
        });
        
        sc.setOnKeyPressed(e->{
            System.out.println("key pressed");
            GameStatus.cue.appears();
        });
        
        
    }
    public static void main(String[] args) {
        Application.launch();
    }

}

    //Max's body

//    @Override
//    public void start(Stage ps){
//        Pane pane = new Pane();
//
//        //CustomAnimation dino = new CustomAnimation("gaming_DinoSprites_walk",5);
//
//       /* Net net = new Net(1);
//        pane.getChildren().add(net);
//        net.setCenterX(50);
//        net.setCenterY(50);*/
//
//
//        GameStatus.initialBallsAndNets();
//
//        for (int i = 0; i < GameStatus.nets.length; i++) {
//            GameStatus.nets[i].setCenterX((i%3)*60+20);
//            GameStatus.nets[i].setCenterY((i<3)?75:125);
//
//            pane.getChildren().add(GameStatus.nets[i]);
//        }
//
//        /*
//        //testVector
//        Vector v1 = new Vector(0,0);
//        Vector v2 = new Vector(0,4);
//        Vector v3 = new Vector(-4,0);
//        Vector v4 = new Vector(4,0);
//        System.out.println(Math.toDegrees(v1.getAngle()));
//        System.out.println(Math.toDegrees(v2.getAngle()));
//        System.out.println(Math.toDegrees(v3.getAngle()));
//        System.out.println(Math.toDegrees(v4.getAngle()));
//        */
//
//
//
//        Scene sc = new Scene(pane,200,200);
//        ps.setTitle("testScene");
//        ps.setScene(sc);
//        ps.show();
//
//
//        //event listener
//        /*sc.setOnMouseClicked(e->{
//                    if(e.getButton()== MouseButton.PRIMARY){dino.nextFrame();}
//                    else if(e.getButton()== MouseButton.SECONDARY){dino.previousFrame();}
//                });*/
//    }
//}
