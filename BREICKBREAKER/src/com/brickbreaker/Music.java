package com.brickbreaker;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Music {
    public static MediaPlayer mediaPlayer;
    public static void Menu(){
        /*موسیقی زمان اجرا بازی را مشخص می کند*/
        Media music = new Media("file:/E:/Music/Music/NEWMELODY/AprilRain/2013/1.mp3");
        mediaPlayer = new MediaPlayer(music);
        mediaPlayer.play();
        mediaPlayer.setAutoPlay(true);
    }

    public static void Hit(){
        Media music = new Media("file:/C:/Users/MashadService.ir/Downloads/Music/hit.mp3");
        mediaPlayer = new MediaPlayer(music);
        mediaPlayer.play();
    }
    public static void hit(){
        Media music = new Media("file:/C:/Users/MashadService.ir/Music/attack.mp3");
        mediaPlayer = new MediaPlayer(music);
        mediaPlayer.play();
    }

    public static void stop(){
        mediaPlayer.stop();
    }
}
