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
    @Override
    public final void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SensorManager rSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor accSensor = rSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        rSensorManager.registerListener(this, accSensor, SensorManager.SENSOR_DELAY_NORMAL);

        accText = findViewById(R.id.ScoreHere);

        accText.setText("working");
    }
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float accX = sensorEvent.values[0];
        float accY = sensorEvent.values[1];
        float accZ = sensorEvent.values[2];

        double accMagnitude = Math.sqrt(accX * accX + accY * accY + accZ * accZ);

        double dropAcc = accMagnitude - SensorManager.GRAVITY_EARTH;


        if (dropAcc > 1) {
            accText.setText("" + dropAcc + "");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

}