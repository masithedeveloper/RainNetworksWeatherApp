package com.rain.weather.repository;

public interface WeatherRepository<V> {

    V loadData(String s);
}
