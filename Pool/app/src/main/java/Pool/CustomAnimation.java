
package Pool;

import java.lang.reflect.InvocationTargetException;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CustomAnimation extends ImageView{

    private int currentFrame;
    private Image[] imgArr;
    
    //constructors
    public CustomAnimation(String nameImg, int nbrOfFrames){
        System.out.println("run");
        currentFrame = 0;
        imgArr = new Image[nbrOfFrames];
        for(int i = 0; i<nbrOfFrames; i++){
            imgArr[i] = new Image("CustomAnimationIMG/"+nameImg+i+".png");
        }
        updateFrame();
    }
    
    public final void updateFrame(){
        if(currentFrame >= imgArr.length){currentFrame =imgArr.length-1;}
        else if(currentFrame < 0){currentFrame = 0;}
        this.setImage(imgArr[currentFrame]);
    }
    
    public void nextFrame(){
        this.currentFrame++;
        updateFrame();
    }
    public void previousFrame(){
        this.currentFrame--;
        updateFrame();
    }
    
    public void setCurrentFrame(int frame){
        this.currentFrame = frame;
        updateFrame();
    }
    public int getCurrentFrame(){
        return this.currentFrame;
    }
}
