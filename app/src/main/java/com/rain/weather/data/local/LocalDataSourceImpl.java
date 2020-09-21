package com.rain.weather.data.local;

import org.threeten.bp.ZoneId;
import org.threeten.bp.ZonedDateTime;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import com.rain.weather.data.local.dao.WeatherDao;
import com.rain.weather.data.local.entity.WeatherResponse;
import com.rain.weather.provider.LocationProvider;

public class LocalDataSourceImpl implements LocalDataSource<WeatherResponse, LiveData<WeatherResponse>> {

    private final WeatherDao mWeatherDao;

    private LocationProvider mLocationProvider;

    @Inject
    LocalDataSourceImpl(LocationProvider locationProvider,
                        WeatherDao weatherDao) {
        mLocationProvider = locationProvider;
        mWeatherDao = weatherDao;
    }

    @Override
    public void save(WeatherResponse response) {
        mWeatherDao.insertWeatherResponse(response);
    }

    @Override
    public boolean hasLocationChanged(WeatherResponse response) {
        return mLocationProvider.isLocationChanged(response.getLocation());
    }

    @Override
    public boolean shouldFetch(WeatherResponse response) {
        try {
            ZonedDateTime locationTime = ZonedDateTime.now(ZoneId.of(response.getLocation().getTimezoneID()));
            ZonedDateTime timeElapsed = ZonedDateTime.now().minusMinutes(30);
            return locationTime.isBefore(timeElapsed);
        }
        catch (Exception e){

        }
        return false;
    }

    @Override
    public LiveData<WeatherResponse> get() {
        return mWeatherDao.getWeatherResponse();
    }
}
