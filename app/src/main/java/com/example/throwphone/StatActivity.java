package com.example.throwphone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.text.DecimalFormat;

public class StatActivity extends AppCompatActivity {

    TextView distance;

    TextView numberOfTimesThrown;

    TextView highestScore;

    TextView lowestScore;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        /*MainActivity mainActivity = new MainActivity();
        mainActivity.displayTotalDistance();*/

        Button goBackButton = (Button) findViewById(R.id.buttonBack);

        goBackButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                startActivity(new Intent(StatActivity.this,MainActivity.class));
            }
        });

        distance = (TextView) findViewById(R.id.distanceThrown);

        numberOfTimesThrown = (TextView) findViewById(R.id.timesThrown);

        highestScore = (TextView) findViewById(R.id.highestScore);

        lowestScore = (TextView) findViewById(R.id.lowestScore);

        setAllText();

        Button goResetButton = (Button) findViewById(R.id.buttonReset);

        goResetButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                DataManager.resetValues();
                setAllText();
            }
        });







    }
    public void setAllText(){
        String twoDecimal = String.format("%.2f",DataManager.getTotalDistance());
        distance.setText(""+twoDecimal+"m");

        numberOfTimesThrown.setText(""+DataManager.getNumberOfThrows()+"");

        String twoDecimalMeter = String.format("%.2f",DataManager.getHighScore());
        highestScore.setText(""+twoDecimalMeter+"m");

        String twoDecimalMet = String.format("%.5f",DataManager.getLowScore());
        lowestScore.setText(""+twoDecimalMet+"m");
    }
}
