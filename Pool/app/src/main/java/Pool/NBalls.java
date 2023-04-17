package Pool;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

import java.util.ArrayList;
import java.util.List;

public class NBalls{
    public List<Ball> balls = new ArrayList<>();
    final double INIT_SPEED_X = 0;
    final double INIT_SPEED_Y = 0;

    //The padding is used to prevent the ball from getting stuck on the border
    final double PADDING = 1.0;
    final double FRICTION_COEFFICIENT = 0.005;


    public NBalls(Pane pane) {

        int numBalls = 9;
        double ballRadius = 10;
        double clusterX = 800 - ballRadius * 15;
        double clusterY = 400/2;


        for (int i = 0; i <= numBalls; i++) {

            if (i ==0) {
                Circle circle = new Circle(ballRadius);
                //For whatever reason keep this >= 10
                circle.setLayoutX(10);
                circle.setLayoutY(400/2);
                pane.getChildren().add(circle);
                balls.add(new Ball(circle, INIT_SPEED_X, INIT_SPEED_Y, Color.RED));
            }
            Circle circle = new Circle(ballRadius);
            double x = clusterX + Math.random() * ballRadius * 2 - ballRadius;
            double y = clusterY + Math.random() * ballRadius * 2 - ballRadius;
            circle.setLayoutX(x);
            circle.setLayoutY(y);
            pane.getChildren().add(circle);
            balls.add(new Ball(circle, 0, 0));

        }


        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10), actionEvent -> {
            for (Ball ball : balls) {
                double speed = Math.sqrt(ball.deltaX * ball.deltaX + ball.deltaY * ball.deltaY);
                double speedFrictionFactor = (speed < 0.001) ? 1 : speed;
                ball.deltaX -= ball.deltaX * FRICTION_COEFFICIENT / speedFrictionFactor;
                ball.deltaY -= ball.deltaY * FRICTION_COEFFICIENT / speedFrictionFactor;

                ball.circle.setLayoutX(ball.circle.getLayoutX() + ball.deltaX);
                ball.circle.setLayoutY(ball.circle.getLayoutY() + ball.deltaY);

              //  Bounds bounds = pane.getBoundsInLocal();
                boolean rightBorder = ball.circle.getLayoutX() >= (800- ball.circle.getRadius() - PADDING);
                boolean leftBorder = ball.circle.getLayoutX() <= (0 - ball.circle.getRadius() + PADDING);
                boolean bottomBorder = ball.circle.getLayoutY() >= (400 - ball.circle.getRadius()) - PADDING;
                boolean topBorder = ball.circle.getLayoutY() <= (0 - ball.circle.getRadius() + PADDING);

                if (rightBorder || leftBorder) {
                    ball.deltaX *= -1;
                }
                if (bottomBorder || topBorder) {
                    ball.deltaY *= -1;
                }

                for (Ball otherBall : balls) {
                    if (ball != otherBall && ballsCollide(ball.circle, otherBall.circle)) {
                        handleBallCollision(ball, otherBall);
                    }
                }
            }
        }));

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public class Ball {
        Circle circle;
        double deltaX;
        double deltaY;

        public Ball(Circle circle, double deltaX, double deltaY) {
            this.circle = circle;
            this.deltaX = deltaX;
            this.deltaY = deltaY;
        }

        public Ball(Circle circle, double deltaX, double deltaY, Color color) {
            this.circle = circle;
            this.circle.setFill(color);
            this.deltaX = deltaX;
            this.deltaY = deltaY;
        }

        public void changeSpeed(double deltaX, double deltaY) {
            this.deltaX = deltaX;
            this.deltaY = deltaY;
        }
    }
    public static boolean ballsCollide(Circle ball1, Circle ball2) {
        double dx = ball1.getLayoutX() - ball2.getLayoutX();
        double dy = ball1.getLayoutY() - ball2.getLayoutY();
        double distance = Math.sqrt(dx * dx + dy * dy);
        double minDistance = ball1.getRadius() + ball2.getRadius();
        return distance < minDistance;
    }

    public static void handleBallCollision(Ball ball1, Ball ball2) {
        double dx = ball1.circle.getLayoutX() - ball2.circle.getLayoutX();
        double dy = ball1.circle.getLayoutY() - ball2.circle.getLayoutY();
        double distance = Math.sqrt(dx * dx + dy * dy);
        double overlap = ball1.circle.getRadius() + ball2.circle.getRadius() - distance;

        ball1.circle.setLayoutX(ball1.circle.getLayoutX() + (overlap * dx / distance));
        ball1.circle.setLayoutY(ball1.circle.getLayoutY() + (overlap * dy / distance));
        ball2.circle.setLayoutX(ball2.circle.getLayoutX() - (overlap * dx / distance));
        ball2.circle.setLayoutY(ball2.circle.getLayoutY() - (overlap * dy / distance));

        double angle = Math.atan2(dy, dx);
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);

        double v1xRotated = cos * ball1.deltaX + sin * ball1.deltaY;
        double v1yRotated = -sin * ball1.deltaX + cos * ball1.deltaY;
        double v2xRotated = cos * ball2.deltaX + sin * ball2.deltaY;
        double v2yRotated = -sin * ball2.deltaX + cos * ball2.deltaY;

        double tempVx = v1xRotated;
        v1xRotated = v2xRotated;
        v2xRotated = tempVx;

        ball1.deltaX = cos * v1xRotated - sin * v1yRotated;
        ball1.deltaY = sin * v1xRotated + cos * v1yRotated;
        ball2.deltaX = cos * v2xRotated - sin * v2yRotated;
        ball2.deltaY = sin * v2xRotated + cos * v2yRotated;
    }

}
