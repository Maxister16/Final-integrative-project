
package Pool;

import javafx.animation.PathTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polyline;

public class CueStick extends ImageView{
    
    public static Polyline guide = new Polyline();
    
    public CueStick(){
        //this.setFill(new ImagePattern(new Image("InteractiveObjectIMG/cueStickIMG.png")));
        Image img = new Image("InteractiveObjectIMG/cueStickIMG.png");
        this.setImage(img);
        this.setSmooth(true);
        this.setPreserveRatio(true);
        this.setFitWidth(145*2);
        this.setFitHeight(this.getFitWidth()*(img.getHeight()/img.getWidth()));
    }
    
    public void appears(){//in degrees
        this.setOpacity(1);
        this.setX(GameStatus.listOfBalls[0].getCenterX()-GameStatus.cue.getFitWidth()/2);
        this.setY(GameStatus.listOfBalls[0].getCenterY()-GameStatus.cue.getFitHeight()/2);
        this.rotateProperty().bind(TestClassM.angleSlider.valueProperty());//bind to slider
    }
    
    public void hitAnim(double force){
        
        this.rotateProperty().unbind();
        PathTransition hitAnim = new PathTransition();
        hitAnim.setNode(this);
        hitAnim.setPath(guide);
        
        double WindUpLenght = 100*(TestClassM.forceSlider.getValue()/TestClassM.forceSlider.getMax());
        
        //set guide
        guide.getPoints().clear();
        guide.getPoints().addAll(new Double[]{
            GameStatus.listOfBalls[0].getCenterX(),GameStatus.listOfBalls[0].getCenterY(),
            GameStatus.listOfBalls[0].getCenterX()+WindUpLenght*this.getFitWidth()/2*Math.cos((180-this.getRotate())*Math.PI/180),GameStatus.listOfBalls[0].getCenterY()-WindUpLenght*this.getFitWidth()/2*Math.sin((180-this.getRotate())*Math.PI/180),
            //GameStatus.listOfBalls[0].getCenterX()-this.getFitWidth()/2*Math.cos((180-this.getRotate())*Math.PI/180),GameStatus.listOfBalls[0].getCenterY()-this.getFitWidth()/2*Math.sin((180-this.getRotate())*Math.PI/180),
            GameStatus.listOfBalls[0].getCenterX(),GameStatus.listOfBalls[0].getCenterY()});
        guide.setStroke(Color.RED);
        System.out.println(this.getRotate());
        hitAnim.play();
        
        //this.setOpacity(0);
        //move the white ball
    }
    
}
