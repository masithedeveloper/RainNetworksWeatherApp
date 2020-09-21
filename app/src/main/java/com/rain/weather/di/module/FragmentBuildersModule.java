package com.rain.weather.di.module;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import com.rain.weather.ui.fragment.WeatherFragment;

@Module
public abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract WeatherFragment contributeWeatherFragmet();
}
