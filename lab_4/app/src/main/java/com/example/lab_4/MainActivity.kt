package com.example.lab_4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lab_4.API.model.WeatherModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.util.Log

data class Weather(val date: String, val temp: String, val feel: String, val details: List<WeatherDetails>) {
    override fun toString(): String {
        return "Date: $date | Temp: $temp | Feel: $feel"
    }
}

data class WeatherDetails(val tod: String, val pressure: String, val temp: String,
                          val feel: String, val humidity: String, val wind: String, val cloud: String) {
    override fun toString(): String {
        return "$tod: temperature: $temp, feel: $feel, " +
                "humidity: $humidity, pressure: $pressure, wind: $wind, cloud: $cloud"
    }
}

class MainActivity : AppCompatActivity() {

    companion object {
        fun todTransform(tod: String) : String {
            return when (tod) {
                "0" -> "Ночь"
                "1" -> "Утро"
                "2" -> "День"
                "3" -> "Вечер"
                else -> ""
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_weather)

        val call = DataManager.getWeather()
        call.enqueue(object : Callback<List<WeatherModel>> {
            override fun onFailure(call: Call<List<WeatherModel>>, t: Throwable) {
                Log.e("onFailure", t.toString())

            }

            override fun onResponse(call: Call<List<WeatherModel>>, response: Response<List<WeatherModel>>) {

                //move tranform to utils class, maybe add new data class, that will hold transformed data, maybe add function
                //that will calculate mean of temp and feel

                val groupedData = response.body()?.groupBy { it.date }
                val transformedData = mutableListOf<Weather>()
                for ((date, weatherList) in groupedData!!) {
                    val details = mutableListOf<WeatherDetails>()
                    for (item in weatherList)
                        details.add(WeatherDetails(
                            todTransform(item.tod), item.pressure, item.temp, item.feel,
                            item.humidity, item.wind, item.cloud))
                    transformedData.add(Weather(date, weatherList[2].temp, weatherList[2].feel, details))
                }

//                val data = mutableListOf<WeatherModel>()
//                for ((date, weatherList) in groupedData!!) {
//                    val weather = weatherList.first()
////                    val weather = WeatherModel(date, temp = weatherList.first().temp, feel = weatherList.first().feel)
//                    weather.tods = weatherList
//                    data.add(weather)
//                }

                WeatherFragment.adapter.addAll(transformedData)
                WeatherFragment.adapter.notifyDataSetChanged()
                WeatherDetailsFragment.weatherDetailsData = transformedData
//                Log.e("onResponse", response.body().toString())
            }
        })
    }
}
