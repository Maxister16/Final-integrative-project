package Pool;

import java.util.ArrayList;

import javafx.animation.Interpolator;
import javafx.scene.shape.Circle;

import java.lang.Math;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

import static java.lang.Math.*;
//import static javafx.animation.PathTransition.interpolate;

public class Ball extends Circle implements InteractiveObject{
//SETTING CLASS FIELDS
public double dx = 1, dy = 1;
    public static double friction;
    private static double gravity=9.806;
    private int id;
    private int type;
    private Vector position= new Vector(this.getCenterX(),this.getCenterY());
    private Vector vi=new Vector(0,0);;
    private Vector a=new Vector(0,0);;
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
//GET POSITION
    public Vector getVectorPosition(){
        return this.position;
    }
//GET VI VECTOR
    public Vector getVi(){
        return this.vi;
    }
//GET A VECTOR
    public Vector getA(){
        return this.a;
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
//SET POSITION
    public void setVectorPosition(Vector p){
        p.setXcomponent(this.getCenterX());
        p.setYcomponent(this.getCenterY());
        this.position=p;
    }
//SET VI VECTOR
    public void setVi(Vector vi){
        this.vi=vi;
    }
//SET A VECTOR
    public void setA(Vector a){
        this.a=a;
    }
    public void updateMovement(Ball x){

        //ALL SHOULD BE VECTORS (FIND ABSOLUTE VALUE)
        double xn=this.getCenterX()-x.getCenterX();
        double yn=this.getCenterY()-x.getCenterY();
        //CREATING NORMAL VECTOR
        Vector norm=new Vector(xn, yn);
        //CREATING RELATIVE VECTOR
        double xv=this.getVi().getXcomponent()-x.getVi().getXcomponent();
        double yv=this.getVi().getYcomponent()-x.getVi().getYcomponent();
        Vector relVel=new Vector(xv, yv);
        //MAKING THE SEPERATING VELOCITY VECTOR
        double sepVel= -1* (relVel.getXcomponent()*norm.getXcomponent() + relVel.getYcomponent()*norm.getYcomponent());
        double spx= sepVel*norm.getXcomponent();
        double spy=sepVel*norm.getYcomponent();
        Vector sepVelVec=new Vector(spx, spy);

        //ADDING SEPERAATING VELOCITY VECTOR TO ORIGINAL VECTORS
        this.getVi().setXcomponent(this.getVi().getXcomponent()+ sepVelVec.getXcomponent());
        this.getVi().setYcomponent(this.getVi().getYcomponent()+ sepVelVec.getYcomponent());


        x.getVi().setXcomponent(x.getVi().getXcomponent()- sepVelVec.getXcomponent());
        x.getVi().setYcomponent(x.getVi().getYcomponent()- sepVelVec.getYcomponent());

        /*
        //only x changes
        double x1=this.getCenterX();
        double x2=x.getCenterX();
        //STAYS THE SAME
        double y1=this.getCenterY();
        //STAYS THE SAME
        double y2= x.getCenterY();

        double relativeV= this.getVi().getMagnitude()-x.getVi().getMagnitude();
        double NormalVector= Math.sqrt( Math.pow( (x1-x2), 2) + Math.pow( (y1-y2), 2) );
        //FIGURE OUT CALCULATIONS LATER
        double UnitNormalV=0;
        Vector VectorFormNormal=new Vector();
        double angleCollision=acos(NormalVector/relativeV);
        //ANGLE WILL BE FOUND USING A VECTOR FROM VELOCITY VECTOR 1 TO VELOCITY VECTOR 2,  THEN THE NORMALVECTOR AND finding arccos
        //SEPARATING VELOCITY IS A SCALAR, BUT CAN BECOME A VECTOR
        double separatingV= relativeV*cos(angleCollision);
        double newSepV=-separatingV;
        Vector vectorSep=new Vector(newSepV*VectorFormNormal.getXcomponent(), newSepV*VectorFormNormal.getYcomponent());

        //CHANGE VELOCITIES
        this.setVi(new Vector(this.getVi().getXcomponent()+vectorSep.getXcomponent(), this.getVi().getYcomponent()+vectorSep.getYcomponent()));
        x.setVi(new Vector(x.getVi().getXcomponent()+vectorSep.getXcomponent(), x.getVi().getYcomponent()+vectorSep.getYcomponent()));

        //UPDATE POSITION
        this.updatePosition(GameStatus.time);

        /*
       // System.out.println("not");
        //DEFINING NECESSARY VARIABLES
        //BOTH OF THESE REFER TO THE X COMPONENT OF THE MAGNITUDE
        double xVelocityMag=0;
        double viVelocityMag = 0;

        //ROTATE THE SYSTEM OF THE BALLS

        //FIND ANGLE OF INITIAL COLLISION
        double vX= this.getCenterX()-x.getCenterX();
        double vY=this.getCenterY()-x.getCenterY();
        double angleCollision= atan(vY/vX);
        double ac=angleCollision;

        //MATRIX MULTIPLICATION

        //X COMPONENTS OF VELOCITY
        double thisVelocity=  this.getVi().getXcomponent()*(cos(ac))+ this.getVi().getYcomponent()*(sin(ac));
        double xBallsVelocity= x.getVi().getXcomponent()*(cos(ac))+ x.getVi().getYcomponent()*(sin(ac));
        //Y COMPONENTS OF VELOCITY (WHICH DO NOT CHANGE)
        double thisY= this.getVi().getYcomponent()*(cos(ac))+ this.getVi().getXcomponent()*(-sin(ac));
        double xBallsY= x.getVi().getXcomponent()*(-sin(ac))+ x.getVi().getYcomponent()*(cos(ac));


        //DEFINING NECESSARY VALUES FOR EQUATION

        //double velocitySum=this.getVi().getMagnitude() + x.getVi().getMagnitude();
        double velocitySum=thisVelocity + xBallsVelocity;
        double q=velocitySum;

        double velocitySumSquared=Math.pow(this.getVi().getMagnitude(), 2) + Math.pow(x.getVi().getMagnitude(), 2);
        double z=velocitySumSquared;

        double r=(q*q)-z;

        //CALCULATE VI
        double velocityFound1= (2*q + Math.sqrt((4*q*q)+ (8*r)))/40;
        double velocityFound2= (2*q - Math.sqrt((4*q*q)+ (8*r)))/40;


        //IF FIRST BALL IS WITH HIGHER INITIAL VELOCITY (knowing that ball with higher velocity loses velocity in collision)
        if(this.getVi().getMagnitude()<x.getVi().getMagnitude()){
        //if(thisVelocity<xBallsVelocity){
            if(velocityFound1<x.getVi().getMagnitude()){
            //if(velocityFound1<xBallsVelocity){
                xVelocityMag=velocityFound1;
                viVelocityMag=velocityFound2;
            }else{
                xVelocityMag=velocityFound2;
                viVelocityMag=velocityFound1;
            }
//NOW IF THE OTHER BALL IS THE ONE WITH HIGHER VELOCITY
        } else if(this.getVi().getMagnitude()>x.getVi().getMagnitude()){
        //else if(thisVelocity>xBallsVelocity){
            if(velocityFound1<this.getVi().getMagnitude()){
            //if(velocityFound1<thisVelocity){
                xVelocityMag=velocityFound2;
                viVelocityMag=velocityFound1;
            }else{
                xVelocityMag=velocityFound1;
                viVelocityMag=velocityFound2;
            }
        }
        System.out.println(xVelocityMag);
        System.out.println(viVelocityMag);

        //ROTATE THE SYSTEM BACK ALONG ANGLE OF ROTATION
        this.getVi().setYcomponent(cos(ac)*thisY+ sin(ac)*viVelocityMag);
        this.getVi().setXcomponent(-sin(ac)*thisY+ cos(ac)*viVelocityMag);

        x.getVi().setXcomponent(xVelocityMag*cos(ac)+ xBallsY*-sin(ac));
        x.getVi().setYcomponent(xBallsY*sin(ac)+ xVelocityMag*cos(ac));

         */

        this.updatePosition(GameStatus.time);



    }
    //CHANGE THE POSITION OF THE BALL
    public void updatePosition(long time){
        double x=  vi.getXcomponent()*time + a.getXcomponent()*time*time*0.5 - position.getXcomponent();
        double y=  vi.getYcomponent()*time + a.getYcomponent()*time*time*0.5 - position.getYcomponent();


        //USE INTERPOLATION TO MOVE BALLS
        double r= Interpolator.LINEAR.interpolate(this.getCenterX(), x, 0.05);
        this.setCenterX(r);
        double d= Interpolator.LINEAR.interpolate(this.getCenterY(), x, 0.05);
        this.setCenterY(d);
        Vector p=new Vector(x,y);
        this.setVectorPosition(p);
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
                this.updatePosition(GameStatus.time);
            }
           
        }
    }

    @Override
    public void setImage(Image newImage) {
        this.setFill(new ImagePattern(newImage));
    }
}
