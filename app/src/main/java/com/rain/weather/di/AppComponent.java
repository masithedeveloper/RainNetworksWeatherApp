package com.rain.weather.di;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;
import com.rain.weather.WeatherApplication;
import com.rain.weather.di.module.ActivityModule;
import com.rain.weather.di.module.ApiModule;
import com.rain.weather.di.module.AppModule;
import com.rain.weather.di.module.DataSourceModule;
import com.rain.weather.di.module.DatabaseModule;
import com.rain.weather.di.module.FragmentBuildersModule;
import com.rain.weather.di.module.LocationModule;
import com.rain.weather.di.module.SignalStrengthModule;
import com.rain.weather.di.module.UnitModule;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        ActivityModule.class,
        FragmentBuildersModule.class,
        ApiModule.class,
        DatabaseModule.class,
        AppModule.class,
        UnitModule.class,
        LocationModule.class,
        SignalStrengthModule.class,
        DataSourceModule.class,
        ViewModelModule.class})
public interface AppComponent {

    void inject(WeatherApplication application);

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
