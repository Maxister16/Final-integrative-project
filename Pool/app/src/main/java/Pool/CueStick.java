
package Pool;

import javafx.animation.PathTransition;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Polyline;

public class CueStick extends ImageView{
    
    public PathTransition hitAnim;
    
    public CueStick(double width){
        
        Image img = new Image("InteractiveObjectIMG/cueStickIMG.png");
        this.setImage(img);
        this.setSmooth(true);
        this.setPreserveRatio(true);
        this.setFitWidth(width);
        this.setFitHeight(this.getFitWidth()*(img.getHeight()/img.getWidth()));
    }
    
    public void appears(Slider angleSlider){
        this.setOpacity(1);
        this.setX(GameStatus.listOfBalls[0].getCenterX()-GameStatus.cue.getFitWidth()/2);
        this.setY(GameStatus.listOfBalls[0].getCenterY()-GameStatus.cue.getFitHeight()/2);
        this.rotateProperty().bind(angleSlider.valueProperty());//bind to slider
    }
    
    public void hitAnim(Slider angleSlider,Slider forceSlider){
        
        this.rotateProperty().unbind();
        double WindUpLenght = (forceSlider.getValue()/forceSlider.getMax())+0.5;
        Polyline guide = new Polyline();
        
        hitAnim = new PathTransition();
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
    }
    
}
