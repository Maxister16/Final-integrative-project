
package Pool;

import javafx.scene.shape.Line;


public class Vector extends Line{
    
    private double x;
    private double y;
    
    //constructors
    public Vector(){
        this.x = 0;
        this.y = 0;
    }
    public Vector(double newX, double newY){
        this.x = newX;
        this.y = newY;
    }
    
    //mutator
    public void setXcomponent(double newX){
        this.x = newX;
    }
    public void setYcomponent(double newY){
        this.y = newY;
    }
    
    //accessor
    public double getXcomponent(){
        return x;
    }
    public double getYcomponent(){//
        return y;
    }
    public double getMagnitude(){
        return Math.sqrt(vectorDotProduct(this, this));
    }
    
    public double getAngle(){//in RAD
        
        if(x==0){//x cannot = 0 because y/x
            if(y==0)
                return 0;//no vector
            else if(y>0)//points toward +y
                return 0.5*Math.PI;//90
            else//points toward -y
                return 1.5*Math.PI;//270
        }
        else if(x>0 & y>0){//(+,+)
            return Math.atan(y/x);// ] 0 , 90 [
        }
        else if(x<0 & y>0){//(-,+)
            return Math.PI + Math.atan(y/x);//] 90 , 180 [
                // 180     + smallAngle
        }
        else if(x<0 & y<0){//(-,-)
            return Math.PI + Math.atan(y/x);//] 180 , 270 [
                // 180     - smallAngle
        }
        else if(x>0 & y<0){//(+,-)
            return 2*Math.PI + Math.atan(y/x);//] 270 , 360 [
                // 360       - smallAngle
        }
        else{//y==0
            if(x>0)//points toward +x
                return 0;
            else//points toward -x
                return Math.PI;//180
        }
    }
    
    //static arithmetic function with other vector
    public static Vector vectorSum(Vector v1, Vector v2){//v1+v2
        return new Vector(v1.getXcomponent()+v2.getXcomponent(),v1.getYcomponent()+v2.getYcomponent());
    }
    public static Vector vectorDifference(Vector v1, Vector v2){//v1-v2
        return new Vector(v1.getXcomponent()-v2.getXcomponent(),v1.getYcomponent()-v2.getYcomponent());
    }
    public static Vector vectorScalarProduct(double k, Vector v){//k*v1
        return new Vector(k*v.getXcomponent(),k*v.getYcomponent());
    }
    public static double vectorDotProduct(Vector v1, Vector v2){//v1 ? v2
        return v1.getXcomponent()*v2.getXcomponent()+v1.getYcomponent()*v2.getYcomponent();
    }
    
}
