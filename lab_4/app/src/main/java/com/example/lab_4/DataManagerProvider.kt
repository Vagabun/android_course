package com.example.lab_4

import retrofit2.Call

interface DataManagerProvider<T> {
    fun getWeather(): Call<T>
}