package com.example.weather

import android.app.Application
import com.example.weather.network.API
import com.example.weather.network.header
import com.example.weather.repo.weatherrepo
import com.example.weather.repo.weatherrepo2
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
                        .addInterceptor(header())
                        .build()
                    Retrofit
                        .Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(client)
                        .baseUrl("http://dataservice.accuweather.com/")
                        .build()
                }
                single {
                    val retrofit:Retrofit = get()
                    retrofit.create(Api::class.java)
                }
                single {
                    val api: API = get()
                    weatherrepo2(api)
                } bind weatherrepo::class
            })
        }
    }
}