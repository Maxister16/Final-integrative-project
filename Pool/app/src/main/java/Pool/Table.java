
package Pool;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Table extends Rectangle implements InteractiveObject{
    
    public Table(){
        //this.setImage(newImage);
    }
    
    @Override
    public void setImage(Image newImage) {
        this.setFill(new ImagePattern(newImage));
    }
}
