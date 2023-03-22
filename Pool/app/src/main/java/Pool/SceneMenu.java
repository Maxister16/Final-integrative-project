package Pool;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class SceneMenu{
    private Scene scene;

    public SceneMenu(Stage primaryStage) {

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);


        ImageView resume = new ImageView("ButtonIMG/ResumeBtnIMG.png");
        resume.setPreserveRatio(true);
        resume.setFitHeight(60);

        ImageView home = new ImageView("ButtonIMG/HomeBtnIMG.png");
        home.setPreserveRatio(true);
        home.setFitHeight(60);

        ImageView exit = new ImageView("ButtonIMG/ExitBtnIMG.png");
        exit.setPreserveRatio(true);
        exit.setFitHeight(60);

        ImageView physicsOn = new ImageView("ButtonIMG/PhysicsOnBtnIMG.png");
        physicsOn.setPreserveRatio(true);
        physicsOn.setFitHeight(120);

        ImageView physicsOff = new ImageView("ButtonIMG/PhysicsOffBtnIMG.png");
        physicsOff.setPreserveRatio(true);
        physicsOff.setFitHeight(120);


        Button resumeButton = new Button("",resume);
        Button homeButton = new Button("",home);
        Button exitButton = new Button("",exit);
        Button physicsButton = new Button("",physicsOn);


        homeButton.setBackground(null);
        homeButton.setBorder(null);
        resumeButton.setBackground(null);
        resumeButton.setBorder(null);
        exitButton.setBackground(null);
        exitButton.setBorder(null);
        physicsButton.setBackground(null);
        physicsButton.setBorder(null);
        gridPane.setHalignment(resumeButton, HPos.CENTER);
        gridPane.setHalignment(homeButton, HPos.CENTER);
        gridPane.setHalignment(exitButton, HPos.CENTER);
        gridPane.setHalignment(physicsButton, HPos.RIGHT);

        gridPane.add(resumeButton, 0, 0);
        gridPane.add(homeButton, 0, 1);
        gridPane.add(exitButton, 0, 2);
        gridPane.add(physicsButton, 0, 3);

        StackPane layout = new StackPane();
        //Set up scene background
        StackPane backgroundPane = new StackPane();
        //ImageViews
        ImageView bg = new ImageView("BackgroundIMG/MenuBgIMG.jpg");

        //Set background image
        bg.setPreserveRatio(true);
        bg.setFitWidth(1350);
        backgroundPane.getChildren().add(bg);


        layout.getChildren().addAll(backgroundPane, gridPane);
        this.scene = new Scene(layout);

        resumeButton.setOnAction((event) -> {
          //go back to paused play scene
            ScenePlay scenePlay = new ScenePlay(primaryStage);
            primaryStage.setScene(scenePlay.getScene());
        });

        homeButton.setOnAction((event) -> {
            SceneWelcome sceneWelcome = new SceneWelcome(primaryStage);
            primaryStage.setScene(sceneWelcome.getScene());
        });

        exitButton.setOnAction((event) -> {
            primaryStage.close();
        });
//        togglePhysicsButton(physicsButton);
    }

//    public void togglePhysicsButton(Button physicsButton) {
//        // Set the initial text of the button
//        physicsButton.setText("Physics on");
//
//        // Add an event handler to the button
//        physicsButton.setOnAction((event) -> {
//            // Check the current text of the button and toggle it
//            if (physicsButton.getText().equals("on")) {
//                physicsButton.setText("Physics off");
//            } else {
//                physicsButton.setText("Physics on");
//            }
//        });
//    }

    public Scene getScene() {
        return this.scene;
    }
}


