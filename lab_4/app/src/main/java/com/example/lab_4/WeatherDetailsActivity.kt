package com.example.lab_4

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.FragmentActivity

class WeatherDetailsActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_weather_details)
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            finish()
            return
        }

        if (savedInstanceState == null) {
            val weatherDetails = WeatherDetailsFragment().apply {
                arguments = intent.extras
            }
            supportFragmentManager.beginTransaction()
                .add(android.R.id.content, weatherDetails)
                .commit()
        }
    }
}
