package com.rain.weather.di.module;

import android.content.Context;

import javax.inject.Singleton;

import androidx.annotation.NonNull;
import androidx.room.Room;
import dagger.Module;
import dagger.Provides;
import com.rain.weather.data.local.WeatherDatabase;
import com.rain.weather.data.local.dao.WeatherDao;


@Module
public class DatabaseModule {

    @Provides
    @Singleton
    static WeatherDatabase provideDatabase(@NonNull Context context) {
        return Room.databaseBuilder(context,
                WeatherDatabase.class, "weather_db")
                .build();
    }

    @Provides
    @Singleton
    static WeatherDao provideWeatherResponseDao(@NonNull WeatherDatabase appDatabase) {
        return appDatabase.weatherDao();
    }
}
