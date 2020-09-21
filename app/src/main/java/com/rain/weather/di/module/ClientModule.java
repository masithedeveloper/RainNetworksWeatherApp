package com.rain.weather.di.module;

import javax.inject.Named;

import dagger.Binds;
import dagger.Module;
import com.rain.weather.data.remote.interceptors.ApiInterceptor;
import com.rain.weather.data.remote.interceptors.ConnectivityInterceptorImpl;
import com.rain.weather.data.remote.interceptors.RequestInterceptorImpl;

@Module(includes = AppModule.class)
public abstract class ClientModule {

    @Binds
    @Named("connect")
    abstract ApiInterceptor provideConnectivityInterceptor(ConnectivityInterceptorImpl interceptor);

    @Binds
    @Named("request")
    abstract ApiInterceptor provideRequestInterceptor(RequestInterceptorImpl interceptor);
}
