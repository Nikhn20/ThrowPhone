package com.example.throwphone;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements SensorEventListener {


    private TextView accText;
    private float MaxValue;

    /*private long lastTimestamp = 0;
    private float lastDropAcc = 0;
    private float lastX = 0;
    private float lastY = 0;
    private float lastZ = 0;*/
     private float distance = 0;

     private boolean Active;

     private SensorManager sensorManager;
     private Sensor accelerometer;
     private long throwStartTime;
     private boolean isThrown = false;

     private float GRAVITY = 9.81f;

     //private final float STOP_THRESHOLD = 15f;

    @Override
    public final void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        accText = findViewById(R.id.ScoreHere);

        accText.setText("Score");

        SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_FASTEST);

        Button goStatButton = (Button) findViewById(R.id.buttonStat);
        goStatButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                startActivity(new Intent(MainActivity.this,StatActivity.class));
            }
                                        });


        Button goButtonStart = (Button) findViewById(R.id.buttonStart);
        goButtonStart.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view)
            {
                if (Active == true)
                {
                    Stop();
                    Start();
                }
                else
                {
                    Start();
                }
            }
        });
    }

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
    /*public void onSensorChanged(SensorEvent sensorEvent) {
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
    }*/

   private void Start () {
       distance = 0;
       Active = true;
       MaxValue = 0;
       accText.setText("" + 0 + "m");
       isThrown = false;
   }

    private void Stop ()
    {
        Active = false;
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        if (Active == true) {
            if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                float x = sensorEvent.values[0];
                float y = sensorEvent.values[1];
                float z = sensorEvent.values[2];
                float acceleration = (float) Math.sqrt(x * x + y * y + z * z);

                GRAVITY = acceleration - sensorManager.GRAVITY_EARTH;

                if (acceleration > 30 && !isThrown) {
                    throwStartTime = System.currentTimeMillis();
                    isThrown = true;
                }

                if (acceleration < 30 && isThrown)
                {
                    Stop();

                    long throwTime = System.currentTimeMillis() - throwStartTime;
                    float distance = 0.5f * (GRAVITY) * (throwTime / 1000f) * (throwTime / 1000f);

                    String TwoDic = String.format("%.4f", distance);

                    accText.setText("" + TwoDic + "m");
                    MaxValue = distance;

                    DataManager.addDistance(distance);
                    DataManager.addTotalThrowTime(throwTime);
                    DataManager.addNumberOfThrows();

                    if (DataManager.getLowScore() == 0)
                    {
                         DataManager.addLowScore(distance);
                    }


                }
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }


    /*private void updateTotalDistance (){
        SharedPreferences sharedPreferences = getSharedPreferences("totalDistance", Context.MODE_PRIVATE);
        float totalDistance = sharedPreferences.getFloat("totalDistance", 0f);
        totalDistance += distance;
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat("totalDistance", totalDistance);
        editor.apply();


    }
    public void displayTotalDistance(){
        SharedPreferences sharedPreferences = getSharedPreferences("totalDistance", Context.MODE_PRIVATE);
        float totalDistance = sharedPreferences.getFloat("totalDistance", 0f);
        TextView totalDistanceTextView = findViewById(R.id.totalDistanceTXT);
        totalDistanceTextView.setText(String.format("Distance in total" + "%.2f", totalDistance) + "m");
    }*/
}