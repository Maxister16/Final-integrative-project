package Pool;

import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;

public class TestCollide extends Application {
    private static ArrayList<Ball> listOfBallsA= new ArrayList<Ball>();
    private static ArrayList<BallMovement> lol= new ArrayList<BallMovement>();
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GameStatus.initialize();
        GameStatus.positionObjects(500, 250);
        BorderPane pane = new BorderPane();
            for(int i=0; i<10;i++){
                    GameStatus.listOfBalls[i].setRadius(18.070866141732285);
                    listOfBallsA.add(GameStatus.listOfBalls[i]);
                    pane.getChildren().add(listOfBallsA.get(i));
                }
        Button ok=new Button("start");
        System.out.println("y is " + listOfBallsA.get(0).getCenterY() + " x is " + listOfBallsA.get(0).getCenterX() );
        listOfBallsA.get(0).setOnMouseClicked(e ->{
            double r=listOfBallsA.get(0).getCenterX();
            double d=400f;
            double yy=400f;
            TranslateTransition tt = new TranslateTransition();
            tt.setNode(listOfBallsA.get(0));
            tt.setRate(0.5);
            tt.setByX(d);
           // tt.setByY(d);
            tt.setCycleCount(1);
            tt.setAutoReverse(true);

            tt.play();
            //System.out.println("After");
            //System.out.println("y is " + listOfBallsA.get(0).getCenterY() + " x is " + listOfBallsA.get(0).getCenterX() );

            if (listOfBallsA.get(0).getCenterX() < listOfBallsA.get(0).getRadius() ||
                    listOfBallsA.get(0).getCenterX() > 400f- listOfBallsA.get(0).getRadius()) {
                //listOfBallsA.get(0).dx *= -1; // Change ball move direction
                System.out.println("lol yes");
                tt.stop();
                tt.setRate(-1*tt.getRate());
                tt.play();
            }
            /*if (listOfBallsA.get(0).getCenterY() < listOfBallsA.get(0).getRadius() ||
                    listOfBallsA.get(0).getCenterY() > 400f - listOfBallsA.get(0).getRadius()) {
                System.out.println("lol no");
                //if(ball.getCenterY()> GameStatus.table.getHeight() || ball.getCenterY()< GameStatus.table.getHeight() ){
                listOfBallsA.get(0).dy *= -1; // Change ball move direction
                tt.stop();
                tt.setRate(-1*tt.getRate());
                tt.play();
            }

             */


            });

        Scene scene = new Scene(pane, 450, 450);
        //ScenePlayNormal sc=new ScenePlayNormal(primaryStage);

        primaryStage.setTitle("Colliding Balls"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage



        }
    }

