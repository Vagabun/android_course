package com.example.lab_4

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class WeatherDetailsFragment : Fragment() {

    companion object {
        val weatherDetailsData = arrayOf(
            "one",
            "two",
            "three",
            "four",
            "five"
        )

        fun newInstance(index: Int): WeatherDetailsFragment {
            val f = WeatherDetailsFragment()

            val args = Bundle()

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

//        if (container == null)
//            return null

        return TextView(activity).apply {
            text = weatherDetailsData[shownIndex]

        }

        //return inflater.inflate(R.layout.fragment_weather_details, container, false)
    }


}
