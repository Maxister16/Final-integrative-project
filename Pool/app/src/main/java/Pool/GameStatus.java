
package Pool;

import javafx.scene.image.Image;
import javafx.scene.paint.Paint;

public class GameStatus {
    public static boolean isGameOn = false;
    public static int team1Points = 0;
    public static int team2Points = 0;
    public static int gameState = 0;
    public static long time = 0;
    public static Ball[] listOfBalls = new Ball[10];
    public static Net[] nets = new Net[6];
    public static Table table = new Table();
    
    public static void updateTime() throws InterruptedException{
        while(GameStatus.isGameOn){
            Thread.sleep(100);
            time += 100;
        }
    }
    
    public static void positionObjects(double widthOfWindow, double heightOfWindow){
        
        table.setX((widthOfWindow-table.getWidth())*0.5);
        table.setY(heightOfWindow-table.getHeight()-50);
        
        for(int i = 0; i<listOfBalls.length;i++){
            if(i<nets.length){
                nets[i].setCenterX((i%3)*(table.getWidth()-nets[0].getRadius()*2.5)/2+table.getX()+nets[0].getRadius());
                nets[i].setCenterY((i<3)?table.getY()+nets[0].getRadius():table.getY()+table.getHeight()-nets[0].getRadius());
            }
            listOfBalls[i].setCenterX(table.getX()+0.75*table.getWidth()+i*2*listOfBalls[i].getRadius());
            listOfBalls[i].setCenterY(table.getY()+0.5*table.getHeight());
        }
        setZorder();
    }
    
    public static void initialize(){
        
        Image team1Img = new Image("InteractiveObjectIMG\\BallTeam1IMG.png");
        Image team2Img = new Image("InteractiveObjectIMG\\BallTeam2IMG.png");
        for(int i = 0; i<listOfBalls.length; i++){
            if(i<nets.length)
                nets[i] = new Net(i);
            
            listOfBalls[i] = new Ball(i);
            if(i==0){ //is it white
                listOfBalls[i].setImage(new Image("InteractiveObjectIMG\\BallWhiteIMG.png"));
                //listOfBalls[i].setFill(Paint.valueOf("#ffffff"));
                listOfBalls[i].setType(0);
            }
            else if(i==8){
                listOfBalls[i].setImage(new Image("InteractiveObjectIMG\\BallBlackIMG.png"));
                //listOfBalls[i].setFill(Paint.valueOf("#000000"));
                listOfBalls[i].setType(3);
            }
            else if(i<=4){//is it team 1
                listOfBalls[i].setImage(team1Img);
                //listOfBalls[i].setFill(Paint.valueOf("#ff0000"));
                listOfBalls[i].setType(1);
            }
            else {//is it team 2
                listOfBalls[i].setImage(team2Img);
                //listOfBalls[i].setFill(Paint.valueOf("#ff9900"));
                listOfBalls[i].setType(2);
            }
        }
    }
    
    public static void setZorder(){
        for (Net net : nets) {
            net.toFront();
        }
        table.getBorder().toFront();
        for (Ball listOfBall : listOfBalls) {
            listOfBall.toFront();
        }
    }
    
    public static void reset(){
        isGameOn = false;
        team1Points = 0;
        team2Points = 0;
        time = 0;
        
        /*
        for(int i = 0; i<listOfBalls.length; i++){
            listOfBalls[i].getVVector().setXcomponent(0);
            listOfBalls[i].getVVector().setYcomponent(0);
            listOfBalls[i].getAVector().setXcomponent(0);
            listOfBalls[i].getAVector().setYcomponent(0);
        
            listOfBalls[i].setIsPocketed(false);
        }
        */
        
    }

}
