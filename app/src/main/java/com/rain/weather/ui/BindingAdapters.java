package com.rain.weather.ui;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.github.pwittchen.weathericonview.WeatherIconView;

import androidx.databinding.BindingAdapter;
import com.rain.weather.R;
import com.rain.weather.utils.GlideApp;
import com.rain.weather.utils.WeatherIconUtils;

public class BindingAdapters {

    @BindingAdapter({"imageUrl"})
    public static void bindImage(ImageView imageView, String imagePath) {
        GlideApp.with(imageView.getContext())
                .load("http:" + imagePath)
                .placeholder(R.drawable.day)
                .into(imageView);
    }

    @BindingAdapter({"showIcon"})
    public static void showIcon(WeatherIconView iconView, String condition) {
        Context context = iconView.getContext();
        WeatherIconUtils.getIconResource(context, iconView, condition);
    }

    @BindingAdapter("visibleGone")
    public static void showHide(View view, Boolean show) {
        if (show) view.setVisibility(View.VISIBLE);
        else view.setVisibility(View.GONE);
    }
}
