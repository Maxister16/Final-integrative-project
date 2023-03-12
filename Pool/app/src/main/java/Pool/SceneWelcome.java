package Pool;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class SceneWelcome {
    private Scene scene;

    public SceneWelcome(Stage primaryStage) {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);

        //Mode buttons
        Button normalBtn = new Button("Normal");
        Button grassBtn = new Button("Grass");
        Button iceBtn = new Button("Ice");

        //Add buttons to gridPane
        gridPane.add(normalBtn, 0, 0);
        gridPane.add(grassBtn, 2, 0);
        gridPane.add(iceBtn, 3, 0);

        StackPane backgroundPane = new StackPane();

        //ImageViews
        ImageView bg = new ImageView("BackgroundIMG/WelcomeBgIMG.jpg");
        ImageView iceMode = new ImageView("Pool/redPlay.png");

        //Set background image
        bg.setPreserveRatio(true);
        bg.setFitWidth(1350);
        backgroundPane.getChildren().add(bg);

        //Set play button images

        StackPane layout = new StackPane();
        layout.getChildren().addAll(backgroundPane,gridPane);

        this.scene = new Scene(layout);
        normalBtn.setOnAction((event) -> {
            ScenePlayNormal scenePlayNormal = new ScenePlayNormal(primaryStage);
            primaryStage.setScene(scenePlayNormal.getScene());
        });
        grassBtn.setOnAction((event) -> {
            ScenePlayGrass scenePlayGrass = new ScenePlayGrass(primaryStage);
            primaryStage.setScene(scenePlayGrass.getScene());
        });
        iceBtn.setOnAction((event) -> {
            ScenePlayIce scenePlayIce = new ScenePlayIce(primaryStage);
            primaryStage.setScene(scenePlayIce.getScene());
        });
    }

    public Scene getScene() {
        return this.scene;
    }
}
