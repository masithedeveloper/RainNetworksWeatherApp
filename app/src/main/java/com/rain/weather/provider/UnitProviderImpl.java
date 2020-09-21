package com.rain.weather.provider;

import android.content.Context;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.rain.weather.utils.UnitSystem;

@Singleton
public class UnitProviderImpl extends PreferenceProvider implements UnitProvider {

    private static final String UNIT_SYSTEM = "UNIT_SYSTEM";

    @Inject
    public UnitProviderImpl(Context context) {
        super(context);
    }

    @Override
    public UnitSystem getUnitSystem() {
        String selectedName = getSharedPreferences().getString(UNIT_SYSTEM, UnitSystem.METRIC.name());
        return UnitSystem.valueOf(selectedName);
    }
}
