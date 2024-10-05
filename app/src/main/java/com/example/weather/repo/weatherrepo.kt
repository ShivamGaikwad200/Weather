package com.example.weather.repo

import com.example.weather.models.BaseModel
import com.example.weather.models.DailyForecasts
import com.example.weather.models.HourlyForecast
import com.example.weather.models.Location

interface  weatherrepo {
    suspend fun searchLocation(query:String): BaseModel<List<Location>>
    suspend fun getDailyForecasts(locationKey:String):BaseModel<DailyForecasts>
    suspend fun getHourlyForecasts(locationKey: String):BaseModel<List<HourlyForecast>>
}