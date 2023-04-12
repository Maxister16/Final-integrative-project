
package Pool;

import javafx.animation.FadeTransition;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class PaneMenu extends GridPane{
    
    public Button resumeButton ;
    public Button homeButton ;
    public Button exitButton ;
    public Button physicsButton;
    public FadeTransition fade = new FadeTransition();
    public GridPane gridPaneMenu = new GridPane();
    
    public PaneMenu(){
        
        this.setAlignment(Pos.CENTER);
                
        //Images
        ImageView resume = new ImageView("ButtonIMG/ResumeBtnIMG.png");
        resume.setPreserveRatio(true);
        resume.setFitHeight(60);
        ImageView home = new ImageView("ButtonIMG/HomeBtnIMG.png");
        home.setPreserveRatio(true);
        home.setFitHeight(60);
        ImageView exit = new ImageView("ButtonIMG/ExitBtnIMG.png");
        exit.setPreserveRatio(true);
        exit.setFitHeight(60);

        ImageView physicsLabel = new ImageView("ButtonIMG/PhysicsLableIMG.png");
        physicsLabel.setPreserveRatio(true);
        physicsLabel.setFitHeight(60);
        ImageView physicsOn = new ImageView("ButtonIMG/OnBtn.png");
        physicsOn.setPreserveRatio(true);
        physicsOn.setFitHeight(60);
        ImageView physicsOff = new ImageView("ButtonIMG/OffBtn.png");
        physicsOff.setPreserveRatio(true);
        physicsOff.setFitHeight(60);

        resumeButton = new Button("",resume);
        homeButton = new Button("",home);
        exitButton = new Button("",exit);
        physicsButton = new Button("",physicsOn);

        homeButton.setBackground(null);
        homeButton.setBorder(null);
        resumeButton.setBackground(null);
        resumeButton.setBorder(null);
        exitButton.setBackground(null);
        exitButton.setBorder(null);
        physicsButton.setBackground(null);

        //GridPaneMenu set up
        this.setHalignment(resumeButton, HPos.CENTER);
        this.setHalignment(homeButton, HPos.CENTER);
        this.setHalignment(exitButton, HPos.CENTER);
        this.setHalignment(physicsLabel, HPos.CENTER);
        this.setHalignment(physicsButton, HPos.CENTER);
        this.setTranslateY(30);

        this.add(resumeButton, 0, 0);
        this.add(homeButton, 0, 1);
        this.add(exitButton, 0, 2);
        this.add(physicsLabel, 0, 3);
        this.add(physicsButton, 0, 4);

        //GridPaneMenu constraints
        ColumnConstraints menuColumn = new ColumnConstraints(200);
        //columnTop1.setHalignment(HPos.CENTER);
        RowConstraints menuRow1 = new RowConstraints(65);
        menuRow1.setValignment(VPos.CENTER);
        RowConstraints menuRow2 = new RowConstraints(65);
        menuRow2.setValignment(VPos.CENTER);
        RowConstraints menuRow3 = new RowConstraints(65);
        menuRow3.setValignment(VPos.CENTER);
        RowConstraints menuRow4 = new RowConstraints(65);
        menuRow4.setValignment(VPos.CENTER);
        RowConstraints menuRow5 = new RowConstraints(65);
        menuRow5.setValignment(VPos.CENTER);
        this.getColumnConstraints().add(menuColumn);
        this.getRowConstraints().addAll(menuRow1,menuRow2,menuRow3,menuRow4,menuRow5);

        ImageView menuBg = new ImageView("BackgroundIMG/MenuBgIMG.png");
        
        menuBg.setPreserveRatio(true);
        menuBg.setFitWidth(500);
        menuBg.setTranslateX(-1350/2);
        Rectangle rectangle = new Rectangle(1350, 700);
        rectangle.setFill(Color.RED);
        rectangle.setTranslateX(-1350/2);
        fade.setNode(rectangle);
        fade.setFromValue(0.0);
        fade.setToValue(0.4);
        fade.setDuration(Duration.millis(100));
        
        this.getChildren().addAll(rectangle,menuBg);
    }
    
}
