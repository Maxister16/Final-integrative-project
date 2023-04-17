package Pool;

import java.util.ArrayList;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.scene.shape.Circle;

import java.lang.Math;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Polyline;
import javafx.util.Duration;

import static Pool.GameStatus.table;
import static Pool.GameStatus.time;
import static java.lang.Math.*;

public class Ball extends Circle implements InteractiveObject {
    //SETTING CLASS FIELDS
    public static double friction;
    private static double gravity = 9.806;

    public Animation mainAnimation;
    private int id;
    private int type;

    private double decceleration=0.2;
    private Vector position = new Vector(this.getCenterX(), this.getCenterY());
    ;
    private Vector vi = new Vector(0, 0);
    private Vector a = new Vector(0, 0);
    ;
    private boolean isMoving;
    private long initialTime;

    //BALL CONSTRUCTOR
    public Ball(int id) {
        this.id = id;
        this.setRadius(10);
    }

    public Ball(int id, double radius) {
        super(radius);
        this.id = id;
    }
    //ACCESSOR METHODS

    //GET ID METHOD
    public double getID() {
        return this.id;
    }

    //GET TYPE
    public double getType() {
        return this.type;
    }

    //GET POSITION
    public Vector getVectorPosition() {
        return this.position;
    }

    //GET VI VECTOR
    public Vector getVi() {
        return this.vi;
    }

    //GET A VECTOR
    public Vector getA() {
        return this.a;
    }


    //GET IS MOVING DOUBLE
    public boolean getIsMoving() {
        return isMoving;
    }

    //SETTER METHODS
//SET ID METHOD
    public void setID(int id) {
        this.id = id;
    }

    //SET TYPE
    public void setType(int type) {
        this.type = type;
    }

    //SET POSITION
    public void setVectorPosition(Vector p) {
        //p.setXcomponent(this.getCenterX());
        //p.setYcomponent(this.getCenterY());
        this.position = p;
    }

    //SET VI VECTOR
    public void setVi(Vector vi) {
        this.vi = vi;
    }

    //SET A VECTOR
    public void setA(Vector a) {
        this.a = a;
    }

