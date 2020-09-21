package com.rain.weather.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import com.rain.weather.data.local.entity.WeatherResponse;
import com.rain.weather.repository.WeatherRepository;
import com.rain.weather.utils.Resource;

public class WeatherViewModel extends ViewModel {

    private MutableLiveData<String> value = new MutableLiveData<>();

    private LiveData<Resource<WeatherResponse>> mWeather;

    @Inject
    WeatherViewModel(WeatherRepository<LiveData<Resource<WeatherResponse>>> repository) {
        mWeather = Transformations.switchMap(value, repository::loadData);
        setRefreshId(value.getValue());
    }

    public LiveData<Resource<WeatherResponse>> getCurrentWeather() {
        return mWeather;
    }

    public void retry(String input) {
        setRefreshId(input);
    }

    private void setRefreshId(String input) {
        value.setValue(input);
    }
}
