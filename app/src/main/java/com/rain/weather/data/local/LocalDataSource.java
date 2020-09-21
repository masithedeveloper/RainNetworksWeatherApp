package com.rain.weather.data.local;

import com.rain.weather.data.source.BaseSource;

public interface LocalDataSource<K, V> extends BaseSource {

    void save(K k);

    boolean hasLocationChanged(K k);

    boolean shouldFetch(K k);

    @Override
    V get();
}
