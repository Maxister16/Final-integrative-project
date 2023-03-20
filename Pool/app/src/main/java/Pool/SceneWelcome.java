package Pool;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


//To do: make volume on off button, add sound effects and music

public class SceneWelcome {
    private Scene scene;

    public SceneWelcome(Stage primaryStage) {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setTranslateY(90);
        gridPane.setTranslateX(110);

        // Create ColumnConstraints objects to set the size of the columns
        ColumnConstraints column1 = new ColumnConstraints(200);
        column1.setHalignment(HPos.CENTER);
        ColumnConstraints column2 = new ColumnConstraints(200);
        column2.setHalignment(HPos.CENTER);
        ColumnConstraints column3 = new ColumnConstraints(200);
        column3.setHalignment(HPos.CENTER);
        RowConstraints row1 = new RowConstraints(200);
        row1.setValignment(VPos.CENTER);


        // Set the sizes of the columns and rows
        gridPane.getColumnConstraints().addAll(column1, column2, column3);
        gridPane.getRowConstraints().addAll(row1);


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
        gridPane.add(grassBtn, 1, 0);
        gridPane.add(iceBtn, 2, 0);

        //Set up scene background
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
        normalBtn.setOnMouseEntered((event) -> {
            normalMode.setFitHeight(185);
        });
        normalBtn.setOnMouseExited((event) -> {
            normalMode.setFitHeight(170);
        });
        grassBtn.setOnAction((event) -> {
            ScenePlayGrass scenePlayGrass = new ScenePlayGrass(primaryStage);
            primaryStage.setScene(scenePlayGrass.getScene());
        });
        grassBtn.setOnMouseEntered((event) -> {
            grassMode.setFitHeight(185);
        });
        grassBtn.setOnMouseExited((event) -> {
            grassMode.setFitHeight(170);
        });
        iceBtn.setOnAction((event) -> {
            ScenePlayIce scenePlayIce = new ScenePlayIce(primaryStage);
            primaryStage.setScene(scenePlayIce.getScene());
        });
        iceBtn.setOnMouseEntered((event) -> {
            iceMode.setFitHeight(185);
        });
        iceBtn.setOnMouseExited((event) -> {
            iceMode.setFitHeight(170);
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
