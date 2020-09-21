package com.rain.weather.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.rain.weather.R;
import com.rain.weather.data.local.entity.WeatherResponse;
import com.rain.weather.data.model.Signal;
import com.rain.weather.databinding.FragmentWeatherBinding;
import com.rain.weather.di.Injectable;
import com.rain.weather.ui.WeatherViewModel;
import com.rain.weather.utils.Resource;
import com.rain.weather.utils.Status;
import com.rain.weather.utils.Utilities;

public class WeatherFragment extends Fragment implements Injectable, SwipeRefreshLayout.OnRefreshListener {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private WeatherViewModel mWeatherViewModel;

    private FragmentWeatherBinding mBinding;

    TelephonyManager telephonyManager;

    private boolean isLoading = true;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViewModel();
        isConnected();
        ((AppCompatActivity) Objects.requireNonNull(getActivity())).getSupportActionBar().setTitle("");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_weather, container, false);
        mBinding.setLifecycleOwner(this);

        mBinding.swipeRefresh.setOnRefreshListener(this);
        mBinding.swipeRefresh.setColorSchemeColors(
                ContextCompat.getColor(Objects.requireNonNull(getActivity()), R.color.colorPrimary),
                ContextCompat.getColor(getActivity(), R.color.colorAccent),
                ContextCompat.getColor(getActivity(), R.color.colorPrimary)
        );

        telephonyManager = (TelephonyManager)getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        telephonyManager.listen(signalStrengthProviderImplInner, PhoneStateListener.LISTEN_SIGNAL_STRENGTHS);

        return mBinding.getRoot();
    }

    private void initViewModel() {
        mWeatherViewModel = ViewModelProviders.of(this, viewModelFactory).get(WeatherViewModel.class);
        observeWeather();
    }

    private void observeWeather() {
        mWeatherViewModel.getCurrentWeather().observe(this, currentWeatherResource -> {
            if (currentWeatherResource.data != null) {
                bindData(currentWeatherResource);
                showError(currentWeatherResource);
                showSuccess(currentWeatherResource);
                isLoading = false;
            } else {
                ((AppCompatActivity) Objects.requireNonNull(getActivity())).getSupportActionBar().setTitle("");
            }
            mBinding.setResource(currentWeatherResource);
        });
    }

    private void bindData(@NonNull Resource<WeatherResponse> currentWeatherResource) {
        assert currentWeatherResource.data != null;
        mBinding.setWeather(currentWeatherResource.data.getCurrent());
        String location = (currentWeatherResource.data.getLocation().getName() + ", " +
                currentWeatherResource.data.getLocation().getRegion());
        ((AppCompatActivity) Objects.requireNonNull(getActivity())).getSupportActionBar().setTitle(location);
        mBinding.setLocation(currentWeatherResource.data.getLocation());
    }

    private void showError(Resource<WeatherResponse> currentWeatherResource) {
        if (currentWeatherResource.status == Status.ERROR) {
            if (currentWeatherResource.message != null) {
                if (!currentWeatherResource.message.isEmpty()) {
                    showSnackBar(currentWeatherResource.message, v -> snackRetryAction());
                }
            }
        }
    }

    private void showSuccess(Resource<WeatherResponse> currentWeatherResource) {
        if (currentWeatherResource.status == Status.SUCCESS) {
            isLoading = false;
        }
    }

    private void showSnackBar(String message, View.OnClickListener listener) {
        Snackbar.make(mBinding.getRoot(), message, Snackbar.LENGTH_LONG)
                .setAction(R.string.retry, listener)
                .show();
    }

    private void retryFetch() {
        mWeatherViewModel.retry(String.valueOf(isLoading));
    }

    private boolean isConnected() {
        if (!Utilities.isOnline(Objects.requireNonNull(getActivity()))) {
            showSnackBar(getString(R.string.no_internet), v -> snackRetryAction());
        }
        return true;
    }

    private void snackRetryAction() {
        if (isConnected()) {
            retryFetch();
        }
        isConnected();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onRefresh() {
        if (isConnected()) {
            retryFetch();
            mBinding.swipeRefresh.setRefreshing(isLoading);
        }
        mBinding.swipeRefresh.setRefreshing(false);
    }

    final PhoneStateListener signalStrengthProviderImplInner = new PhoneStateListener () {

        public int signalSupport = 0;
        public String signalStrengthText = "";

        @Override
        public void onSignalStrengthsChanged(SignalStrength signalStrength) {
            super.onSignalStrengthsChanged(signalStrength);

            signalSupport = signalStrength.getGsmSignalStrength();

            if (signalSupport > 30) {
                signalStrengthText = "Signal GSM : Good";

            } else if (signalSupport > 20 && signalSupport < 30) {
                signalStrengthText = "Signal GSM : Avarage";

            } else if (signalSupport < 20 && signalSupport > 3) {
                signalStrengthText = "Signal GSM : Weak";

            } else if (signalSupport < 3) {
                signalStrengthText = "Signal GSM : Very weak";
            }

            mBinding.setSignal(new Signal(signalStrengthText));
        }
    };
}