package com.rain.weather.di.module;

import androidx.lifecycle.LiveData;
import dagger.Binds;
import dagger.Module;
import com.rain.weather.data.local.LocalDataSource;
import com.rain.weather.data.local.LocalDataSourceImpl;
import com.rain.weather.data.local.entity.WeatherResponse;
import com.rain.weather.data.remote.RemoteSourceImpl;
import com.rain.weather.data.source.BaseSource;
import com.rain.weather.repository.WeatherRepository;
import com.rain.weather.repository.WeatherRepositoryImpl;
import com.rain.weather.utils.Resource;

@Module
public abstract class DataSourceModule {

    @Binds
    abstract LocalDataSource provideDataSource(LocalDataSourceImpl localDataSource);

    @Binds
    abstract BaseSource provideBaseSource(RemoteSourceImpl remoteSource);

    @Binds
    abstract WeatherRepository<LiveData<Resource<WeatherResponse>>> provideRepoImpl(WeatherRepositoryImpl repo);
}

