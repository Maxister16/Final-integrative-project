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

public class FinalCollide extends Application {
    private static ArrayList<Ball> listOfBallsA = new ArrayList<Ball>();
    private static ArrayList<BallMovement> lol = new ArrayList<BallMovement>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GameStatus.initialize();
        GameStatus.positionObjects(500, 250);
        BorderPane pane = new BorderPane();
        for (int i = 0; i < 10; i++) {
            GameStatus.listOfBalls[i].setRadius(18.070866141732285);
            listOfBallsA.add(GameStatus.listOfBalls[i]);
            pane.getChildren().add(listOfBallsA.get(i));
        }














        Scene scene = new Scene(pane, 450, 450);
        //ScenePlayNormal sc=new ScenePlayNormal(primaryStage);
        primaryStage.setTitle("Colliding Balls"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }
}
