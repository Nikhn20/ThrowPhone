package com.example.throwphone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    //Fun fact cold showers are cold
    //Fun fact

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button statButton = (Button) findViewById(R.id.buttonStat);

        statButton.setOnClickListener(new View.OnClickListener(){

            @Override public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, StatActivity.class));
            }
        }

        );


    }
}