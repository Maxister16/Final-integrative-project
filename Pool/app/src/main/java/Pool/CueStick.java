
package Pool;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.PathTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polyline;

public class CueStick extends ImageView{
    
    public CueStick(){
        //this.setFill(new ImagePattern(new Image("InteractiveObjectIMG/cueStickIMG.png")));
        Image img = new Image("InteractiveObjectIMG/cueStickIMG.png");
        this.setImage(img);
        this.setSmooth(true);
        this.setPreserveRatio(true);
        this.setFitWidth(145*2);
        this.setFitHeight(this.getFitWidth()*(img.getHeight()/img.getWidth()));
    }
    
    public void appears(){
        this.setOpacity(1);
        this.setX(GameStatus.listOfBalls[0].getCenterX()-GameStatus.cue.getFitWidth()/2);
        this.setY(GameStatus.listOfBalls[0].getCenterY()-GameStatus.cue.getFitHeight()/2);
        //this.rotateProperty().bind(TestClassM.angleSlider.valueProperty());//bind to slider

    }
    
    public void hitAnim(double force){
        
        this.rotateProperty().unbind();
        //double WindUpLenght = (TestClassM.forceSlider.getValue()/TestClassM.forceSlider.getMax())+0.5;
        double WindUpLenght = (5/4+0.5);
        Polyline guide = new Polyline();
        
        PathTransition hitAnim = new PathTransition();
        hitAnim.setNode(this);
        hitAnim.setPath(guide);
        hitAnim.setRate(0.8);
        
        //set guide
        guide.getPoints().clear();
        guide.getPoints().addAll(new Double[]{
            GameStatus.listOfBalls[0].getCenterX(),GameStatus.listOfBalls[0].getCenterY(),
            GameStatus.listOfBalls[0].getCenterX()+WindUpLenght*this.getFitWidth()/2*Math.cos((180-this.getRotate())*Math.PI/180),GameStatus.listOfBalls[0].getCenterY()-WindUpLenght*this.getFitWidth()/2*Math.sin((180-this.getRotate())*Math.PI/180),
            GameStatus.listOfBalls[0].getCenterX()+0.25*this.getFitWidth()/2*Math.cos((this.getRotate())*Math.PI/180),GameStatus.listOfBalls[0].getCenterY()+.25*this.getFitWidth()/2*Math.sin((this.getRotate())*Math.PI/180),
            GameStatus.listOfBalls[0].getCenterX(),GameStatus.listOfBalls[0].getCenterY()});
        //guide.setStroke(Color.RED);
        hitAnim.play();
        
        hitAnim.setOnFinished(e->{
            try {
                System.out.println("hit the white ball");
                Thread.sleep(500);
                this.setOpacity(0);
            } catch (InterruptedException ex) {}
        });
        //move the white ball
    }
    
}
