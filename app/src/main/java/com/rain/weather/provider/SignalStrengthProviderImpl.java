package com.rain.weather.provider;

import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.util.Log;

import javax.inject.Inject;

public class SignalStrengthProviderImpl extends PhoneStateListener implements SignalStrengthProvider {

    public int signalSupport = 0;

    @Inject
    SignalStrengthProviderImpl(){}

    @Override
    public void onSignalStrengthsChanged(SignalStrength signalStrength) {
        super.onSignalStrengthsChanged(signalStrength);

        signalSupport = signalStrength.getGsmSignalStrength();
        Log.d(getClass().getCanonicalName(), "------ gsm signal --> " + signalSupport);

        if (signalSupport > 30) {
            Log.d(getClass().getCanonicalName(), "Signal GSM : Good");

        } else if (signalSupport > 20 && signalSupport < 30) {
            Log.d(getClass().getCanonicalName(), "Signal GSM : Avarage");

        } else if (signalSupport < 20 && signalSupport > 3) {
            Log.d(getClass().getCanonicalName(), "Signal GSM : Weak");

        } else if (signalSupport < 3) {
            Log.d(getClass().getCanonicalName(), "Signal GSM : Very weak");
        }
    }
}
