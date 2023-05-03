
package Pool;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class SceneWelcome {
    
    public Scene scene;
//button to gamemode
    private Button normalBtn;
    private Button grassBtn; 
    private Button iceBtn;
//button sound mute
    private Button welcomeSoundBtn;
    private ImageView welcomeSoundOn;
    private ImageView welcomeSoundOff;
    
    //accessor methods
    public Scene getScene () {
        return this.scene;
    }
    public Button getNormalBtn(){
        return normalBtn;
    }
    public Button getGrassBtn(){
        return grassBtn;
    }
    public Button getIceBtn(){
        return iceBtn;
    }
    public Button getWelcomeSoundBtn(){
        return welcomeSoundBtn;
    }
    public ImageView getWelcomeSoundOn(){
        return welcomeSoundOn;
    }
    public ImageView getWelcomeSoundOff(){
        return welcomeSoundOff;
    }
    
    
    public SceneWelcome() {

        //GridPane for buttons
        GridPane gridPane = new GridPane();
        GridPane soundGridPane = new GridPane();
        soundGridPane.setMaxSize(80,80);
        soundGridPane.setAlignment(Pos.CENTER);
        gridPane.setAlignment(Pos.CENTER);
        soundGridPane.setTranslateX(620);
        soundGridPane.setTranslateY(330);
        gridPane.setTranslateY(90);
        gridPane.setTranslateX(110);

        // Create ColumnConstraints objects to set the size of the columns and rows
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
        ImageView normalMode = new ImageView("ButtonIMG/NormalPlayBtnIMG.png");
        normalMode.setPreserveRatio(true);
        normalMode.setFitHeight(170);
        ImageView grassMode = new ImageView("ButtonIMG/GrassPlayBtnIMG.png");
        grassMode.setPreserveRatio(true);
        grassMode.setFitHeight(170);
        welcomeSoundOn = new ImageView("ButtonIMG/VolumeBtnOnIMG.png");
        welcomeSoundOn.setPreserveRatio(true);
        welcomeSoundOn.setFitHeight(60);
        welcomeSoundOff = new ImageView("ButtonIMG/VolumeBtnOffIMG.png");
        welcomeSoundOff.setPreserveRatio(true);
        welcomeSoundOff.setFitHeight(60);

        //Mode buttons with images
        normalBtn = new Button("", normalMode);
        grassBtn = new Button("", grassMode);
        iceBtn = new Button("", iceMode);
        welcomeSoundBtn =new Button("",welcomeSoundOn);

        //Set button node background and border to transparent
        normalBtn.setBackground(null);
        normalBtn.setBorder(null);
        grassBtn.setBackground(null);
        grassBtn.setBorder(null);
        iceBtn.setBackground(null);
        iceBtn.setBorder(null);
        welcomeSoundBtn.setBorder(null);
        welcomeSoundBtn.setBackground(null);

        //Add buttons to gridPane
        soundGridPane.add(welcomeSoundBtn,0,0);
        gridPane.add(normalBtn, 0, 0);
        gridPane.add(grassBtn, 2, 0);
        gridPane.add(iceBtn, 1, 0);

        //Set up scene background
        StackPane backgroundPane = new StackPane();
        ImageView bg = new ImageView("BackgroundIMG/WelcomeBgIMG.jpg");

        //Set background image
        bg.setPreserveRatio(true);
        bg.setFitWidth(1350);
        backgroundPane.getChildren().add(bg);

        //Set play button images
        StackPane layout = new StackPane();
        layout.getChildren().addAll(backgroundPane, gridPane, soundGridPane);
        /*
        Label nameLabel = new Label("Made by Maxime Sevigny, Marie Cogna Diagne & Eszter Anna Kovacs");
        nameLabel.setStyle("-fx-color:red;");
        nameLabel.setTranslateX(-1350/2+(nameLabel.getText().length())*nameLabel.getFont().getSize()/2+30);
        nameLabel.setTranslateY(350);
        
        nameLabel.setStyle(
                "-fx-font-size: 25px; -fx-text-fill: black; -fx-background-fill: black;");
        
        nameLabel.setBackground(new Background(
   new BackgroundFill(Color.color(1, 1, 1, 0.5d), CornerRadii.EMPTY, Insets.EMPTY)));*/
        
        Label nameLabel = new Label("Created by" + ":\nMaxime Sévigny\nEszter Anna Kovacs\nMarie Cogna Diagne");
        nameLabel.setTranslateX(-582);
        nameLabel.setTranslateY(315);

        nameLabel.setStyle("-fx-font-size: 18px; -fx-background-fill: null;");
        nameLabel.setTextFill(Color.WHITE);
        
        nameLabel.setPadding(new Insets(5));
        
        layout.getChildren().add(nameLabel);

        //create Welcome scene
        this.scene = new Scene(layout);

    }
}
