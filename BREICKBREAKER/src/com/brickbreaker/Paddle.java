package com.brickbreaker;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.input.KeyCode;
import javafx.scene.Node;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Paddle {

    public static Rectangle shape;
    Timeline movement;

    DoubleProperty xTranslate = new SimpleDoubleProperty();
    int i;
    private boolean left = false;
    private boolean right = false;

    public Paddle() {
        xTranslate.set(0);
        shape = new Rectangle(FixedValue.PADDLE_STARTX, FixedValue.PADDLE_STARTY, FixedValue.PADDLE_WIDTH, FixedValue.PADDLE_HEIGHT);

        movement = new Timeline(
                new KeyFrame(new Duration(20.0), t -> {
                    shape.setFill(FixedValue.PADDLE_COLOR[i % 5]);
                    i++;
                    int displacement = 0;
                    displacement += left ? -FixedValue.PADDLE_SPEED : 0;
                    displacement += right ? FixedValue.PADDLE_SPEED : 0;
                    xTranslate.set(xTranslate.get() + displacement);
                    if (xTranslate.get() > (FixedValue.SCREEN_WIDTH - FixedValue.WALL_WIDTH - FixedValue.PADDLE_WIDTH) / 2) {
                        xTranslate.set((FixedValue.SCREEN_WIDTH - FixedValue.WALL_WIDTH - FixedValue.PADDLE_WIDTH) / 2);
                    } else if (xTranslate.get() < (FixedValue.SCREEN_WIDTH - FixedValue.WALL_WIDTH - FixedValue.PADDLE_WIDTH) / -2) {
                        xTranslate.set((FixedValue.SCREEN_WIDTH - FixedValue.WALL_WIDTH - FixedValue.PADDLE_WIDTH) / -2);
                    }
                }));
        movement.setCycleCount(Timeline.INDEFINITE);
        movement.playFromStart();

        shape.setFocusTraversable(true);
        shape.setOnKeyPressed(key -> {
            left = key.getCode() == KeyCode.LEFT;
            right = key.getCode() == KeyCode.RIGHT;
        });
        shape.setOnKeyReleased(key -> {
            if (key.getCode() == KeyCode.LEFT) {
                left = false;
            } else if (key.getCode() == KeyCode.RIGHT) {
                right = false;
            }
        });

        shape.translateXProperty().bind(xTranslate);
    }

    public boolean checkCollision(Node objToCheck) {
        return shape.intersects(objToCheck.getBoundsInLocal());
    }
    public Rectangle getShape() {
        return shape;
    }
    public double getCenterX() {
        return shape.getX() + xTranslate.get() + FixedValue.PADDLE_WIDTH / 2;
    }
    public double getCenterY() {
        return shape.getY() + FixedValue.PADDLE_HEIGHT / 2;
    }
}