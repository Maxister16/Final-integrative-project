package Pool;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ScenePlayNormal extends ScenePlay{
    private Scene scene;

    public ScenePlayNormal() {
        
        GameStatus.table.setImage(new Image("InteractiveObjectIMG/tableNormalIMG.png"));
        /*
        System.out.println("ScenePlayNormal - create new ScenePlay");
        ScenePlay scenePlay = new ScenePlay(primaryStage);//ERROR
        System.out.println("ScenePlayNormal - scene = ");
        scene = scenePlay.getScene();
        System.out.println("ScenePlayNormal - set scene");
        primaryStage.setScene(scenePlay.getScene());*/
    }
/*
    public Scene getScene() {
        return this.scene;
    }*/
}


