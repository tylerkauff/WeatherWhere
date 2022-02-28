package com.example.weatherwherejava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class WeatherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        TextView info = findViewById(R.id.weatherInfo);
        Intent intent = getIntent();
        String country = intent.getStringExtra("country");
        float temp = intent.getFloatExtra("temp", 0);

        String stringBuilder = "Country: " + country + "\n" + "Temperature: " + temp;

        info.setText(stringBuilder);

    }
}