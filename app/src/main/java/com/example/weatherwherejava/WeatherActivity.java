package com.example.weatherwherejava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import java.lang.Math;

public class WeatherActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        TextView info = findViewById(R.id.weatherInfo);
        TextView city = findViewById(R.id.bigtext);
        TextView currentTemp = findViewById(R.id.currentTemp);

        ImageView img = findViewById(R.id.icon);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        float min = intent.getFloatExtra("temp_min", 0);
        float max = intent.getFloatExtra("temp_max", 0);
        float clouds = intent.getFloatExtra("clouds", 0);
        float temp = intent.getFloatExtra("temp", 0);
        // float rain = intent.getFloatExtra("rain", 0);
        double f = temp * 1.8 + 32;


        String stringBuilder =
                 "Today's low: " + Math.round((min * 1.8 + 32)) + "° F" + "\n"
                + "Today's high: " + Math.round((max * 1.8 + 32)) + "° F" + "\n"
                + "Clouds: "  + clouds + "%";
                // + "Rain: "  +  rain;

        info.setText(stringBuilder);
        city.setText(name);
        currentTemp.setText("Currently " +  Double.toString(Math.round(f)) + "° F");

        if(clouds > 40 && clouds < 60){
            img.setImageResource(R.drawable.partlycloudy);
        } else if(clouds < 40){
            img.setImageResource(R.drawable.sun);
        } else {
            img.setImageResource(R.drawable.cloudy);
        }

    }
}