package com.example.lab_4

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.ListFragment
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.FragmentTransaction
import com.google.gson.Gson


class WeatherFragment : ListFragment() {

    companion object {
        val weatherData = arrayOf(
            "Day 1",
            "Day 2",
            "Day 3",
            "Day 4",
            "Day 5"
        )
    }

    private var dualPane: Boolean = false
    private var curCheckPosition = 0

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        listAdapter = ArrayAdapter<String>(activity, android.R.layout.simple_list_item_activated_1, weatherData)

        val weatherDetailsFrame: View? = activity?.findViewById(R.id.weatherDetails)
        dualPane = weatherDetailsFrame?.visibility == View.VISIBLE

        curCheckPosition = savedInstanceState?.getInt("curChoice", 0) ?: 0

        if (dualPane) {
            listView.choiceMode = ListView.CHOICE_MODE_SINGLE
            showWeatherDetails(curCheckPosition)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("curChoice", curCheckPosition)
    }

    override fun onListItemClick(l: ListView?, v: View?, position: Int, id: Long) {
        showWeatherDetails(position)
    }

    private fun showWeatherDetails(index: Int) {
        curCheckPosition = index

        if (dualPane) {
            listView.setItemChecked(index, true)

            var weatherDetails = fragmentManager?.findFragmentById(R.id.weatherDetails) as? WeatherDetailsFragment
            if (weatherDetails?.shownIndex != index) {
                weatherDetails = WeatherDetailsFragment.newInstance(index)

                fragmentManager?.beginTransaction()?.apply {
                    replace(R.id.weatherDetails, weatherDetails)
                    setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    commit()
                }
            }
        }
        else {
            val intent = Intent().apply {
                setClass(activity, WeatherDetailsActivity::class.java)
                putExtra("index", index)
            }
            startActivity(intent)
        }
    }

//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_weather, container, false)
//    }


}
