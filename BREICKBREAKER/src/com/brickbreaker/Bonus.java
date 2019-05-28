package com.brickbreaker;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.util.Timer;
import java.util.TimerTask;

public class Bonus {
    private static int secondsPassed1 = 0;
    private static int secondsPassed2 = 0;
    private static int secondsPassed3 = 0;

    public static boolean isGift_0 = false;
    public static boolean isGift_1 = false;
    public static boolean isGift_2 = false;

    public static void Gift(int i) {
        Label b1 = new Label("PRIZE");
        /*نوع و زمان اجرا جوایز را مشخص می کند*/
        Timer myTimer = new Timer();
        TimerTask task0 = new TimerTask() {
            @Override
            public void run() {
                secondsPassed1++;
                if(secondsPassed1 / 1000 >= 15) {
                    Paddle.shape.setWidth(FixedValue.PADDLE_WIDTH);
                    myTimer.cancel();
                    secondsPassed1 = 0;
                }
            }
        };

        TimerTask task1 = new TimerTask() {
            @Override
            public void run() {
                secondsPassed2++;
                if(secondsPassed2 / 1000 >= 20) {
                    isGift_1 = false;
                    myTimer.cancel();
                    secondsPassed2 = 0;
                }
            }
        };

        TimerTask task2 = new TimerTask() {
            @Override
            public void run() {
                secondsPassed3++;
                if(secondsPassed3 / 1000 >= 10) {
                    isGift_2 = false;
                    FixedValue.BALL_START_SPEED = 5;
                    myTimer.cancel();
                    secondsPassed3 = 0;
                }
            }
        };

        if (i == 0) {
            isGift_0 = true;
            myTimer.scheduleAtFixedRate(task0, 1000,1);
            System.out.println("Gift1");
            Paddle.shape.setWidth(FixedValue.PADDLE_WIDTH * 2);
        }

        if (i == 1) {
            isGift_1 = true;
            System.out.println("Gift2");
            myTimer.scheduleAtFixedRate(task1, 1000,1);
        }

        if (i == 2) {
            isGift_2 = true;
            System.out.println("Gift3");
            myTimer.scheduleAtFixedRate(task2, 1000,1);
        }
    }
}
