package com.brickbreaker;
import javafx.scene.shape.Rectangle;

public class Brick {
    public final Rectangle shape;
    public static double centerX;
    public static double centerY;
    public Brick(double x, double y, int i) {
        centerX = x + FixedValue.BRICKS_WIDTH / 2;
        centerY = y + FixedValue.BRICKS_HEIGHT / 2;
        shape = new Rectangle(x, y, FixedValue.BRICKS_WIDTH, FixedValue.BRICKS_HEIGHT);
        shape.setFill(FixedValue.BRICKS_COLOR[i % 5]);
    }

    public Rectangle getShape() {
        return shape;
    }
    public double getCenterX() {
        return centerX;
    }
    public double getCenterY() {
        return centerY;
    }
}
