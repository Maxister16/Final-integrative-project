package Pool;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BallsMovement extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    private static ArrayList<Ball> listOfBallsA= new ArrayList<Ball>();

    @Override
    public void start(Stage primaryStage) throws Exception {
        GameStatus.initialize();
        GameStatus.positionObjects(500, 250);
        BorderPane pane = new BorderPane();
        //ADD ALL BALLS FROM GAME STATUS INTO ARRAY LIST
        for (int i = 0; i < 10; i++) {
            listOfBallsA.add(GameStatus.listOfBalls[i]);
            pane.getChildren().add(listOfBallsA.get(i));

        }







        //SETTING THE SCENE
        Scene scene = new Scene(pane, 450, 450);
        primaryStage.setTitle("Colliding Balls"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage

        /*for(int i=0; i<100; i++ ){
            double m=listOfBallsA.get(0).getCenterX() + 1;
            listOfBallsA.get(0).setCenterX(m);
        }

         */

    }
}
