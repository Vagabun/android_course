package com.example.lab_4

import com.example.lab_4.API.ApiService
import com.example.lab_4.API.ServiceConstructor
import com.example.lab_4.API.model.WeatherModel
import retrofit2.Call

//singleton
object DataManager: DataManagerProvider<List<WeatherModel>> {
    private val service = ServiceConstructor.createService(ApiService::class.java)

    override fun getWeather(): Call<List<WeatherModel>> {
        return service.requestWeather()
    }
}