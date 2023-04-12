package Pool;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.animation.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;


public class Animate extends Application {
    private static ArrayList<Ball> listOfBallsA= new ArrayList<Ball>();

    public static void main(String[] args) {
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Rectangle rect = new Rectangle (100, 40, 100, 100);
        rect.setArcHeight(50);
        rect.setArcWidth(50);
        rect.setFill(Color.VIOLET);
        GameStatus.initialize();
        BorderPane pane = new BorderPane();
        for(int i=0; i<10;i++){
            listOfBallsA.add(GameStatus.listOfBalls[i]);
            pane.getChildren().add(listOfBallsA.get(i));
        }
//WE GET VALUES OF FINAL X AND FINAL Y
        //BUT THEN BOOM COLLIDE
        //HOW DETECT COLLIDE
        //FACTOR IN COLLIDE BEFORE HAND
        TranslateTransition tt = new TranslateTransition(Duration.millis(2000), rect);
        tt.setByX(200f);
        tt.setCycleCount(1);
        tt.setAutoReverse(true);

        tt.play();

        //BorderPane pane= new BorderPane();
        pane.setCenter(rect);

        Scene scene = new Scene(pane, 450, 450);
        //ScenePlayNormal sc=new ScenePlayNormal(primaryStage);

        primaryStage.setTitle("Colliding Balls"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }
}
