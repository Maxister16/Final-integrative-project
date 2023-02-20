/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package Pool;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class TestClassM extends Application{

    public static void main(String[] args) {
        Application.launch();
    }

    @Override
    public void start(Stage ps){
        
        CustomAnimation dino = new CustomAnimation("gaming_DinoSprites_walk",5);
        Pane pane = new Pane();
        
        pane.getChildren().add(dino);
        Scene sc = new Scene(pane,200,200);
        
        sc.setOnMouseClicked(e->{
            if(e.getButton()== MouseButton.PRIMARY){dino.nextFrame();}
            else if(e.getButton()== MouseButton.SECONDARY){dino.previousFrame();}
        });
        
        
        
        //testVector
        Vector v1 = new Vector(0,0);
        Vector v2 = new Vector(0,4);
        Vector v3 = new Vector(-4,0);
        Vector v4 = new Vector(4,0);
        System.out.println(Math.toDegrees(v1.getAngle()));
        System.out.println(Math.toDegrees(v2.getAngle()));
        System.out.println(Math.toDegrees(v3.getAngle()));
        System.out.println(Math.toDegrees(v4.getAngle()));
        
        ps.setTitle("testScene");
        ps.setScene(sc);
        ps.show();

    }
}
