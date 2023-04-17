
package Pool;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import java.awt.*;

public class GameStatus {
    public static String CLIENT_LOCATION_OF_PROJECT = System.getProperty("user.dir");
    public static boolean isGameOn = false;
    public static int team1Points = 0;
    public static int team2Points = 0;
    public static int gameState = 0;
    public static long time = 0;
    public static Ball[] listOfBalls = new Ball[10];
    public static Net[] nets = new Net[6];
    public static Table table = new Table();
    public static Line[] tableLines = new Line[2];
    public static double[] FRICTION_COEFFICIENT = {0.95,0.1,0.5}; //0:normal, 1:ice, 2:grass NOT OFFICIAL
    public static CueStick cue;
    
    static public void updateVisual(){
        for(Ball ball: listOfBalls){
            ball.setCenterX(ball.getVectorPosition().getXcomponent());
            ball.setCenterY(ball.getVectorPosition().getYcomponent());
        }
    }
    
    static public void checkBallsCollisions(ScenePlay sc){


            for(int firstBallIndex = 0; firstBallIndex<GameStatus.listOfBalls.length-1; firstBallIndex++){
                for(int r=0; r< nets.length; r++) {

                    double netBorderMinX = nets[r].getCenterX() + listOfBalls[firstBallIndex].getRadius();
                    double netBorderMinY = nets[r].getCenterY() + listOfBalls[firstBallIndex].getRadius();

                    Vector netsPositionVector = new Vector(nets[r].getCenterX(),nets[r].getCenterY());
                    if(Vector.vectorDifference(GameStatus.listOfBalls[firstBallIndex].getVectorPosition(),netsPositionVector).getMagnitude() <= GameStatus.listOfBalls[firstBallIndex].getRadius()+nets[r].getRadius()){
                        //ScenePlay.gamePane
                        if(firstBallIndex!=0) {
                            //System.out.println("HAHA GET LOST" + firstBallIndex);

                            //sc.gamePane.getChildren().remove(GameStatus.listOfBalls[firstBallIndex]);
                        } else if(firstBallIndex==0){
                           /* GameStatus.listOfBalls[firstBallIndex].setCenterX(tableLines[0].getStartX());
                            GameStatus.listOfBalls[firstBallIndex].setCenterY(table.getY()+0.5*table.getHeight());
                            GameStatus.listOfBalls[firstBallIndex].setVectorPosition(new Vector(GameStatus.listOfBalls[firstBallIndex].getCenterX(), GameStatus.listOfBalls[firstBallIndex].getCenterY() ));
                            GameStatus.listOfBalls[firstBallIndex].setVi(new Vector(0,0));

                            */
                            System.out.println("lol");

                        }

                    }


                    /*
                    if ((listOfBalls[firstBallIndex].getCenterX() + listOfBalls[firstBallIndex].getRadius()) <= netBorderMinX||(listOfBalls[firstBallIndex].getCenterX() - listOfBalls[firstBallIndex].getRadius()) <= netBorderMinX) {
                        //listOfBalls[firstBallIndex].setFill(new Color(2,1,1,1));
                        System.out.println("We done got hit");
                    }if ((listOfBalls[firstBallIndex].getCenterY() + listOfBalls[firstBallIndex].getRadius()) <= netBorderMinY||(listOfBalls[firstBallIndex].getCenterY() - listOfBalls[firstBallIndex].getRadius()) <= netBorderMinY) {
                       System.out.println("We done got hit along the y");
                    }*/
                }

/*
                if(position.x<=netBorderMinX|| position.x>=netBorderMaxX){
                    this.getVi().setXcomponent(-1*this.getVi().getXcomponent());
                    System.out.println("lol");
                    if(position.x <=netBorderMinX)
                        position.x = netBorderMinX;
                    else
                        position.x = netBorderMaxX;
                    //xPosition=this.getCenterX() + this.getVi().getXcomponent();
                }
                if(position.y<=netBorderMinY|| position.y>=netBorderMaxY){
                    this.getVi().setYcomponent(-1*this.getVi().getYcomponent());
                    if(position.y <=netBorderMinY)
                        position.y = netBorderMinY;
                    else
                        position.y = netBorderMaxY;
                    //yPosition=this.getCenterY() + this.getVi().getYcomponent();
                }


                    //check if collide with nets
                   /* for (int r = 0; r < 6; r++) {
                        if ((nets[r].getCenterX() + nets[r].getRadius()) >= listOfBalls[firstBallIndex].getCenterX()|| (nets[r].getCenterX() - nets[r].getRadius()) >= listOfBalls[firstBallIndex].getCenterX()) {
                            System.out.println("LOL WE NETTED");

                        } if ((nets[r].getCenterY() + nets[r].getRadius()) >= listOfBalls[firstBallIndex].getCenterY()|| (nets[r].getCenterY() - nets[r].getRadius()) >= listOfBalls[firstBallIndex].getCenterY()) {

                            System.out.println("Lol we netted 2");
                        }
                    }

                    */


                
                for(int secondBallIndex=0; secondBallIndex<GameStatus.listOfBalls.length; secondBallIndex++){
                    //do balls collide
                    if(firstBallIndex!=secondBallIndex&&Vector.vectorDifference(GameStatus.listOfBalls[firstBallIndex].getVectorPosition(),GameStatus.listOfBalls[secondBallIndex].getVectorPosition()).getMagnitude() <= 2*GameStatus.listOfBalls[secondBallIndex].getRadius()){
                        System.out.println("collided check ball"+ firstBallIndex+" "+secondBallIndex);
                        Ball.reactIsHit(GameStatus.listOfBalls[firstBallIndex], GameStatus.listOfBalls[secondBallIndex]);
                    }
                }
            }
            
        
    }
    
