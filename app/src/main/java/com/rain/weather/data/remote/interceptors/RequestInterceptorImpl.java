package com.rain.weather.data.remote.interceptors;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.Response;



@Singleton
public class RequestInterceptorImpl implements ApiInterceptor {

    @Inject
    public RequestInterceptorImpl() {
    }

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request request = chain.request();

        HttpUrl url = request.url().newBuilder()
                .addQueryParameter("access_key", "83dd22810dccfaac8a7163c4ae2d9849")
                .build();

        request = request.newBuilder().url(url).build();
        return chain.proceed(request);
    }
}
