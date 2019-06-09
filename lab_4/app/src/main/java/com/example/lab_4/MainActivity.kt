package com.example.lab_4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lab_4.API.model.WeatherModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.util.Log


class MainActivity : AppCompatActivity() {

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
                val data = mutableListOf<WeatherModel>()
                for ((date, weatherList) in groupedData!!) {
                    val weather = weatherList.first()
//                    val weather = WeatherModel(date, temp = weatherList.first().temp, feel = weatherList.first().feel)
                    weather.tods = weatherList
                    data.add(weather)
                }


                WeatherFragment.adapter.addAll(data)
                WeatherFragment.adapter.notifyDataSetChanged()
//                Log.e("onResponse", response.body().toString())
            }
        })
    }
}
