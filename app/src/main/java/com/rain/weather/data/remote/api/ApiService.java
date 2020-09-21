package com.rain.weather.data.remote.api;

import androidx.lifecycle.LiveData;
import com.rain.weather.data.local.entity.WeatherResponse;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("current")
    LiveData<ApiResponse<WeatherResponse>> getWeather(@Query("query") String location);

}

