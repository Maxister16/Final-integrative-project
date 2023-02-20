
package Pool;

public class GameStatus {
    public static boolean isGameOn = false;
    public static int team1Points = 0;
    public static int team2Points = 0;
    public static int gameState = 0;
    public static long time = 0;
    //public static Ball[] listOfBalls;
    public static Net[] nets;
    public static Table table = new Table();
    
    public static void updateTime() throws InterruptedException{
        while(GameStatus.isGameOn){
            Thread.sleep(100);
            time += 100;
        }
    }
    
    public void initialBallsAndNets(){
        //set list of Balls[10]
        nets = new Net[6];
        for(int i = 0; i<nets.length;i++){
            nets[i] = new Net();
        }
    }
    
    public static void reset(){
        isGameOn = false;
        team1Points = 0;
        team2Points = 0;
        time = 0;
    }

}
