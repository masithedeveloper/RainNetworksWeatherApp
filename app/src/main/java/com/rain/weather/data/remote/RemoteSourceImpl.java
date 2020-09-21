package com.rain.weather.data.remote;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import com.rain.weather.data.local.entity.WeatherResponse;
import com.rain.weather.data.remote.api.ApiResponse;
import com.rain.weather.data.remote.api.ApiService;
import com.rain.weather.data.source.BaseSource;
import com.rain.weather.provider.LocationProvider;

public class RemoteSourceImpl implements BaseSource<LiveData<ApiResponse<WeatherResponse>>> {

    private final ApiService mApiService;

    private final LocationProvider mLocationProvider;

    @Inject
    RemoteSourceImpl(ApiService service,
                     LocationProvider locationProvider) {
        mApiService = service;
        mLocationProvider = locationProvider;
    }

    @Override
    public LiveData<ApiResponse<WeatherResponse>> get() {
        return mApiService.getWeather(mLocationProvider.getPreferredLocationString());
    }
}
