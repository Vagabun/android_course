package com.example.lab_4.API.model

data class WeatherModel(val date: String, val tod: String, val pressure: String, val temp: String,
                        val feel: String, val humidity: String, val wind: String, val cloud: String) {
    lateinit var tods: List<WeatherModel>
    override fun toString(): String {
        return "Date: $date | Temp: $temp | Feel: $feel"
    }
}