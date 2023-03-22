package Pool;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

public class TextTheBall extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    int xspeed=10;
    @Override
    public void start(@NotNull Stage primaryStage) throws Exception {
        Pane p=new Pane();

        Circle c=new Circle(10,10,10);
        c.setFill(Color.CORAL);

        p.getChildren().add(c);

        Scene scene = new Scene(p, 400, 250);
        primaryStage.setTitle("Exercise15_25"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
        while(primaryStage.isShowing()) {
            c.setCenterX(c.getCenterX() + xspeed);
            if(c.getCenterX()>= p.getMaxWidth()){
                c.setCenterX(c.getCenterX() - xspeed);
            } else if(c.getCenterX()<=0){
                c.setCenterX(c.getCenterX() + xspeed);


            }
        }


    }
}
