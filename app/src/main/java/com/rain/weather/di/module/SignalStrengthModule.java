package com.rain.weather.di.module;

import com.rain.weather.provider.SignalStrengthProvider;
import com.rain.weather.provider.SignalStrengthProviderImpl;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class SignalStrengthModule {

    @Binds
    abstract SignalStrengthProvider provideSignalStrengthProvider(SignalStrengthProviderImpl signalStrengthProvider);

}
