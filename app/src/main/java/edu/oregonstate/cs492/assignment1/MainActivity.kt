package edu.oregonstate.cs492.assignment1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.os.IResultReceiver._Parcel
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.text.TextUtils
import android.widget.LinearLayout
import androidx.appcompat.view.menu.MenuView.ItemView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val coordinator: CoordinatorLayout = findViewById(R.id.coordinator_layout)
        val weatherListRV: RecyclerView = findViewById(R.id.rv_weather_list)

        weatherListRV.layoutManager = LinearLayoutManager(this)
        weatherListRV.setHasFixedSize(true)

        val adapter = WeatherAdapter(coordinator)
        weatherListRV.adapter = adapter

        adapter.addWeatherItem(
            WeatherItem("4", "31°F", "51°F",
                        "70% precip.", "Mostly Cloudy/PM Showers",
                        "Cloudy all day with expected evening showers. A low of 31°F and a high of 51°F"), 0
        )
        adapter.addWeatherItem(
            WeatherItem("5", "41°F", "48°F",
                "85% precip.", "Mostly Cloudy/AM Showers",
                "Cloudy all day with expected morning showers. A low of 41°F and a high of 48°F"), 1
        )
        adapter.addWeatherItem(
            WeatherItem("6", "41°F", "50°F",
                "0% precip.", "Cloudy All Day",
                "Cloudy all day with no expectation of any rain showers. A low of 41°F and a high of 50°F"), 2
        )
        adapter.addWeatherItem(
            WeatherItem("7", "37°F", "48°F",
                "30% precip.", "Cloudy/AM Showers",
                "Cloudy all day with a chance of morning showers. A low of 31°F and a high of 51°F"), 3
        )
        adapter.addWeatherItem(
            WeatherItem("8", "33°F", "48°F",
                "25% precip.", "Mostly Cloudy/Midday Sun",
                "Partly cloud with a break in the cover in the AM. Mild chance of rain in the evening"), 4
        )
        adapter.addWeatherItem(
            WeatherItem("9", "40°F", "49°F",
                "30% precip.", "Mostly Cloudy/AM Showers",
                "Cloudy all day with a chance of overnight showers, a break from the rain from early morning until noon"), 5
        )
        adapter.addWeatherItem(
            WeatherItem("10", "37°F", "49°F",
                "0% precip.", "Cloudy All Day",
                "Cloudy all day with no expectation of any rain showers. A low of 37°F and a high of 49°F"), 6
        )
        adapter.addWeatherItem(
            WeatherItem("11", "38°F", "52°F",
                "0% precip.", "Mostly Cloudy",
                "Cloudy all day with a short break in the afternoon around 4PM. Low of 38°F and high of 52°F"), 7
        )
        adapter.addWeatherItem(
            WeatherItem("12", "35°F", "50°F",
                "10% precip.", "Cloudy/Midday Showers",
                "Cloudy all day with a slight chance of showers around lunch time. A low of 35°F and a high of 50°F"),8
        )
        adapter.addWeatherItem(
            WeatherItem("13", "36°F", "51°F",
                "10% precip.", "Mostly Cloudy",
                "Cloudy all day with a slight chance of evening showers. A low of 31°F and a high of 51°F"),9
        )


        val itemTouchCallback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                return
            }
        }
        ItemTouchHelper(itemTouchCallback).attachToRecyclerView(weatherListRV)


    }
}