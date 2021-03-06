package com.example.weatherwherejava;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherService {
    @GET("data/2.5/weather?")
    Call<WeatherResponse> getCurrentWeatherData(@Query("zip") String zip,  @Query("APPID") String app_id, @Query("units") String units);
}
