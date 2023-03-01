
package Pool;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Table implements InteractiveObject{
    
    Rectangle background;
    Rectangle border;
    
    public Table(){
        this.setImage(newImage);
    }
    
    @Override
    public void setImage(Image newImage) { //sets the background
        background.setFill(new ImagePattern(newImage));
    }
}
