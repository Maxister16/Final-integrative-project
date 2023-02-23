package Pool;

import java.util.ArrayList;
import javafx.scene.shape.Circle;

import java.lang.Math;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
public class Ball extends Circle implements InteractiveObject{
//SETTING CLASS FIELDS
    public static double friction;
    private static double gravity=9.806;
    private int id;
    private int type;
    private double xi;
    private double yi;
    private double vi;
    private double vix;
    private double viy;
    private double a;
    private double ax;
    private double ay;
    private boolean isMoving;
    private long initialTime;
//BALL CONSTRUCTOR
    public Ball(int id){
        this.id=id;
        this.setRadius(10);
    }
    //ACCESSOR METHODS
   
//GET ID METHOD
    public double getID(){
        return this.id;
    }
//GET TYPE
    public double getType(){
        return this.type;
    }
//GET X DOUBLE
    public double getXi(){
        return this.xi;
    }
//GET Y DOUBLE
    public double getYi(){
        return this.yi;
    }
//GET VI DOUBLE
    public double getVi(){
        return this.vi;
    }
//GET VIX DOUBLE
    public double getVix(){
        return this.vix;
    }
//GET VIY DOUBLE
    public double getViy(){
        return this.viy;
    }
//GET A DOUBLE
    public double getA(){
        return this.a;
    }
//GET AY DOUBLE
    public double getAy(){
        return this.ay;
    }
//GET AX DOUBLE
    public double getAx(){
        return this.ax;
    }
//GET IS MOVING DOUBLE
    public boolean getIsMoving(){
        return isMoving;
    }
   
    //SETTER METHODS
//SET ID METHOD
    public void setID(int id){
        this.id=id;
    }
//SET TYPE
    public void setType(int type){
        this.type=type;
    }
//SET X DOUBLE
    public void setXi(double xi){
        this.xi=xi;
    }
//SET Y DOUBLE
    public void setYi(double yi){
        this.yi=yi;
    }
//SET VI DOUBLE
    public void setVi(double vi){
        this.vi=vi;
    }
//SET VIX DOUBLE
    public void setVix(double vix){
        this.vix=vix;
    }
//SET A DOUBLE
    public void setA(double a){
        this.a=a;
    }
//SET AY DOUBLE
    public void setAy(double ay){
        this.ay=ay;
    }
//SET AX DOUBLE
    public void setAx(double ax){
        this.ax=ax;
    }
    public void updateMovement(Ball x){
        //update the movement using the principles of collision
        //DEFINING NECESSARY VALUES FOR EQUATION
        double velocitySum=this.getVix() + x.getVix();
        double q=velocitySum;
       
        double velocitySumSquared=Math.pow(this.getVix(), 2) + Math.pow(x.getVix(), 2);
        double z=velocitySumSquared;
       
        double r=(q*q)-z;
       
        //CALCULATE VIX
        double velocityFound= (2*q + Math.sqrt((4*q*q)+ (8*r)))/4;
        //if(velocityFound<=this.)

    }
   
    public void reactIsHit(){
        //check if the ball touches another
        //IN ANOTHER METHOD IN MAIN CLASS, IS THE FIRST FOR LOOP WHERE
        for(int i=0; i<GameStatus.listOfBalls.length; i++){
        //SET VALUES FOR X1,Y1 AND X2,Y2
            double x1=this.getCenterX();
            double x2=GameStatus.listOfBalls[i].getCenterX();
            double y1=this.getCenterY();
            double y2= GameStatus.listOfBalls[i].getCenterY();
        //CHECK IF BALLS ARE COLLIDING
            if(Math.sqrt( Math.pow( (x1-x2), 2) + Math.pow( (y1-y2), 2) ) <= 2*this.getRadius()){
                this.updateMovement(GameStatus.listOfBalls[i]);
                //listOfBalls.get(i).updateMovement(this);  
            }
            else{
            }
           
        }
    }

    @Override
    public void setImage(Image newImage) {
        this.setFill(new ImagePattern(newImage));
    }
}
