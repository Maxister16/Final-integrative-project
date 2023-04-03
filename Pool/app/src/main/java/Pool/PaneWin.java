
package Pool;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class PaneWin extends Pane{
    public PaneWin(){
        ImageView orangeWin = new ImageView("BackgroundIMG/star0.png");
        orangeWin.setPreserveRatio(true);
        orangeWin.setFitHeight(760);
        ImageView redWin = new ImageView("BackgroundIMG/redStar.png");
        redWin.setPreserveRatio(true);
        redWin.setFitHeight(760);
        ImageView apple = new ImageView("ButtonIMG/redReplay.png");
        apple.setPreserveRatio(true);
        apple.setFitHeight(145);
        ImageView orange = new ImageView("ButtonIMG/orangeReplay.png");
        orange.setPreserveRatio(true);
        orange.setFitHeight(145);

        Button redReplay = new Button("", apple);
        Button orangeReplay = new Button("", orange);

        redReplay.setBackground(null);
        orangeReplay.setBackground(null);
        redReplay.setBorder(null);
        orangeReplay.setBorder(null);
        redReplay.setTranslateX(384);
        redReplay.setTranslateY(310);
        orangeReplay.setTranslateX(391);
        orangeReplay.setTranslateY(327);

        this.setTranslateX(180);
        this.setTranslateY(0);
        Rectangle winBlur = new Rectangle(0,0, 1350, 780);
        //(will add fade transition later)
        winBlur.setFill(Color.WHITE);
        winBlur.setOpacity(0.5);
        winBlur.setTranslateX(-180);
    }
}
