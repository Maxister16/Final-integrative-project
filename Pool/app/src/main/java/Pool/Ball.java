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
import java.awt.event.ActionListener;
import static java.lang.Math.*;

public class Ball extends Circle implements InteractiveObject {
    //SETTING CLASS FIELDS
    private int id;
    private int type;
    
    private Vector position = new Vector(0, 0);
    private Vector vi = new Vector(0, 0);
    private Vector a = new Vector(0, 0);
    
    public boolean isPocketed;

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
    
    //CHANGE THE POSITION OF THE BALL
    public void updatePosition () {

    Vector deltaPosition = Vector.vectorScalarProduct(16, vi);//by how much ball moves
    this.position = Vector.vectorSum(deltaPosition, this.position);//adding the vectors to get the final position

    //BORDER LIMITATIONS
    double tableBorderMinX = table.getX() + 30*table.getWidth()/702 + getRadius();
    double tableBorderMinY = table.getY()+ 30*table.getHeight()/352 + getRadius();
    double tableBorderMaxX = table.getX() +(table.getWidth()) -30*table.getWidth()/702 - getRadius();
    double tableBorderMaxY = table.getY() + (table.getHeight()) - 30*table.getHeight()/352 - getRadius();

    
    //checking if the ball collides with the table
        if(position.x<=tableBorderMinX|| position.x>=tableBorderMaxX){
            this.getVi().setXcomponent(-1*this.getVi().getXcomponent());
            if(position.x <=tableBorderMinX)
                position.x = tableBorderMinX;
            else
                position.x = tableBorderMaxX;
        }
        if(position.y<=tableBorderMinY|| position.y>=tableBorderMaxY){
            this.getVi().setYcomponent(-1*this.getVi().getYcomponent());
            if(position.y <=tableBorderMinY)
                position.y = tableBorderMinY;
            else
                position.y = tableBorderMaxY;
        }

        //SLOWING DOWN OF THE VELOCITY
        this.setVi(this.getVi().vectorScalarProduct(GameStatus.FRICTION_COEFFICIENT[GameStatus.gameState], vi));
    }

    //Calculation for when balls collide
    static public void reactIsHit(Ball firstBall, Ball secondBall){

        //get distance between centers of balls
        double dx=(firstBall.getCenterX()+firstBall.getRadius()) - (secondBall.getCenterX()-firstBall.getRadius());
        double dy=(firstBall.getCenterY()+firstBall.getRadius()) - (secondBall.getCenterY()-firstBall.getRadius());

        //get angle, cos and sin
        double angle = atan2(dx, dy);
        double cos = cos(angle);
        double sin = sin(angle);

        //turn the frame of reference
        double v1xRotated = cos * firstBall.getVi().getXcomponent() + sin * firstBall.getVi().getYcomponent();
        double v1yRotated = -sin * firstBall.getVi().getXcomponent() + cos * firstBall.getVi().getYcomponent();
        double v2xRotated = cos * secondBall.getVi().getXcomponent() + sin * secondBall.getVi().getYcomponent();
        double v2yRotated = -sin * secondBall.getVi().getXcomponent() + cos * secondBall.getVi().getYcomponent();

        //
        double tempVx = v1xRotated;
        v1xRotated = v2xRotated;
        v2xRotated = tempVx;

        //turn the frame of reference to normal
        double ball1deltaX = cos * v1xRotated - sin * v1yRotated;
        double ball1deltaY = sin * v1xRotated + cos * v1yRotated;
        double ball2deltaX = cos * v2xRotated - sin * v2yRotated;
        double ball2deltaY = sin * v2xRotated + cos * v2yRotated;

        //set speed vector of both balls
        firstBall.setVi(new Vector(ball1deltaX, ball1deltaY));
        System.out.println("Ball 1 speed: " + firstBall.getVi().getMagnitude());
        secondBall.setVi(new Vector(ball2deltaX, ball2deltaY));
        System.out.println("Ball 2 speed: " + secondBall.getVi().getMagnitude());

    }

    @Override
    public void setImage(Image newImage){
        this.setFill(new ImagePattern(newImage));
    }

    }