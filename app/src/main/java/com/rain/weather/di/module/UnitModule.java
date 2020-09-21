package com.rain.weather.di.module;

import dagger.Binds;
import dagger.Module;
import com.rain.weather.provider.UnitProvider;
import com.rain.weather.provider.UnitProviderImpl;

@Module
public abstract class UnitModule {

    @Binds
    abstract UnitProvider provideUnitProvider(UnitProviderImpl unitProvider);
}
