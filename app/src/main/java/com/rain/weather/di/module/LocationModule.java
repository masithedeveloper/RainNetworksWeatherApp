package com.rain.weather.di.module;

import dagger.Binds;
import dagger.Module;
import com.rain.weather.provider.LocationProvider;
import com.rain.weather.provider.LocationProviderImpl;

@Module
public abstract class LocationModule {

    @Binds
    abstract LocationProvider provideLocationProvider(LocationProviderImpl locationProvider);

}
