package Pool;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MenuScene {
    private Scene scene;

    public MenuScene(Stage primaryStage, Table table) {

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);

        Button resumeButton = new Button("Resume");
        Button homeButton = new Button("Home");
        Button exitButton = new Button("Exit");
        Button physicsButton = new Button();


        gridPane.add(resumeButton, 0, 0);
        gridPane.add(homeButton, 0, 1);
        gridPane.add(exitButton, 0, 2);
        gridPane.add(physicsButton, 0, 3);

        StackPane layout = new StackPane();
        layout.setMinWidth(900);
        layout.setMinHeight(600);


        layout.getChildren().add(gridPane);
        this.scene = new Scene(layout);

        resumeButton.setOnAction((event) -> {
          //go back to paused play scene
        });

        homeButton.setOnAction((event) -> {
            WelcomeScene welcomeScene = new WelcomeScene(primaryStage, table);
            primaryStage.setScene(welcomeScene.getScene());
        });

        exitButton.setOnAction((event) -> {
            primaryStage.close();
        });
        togglePhysicsButton(physicsButton);
    }

    public void togglePhysicsButton(Button physicsButton) {
        // Set the initial text of the button
        physicsButton.setText("Physics on");

        // Add an event handler to the button
        physicsButton.setOnAction((event) -> {
            // Check the current text of the button and toggle it
            if (physicsButton.getText().equals("on")) {
                physicsButton.setText("Physics off");
            } else {
                physicsButton.setText("on");
            }
        });
    }

    public Scene getScene() {
        return this.scene;
    }
}


