
package Pool;

import javafx.fxml.FXML;

public class Pool_Controller {
    
    //fields
    double[] FRICTION_COEFFICIENT = {0.01,0.005,0.5}; //0:normal, 1:ice, 2:grass
    
    @FXML
    public void initialize(){
        //play music
        //set scene to titleScreen
    }
    
//SETUP FUNCTIONS
    //scene setup
    public void setupTitleScreen(){
        //change to titleScreen scene
    }
    public void setupMenu(){
        //GameStatus.isGameOn = false;
        //change scene to menuScene
    }
    public void handleBackToGame(){
        //GameStatus.isGameOn = true;
        //change scene to gameScene
    }
    
    public void setUpGame(){
        //change to gameScene
        //initialise listOfBalls[]
        
        //place objects in the place
        //start the game
    }
    public void setupNormal(){
        //Ball.friction = FRICTION_COEFFICIENT[0]
        setUpGame();
    }
    public void setupIce(){
        //Ball.friction = FRICTION_COEFFICIENT[1] 
        setUpGame();
    }
    public void setupGrass(){
        //Ball.friction = FRICTION_COEFFICIENT[2]
        setUpGame();
    }
    
    
    
}
