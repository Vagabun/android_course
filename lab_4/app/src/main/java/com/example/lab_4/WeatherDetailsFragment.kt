package com.example.lab_4

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView

class WeatherDetailsFragment : Fragment() {

    companion object {
        lateinit var weatherDetailsData: List<Weather>

        fun newInstance(index: Int): WeatherDetailsFragment {
            val f = WeatherDetailsFragment()

            val args = Bundle()
//            args.putParcelableArrayList("weather", ArrayList())
            args.putInt("index", index)
            f.arguments = args


            return f
        }
    }

    val shownIndex: Int by lazy {
        arguments?.getInt("index", 0) ?: 0
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ListView(activity).apply {
            adapter = ArrayAdapter<WeatherDetails>(activity, android.R.layout.simple_list_item_1, weatherDetailsData[shownIndex].details)
        }

        //return inflater.inflate(R.layout.fragment_weather_details, container, false)
    }


}
