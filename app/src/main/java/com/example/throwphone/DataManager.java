package com.example.throwphone;

import androidx.appcompat.app.AppCompatActivity;

public class DataManager extends AppCompatActivity {

    private static float totalDistance = 0;
    private static int numberOfThrows = 0;
    private static float highScore = 0;
    private static float lowScore = 0;

    public static void addDistance(float distanceFloat){
        totalDistance += distanceFloat;

        if (distanceFloat > highScore){
            highScore = distanceFloat;
        }
    }
    public static double getTotalDistance(){
        return totalDistance;
    }



    public static int getNumberOfThrows() {
        return numberOfThrows;
    }

    public static void addNumberOfThrows() {
        numberOfThrows ++;
    }

    public static float getHighScore() {
        return highScore;
    }

    public static float getLowScore(){
        return lowScore;
    }

    public static void addLowScore(float calcLowScore){
        lowScore = calcLowScore;
    }


    public static void resetValues(){
        totalDistance = 0;
        numberOfThrows = 0;
        highScore = 0;
        lowScore = 0;
    }
}
