package com.brickbreaker;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import javax.xml.soap.Text;
import java.io.File;


public class Main extends Application {

    static Scene scene;
    private Object Stage;
   // private Music mainMusic;
    private MediaPlayer mediaPlayer;
    private Pane pane = new Pane();
    private Pane pane1 = new Pane();
    private  Pane pane2 = new Pane();
    String[] design = new String[5];
    int i = 0;
    Timeline button;
    @Override
    public void start(javafx.stage.Stage primaryStage) {

        /*در این قسمت  سازنده کلاس موسیقی برای  منو اصلی را صدا می رنیم*/
        Music.Menu();
        design[0]= " -fx-background-color:" + "#c3c4c4," + "linear-gradient(#d6d6d6 50%, white 100%)," + "radial-gradient(center 50% -40%, radius 200%, #e6e6e6 45%, rgba(230,230,230,0) 50%);" +
                "-fx-background-radius: 30;" + "-fx-background-insets: 0,1,1;" + "-fx-text-fill: black;" + "-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 3, 0.0 , 0 , 1 );";
        design[1] = " -fx-background-color: " + "#a6b5c9," + "linear-gradient(#303842 0%, #3e5577 20%, #375074 100%)," +
                "linear-gradient(#768aa5 0%, #849cbb 5%, #5877a2 50%, #486a9a 51%, #4a6c9b 100%);" + "-fx-background-insets: 0 0 -1 0,0,1;" + "-fx-background-radius: 5,5,4;" +
                "-fx-padding: 7 30 7 30;" + "-fx-text-fill: #242d35;" + "-fx-font-family: Helvetica;" + "-fx-font-size: 12px;" + "-fx-text-fill: white;";
        design[2] = "-fx-background-color: linear-gradient(#ff5400, #be1d00);" + "-fx-background-radius: 30;" + "-fx-background-insets: 0;" + "-fx-text-fill: white;";

        design[3] = "-fx-background-color:" + "linear-gradient(#ffd65b, #e68400)," + "linear-gradient(#ffef84, #f2ba44)," + "linear-gradient(#ffea6a, #efaa22)," +
                "linear-gradient(#ffe657 0%, #f8c202 50%, #eea10b 100%)," + "linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9), rgba(255,255,255,0));" +
                "-fx-background-radius: 30;" + "-fx-background-insets: 0,1,2,3,0;" + "-fx-text-fill: #654b00;" + "-fx-font-weight: bold;" + "-fx-font-size: 14px;" + "-fx-padding: 10 20 10 20;";
        design[4] = "-fx-background-color:" + "#ecebe9," + "rgba(0,0,0,0.05)," + "linear-gradient(#dcca8a, #c7a740)," + "linear-gradient(#f9f2d6 0%, #f4e5bc 20%, #e6c75d 80%, #e2c045 100%),"
                + "linear-gradient(#f6ebbe, #e6c34d);" + "-fx-background-insets: 0,9 9 8 9,9,10,11;" + "-fx-background-radius: 50;" + "-fx-padding: 15 30 15 30;" +
                "-fx-font-family: Helvetica;" + "-fx-font-size: 18px;" + "-fx-text-fill: #311c09;" + "-fx-effect: innershadow( three-pass-box , rgba(0,0,0,0.1) , 2, 0.0 , 0 , 1)";
        /*دکمه های منو را تعریف می کنیم و علاوه بر آن استایل آن را مشخص می کنیم */
        Button start = new Button("NEW GAME");
        start.setMinWidth(200);
        Button exit = new Button("QUIT");
        exit.setMinWidth(200);
        Button option = new Button("OPTION");
        option.setMinWidth(200);
        Button easy = new Button("EASY");
        easy.setMinWidth(200);
        Button medium = new Button("MEDIUM");
        medium.setMinWidth(200);
        Button hard = new Button("HARD");
        hard.setMinWidth(200);
        Button about = new Button("ABOUT");
        about.setMinWidth(200);
        Button watch = new Button("WATCH");
        watch.setMinWidth(200);
        Button back = new Button("BACK");
        back.setMinWidth(50);
        back.setStyle(design[0]);
        start.setStyle(design[0]);
        exit.setStyle(design[1]);
        option.setStyle(design[2]);
        easy.setStyle(design[3]);
        medium.setStyle(design[4]);
        hard.setStyle(design[0]);
        about.setStyle(design[4]);
        watch.setStyle(design[3]);


        /* در منوی شروع گطینه option را برای تعیین سختی بازی در نظر می گیریم*/
        start.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                gamePlay easyOption = new gamePlay(Stage, primaryStage);
            }
        });
        /*در این قسمت گزینه خروج از بازی در نظر گرفته می شود*/
        exit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                System.exit(0);
            }
        });
        about.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                Music.stop();
                String text = "Destroy all the bricks by smashing them." + "\n" + "The more you get at once, the higher the score.\n" +
                        "The only way to remove a brick is to zap it with a ball." + "\n" +
                        "The paddle may move horizontally and" + "\n" + "is controlled with the keyboard," + "\n"  +
                        "The player gets 3 lives to start with;" +
                        " a life is lost if the ball hits" + "\n" + "the bottom of the screen." + "\n" +"When all the bricks have been destroyed," + "\n" +
                        "the player win If all lives are lost, the game is over" + "\n" + "Ifyou want to watch how to play this video";

                Label aboutL = new Label(text);
                aboutL.setStyle("-fx-text-fill: black;" + "-fx-font-weight: bold;" + "-fx-font-size: 16;");
                aboutL.setLayoutX(50);
                aboutL.setLayoutY(100);
                pane1.getChildren().add(aboutL);
                watch.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent event) {
                        final File file = new File("E:/Linux/src/com/as.mp4");
                        final String MEDIA_URL = file.toURI().toString();
                        final Media media = new Media(MEDIA_URL);
                        final MediaPlayer player = new MediaPlayer(media);
                        player.play();
                        primaryStage.setScene(new Scene(new Pane(new MediaView(player)), 320, 470));
                        primaryStage.getIcons().add(new Image("file:/E:/Linux/src/com/icon.jpg"));
                        primaryStage.setTitle("Video");
                        primaryStage.show();
                    }
                });

               /* back.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent event) {
                        primaryStage.setScene(new Scene(pane, FixedValue.SCREEN_WIDTH, FixedValue.SCREEN_HEIGHT));
                        primaryStage.getIcons().add(new Image("file:/E:/Linux/src/com/icon.jpg"));
                        primaryStage.setTitle("Menu");
                        primaryStage.setResizable(false);
                        primaryStage.sizeToScene();
                        primaryStage.show();
                    }
                });
*/
                BackgroundImage myBI = new BackgroundImage(new Image("file:/E:/Linux/src/com/c.jpg"),
                        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                pane1.setBackground(new Background(myBI));
                scene = new Scene(pane1, FixedValue.SCREEN_WIDTH, FixedValue.SCREEN_HEIGHT);
                primaryStage.getIcons().add(new Image("file:/E:/Linux/src/com/icon.jpg"));
                primaryStage.setScene(scene);
                primaryStage.setTitle("About");
                primaryStage.setResizable(false);
                primaryStage.sizeToScene();
                primaryStage.show();
            }
        });

        option.setOnMouseClicked(new EventHandler<MouseEvent>() {
            // در این قسمت برای گزینه option  نوع سختی بازی را مشخص می کنیم در سه سطح اسان متوسط و سخت
            public void handle(MouseEvent event) {
                easy.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent event) {
                        FixedValue.BALL_START_SPEED = 5;
                        gamePlay easyGamePlay = new gamePlay(Stage, primaryStage);
                    }
                });

                medium.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent event) {
                        FixedValue.BALL_START_SPEED = 10;
                        gamePlay mediumGamePlay = new gamePlay(Stage, primaryStage);
                    }
                });

                hard.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent event) {
                        FixedValue.BALL_START_SPEED = 15;
                        gamePlay hardGamePlay = new gamePlay(Stage, primaryStage);

                    }
                });

                easy.setLayoutX(50);
                easy.setLayoutY(100);
                medium.setLayoutX(90);
                medium.setLayoutY(200);
                hard.setLayoutX(150);
                hard.setLayoutY(300);

                BackgroundImage myBI = new BackgroundImage(new Image("file:E:/Linux/src/com/s.png"),
                        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                pane2.setBackground(new Background(myBI));

                scene = new Scene(pane2, FixedValue.SCREEN_WIDTH, FixedValue.SCREEN_HEIGHT);

                primaryStage.setScene(scene);
                primaryStage.getIcons().add(new Image("file:/E:/Linux/src/com/icon.jpg"));
                primaryStage.setTitle("Difficalty");
                primaryStage.setResizable(false);
                primaryStage.sizeToScene();
                primaryStage.show();
            }
        });

        /*دکمه ها به صفحه منو اضافه می شوند*/
        pane.getChildren().add(start);
        pane.getChildren().add(exit);
        pane.getChildren().add(option);
        pane.getChildren().add(about);
        pane1.getChildren().add(watch);
        pane2.getChildren().add(easy);
        pane2.getChildren().add(medium);
        pane2.getChildren().add(hard);
        pane1.getChildren().add(back);
        back.setLayoutX(10);
        back.setLayoutY(10);
        start.setLayoutX(30);
        start.setLayoutY(240);
        option.setLayoutX(80);
        option.setLayoutY(290);
        about.setLayoutX(130);
        about.setLayoutY(330);
        watch.setLayoutX(250);
        watch.setLayoutY(400);
        exit.setLayoutX(180);
        exit.setLayoutY(400);

        BackgroundImage myBI = new BackgroundImage(new Image("file:E:/Linux/src/com/brickbreaker.jpg"),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        pane.setBackground(new Background(myBI));

        scene = new Scene(pane, FixedValue.SCREEN_WIDTH, FixedValue.SCREEN_HEIGHT);

        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image("file:/E:/Linux/src/com/icon.jpg"));
        primaryStage.setTitle("Menu");
        primaryStage.setResizable(false);
        primaryStage.sizeToScene();
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}


