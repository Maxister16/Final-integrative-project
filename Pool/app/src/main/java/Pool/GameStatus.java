
package Pool;

import javafx.scene.image.Image;
import javafx.scene.shape.Line;

public class GameStatus {
    public static String CLIENT_LOCATION_OF_PROJECT = System.getProperty("user.dir").replace("\\","/");
    public static boolean isGameOn = false;
    public static int team1Points = 0;
    public static int team2Points = 0;
    public static int gameState = 0;
    public static long time = 0;
    public static Ball[] listOfBalls = new Ball[10];
    public static Net[] nets = new Net[6];
    public static Table table = new Table();
    public static Line[] tableLines = new Line[2];
    
    public static void updateTime() throws InterruptedException{
        while(GameStatus.isGameOn){
            Thread.sleep(100);
            time += 100;
        }
    }
    
    public static void positionObjects(double widthOfWindow, double heightOfWindow){
        //center table
        table.setX((widthOfWindow-table.getWidth())*0.5);
        table.setY(heightOfWindow-table.getHeight()-50);
        
        //set position of lines
    //HEADLINE
        tableLines[0].setStartX(table.getX()+0.2*table.getWidth());
        tableLines[0].setStartY(table.getY()+10);
        tableLines[0].setEndX(table.getX()+0.2*table.getWidth());
        tableLines[0].setEndY(table.getY()+table.getHeight()-10);
    //START OF THE LOSANGE
        tableLines[1].setStartX(table.getX()+0.7*table.getWidth());
        tableLines[1].setStartY(table.getY()+10);
        tableLines[1].setEndX(table.getX()+0.7*table.getWidth());
        tableLines[1].setEndY(table.getY()+table.getHeight()-10);
        
        //constant and calculations for position of balls
        double DISTANCE_BETWEEN_BALLS = 0d;
        double DISTANCE_BETWEEN_CENTER_OF_BALLS = DISTANCE_BETWEEN_BALLS+listOfBalls[0].getRadius()*2; //distance between the center of the balls
        //center of losange
        double xCenter = tableLines[1].getStartX();
        double yCenter = table.getY()+0.5*table.getHeight();
        
        //set position of Nets and Balls
        for(int i = 0; i<listOfBalls.length;i++){
            if(i<nets.length){
                nets[i].setCenterX((i%3)*(table.getWidth()-nets[0].getRadius()*2.5)/2+table.getX()+nets[0].getRadius());
                nets[i].setCenterY((i<3)?table.getY()+nets[0].getRadius():table.getY()+table.getHeight()-nets[0].getRadius());
            }
            
            if(i==0){
                listOfBalls[i].setCenterX(tableLines[0].getStartX());
                listOfBalls[i].setCenterY(table.getY()+0.5*table.getHeight());
            }
            else if(i==8){
                listOfBalls[i].setCenterX(xCenter);
                listOfBalls[i].setCenterY(yCenter);
            }
            else if(i<=4){//team 1
                if(i%2==0){ //2,4
                    listOfBalls[i].setCenterX(xCenter);
                    listOfBalls[i].setCenterY(yCenter+(i-3)*(DISTANCE_BETWEEN_CENTER_OF_BALLS+DISTANCE_BETWEEN_BALLS));
                }
                else{ //1,3
                    listOfBalls[i].setCenterX(xCenter+(i-2)*(Math.sqrt(3)*DISTANCE_BETWEEN_CENTER_OF_BALLS+DISTANCE_BETWEEN_BALLS));
                    listOfBalls[i].setCenterY(yCenter);
                }
            }
            else{ //team2
                int xMult = 1;
                int yMult = 1;
                switch(i){
                    case 5: xMult = -1; yMult = -1; break;
                    case 6: xMult = 1; yMult = -1; break;
                    case 7: xMult = 1; yMult = 1; break;
                    case 9: xMult = -1; yMult = 1; break;
                }
                listOfBalls[i].setCenterX(xCenter+xMult*(Math.sqrt(0.75)*DISTANCE_BETWEEN_CENTER_OF_BALLS+DISTANCE_BETWEEN_BALLS));
                listOfBalls[i].setCenterY(yCenter+yMult*(0.5*DISTANCE_BETWEEN_CENTER_OF_BALLS+DISTANCE_BETWEEN_BALLS));
            }
        }
        setZorder();
    }
    
    public static void initialize(){
        
        Image team1Img = new Image("InteractiveObjectIMG/BallTeam1IMG.png");
        Image team2Img = new Image("InteractiveObjectIMG/BallTeam2IMG.png");
        for(int i = 0; i<listOfBalls.length; i++){
            if(i<nets.length)
                nets[i] = new Net(i);
            
            if(i<tableLines.length)
                tableLines[i] = new Line();
            
            listOfBalls[i] = new Ball(i);
            if(i==0){ //is it white
                listOfBalls[i].setImage(new Image("InteractiveObjectIMG/BallWhiteIMG.png"));
                //listOfBalls[i].setFill(Paint.valueOf("#ffffff"));
                listOfBalls[i].setType(0);
            }
            else if(i==8){
                listOfBalls[i].setImage(new Image("InteractiveObjectIMG/BallBlackIMG.png"));
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
        for(Line line : tableLines){
            line.toFront();
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
