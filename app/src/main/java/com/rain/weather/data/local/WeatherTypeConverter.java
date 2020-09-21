package com.rain.weather.data.local;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import androidx.room.TypeConverter;

import com.rain.weather.data.model.Current;
import com.rain.weather.data.model.Request;
import com.rain.weather.data.model.WLocation;
import com.squareup.moshi.Types;

public class WeatherTypeConverter {

    private Moshi moshi = new Moshi.Builder().build();

    @TypeConverter
    public String weatherToJson(Current weather) {
        return weather == null ? null : moshi.adapter(Current.class).toJson(weather);
    }

    @TypeConverter
    public Current weatherFromJson(String string) {
        Current weather;
        try {
            weather = moshi.adapter(Current.class).fromJson(string);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return weather;
    }


    @TypeConverter
    public String locationToJson(WLocation weatherLocation) {
        return weatherLocation == null ? null : moshi.adapter(WLocation.class).toJson(weatherLocation);
    }

    @TypeConverter
    public WLocation locationFromJson(String string) {
        WLocation location;
        try {
            location = moshi.adapter(WLocation.class).fromJson(string);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return location;
    }

    @TypeConverter
    public String requestToJson(Request request) {
        return request == null ? null : moshi.adapter(Request.class).toJson(request);
    }

    @TypeConverter
    public Request requestFromJson(String string) {
        Request request;
        try {
            request = moshi.adapter(Request.class).fromJson(string);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return request;
    }

    @TypeConverter
    public ArrayList<String> fromString(String value) {
        Type type = Types.newParameterizedType(List.class, String.class);
        JsonAdapter<List<String>> adapter = moshi.adapter(type);
        try {
            return value == null ? null : (ArrayList<String>) adapter.fromJson(value);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @TypeConverter
    public String fromArrayList(ArrayList<String> list) {

        return null;
    }

}
