package com.brickbreaker;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class Wall{
    private final Rectangle leftWall;
    private final Rectangle topWall;
    private final Rectangle rightWall;
    private final Rectangle lowerBoundary;

    public Wall(Color fill) {
        leftWall = new Rectangle(0, 0, FixedValue.WALL_WIDTH, FixedValue.SCREEN_WIDTH);
        topWall = new Rectangle(0, 0, FixedValue.SCREEN_HEIGHT, FixedValue.WALL_WIDTH);
        rightWall = new Rectangle(FixedValue.SCREEN_WIDTH - FixedValue.WALL_WIDTH, 0, FixedValue.WALL_WIDTH, FixedValue.SCREEN_HEIGHT);
        lowerBoundary = new Rectangle(0, FixedValue.SCREEN_HEIGHT, FixedValue.SCREEN_WIDTH, 30);

        leftWall.setFill(fill);
        topWall.setFill(fill);
        rightWall.setFill(fill);
    }

    public List<Rectangle> getShapeList() {
        ArrayList<Rectangle> returnList = new ArrayList();
        returnList.add(leftWall);
        returnList.add(topWall);
        returnList.add(rightWall);
        returnList.add(lowerBoundary);
        return returnList;
    }
    public Rectangle getLeftRectangle() {
        return leftWall;
    }
    public Rectangle getTopRectangle() {
        return topWall;
    }
    public Rectangle getRightRectangle() {
        return rightWall;
    }
    public Rectangle getLowerBoundary() {
        return lowerBoundary;
    }
}
