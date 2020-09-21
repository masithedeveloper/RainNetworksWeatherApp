package com.rain.weather.data.model

import com.squareup.moshi.Json

class Current (
        @Json(name = "observation_time")
        val observationTime: String,

        val temperature: Long,

        @Json(name = "weather_code")
        val weatherCode: Long,

        @Json(name = "weather_icons")
        val weatherIcons: List<String>,

        val currentWeatherIcon: String = weatherIcons[0],

        @Json(name = "weather_descriptions")
        val weatherDescriptions: List<String>,

        val weatherDescription: String = weatherDescriptions[0],

        @Json(name = "wind_speed")
        val windSpeed: Long,

        @Json(name = "wind_degree")
        val windDegree: Long,

        @Json(name = "wind_dir")
        val windDir: String,

        val pressure: Long,
        val precip: Double,
        val humidity: Long,
        val cloudcover: Long,
        val feelslike: Long,

        @Json(name = "uv_index")
        val uvIndex: Long,

        val visibility: Long,

        @Json(name = "is_day")
        val isDay: String
)


