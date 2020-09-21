package com.rain.weather.di.module;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import com.rain.weather.ui.activity.MainActivity;

@Module
public abstract class ActivityModule {

    @ContributesAndroidInjector(modules = FragmentBuildersModule.class)
    abstract MainActivity contributeMainActivity();
}
