package com.example.throwphone;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.throwphone.SensorManagerJava;

public class MainActivity extends Activity implements SensorEventListener {


    private TextView accText;
    private float MaxValue;

    private long lastTimestamp = 0;
    private float lastDropAcc = 0;
    private float lastX = 0;
    private float lastY = 0;
    private float lastZ = 0;
     private float distance = 0;

    @Override
    public final void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SensorManager rSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor accSensor = rSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        rSensorManager.registerListener(this, accSensor, SensorManager.SENSOR_DELAY_FASTEST);

        accText = findViewById(R.id.ScoreHere);

        accText.setText("working");
    }
    @Override
    /*public void onSensorChanged(SensorEvent sensorEvent) {
        float accX = sensorEvent.values[0];
        float accY = sensorEvent.values[1];
        float accZ = sensorEvent.values[2];

        double accMagnitude = Math.sqrt(accX * accX + accY * accY + accZ * accZ);

        double dropAcc = accMagnitude - SensorManager.GRAVITY_EARTH;


        //distance
        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
        long timestamp = sensorEvent.timestamp;
            float disX = sensorEvent.values[0];
            float disY = sensorEvent.values[1];
            float disZ = sensorEvent.values[2];

            if (lastTimestamp != 0) {
                float timeInter = (timestamp - lastTimestamp) * 1e-9f;
                float dX = disX - lastX;
                float dY = disY - lastY;
                float dZ = disZ - lastZ;
                distance = (float) (Math.sqrt(dX * dX + dY * dY + dZ * dZ) * timeInter * timeInter)*1000000;
            }
            lastTimestamp = timestamp;
            lastX = disX;
            lastY = disY;
            lastZ = disZ;
        }


            if (dropAcc > 20 && distance > MaxValue) {
                accText.setText("" + (int)distance + "");
                MaxValue = distance;
            }
        }*/
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {

            float accX = sensorEvent.values[0];
            float accY = sensorEvent.values[1];
            float accZ = sensorEvent.values[2];

            double accMagnitude = Math.sqrt(accX * accX + accY * accY + accZ * accZ);

            float dropAcc = (float) (accMagnitude - SensorManager.GRAVITY_EARTH);

            if (lastTimestamp != 0) {
                float timeInter = (sensorEvent.timestamp - lastTimestamp) * 1e-9f;
                float avgDropAcc = (dropAcc + lastDropAcc) / 2;
                distance += avgDropAcc + timeInter * timeInter;
            }
            lastTimestamp = sensorEvent.timestamp;
            lastDropAcc = dropAcc;


            accText.setText("" + distance + "");


            lastTimestamp = 0;
        }
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

}