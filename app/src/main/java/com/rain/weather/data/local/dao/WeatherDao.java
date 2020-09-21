package com.rain.weather.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.rain.weather.data.local.entity.WeatherResponse;

@Dao
public interface WeatherDao {

    @Query("select * from weather_response")
    LiveData<WeatherResponse> getWeatherResponse();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertWeatherResponse(WeatherResponse weather);
}
