package com.rain.weather.data.local.entity;

import com.squareup.moshi.Json;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.rain.weather.data.model.Current;
import com.rain.weather.data.model.Request;
import com.rain.weather.data.model.WLocation;


@Entity(tableName = "weather_response")
public class WeatherResponse {

    @PrimaryKey
    private int id;

    @Json(name = "current")
    private Current current;

    @Json(name = "location")
    private WLocation location;

    @Json(name = "request")
    private Request request;

    public WeatherResponse() {
    }

    public Current getCurrent() {
        return current;
    }

    public void setCurrent(Current current) {
        this.current = current;
    }

    public WLocation getLocation() {
        return location;
    }

    public void setLocation(WLocation location) {
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }
}