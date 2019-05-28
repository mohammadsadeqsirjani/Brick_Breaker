package com.brickbreaker;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;


public class gamePlay {
    Scene scene;

    private Ball mainBall;
    private Wall mainWall;
    private List<List<Brick>> brickList = new ArrayList();
    private Paddle mainPaddle;
    private Pane mainPane;
    private Object primaryStage;
    private Stage stage;
    private IntegerProperty score = new SimpleIntegerProperty(0);
    public static boolean isGameOver = false, isWin = false, bonus = false;

    Timeline mainTimeline;
    Text txtScore;
    public static int k, lives = FixedValue.STARTING_LIVES, totalScore, damagedBrick;
    public static long passedSec, currentSec;
    public Random random = new Random();
    List<Circle> livesIconList = new ArrayList();
    Timer myTimer = new Timer();
    Label[] damage = new Label[FixedValue.BRICKS_PER_ROW * FixedValue.BRICKS_PER_COLUMN];
    int[] damageBrick = new int[FixedValue.BRICKS_PER_COLUMN * FixedValue.BRICKS_PER_ROW];
    int q = 1;

    public gamePlay(Object stage, Stage primaryStage)  {
        /*متغییری به نام mainGroup  تعریف می کینم که عملکرد آن به مانند pane  می باشد و تنها قصد اضافه کردن عوامل  بازی استفاده می شود*/
        Music.stop();
        String design= " -fx-background-color:" + "#c3c4c4," + "linear-gradient(#d6d6d6 50%, white 100%)," + "radial-gradient(center 50% -40%, radius 200%, #e6e6e6 45%, rgba(230,230,230,0) 50%);" +
                "-fx-background-radius: 30;" + "-fx-background-insets: 0,1,1;" + "-fx-text-fill: black;" + "-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 3, 0.0 , 0 , 1 );";
        Button resume = new Button("RESUME");
        resume.setStyle(design);
        Button stop = new Button("STOP");
        stop.setStyle(design);

        mainPane = new Pane();
         /*حاشیه بازی نواری قهوه ای  رنگ را شامل می شود در این قسمت برای ساختن این حاشیه تلاش می شود
         این قسمت از 4 مستطیل تشکیل شده است که برای اضافه کردن آن به صفحه اصلی بازی صدا زده می شوند*/
        mainWall = new Wall(FixedValue.WALL_COLOR);
        for (Rectangle wall : mainWall.getShapeList()) {
            mainPane.getChildren().add(wall);
        }
        /*مستطیل های آجر بازی را در این قسمت می سازیم
         * تنها استفاده از کلاس brick ان است که مستطیل ها را انجا صدا زده مختصات مورد نطر به انها داده می شود و
         *  در کلاس  newGamePlay به ارایه ای هز جنس list  اضافه می شودو ان اریه به صفحه اصلی بازی اضافه می شود*/
        for (int i = 0; i < FixedValue.BRICKS_PER_ROW; i++) {
            brickList.add(new ArrayList());
            for (int j = 0; j < FixedValue.BRICKS_PER_COLUMN; j++) {
                double xOffset = FixedValue.WALL_WIDTH + FixedValue.BRICKS_HORIZONTAL_SPACING + i * (FixedValue.BRICKS_WIDTH + FixedValue.BRICKS_HORIZONTAL_SPACING);
                double yOffset = FixedValue.WALL_WIDTH + FixedValue.BRICKS_VERTICAL_SPACING + j * (FixedValue.BRICKS_HEIGHT + FixedValue.BRICKS_VERTICAL_SPACING);
                Brick brickToAdd = new Brick(xOffset, yOffset, j + i);
                damage[5 * i + j] = new Label();
                damage[5 * i + j].setTextFill(Color.DARKBLUE);
                damageBrick[5 * i + j] = random.nextInt(5) + 1;
                damage[5 * i + j].setText("" + damageBrick[5 * i + j]);
                damage[5 * i + j].setLayoutX(Brick.centerX - 5);
                damage[5 * i + j].setLayoutY(Brick.centerY - 10);
                brickList.get(i).add(brickToAdd);
                mainPane.getChildren().addAll(brickToAdd.getShape(),damage[5 * i + j]);
            }
        }
        /*سازنده کلاس paddle را صدا می زنیم و بعد object های ساخته شده در سازنده را به صفحه اصلی اضافه می کند*/
        mainPaddle = new Paddle();
        mainPane.getChildren().add(mainPaddle.getShape());
        /*سازنهده مربوط به کلاس توپ را صدا می زنیم تا شی مزبوط به توپ را بسازد و ان را به صفحه اصلی بازی اضافه کند*/
        mainBall = new Ball(FixedValue.BALL_STARTX, FixedValue.BALL_STARTY);
        mainPane.getChildren().add(mainBall.getShape());

        mainPane.setFocusTraversable(true);
        mainPane.requestFocus();
        /*این قسمت برای تعیین جهت حرکت توپ پس از برخورد با اجر ها و paddle و حاشیه بازی است*/
        currentSec = System.currentTimeMillis();
        mainTimeline = new Timeline(new KeyFrame(new Duration(10.0), t -> {
            // برخورد با paddle
            if (mainBall.getShape().intersects(mainPaddle.getShape().getBoundsInParent()) && mainBall.isMovingDown()) {
                mainBall.collideWithPaddle(mainPaddle);
                mainBall.changeYDirection();
            }
            //حاشیه بازی برخورد با چپ
            if (mainBall.getShape().intersects(mainWall.getLeftRectangle().getBoundsInLocal()) && !mainBall.isMovingRight()) {
                mainBall.changeXDirection();
            }
            //حاشیه بازی برخورد با راست
            if (mainBall.getShape().intersects(mainWall.getRightRectangle().getBoundsInLocal()) && mainBall.isMovingRight()) {
                mainBall.changeXDirection();
            }
            //حاشیه بازی برخورد با بالا
            if (mainBall.getShape().intersects(mainWall.getTopRectangle().getBoundsInLocal()) && !mainBall.isMovingDown()) {
                mainBall.changeYDirection();
            }
            //حاشیه بازی  برخورد با پایین
            if (mainBall.getShape().intersects(mainWall.getLowerBoundary().getBoundsInLocal())) {
                decreaseLife(primaryStage);
            }
            //
            if (mainBall.hasUpdated()) {
                crash();
                mainBall.changeUpdate();
            }
        }));
        mainTimeline.setCycleCount(Timeline.INDEFINITE);
        mainTimeline.playFromStart();

        /*امتیاز بازی را نشان می دهد*/
        txtScore = new Text(10, -20, "Score");
        txtScore.textProperty().bind(Bindings.format("Score: %1$d", score));
        txtScore.setFill(Color.BLACK);
        txtScore.setStyle("-fx-font: 22 arial;");
        mainPane.getChildren().add(txtScore);
        mainPane.setLayoutY(FixedValue.SCORE_HEIGHT);

        txtScore = new Text(320, -20, "Ball");
        txtScore.textProperty().bind(Bindings.format("Ball: "));
        txtScore.setFill(Color.BLACK);
        txtScore.setStyle("-fx-font: 22 arial;");
        mainPane.getChildren().add(txtScore);
        mainPane.setLayoutY(FixedValue.SCORE_HEIGHT);
        
        /*توپ های اضافی را به صفحه بازی اضافه می کند*/
        for (int i = 0; i < lives; i++) {
            Circle newLife = new Circle(FixedValue.SCREEN_WIDTH - 30 * i - 90, -25, FixedValue.BALL_SIZE, FixedValue.BALL_COLOR);
            mainPane.getChildren().add(newLife);
            livesIconList.add(newLife);
        }

        BackgroundImage myBI = new BackgroundImage(new Image("file:E:/Linux/src/com/back.png"),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        mainPane.setBackground(new Background(myBI));
        scene = new Scene(mainPane, FixedValue.SCREEN_WIDTH, FixedValue.SCREEN_HEIGHT + FixedValue.SCORE_HEIGHT);
        scene.setFill(FixedValue.SCREEN_COLOR);

        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image("file:/E:/Linux/src/com/icon.jpg"));
        primaryStage.setTitle("BrickBreaker");
        primaryStage.setResizable(false);
        primaryStage.sizeToScene();
        primaryStage.show();
    }
    /*در این قسمت برای برخورد ها و حذف شدن اجر ها کسب امتیاز و ... تدابیری اندیشیده می شود
     * خیلی از متود های به کار رفته در این در ملاس توپ تعریف می شود*/
    public void crash() {
        for (int i = 0; i < brickList.size(); i++) {
            for (int j = 0; j < brickList.get(i).size(); j++) {
                Brick brick = brickList.get(i).get(j);
                if (mainBall.getShape().intersects(brick.getShape().getBoundsInLocal())) {
//                    System.out.println("i: " + i + " && j: " + j);
                    if (damageBrick[5 * i + j] == 1) {
                        mainBall.collideWithBrick(brick);
                        Music.Hit();
                        brickList.get(i).remove(brick);
                        mainPane.getChildren().removeAll(brick.getShape(), damage[5 * i + j]);
                        if (bonus) {
                            Bonus.Gift(k % 3);
                            k++;
                            bonus = false;
                        }
                        if (Bonus.isGift_1) {
                            score.set(score.get() + 10);
                            totalScore += 10;
                        } else {
                            score.set(score.get() + 5);
                            totalScore += 5;
                        }
                        damagedBrick++;
                        if (damagedBrick == (FixedValue.BRICKS_PER_COLUMN) * (FixedValue.BRICKS_PER_ROW)) {
                            isWin = true;
                            EndView winView = new EndView(stage, (Stage) primaryStage);
                        }
                        return;
                    } else if(damageBrick[5 * i + j] != 1) {
                        mainBall.collideWithBrick(brick);
                        Music.hit();
                        damageBrick[5 * i + j]--;
                        if (Bonus.isGift_1) {
                            score.set(score.get() + 2);
                            totalScore += 2;
                        } else {
                            score.set(score.get() + 1);
                            totalScore += 1;
                        }
                        brick.shape.setFill(FixedValue.BRICKS_COLOR[q % 5]);
                        q++;
                        damage[5 * i + j].setText("" + damageBrick[5 * i + j]);
                        if(damageBrick[5 * i + j] == 1){
                            brick.shape.setFill(Color.HOTPINK);
                            damage[5 * i + j].setText("P");
                            bonus = true;
                        }
                    }
                }
            }
        }
    }

    /*کم شدن توپ ها از بازی*/
    public void decreaseLife(Object primaryStage) {
        lives -= 1;
        if (lives < 0) {
            passedSec = System.currentTimeMillis();
            isGameOver = true;
            if (isGameOver) {
                EndView gameoverView = new EndView(stage, (Stage) primaryStage);
                mainTimeline.stop();
            }
        }
        if(!isGameOver && !isWin) {
            mainPane.getChildren().remove(livesIconList.get(lives));
            mainPane.getChildren().remove(mainBall.getShape());
            livesIconList.remove(lives);
            mainBall = new Ball(FixedValue.BALL_STARTX, FixedValue.BALL_STARTY);
            mainPane.getChildren().add(mainBall.getShape());
        }
    }

    public static long Time(){
        long time;
        time = passedSec - currentSec;
        return  time;
    }
   /* *//*در صورت پیروزی اجرا میشود*//*
    public void youWin (Object primaryStage) {
        isWin = true;
        if(isWin == true){
            EndView winView = new EndView(stage, (Stage) primaryStage);
        }
    }*/
}


