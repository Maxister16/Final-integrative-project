package Pool;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class BallsCollidingAnimation extends Application {
    private static ArrayList<Ball> listOfBallsA= new ArrayList<Ball>();
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GameStatus.initialize();
        GameStatus.positionObjects(500, 250);
        BorderPane pane= new BorderPane();
        //ADD ALL BALLS FROM GAME STATUS INTO ARRAY LIST
        for(int i=0; i<10;i++){
                listOfBallsA.add(GameStatus.listOfBalls[i]);
            listOfBallsA.get(i).setA(new Vector(-0.2,-0.2));
                pane.getChildren().add(listOfBallsA.get(i));

            }


        Scene scene = new Scene(pane, 450, 450);
        primaryStage.setTitle("Colliding Balls"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage



        //FOR THE PURPOSE OF CHECKING CODE I SET THE VALUE OF ISSTRIKED FOR WHITE BALL TO TRUE AND GIVE A VALUE FOR THE VELOCITY
        listOfBallsA.get(0).IsStriked(true);
        listOfBallsA.get(0).setVi(new Vector(10, 0));

        //CHECK IF THE WHITE BALL HAS BEEN STRICKEN
        while (listOfBallsA.get(0).getVi().getXcomponent()>0){
        if(listOfBallsA.get(0).getIsStriked()==true){
            listOfBallsA.get(0).updatePosition(GameStatus.time);
            listOfBallsA.get(0).reactIsHit();
        }}





    }


}
