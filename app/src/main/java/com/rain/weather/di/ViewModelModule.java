package com.rain.weather.di;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import com.rain.weather.ui.WeatherViewModel;
import com.rain.weather.viewmodel.WeatherViewModelFactory;

@Module
abstract class ViewModelModule {

    /*
     * inject this object into a Map using the @IntoMap annotation,
     * with the  WeatherViewModel.class as key,
     * and a Provider that will build a WeatherViewModel
     * object.
     *
     * */

    @Binds
    @IntoMap
    @ViewModelKey(WeatherViewModel.class)
    abstract ViewModel currentWeatherViewModel(WeatherViewModel weatherViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(WeatherViewModelFactory factory);
}
