package com.example.weather.network

import com.example.weather.models.Location
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

const val APIKEY = "8lQbZLgO7xPiEQzNSubK4sjLRFLDIPEC\n"

interface API {

        @GET("locations/v1/cities/search")
    suspend fun searchLocation(
        @Query("apikey") apiKey: String = APIKEY,
        @Query("q") query: String
    ): Response<List<Location>>
}