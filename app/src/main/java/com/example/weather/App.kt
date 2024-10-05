package com.example.weather

import android.app.Application
import com.example.weather.network.API
import com.example.weather.network.Header
import com.example.weather.repo.WeatherRepo
import com.example.weather.repo.WeatherRepo2
import com.google.android.gms.common.api.Api
import okhttp3.OkHttpClient
import org.koin.core.context.startKoin
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            modules(module {
                single {
                    val client = OkHttpClient.Builder()
                        .addInterceptor(Header())
                        .build()
                    Retrofit
                        .Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(client)
                        .baseUrl("https://dataservice.accuweather.com")
                        .build()
                }
                single {
                    val retrofit:Retrofit = get()
                    retrofit.create(API::class.java)
                }
                single {
                    val api: API = get()
                    WeatherRepo2(api)
                } bind WeatherRepo::class
            })
        }
    }
}