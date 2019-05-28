package com.brickbreaker;
import com.sun.xml.internal.bind.WhiteSpaceProcessor;
import javafx.scene.paint.Color;

import static javafx.scene.paint.Color.*;

public class FixedValue {
    public static final double SCREEN_WIDTH = 500;
    public static final double SCREEN_HEIGHT = 500;
    public static final Color SCREEN_COLOR = Color.ALICEBLUE;
    public static final double SCORE_HEIGHT = 50;

    public static final double WALL_WIDTH = 1;
    public static final Color WALL_COLOR = Color.ALICEBLUE;

    public static final int STARTING_LIVES = 2;

    public static final double BRICKS_WIDTH = 50;
    public static final double BRICKS_HEIGHT = 25;
    public static Color[] BRICKS_COLOR = {LIGHTBLUE, WHITE, GREENYELLOW, YELLOW, RED};
    //public static Color BONUS_COLOR = LIGHTBLUE;
    public static final int BRICKS_PER_ROW = 9;
    public static final int BRICKS_PER_COLUMN = 5;
    public static final double BRICKS_VERTICAL_SPACING = 5;
    public static final double BRICKS_HORIZONTAL_SPACING = 5;
    public static final double BRICK_DIAGONAL_ANGLE = Math.toDegrees(Math.atan(BRICKS_HEIGHT / BRICKS_WIDTH));

    public static final double PADDLE_WIDTH = 75;
    public static final double PADDLE_HEIGHT = 10;
    public static final double PADDLE_SPEED = 6;
    public static final Color[] PADDLE_COLOR = {DARKRED,RED , DARKORANGE, ORANGE, YELLOW, LIGHTYELLOW};
    public static final double PADDLE_STARTX = (SCREEN_WIDTH - PADDLE_WIDTH) / 2;
    public static final double PADDLE_STARTY = SCREEN_HEIGHT - PADDLE_HEIGHT;

    public static final int BALL_SIZE = 5;
    public static Color BALL_COLOR = Color.GOLD;
    public static final double BALL_STARTX = (SCREEN_WIDTH - BALL_SIZE) / 2;
    public static final double BALL_STARTY = (PADDLE_STARTY + WALL_WIDTH + BRICKS_VERTICAL_SPACING + (BRICKS_HEIGHT + BRICKS_VERTICAL_SPACING) * BRICKS_PER_COLUMN) / 2 + 100;
    public static double BALL_START_SPEED = 5;
    public static double BALL_INCREASE_SPEED_INCREMENT = 0;
    public static final double BALL_MIN_BOUNCE_ANGLE = 30;
}