    public static void positionObjects(double widthOfWindow, double heightOfWindow, double xPositionOfGamepane, double yPositionOfGamepane){
        //center table
        table.setX(-xPositionOfGamepane+(widthOfWindow-table.getWidth())*0.5);
        table.setY(-yPositionOfGamepane+heightOfWindow-table.getHeight()-125);
        
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
        
        //constant and calculations for position of balls and nets
        double heightOfBand = 30*table.getHeight()/352;
        double widthOfBand = 30*table.getWidth()/702;
        double extraBufferNets = 10;
        
        double DISTANCE_BETWEEN_BALLS = 1d;
        double DISTANCE_BETWEEN_CENTER_OF_BALLS = DISTANCE_BETWEEN_BALLS+listOfBalls[0].getRadius()*2; //distance between the center of the balls
        //center of losange
        double xCenter = tableLines[1].getStartX();
        double yCenter = table.getY()+0.5*table.getHeight();
        
        //set position of Nets and Balls
        for(int i = 0; i<listOfBalls.length;i++){
            if(i<nets.length){
                nets[i].setCenterX(table.getX()+widthOfBand+extraBufferNets+(i%3)*(table.getWidth()-2*widthOfBand-2*extraBufferNets)/2);
                nets[i].setCenterY((i<3)?table.getY()+heightOfBand+extraBufferNets:table.getY()+table.getHeight()-heightOfBand-extraBufferNets);
                
//                nets[i].setCenterX((i%3)*(table.getWidth()-nets[0].getRadius()*2.5)/2+table.getX()+nets[0].getRadius());
//                nets[i].setCenterY((i<3)?table.getY()+nets[0].getRadius():table.getY()+table.getHeight()-nets[0].getRadius());
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
            
            listOfBalls[i].setVectorPosition(new Vector(listOfBalls[i].getCenterX(),listOfBalls[i].getCenterY()));
        }
        setZorder();
    }
    
    public static void initialize(){
        
        Image team1Img = new Image("InteractiveObjectIMG/BallTeam1IMG.png");
        Image team2Img = new Image("InteractiveObjectIMG/BallTeam2IMG.png");
        double ballRadius = 0.054*Math.sqrt(table.getHeight()*table.getWidth()/3.2258);
        
        cue = new CueStick(148.6/254*table.getWidth());
        
        for(int i = 0; i<listOfBalls.length; i++){
            if(i<nets.length)
                nets[i] = new Net(i,2*ballRadius);
            
            if(i<tableLines.length)
                tableLines[i] = new Line();
            
            listOfBalls[i] = new Ball(i, ballRadius);
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
        table.getBackground().toFront();
        table.getBorder().toFront();
        
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
        cue.toFront();
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
