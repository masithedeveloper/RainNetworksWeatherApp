package com.rain.weather.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import com.rain.weather.data.local.dao.WeatherDao;
import com.rain.weather.data.local.entity.WeatherResponse;

@Database(entities = {WeatherResponse.class}, version = 1, exportSchema = false)
@TypeConverters({WeatherTypeConverter.class})
public abstract class WeatherDatabase extends RoomDatabase {

    public abstract WeatherDao weatherDao();
}
