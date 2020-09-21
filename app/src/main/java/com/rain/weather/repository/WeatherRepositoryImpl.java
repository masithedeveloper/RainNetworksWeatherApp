package com.rain.weather.repository;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import com.rain.weather.data.NetworkBoundResource;
import com.rain.weather.data.local.LocalDataSource;
import com.rain.weather.data.local.entity.WeatherResponse;
import com.rain.weather.data.remote.api.ApiResponse;
import com.rain.weather.data.source.BaseSource;
import com.rain.weather.utils.AppExecutors;
import com.rain.weather.utils.RateLimiter;
import com.rain.weather.utils.Resource;

@Singleton
public class WeatherRepositoryImpl implements WeatherRepository<LiveData<Resource<WeatherResponse>>> {

    private final LocalDataSource<WeatherResponse, LiveData<WeatherResponse>> mLocalDataSource;

    private final BaseSource<LiveData<ApiResponse<WeatherResponse>>> mBaseSource;

    private final AppExecutors mExecutors;

    private RateLimiter<String> rateLimit = new RateLimiter<>(10, TimeUnit.SECONDS);

    @Inject
    WeatherRepositoryImpl(AppExecutors executors, LocalDataSource dataSource, BaseSource baseSource) {
        mExecutors = executors;
        mLocalDataSource = dataSource;
        mBaseSource = baseSource;
    }

    @Override
    public LiveData<Resource<WeatherResponse>> loadData(String input) {
        return new NetworkBoundResource<WeatherResponse, WeatherResponse>(mExecutors) {

            @Override
            protected void saveCallResult(@NonNull WeatherResponse item) {
                mLocalDataSource.save(item);
            }

            @Override
            protected boolean shouldFetch(@Nullable WeatherResponse data) {
                if (data != null) {
                    return mLocalDataSource.shouldFetch(data) || mLocalDataSource.hasLocationChanged(data);
                }
                return data == null || rateLimit.shouldFetch(input);
            }

            @NonNull
            @Override
            protected LiveData<WeatherResponse> loadFromDb() {

                return mLocalDataSource.get();
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<WeatherResponse>> createCall() {
                return mBaseSource.get();
            }

            @Override
            protected void onFetchFailed() {
                rateLimit.reset(input);
            }

        }.asLiveData();
    }
}
