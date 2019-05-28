package com.brickbreaker;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;


public class EndView {
    /*صفحه اخر بازی را در صورت پیروزی و شکست اجرا میکند*/
    private Scene endView;
    private Pane pane = new Pane();
    private long minute, second;
    String[] design = new String[5];
    Timeline button;
    int i = 0;
    public EndView(Object stage, Stage primaryStage) {
        Music.Menu();
        Pane pane = new Pane();

        /*دکمه را تعریف می کنیم و علاوه بر آن استایل آن را مشخص می کنیم */
        Button restart = new Button("RESTART");
        Button exit = new Button("EXIT");
        design[0]= " -fx-background-color:" + "#c3c4c4," + "linear-gradient(#d6d6d6 50%, white 100%)," + "radial-gradient(center 50% -40%, radius 200%, #e6e6e6 45%, rgba(230,230,230,0) 50%);" +
                "-fx-background-radius: 30;" + "-fx-background-insets: 0,1,1;" + "-fx-text-fill: black;" + "-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 3, 0.0 , 0 , 1 );";
        design[1] = "-fx-background-color:" + "linear-gradient(#f0ff35, #a9ff00)," + "radial-gradient(center 50% -40%, radius 200%, #b8ee36 45%, #80c800 50%);" +
                "-fx-background-radius: 6, 5;" + "-fx-background-insets: 0, 1;" + "-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.4) , 5, 0.0 , 0 , 1 );" + "-fx-text-fill: #395306;";
        design[2] = "-fx-background-color: linear-gradient(#ff5400, #be1d00);" + "-fx-background-radius: 30;" + "-fx-background-insets: 0;" + "-fx-text-fill: white;";

        design[3] = "-fx-background-color:" + "linear-gradient(#ffd65b, #e68400)," + "linear-gradient(#ffef84, #f2ba44)," + "linear-gradient(#ffea6a, #efaa22)," +
                "linear-gradient(#ffe657 0%, #f8c202 50%, #eea10b 100%)," + "linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9), rgba(255,255,255,0));" +
                "-fx-background-radius: 30;" + "-fx-background-insets: 0,1,2,3,0;" + "-fx-text-fill: #654b00;" + "-fx-font-weight: bold;" + "-fx-font-size: 14px;" + "-fx-padding: 10 20 10 20;";
        design[4] = "-fx-background-color:" + "#ecebe9," + "rgba(0,0,0,0.05)," + "linear-gradient(#dcca8a, #c7a740)," + "linear-gradient(#f9f2d6 0%, #f4e5bc 20%, #e6c75d 80%, #e2c045 100%),"
                + "linear-gradient(#f6ebbe, #e6c34d);" + "-fx-background-insets: 0,9 9 8 9,9,10,11;" + "-fx-background-radius: 50;" + "-fx-padding: 15 30 15 30;" +
                "-fx-font-family: Helvetica;" + "-fx-font-size: 18px;" + "-fx-text-fill: #311c09;" + "-fx-effect: innershadow( three-pass-box , rgba(0,0,0,0.1) , 2, 0.0 , 0 , 1)";
        restart.setStyle(design[2]);
        exit.setStyle(design[4]);

        second = gamePlay.Time() / 1000;
        minute =  second / 60;
        second = second - minute * 60;

        if (gamePlay.isGameOver) {
            Label score = new Label("Score: " + gamePlay.totalScore);
            score.setTextFill(Color.WHITESMOKE);
            Label time = new Label();
            if(minute < 10 && second < 10) {
                time.setText("Time: 0" + minute + " : 0" + second);
            }
            else {
                time.setText("Time: 0" + minute + " : " + second);
            }
            time.setTextFill(Color.WHITESMOKE);
            pane.getChildren().addAll(score, time);
            score.setStyle("-fx-font: 24 arial");
            time.setStyle("-fx-font: 18 arial");
            score.setLayoutX(200);
            score.setLayoutY(350);
            time.setLayoutX(200);
            time.setLayoutY(390);
            BackgroundImage myBI = new BackgroundImage(new Image("file:E:/Linux/src/com/gameOver.jpg"),
                    BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
            pane.setBackground(new Background(myBI));
            restart.setOnMouseClicked(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event) {
                    gamePlay.totalScore = 0;
                    gamePlay.currentSec = 0;
                    gamePlay.lives = FixedValue.STARTING_LIVES;
                    Music.stop();
                    gamePlay restart = new gamePlay(stage, primaryStage);
                }
            });
            exit.setOnMouseClicked(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event) {
                    System.exit(0);
                }
            });
            pane.getChildren().add(restart);
            pane.getChildren().add(exit);
        }
        if(gamePlay.isWin)
        {
            Label score = new Label("Score: " + gamePlay.totalScore);
            score.setTextFill(Color.WHITESMOKE);
            Label time = new Label("Time: " + minute + " : " + second);
            time.setTextFill(Color.WHITESMOKE);
            pane.getChildren().addAll(score, time);
            score.setStyle("-fx-font: 24 arial");
            time.setStyle("-fx-font: 18 arial");
            score.setLayoutX(200);
            score.setLayoutY(350);
            time.setLayoutX(200);
            time.setLayoutY(390);
            BackgroundImage myBI = new BackgroundImage(new Image("file:E:/Linux/src/com/youWin.jpg"),
                    BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
            pane.setBackground(new Background(myBI));
            restart.setOnMouseClicked(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event) {
                    gamePlay.totalScore = 0;
                    gamePlay.currentSec = 0;
                    gamePlay.lives = FixedValue.STARTING_LIVES;
                    gamePlay restart = new gamePlay(stage, primaryStage);
                }
            });
            exit.setOnMouseClicked(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event) {
                    System.exit(0);
                }
            });
            pane.getChildren().add(restart);
            pane.getChildren().add(exit);
        }

        restart.setMinWidth(200);
        exit.setMinWidth(200);
        restart.setLayoutX(150);
        restart.setLayoutY(80);
        exit.setLayoutX(150);
        exit.setLayoutY(130);

        endView = new Scene(pane, FixedValue.SCREEN_WIDTH, FixedValue.SCREEN_HEIGHT);

        primaryStage.setScene(endView);
        primaryStage.getIcons().add(new Image("file:/E:/Linux/src/com/icon.jpg"));
        primaryStage.setTitle("Result");
        primaryStage.setResizable(false);
        primaryStage.sizeToScene();
        primaryStage.show();
    }
}
