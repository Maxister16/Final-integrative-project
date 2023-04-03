package Pool;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Table{
    
    final private double WIDTH = 850;
    final private double HEIGHT = 425;
    
    private Rectangle background;
    private Rectangle border;
    
    public Table(){
        background = new Rectangle(WIDTH, HEIGHT);
        background.setArcWidth(50); 
        background.setArcHeight(50); 
        border = new Rectangle(WIDTH, HEIGHT);
        border.setFill(new ImagePattern(new Image("InteractiveObjectIMG/tableBorderIMG.png")));
    }

    
    public void setX(double x){
        background.setX(x);
        border.setX(x);
    }
    public void setY(double y){
        background.setY(y);
        border.setY(y);
    }
    public double getX(){
        return background.getX();
    }
    public double getY(){
        return background.getY();
    }
    public double getWidth(){
        return background.getWidth();
    }
    public double getHeight(){
        return background.getHeight();
    }
    
    
    public Rectangle getBorder(){
        return this.border;
    }
    public Rectangle getBackground(){
        return this.background;
    }
    
    public void setImage(int typeOfGame) { //sets the background
        switch(typeOfGame){//0:normal, 1:ice, 2:grass 
            case 0 -> background.setFill(Color.MOCCASIN);
            case 1 -> background.setFill(Color.LIGHTCYAN);
            case 2 -> background.setFill(Color.SEAGREEN);
        }
    }
}