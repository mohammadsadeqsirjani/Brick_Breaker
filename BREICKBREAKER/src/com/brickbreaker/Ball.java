package com.brickbreaker;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.util.Random;

public class Ball {
    public static Random random = new Random();
    private final Circle shape;

    private DoubleProperty centerX = new SimpleDoubleProperty();
    private DoubleProperty centerY = new SimpleDoubleProperty();
    private double speedX = Math.sqrt(2) / 2;
    private double speedY = Math.sqrt(2) / 2;
    private boolean movingRight = random.nextBoolean();
    private boolean movingDown = random.nextBoolean();
    private double speed = FixedValue.BALL_START_SPEED;
    private int cnt = 0;

    private Timeline ballAnimation;
    private final Timeline waitPeriod;
    private boolean hasUpdated;

    public Ball(double startX, double startY) {

        centerX.set(startX);
        centerY.set(startY);

        waitPeriod = new Timeline(
                new KeyFrame(new Duration(2000.0), i -> {
                    ballAnimation = new Timeline(
                            new KeyFrame(new Duration(20.0), t -> {
                                centerX.set(centerX.get() + speedX * speed * (movingRight ? 1 : -1));
                                centerY.set(centerY.get() + speedY * speed * (movingDown ? 1 : -1));
                                hasUpdated = true;
                            })
                    );
                    ballAnimation.setCycleCount(Timeline.INDEFINITE);
                    ballAnimation.playFromStart();
                }));
        waitPeriod.playFromStart();

        shape = new Circle(centerX.get(), centerY.get(), 5, FixedValue.BALL_COLOR);

      shape.centerXProperty().bind(centerX);
      shape.centerYProperty().bind(centerY);

    }

    public void collideWithBrick(Brick brick) {
        cnt = 0;

        double brickWidth = brick.getShape().getWidth();
        double brickHeight = brick.getShape().getHeight();

        double brickOriginX = brick.getCenterX() - brickWidth / 2;
        double brickOriginY = brick.getCenterY() - brickHeight / 2;

        boolean collideWithLeft = centerX.get() >= brickOriginX;
        boolean collideWithRight = centerX.get() <= brickOriginX + brickWidth;
        boolean collideWithBottom = centerY.get() <= brickOriginY + brickHeight;
        boolean collideWithTop = centerY.get() >= brickOriginY;

        System.out.println(String.format("<: %s >: %s \\/: %s /\\: %s", collideWithLeft, collideWithRight, collideWithBottom, collideWithTop));

        if (collideWithRight && collideWithBottom && collideWithTop && !collideWithLeft) {
//            System.out.println("khord rast");
            movingRight = true;
            return;
        }
        if (collideWithLeft && collideWithBottom && collideWithTop && !collideWithRight) {
//            System.out.println("khord chapesh");
            movingRight = false;
            return;
        }

        if ( collideWithTop && collideWithRight && collideWithLeft && !collideWithBottom) {
//            System.out.println("khord bela");
            movingDown = false;
            return;
        }
        if (collideWithBottom && collideWithRight && collideWithLeft && !collideWithTop) {
//            System.out.println("khord peyin");
            movingDown = true;
            return;
        }

        changeYDirection();
        changeXDirection();

//        double x1 = brick.getCenterX();
//        double x2 = centerX.get();
//        double y1 = brick.getCenterY();
//        double y2 = centerY.get();
////      double ballAngle = Math.toDegrees(Math.atan(Math.abs((y2 - y1) / (x2 - x1))));
//        if (ballAngle < FixedValue.BRICK_DIAGONAL_ANGLE) {
//            changeYDirection();
//        } else {
//            changeXDirection();
//        }

        /*if ((ballAngle > FixedValue .BRICK_DIAGONAL_ANGLE && ballAngle < 180 - FixedValue.BRICK_DIAGONAL_ANGLE) ||
                (ballAngle > -180 + FixedValue.BRICK_DIAGONAL_ANGLE && ballAngle < -1 * FixedValue.BRICK_DIAGONAL_ANGLE)) { changeYDirection(); }
        else { changeXDirection(); }*/
    }

    public void collideWithPaddle(Paddle paddle) {
        double x1 = paddle.getCenterX();
        double x2 = centerX.get();
        double y1 = paddle.getCenterY();
        double y2 = centerY.get();
        double ballAngle = (Math.toDegrees(Math.atan(Math.abs((y1 - y2) / (x2 - x1)))));
        ballAngle /= 90;
        ballAngle *= 90 - FixedValue.BALL_MIN_BOUNCE_ANGLE;
        ballAngle += FixedValue.BALL_MIN_BOUNCE_ANGLE;
        movingRight = x2 >= x1;
        speedX = Math.cos(Math.toRadians(ballAngle));
        speedY = Math.sin(Math.toRadians(ballAngle));
    }

    public boolean hasUpdated() {
        return hasUpdated;
    }

    public void changeUpdate() {
        hasUpdated = !hasUpdated;
    }

    //    public void increaseSpeed() { speed += FixedValue.BALL_INCREASE_SPEED_INCREMENT; }
    public void changeXDirection() {
        movingRight = !movingRight;
    }

    public void changeYDirection() {
        movingDown = !movingDown;
    }

    public boolean isMovingRight() {
        return movingRight;
    }

    public boolean isMovingDown() {
        return movingDown;
    }

    public Circle getShape() {
        return shape;
    }
}