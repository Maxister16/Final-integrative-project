package Pool;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainClassTest extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane pane=new Pane();
        GameStatus.initialize();
        Ball[] listB= GameStatus.listOfBalls;
        for (int i=0; i<10;i++){

            pane.getChildren().add(listB[i]);
        }

        Scene scene = new Scene(pane, 400, 250);
        primaryStage.setTitle("Exercise15_25"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage




        Bounds bounds = pane.getBoundsInLocal();
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(10),
        new KeyValue(listB[0].layoutXProperty(), bounds.getMaxX()-listB[0].getRadius())));
        timeline.setCycleCount(2);
        timeline.play();

    }
}
