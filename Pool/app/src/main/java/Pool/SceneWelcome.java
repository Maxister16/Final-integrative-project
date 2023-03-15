package Pool;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javax.swing.*;

public class SceneWelcome {
    private Scene scene;

    public SceneWelcome(Stage primaryStage) {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        //Set up Button ImageViews
        ImageView iceMode = new ImageView("ButtonIMG/IcePlayBtnIMG.png");
        iceMode.setPreserveRatio(true);
        iceMode.setFitHeight(170);
        ImageView normalMode= new ImageView("ButtonIMG/NormalPlayBtnIMG.png");
        normalMode.setPreserveRatio(true);
        normalMode.setFitHeight(170);
        ImageView grassMode = new ImageView("ButtonIMG/GrassPlayBtnIMG.png");
        grassMode.setPreserveRatio(true);
        grassMode.setFitHeight(170);

        gridPane.translateYProperty().bind(iceMode.fitHeightProperty().divide(2.15));
        gridPane.translateXProperty().bind(iceMode.fitHeightProperty().divide(1.6));


        //Mode buttons with images
        Button normalBtn = new Button("",normalMode);
        Button grassBtn = new Button("",grassMode);
        Button iceBtn = new Button("",iceMode);

        //Set button node background and border to transparent
        normalBtn.setBackground(null);
        normalBtn.setBorder(null);
        grassBtn.setBackground(null);
        grassBtn.setBorder(null);
        iceBtn.setBackground(null);
        iceBtn.setBorder(null);

        //Add buttons to gridPane
        gridPane.add(normalBtn, 0, 0);
        gridPane.add(grassBtn, 2, 0);
        gridPane.add(iceBtn, 3, 0);

        StackPane backgroundPane = new StackPane();

        //ImageViews
        ImageView bg = new ImageView("BackgroundIMG/WelcomeBgIMG.jpg");


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

        //Styling found on google
        //final String IDLE_BUTTON_STYLE = "-fx-background-color: transparent;";
        //final String HOVERED_BUTTON_STYLE = "-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color;";
        //
        //
        //button.setStyle(IDLE_BUTTON_STYLE);
        //button.setOnMouseEntered(e -> button.setStyle(HOVERED_BUTTON_STYLE));
        //button.setOnMouseExited(e -> button.setStyle(IDLE_BUTTON_STYLE));
    }

    public Scene getScene() {
        return this.scene;
    }
}