    public void updateMovement(Ball x) {

            //ALL SHOULD BE VECTORS (FIND ABSOLUTE VALUE)
            double NormalVectorX = java.lang.Math.abs(this.getCenterX() - x.getCenterX());
            double NormalVectorY = java.lang.Math.abs(this.getCenterY() - x.getCenterY());
            //CREATING NORMAL VECTOR
            Vector norm = new Vector(NormalVectorX, NormalVectorY);
            //CREATING RELATIVE VECTOR
            double relativeVectorX = this.getVi().getXcomponent() - x.getVi().getXcomponent();
            double relativeVectorY = this.getVi().getYcomponent() - x.getVi().getYcomponent();
            Vector relVel = new Vector(relativeVectorX, relativeVectorY);
            //MAKING THE SEPERATING VELOCITY VECTOR
            double sepVel = -1 * (relVel.getXcomponent() * norm.getXcomponent() + relVel.getYcomponent() * norm.getYcomponent());
            double spx = sepVel * norm.getXcomponent();
            double spy = sepVel * norm.getYcomponent();
            Vector sepVelVec = new Vector(spx, spy);

            //ADDING SEPERATING VELOCITY VECTOR TO ORIGINAL VECTORS
            this.getVi().setXcomponent(this.getVi().getXcomponent() + sepVelVec.getXcomponent());
            this.getVi().setYcomponent(this.getVi().getYcomponent() + sepVelVec.getYcomponent());
            //System.out.println("this ball speed is "+this.getVi().getMagnitude());

            x.getVi().setXcomponent(x.getVi().getXcomponent() - sepVelVec.getXcomponent());
            x.getVi().setYcomponent(x.getVi().getYcomponent() - sepVelVec.getYcomponent());
            // System.out.println("x ball speed is "+x.getVi().getMagnitude());
            this.updatePosition();
            x.updatePosition();
        /*
        //DEFINING NECESSARY VARIABLES
        //BOTH OF THESE REFER TO THE X COMPONENT OF THE MAGNITUDE
        double xVelocityMag=0;
        double viVelocityMag = 0;

        //ROTATE THE SYSTEM OF THE BALLS

        //FIND ANGLE OF INITIAL COLLISION
        double vX= java.lang.Math.abs(this.getCenterX()-x.getCenterX());
        double vY=java.lang.Math.abs(this.getCenterY()-x.getCenterY());
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
        double velocityFound1= (2*q + Math.sqrt((4*q*q)+ (8*r)))/4;
        double velocityFound2= (2*q - Math.sqrt((4*q*q)+ (8*r)))/4;


        //IF FIRST BALL IS WITH HIGHER INITIAL VELOCITY (knowing that ball with higher velocity loses velocity in collision)
        //if(this.getVi().getMagnitude()<x.getVi().getMagnitude()){
        if(thisVelocity<xBallsVelocity){
            //if(velocityFound1<x.getVi().getMagnitude()){
            if(velocityFound1<xBallsVelocity){
                xVelocityMag=velocityFound1;
                viVelocityMag=velocityFound2;
            }else{
                xVelocityMag=velocityFound2;
                viVelocityMag=velocityFound1;
            }
//NOW IF THE OTHER BALL IS THE ONE WITH HIGHER VELOCITY
        } //else if(this.getVi().getMagnitude()>x.getVi().getMagnitude()){
        else if(thisVelocity>xBallsVelocity){
            //if(velocityFound1<this.getVi().getMagnitude()){
            if(velocityFound1<thisVelocity){
                xVelocityMag=velocityFound2;
                viVelocityMag=velocityFound1;
            }else{
                xVelocityMag=velocityFound1;
                viVelocityMag=velocityFound2;
            }
        }

        //ROTATE THE SYSTEM BACK ALONG ANGLE OF ROTATION
        this.getVi().setYcomponent(cos(ac)*thisY+ sin(ac)*viVelocityMag);
        this.getVi().setXcomponent(-sin(ac)*thisY+ cos(ac)*viVelocityMag);

        x.getVi().setXcomponent(xVelocityMag*cos(ac)+ xBallsY*-sin(ac));
        x.getVi().setYcomponent(xBallsY*sin(ac)+ xVelocityMag*cos(ac));


*/
        }
        //CHANGE THE POSITION OF THE BALL
        public void updatePosition () {
            Vector deltaPosition=Vector.vectorScalarProduct(0.0167, vi);
            // this.setVectorPosition()
            //Vector this.setVectorPosition(Vector.vectorSum(this.getVi(), Vector.vectorScalarProduct(E, ));
            this.position = Vector.vectorSum(deltaPosition, this.position);




            this.setCenterX(position.getXcomponent()+this.getCenterX());
            this.setCenterY(position.getYcomponent()+this.getCenterY());
            this.setVi(this.getVi().vectorScalarProduct(GameStatus.FRICTION_COEFFICIENT[GameStatus.gameState], this.getVi()));

        /*double xPosition=this.getCenterX() + this.getVi().getXcomponent();
        double yPosition=this.getCenterY() + this.getVi().getYcomponent();
        //this.position=new Vector(xPosition, yPosition);


        //BORDER LIMITATIONS
        double tableBorderMinX= table.getX() + 30*table.getWidth()/702 ;
        double tableBorderMinY= table.getY()+ 30*table.getHeight()/352;
        double tableBorderMaxX= table.getX() +(table.getWidth()) -30*table.getWidth()/702;
        double tableBorderMaxY= table.getY() + (table.getHeight()) - 30*table.getHeight()/352;

            if(this.getCenterX()<=tableBorderMinX|| this.getCenterX()>=tableBorderMaxX){
                this.getVi().setXcomponent(-1*this.getVi().getXcomponent());
                System.out.println("lol");
                 xPosition=this.getCenterX() + this.getVi().getXcomponent();
            }
            if(this.getCenterY()<=tableBorderMinY|| this.getCenterY()>=tableBorderMaxY){
                this.getVi().setYcomponent(-1*this.getVi().getYcomponent());

                 yPosition=this.getCenterY() + this.getVi().getYcomponent();
            }
            this.position=new Vector(xPosition, yPosition);
            this.setCenterX(xPosition);
            this.setCenterY(yPosition);

            //this.relocate(xPosition, yPosition);
            //SLOWING DOWN OF THE VELOCITY
            this.setVi(this.getVi().vectorScalarProduct(GameStatus.FRICTION_COEFFICIENT[GameStatus.gameState], this.getVi()));
            //System.out.println("lol");
            //this.reactIsHit();


        /*
        double x=  vi.getXcomponent()*time + a.getXcomponent()*time*time*0.5 - position.getXcomponent();
        double y=  vi.getYcomponent()*time + a.getYcomponent()*time*time*0.5 - position.getYcomponent();
        Vector p=new Vector(x,y);
        this.setVectorPosition(p);

         */
        }

        public void reactIsHit() {
            //check if the ball touches another
            //IN ANOTHER METHOD IN MAIN CLASS, IS THE FIRST FOR LOOP WHERE
            for (int i = 0; i < GameStatus.listOfBalls.length; i++) {
                //SET VALUES FOR X1,Y1 AND X2,Y2
                if(this.id!=i) {
                    double x1 = this.getCenterX();
                    double x2 = GameStatus.listOfBalls[i].getCenterX();
                    double y1 = this.getCenterY();
                    double y2 = GameStatus.listOfBalls[i].getCenterY();
                    //CHECK IF BALLS ARE COLLIDING
                    if (Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2)) <= 2 * this.getRadius()) {
                        //this.updateMovement(GameStatus.listOfBalls[i]);
                        System.out.println("Haha collided");
                        //listOfBalls.get(i).updateMovement(this);
                    } else {
                    }
                }

            }
        }

        @Override
        public void setImage(Image newImage){
            this.setFill(new ImagePattern(newImage));
        }
    }

