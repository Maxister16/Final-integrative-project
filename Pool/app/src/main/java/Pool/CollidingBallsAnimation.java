package Pool;

import java.awt.*;
import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;

import static Pool.GameStatus.listOfBalls;
import static javafx.application.Application.launch;

import javafx.beans.property.DoubleProperty;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Marie
 */
public class CollidingBallsAnimation extends Application {
    private static ArrayList<Ball> listOfBallsA= new ArrayList<Ball>();
    private static ArrayList<BallMovement> lol= new ArrayList<BallMovement>();
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GameStatus.initialize();
        GameStatus.positionObjects(500,250);
        BorderPane pane=new BorderPane();
        BallMovement p=new BallMovement(0.5);
        BallMovement inactive=new BallMovement(0);
//WE WOULD REPLACE THIS BY CUE STICK RELEASED
        Button ok=new Button("start");
        ok.setOnAction(e -> {p.play();});
        pane.setBottom(ok);


        //pane.getChildren().add(ok);

        for(int i=0; i<10;i++){
            if(i==0 ||i==1){
                listOfBallsA.add(GameStatus.listOfBalls[i]);
              //  lol.add(i,new BallMovement(0.5));
              //  lol.get(i).getChildren().add(listOfBallsA.get(i));
            } else {
                listOfBallsA.add(GameStatus.listOfBalls[i]);
                //pane.getChildren().add(listOfBallsA.get(i));
                inactive.getChildren().add(listOfBallsA.get(i));
                //lol.add(i,new BallMovement(0));
                //lol.get(i).getChildren().add(listOfBallsA.get(i));
            }
        }

        pane.getChildren().add(inactive);
       /*for(int i=0; i<10; i++) {
            pane.setCenter(lol.get(i));
        }
*/

        /*for(int i=0; i<10;i++){
            listOfBallsA.add(GameStatus.listOfBalls[i]);
            listOfBallsA.get(i).setVi(new Vector (5,5));
            p.add(listOfBallsA.get(i));

        }

         */


        //Ball white=new Ball(100,100,20,1);
        //white.setFill(Color.AQUAMARINE);

        listOfBallsA.get(0).setVi(new Vector (5,5));
        listOfBallsA.get(1).setVi(new Vector (2,2));
        p.add(listOfBallsA.get(0));
        p.add(listOfBallsA.get(1));


        pane.setCenter(p);



        //pane.setLeft(inactive);

        Scene scene = new Scene(pane, 450, 450);
        //ScenePlayNormal sc=new ScenePlayNormal(primaryStage);

        primaryStage.setTitle("Colliding Balls"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage



        //CHECK FOR COLLISIONS
       /* while(true){
            for(int i=0; i<listOfBallsA.size(); i++){
                listOfBallsA.get(i).reactIsHit();
            }
        }

        */




    }

}

class BallMovement extends Pane {
    private Timeline animation;

    public BallMovement(double z) {
        // Create an animation for moving the ball
        animation = new Timeline(
                new KeyFrame(Duration.millis(50), e -> moveBall()));
        animation.setRate(z);
        //FOR THIS WE CAN SIMPLY DO A CALCULATION OF HOW LONG BEFORE VELOCITY REACHES ZERO WITH DECCELERATION
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play(); // Start animation

    }

    public void add(Ball z) {
        getChildren().add(z);

    }

    public void play() {
        animation.play();
    }

    public void pause() {
        animation.pause();
    }

    public void increaseSpeed() {
        animation.setRate(animation.getRate() + 0.1);
    }

    public void decreaseSpeed() {
        animation.setRate(
                animation.getRate() > 0 ? animation.getRate() - 0.1 : 0);
    }

    public DoubleProperty rateProperty() {
        return animation.rateProperty();
    }

    protected void moveBall() {
        // Node node= (Node) this.getChildren();


        for (Node node : this.getChildren()) {
            //System.out.println("Width" + getWidth());
            //System.out.println("Height" + getHeight());
            Ball ball = (Ball) node;

            //Ball b=this.getChildren().get(0);
            // Check boundaries
            //ADD THIS TO REACTISHIT TO SEE IF BALLS HIT CORNERS
            if (ball.getCenterX() < ball.getRadius() ||
                    ball.getCenterX() > getWidth() - ball.getRadius()) {
                ball.dx *= -1; // Change ball move direction
            }
            if (ball.getCenterY() < ball.getRadius() ||
                    ball.getCenterY() > getHeight() - ball.getRadius()) {
                //if(ball.getCenterY()> GameStatus.table.getHeight() || ball.getCenterY()< GameStatus.table.getHeight() ){
                ball.dy *= -1; // Change ball move direction
            }
            for (int i = 0; i < GameStatus.listOfBalls.length; i++) {
                //SET VALUES FOR X1,Y1 AND X2,Y2
                double x1 = ball.getCenterX();
                double x2 = GameStatus.listOfBalls[i].getCenterX();
                double y1 = ball.getCenterY();
                double y2 = GameStatus.listOfBalls[i].getCenterY();
                //CHECK IF BALLS ARE COLLIDING
                if (Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2) <= 4 * ball.getRadius() * ball.getRadius()) {
                    //System.out.println("hey");
                    //CALL A METHOD IN THIS CLASS THAT WILL CHECK FOR COLLISIONS
                    ball.updateMovement(GameStatus.listOfBalls[i]);
                    //listOfBalls.get(i).updateMovement(this);
                }

                // Adjust ball position
                ball.setCenterX(ball.dx + ball.getCenterX());
                ball.setCenterY(ball.dy + ball.getCenterY());
                //SET THE VECTOR POSITIONS
                ball.setVectorPosition(new Vector(ball.getCenterX(), ball.getCenterY()));
                //CHECK COLLISIONS
                //ball.reactIsHit();


            }
        }


    }
}
