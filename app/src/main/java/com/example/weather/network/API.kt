package com.example.weather.network

import com.example.weather.models.DailyForecasts
import com.example.weather.models.HourlyForecast
import com.example.weather.models.Location
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val APIKEY = "8lQbZLgO7xPiEQzNSubK4sjLRFLDIPEC"

interface API {
        @GET("locations/v1/cities/search")
        suspend fun searchLocation(
            @Query("apikey") apiKey: String = APIKEY,
            @Query("q") query: String
        ): Response<List<Location>>

    @GET("forecasts/v1/daily/5day/{location_key}")
    suspend fun getDailyForecasts(
        @Path("location_key") locationKey: String,
        @Query("apikey") apiKey: String = APIKEY,
        @Query("metric") metric: Boolean = true
    ): Response<DailyForecasts>

    @GET("forecasts/v1/hourly/12hour/{location_key}")
    suspend fun getHourlyForecasts(
        @Path("location_key") locationKey: String,
        @Query("apikey") apiKey: String = APIKEY,
        @Query("metric") metric: Boolean = true
    ):Response<List<HourlyForecast>>
}