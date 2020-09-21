package com.rain.weather.data.model

import com.rain.weather.utils.Utilities
import com.squareup.moshi.Json

class WLocation  (
        val name: String,
        val country: String,
        val region: String,
        val lat: String,
        val lon: String,

        @Json(name = "timezone_id")
        val timezoneID: String,

        val localtime: String,

        @Json(name = "localtime_epoch")
        val localtimeEpoch: Long,

        @Json(name = "utc_offset")
        val utcOffset: String
){
        fun getDayOfWeek(): String{
                return Utilities.getDay(localtime)
        }
}