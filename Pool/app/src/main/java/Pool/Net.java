
package Pool;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class Net extends Circle implements InteractiveObject{
    
    public Net(int index){
        super(25);
        this.setImage(new Image("InteractiveObjectIMG/netIMG.png"));
        switch(index){
            case 0, 5 -> this.setRotate(-45);
            case 1, 4 -> this.setRotate(0);
            case 2, 3 -> this.setRotate(45);
        }
    }
    @Override
    public void setImage(Image newImage) {
        this.setFill(new ImagePattern(newImage));
    }
}
