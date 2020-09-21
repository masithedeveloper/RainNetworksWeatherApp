// Generated by Dagger (https://google.github.io/dagger).
package com.rain.weather.viewmodel;

import androidx.lifecycle.ViewModel;
import dagger.internal.Factory;
import java.util.Map;
import javax.inject.Provider;

public final class WeatherViewModelFactory_Factory implements Factory<WeatherViewModelFactory> {
  private final Provider<Map<Class<? extends ViewModel>, Provider<ViewModel>>> creatorsProvider;

  public WeatherViewModelFactory_Factory(
      Provider<Map<Class<? extends ViewModel>, Provider<ViewModel>>> creatorsProvider) {
    this.creatorsProvider = creatorsProvider;
  }

  @Override
  public WeatherViewModelFactory get() {
    return provideInstance(creatorsProvider);
  }

  public static WeatherViewModelFactory provideInstance(
      Provider<Map<Class<? extends ViewModel>, Provider<ViewModel>>> creatorsProvider) {
    return new WeatherViewModelFactory(creatorsProvider.get());
  }

  public static WeatherViewModelFactory_Factory create(
      Provider<Map<Class<? extends ViewModel>, Provider<ViewModel>>> creatorsProvider) {
    return new WeatherViewModelFactory_Factory(creatorsProvider);
  }

  public static WeatherViewModelFactory newWeatherViewModelFactory(
      Map<Class<? extends ViewModel>, Provider<ViewModel>> creators) {
    return new WeatherViewModelFactory(creators);
  }
}