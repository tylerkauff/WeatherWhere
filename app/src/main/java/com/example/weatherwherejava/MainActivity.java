package com.example.weatherwherejava;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.Serializable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static String BaseUrl = "http://api.openweathermap.org/";
    // TODO Change the lat and lon to device location
    public static String lat = "32.7767";
    public static String lon = "96.7970";
    // Get the location provider

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnSearch = findViewById(R.id.btnSearch);
        Button btnLocation = findViewById(R.id.btnLocation);

        // For using set city data
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText input = findViewById(R.id.etSearch);
                String message = input.getText().toString();
                getCurrentData(message);
            }
        });
    }

    void getCurrentData(String city) {
        String apikey = getString(R.string.apikey);
        TextView weatherData = findViewById(R.id.tvInfo);
        WeatherResponse finalWeather;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        String units = "metric";
        WeatherService service = retrofit.create(WeatherService.class);
        // TODO
        Call<WeatherResponse> call = service.getCurrentWeatherData(city, apikey, units);
        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(@NonNull Call<WeatherResponse> call, @NonNull Response<WeatherResponse> response) {
                if (response.code() == 200) {
                    // Weather info object
                    WeatherResponse weatherResponse = response.body();
                    //
                    assert weatherResponse != null;
                    Intent intent = new Intent(MainActivity.this, WeatherActivity.class);
                    intent.putExtra("country", weatherResponse.sys.country);
                    intent.putExtra("temp", weatherResponse.main.temp);

                    intent.putExtra("clouds", weatherResponse.clouds.all);
                    // intent.putExtra("rain", weatherResponse.rain.h3);

                    intent.putExtra("temp_min", weatherResponse.main.temp_min);
                    intent.putExtra("temp_max", weatherResponse.main.temp_max);
                    intent.putExtra("humidity", weatherResponse.main.humidity);
                    intent.putExtra("pressure", weatherResponse.main.pressure);





                    startActivity(intent);

                    /*
                    String stringBuilder = "City: " +
                            weatherResponse.sys.country +
                            "\n" +
                            "Temperature: " +
                            weatherResponse.main.temp +
                            "\n" +
                            "Temperature(Min): " +
                            weatherResponse.main.temp_min +
                            "\n" +
                            "Temperature(Max): " +
                            weatherResponse.main.temp_max +
                            "\n" +
                            "Humidity: " +
                            weatherResponse.main.humidity +
                            "\n" +
                            "Pressure: " +
                            weatherResponse.main.pressure;

                    weatherData.setText(stringBuilder);
                    */

                }
            }

            @Override
            public void onFailure(@NonNull Call<WeatherResponse> call, @NonNull Throwable t) {
                weatherData.setText(t.getMessage());
            }
        });
    }

}