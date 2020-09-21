package com.rain.weather.provider;

import android.telephony.SignalStrength;

public interface SignalStrengthProvider {

    void onSignalStrengthsChanged(SignalStrength signalStrength);

}
