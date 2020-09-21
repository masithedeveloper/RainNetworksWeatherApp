package com.rain.weather.provider;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.tasks.OnSuccessListener;

import javax.inject.Inject;

import com.rain.weather.R;
import com.rain.weather.data.model.WLocation;
import com.rain.weather.utils.Utilities;

public class LocationProviderImpl extends PreferenceProvider implements LocationProvider, OnSuccessListener<Location> {

    private static final String USE_DEVICE_LOCATION = "USE_DEVICE_LOCATION";

    private static final String CUSTOM_LOCATION = "CUSTOM_LOCATION";

    private Context mContext;

    private FusedLocationProviderClient mFusedLocationProviderClient;

    private Location deviceWLocation;

    @Inject
    LocationProviderImpl(Context context, FusedLocationProviderClient client) {
        super(context);
        mFusedLocationProviderClient = client;
        mContext = context;
    }

    @Override
    public boolean isLocationChanged(WLocation WLocation) {

        return hasDeviceLocationChanged(WLocation) || hasCustomLocationChanged(WLocation);
    }

    @Override
    public String getPreferredLocationString() {
        if (isUsingDeviceLocation()) {

            if (getLastDeviceLocation() == null) {
                Utilities.showToast(mContext, mContext.getString(R.string.location_not_available), Toast.LENGTH_LONG);
                    return getCustomLocationName();
                } else {
                String latitude = String.valueOf(getLastDeviceLocation().getLatitude());
                String longitude = String.valueOf(getLastDeviceLocation().getLongitude());

                    return (latitude + "," + longitude);
                }
        } else {
            return getCustomLocationName();
        }
    }

    private boolean hasDeviceLocationChanged(WLocation location) {
        if (!isUsingDeviceLocation()) {
            return false;
        }

        double comparisonThreshold = 0.03;

        if (getLastDeviceLocation() != null && location != null) {
            return Math.abs(getLastDeviceLocation().getLatitude() - Long.valueOf(location.getLat())) > comparisonThreshold
                    && Math.abs(getLastDeviceLocation().getLongitude() - Long.valueOf(location.getLon())) > comparisonThreshold;
        }
        return false;
    }

    private boolean hasCustomLocationChanged(WLocation location) {
        if (!isUsingDeviceLocation()) {
            String customLocationName = getCustomLocationName();
            return !customLocationName.equals(location.getName());
        }
        return false;
    }

    private boolean isUsingDeviceLocation() {
        startLocationUpdates();
        return getSharedPreferences().getBoolean(USE_DEVICE_LOCATION, true);
    }

    private String getCustomLocationName() {
        return getSharedPreferences().getString(CUSTOM_LOCATION, null);
    }

    private Location getLastDeviceLocation() {

        startLocationUpdates();

        return deviceWLocation;
    }

    @SuppressLint("MissingPermission")
    private void startLocationUpdates() {
        mFusedLocationProviderClient.getLastLocation().addOnSuccessListener(this);

        new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);
                for (Location location : locationResult.getLocations()) {
                    if (location != null) {
                        deviceWLocation = location;
                    }
                }
            }
        };
    }

    @Override
    public void onSuccess(Location location) {
        if (location != null) {
            deviceWLocation = location;
        } else {
        }
    }

}
