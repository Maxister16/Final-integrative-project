
package Pool;


public class GameStatus {
    public static boolean isGameOn;
    public static int team1Points;
    public static int team2Points;
    public static int gameState;
    public static long time;
    
    public static void updateTime() throws InterruptedException{
        while(GameStatus.isGameOn){
            Thread.sleep(100);
            time += 100;
        }
    
    }

}
