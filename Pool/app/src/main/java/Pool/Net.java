
package Pool;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class Net extends Circle implements InteractiveObject{
    
    public Net(){
        //this.setImage(new Image());
    }
    @Override
    public void setImage(Image newImage) {
        this.setFill(new ImagePattern(newImage));
    }
}
