package com.example.lab_4

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers

class ApiClient {

    private val API_ENDPOINT = "https://meteo.vagabun.now.sh"

    data class WeatherData(val date: String, val temp: String, val feel: String, val cloud: String)

    interface WeatherApi {
        @Headers("Content-Type: application/json")
        @GET("/meteo.htm")
        fun requestWeather(): Call<List<WeatherData>>
    }


    init {

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(API_ENDPOINT)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val weather = retrofit.create(WeatherApi::class.java)
        weather.requestWeather().enqueue(object : Callback<List<WeatherData>> {
            override fun onFailure(call: Call<List<WeatherData>>, t: Throwable) {
                Log.e("onFailure", t.toString())
            }

            override fun onResponse(call: Call<List<WeatherData>>, response: Response<List<WeatherData>>) {
                Log.e("onResponse", response.body().toString())
            }
        })
    }
}