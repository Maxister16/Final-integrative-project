package Pool;

import static Pool.GameStatus.listOfBalls;
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
    private Vector position = new Vector(0, 0);
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
        }
    
    
        //CHANGE THE POSITION OF THE BALL
        public void updatePosition () {
     
        Vector deltaPosition = Vector.vectorScalarProduct(16, vi);
        // this.setVectorPosition()
        //Vector this.setVectorPosition(Vector.vectorSum(this.getVi(), Vector.vectorScalarProduct(E, ));
        this.position = Vector.vectorSum(deltaPosition, this.position);

        //BORDER LIMITATIONS
        double tableBorderMinX= table.getX() + 30*table.getWidth()/702 - getRadius();
        double tableBorderMinY= table.getY()+ 30*table.getHeight()/352 - getRadius();
        double tableBorderMaxX= table.getX() +(table.getWidth()) -30*table.getWidth()/702 - getRadius();
        double tableBorderMaxY= table.getY() + (table.getHeight()) - 30*table.getHeight()/352 - getRadius();

            if(position.x<=tableBorderMinX|| position.x>=tableBorderMaxX){
                this.getVi().setXcomponent(-1*this.getVi().getXcomponent());
                System.out.println("lol");
                if(position.x <=tableBorderMinX)
                    position.x = tableBorderMinX;
                else
                    position.x = tableBorderMaxX;
                //xPosition=this.getCenterX() + this.getVi().getXcomponent();
            }
            if(position.y<=tableBorderMinY|| position.y>=tableBorderMaxY){
                this.getVi().setYcomponent(-1*this.getVi().getYcomponent());
                if(position.y <=tableBorderMinY)
                    position.y = tableBorderMinY;
                else
                    position.y = tableBorderMaxY;
                //yPosition=this.getCenterY() + this.getVi().getYcomponent();
            }

            //this.relocate(xPosition, yPosition);
            //SLOWING DOWN OF THE VELOCITY
            this.setVi(this.getVi().vectorScalarProduct(GameStatus.FRICTION_COEFFICIENT[GameStatus.gameState], vi));
            //System.out.println("lol");
            //this.reactIsHit();


        /*
        double x=  vi.getXcomponent()*time + a.getXcomponent()*time*time*0.5 - position.getXcomponent();
        double y=  vi.getYcomponent()*time + a.getYcomponent()*time*time*0.5 - position.getYcomponent();
        Vector p=new Vector(x,y);
        this.setVectorPosition(p);

         */
        }

        
        static public void reactIsHit(Ball firstBall, Ball secondBall){
            
            double angle = atan2(firstBall.getVi().getMagnitude(), secondBall.getVi().getMagnitude());
            double cos = cos(angle);
            double sin = sin(angle);

            double v1xRotated = cos * firstBall.getVi().getXcomponent() + sin * firstBall.getVi().getYcomponent();
            double v1yRotated = -sin * firstBall.getVi().getXcomponent() + cos * firstBall.getVi().getYcomponent();
            double v2xRotated = cos * secondBall.getVi().getXcomponent() + sin * secondBall.getVi().getYcomponent();
            double v2yRotated = -sin * secondBall.getVi().getXcomponent() + cos * secondBall.getVi().getYcomponent();

            double tempVx = v1xRotated;
            v1xRotated = v2xRotated;
            v2xRotated = tempVx;

            double ball1deltaX = cos * v1xRotated - sin * v1yRotated;
            double ball1deltaY = sin * v1xRotated + cos * v1yRotated;
            double ball2deltaX = cos * v2xRotated - sin * v2yRotated;
            double ball2deltaY = sin * v2xRotated + cos * v2yRotated;

            firstBall.setVi(new Vector(ball1deltaX, ball1deltaY));
            System.out.println("Ball 1 speed: " + firstBall.getVi().getMagnitude());
            secondBall.setVi(new Vector(ball2deltaX, ball2deltaY));
            System.out.println("Ball 2 speed: " + secondBall.getVi().getMagnitude());
        
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
                    //if (Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2) <= 4* this.getRadius()*this.getRadius()) {
                      if(Vector.vectorDifference(this.getVectorPosition(),GameStatus.listOfBalls[i].getVectorPosition()).getMagnitude() <= 2*this.getRadius()){  
                        //this.updateMovement(GameStatus.listOfBalls[i]);
                        System.out.println("Haha collided");
                        GameStatus.listOfBalls[i].updateMovement(this);
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