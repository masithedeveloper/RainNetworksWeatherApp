package com.rain.weather;

import android.app.Activity;

import com.jakewharton.threetenabp.AndroidThreeTen;

import javax.inject.Inject;

import androidx.multidex.MultiDexApplication;
import androidx.preference.PreferenceManager;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import com.rain.weather.di.AppInjector;

public class WeatherApplication extends MultiDexApplication implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    @Override
    public void onCreate() {

        AppInjector.init(this);

        super.onCreate();

        AndroidThreeTen.init(this);

        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);

    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }
}
