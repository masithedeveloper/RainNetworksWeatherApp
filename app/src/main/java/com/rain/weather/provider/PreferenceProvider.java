package com.rain.weather.provider;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

abstract class PreferenceProvider {

    private Context mContext;

    public PreferenceProvider(Context context) {
        mContext = context;
    }

    public SharedPreferences getSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(mContext);
    }
}
