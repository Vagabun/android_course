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
                Log.e("onResponse", response.body().toString())
            }
        })
    }
}
