package com.rain.weather.provider;

import com.rain.weather.data.model.WLocation;

public interface LocationProvider {

    boolean isLocationChanged(WLocation WLocation);

    String getPreferredLocationString();
}
