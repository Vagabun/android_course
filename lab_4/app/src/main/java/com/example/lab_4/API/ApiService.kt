package com.example.lab_4.API

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

import com.example.lab_4.API.model.WeatherModel

interface ApiService {
        @Headers("Content-Type: application/json")
        @GET("/meteo.htm")
        fun requestWeather(): Call<List<WeatherModel>>
    companion object {
        const val API_ENDPOINT = "https://meteo.vagabun.now.sh"
    }
}