
package Pool;

import javafx.animation.PathTransition;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class CueStick extends Rectangle{
    
    private Circle guide;
    
    public CueStick(){
        this.setFill(new ImagePattern(new Image("InteractiveObjectIMG/cueStickIMG.png")));
        this.setWidth(145);
        this.setHeight(10);
        this.setX(100);
        this.setY(100);
        guide = new Circle();
        guide.setFill(Color.TRANSPARENT);
        guide.setStroke(Color.RED);
    }
    public Circle getGuide(){
        System.out.println("return guide");
        return this.guide;
    }
    
    public void rotateAt(double angle){//in degrees
        System.out.println("set guide");
        guide.setRadius(GameStatus.listOfBalls[0].getRadius()*3);
        guide.setCenterX(GameStatus.listOfBalls[0].getCenterX());
        guide.setCenterY(GameStatus.listOfBalls[0].getCenterY());
        
        System.out.println("set guide works");
        PathTransition anim = new PathTransition();
        anim.setNode(this);
        anim.setPath(guide);
        anim.play();
    }
    
}
