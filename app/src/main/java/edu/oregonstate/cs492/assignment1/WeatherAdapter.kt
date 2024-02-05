package edu.oregonstate.cs492.assignment1

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class WeatherAdapter(private val coordinatorLayout: CoordinatorLayout) : RecyclerView.Adapter<WeatherAdapter.WeatherHolder>() {
    val weatherList: MutableList<WeatherItem> = mutableListOf()

    fun addWeatherItem(weatherItem: WeatherItem, position: Int){
        weatherList.add(position, weatherItem)
        notifyItemInserted(position)
    }

    override fun getItemCount() = weatherList.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.weather_item, parent, false)
        return WeatherHolder(view)
    }

    override fun onBindViewHolder(holder: WeatherHolder, position: Int) {
        holder.bind(weatherList[position])
    }
    @SuppressLint("ClickableViewAccessibility")
    class WeatherHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val date: TextView = view.findViewById(R.id.weather_day)
        private val shortDesc: TextView = view.findViewById(R.id.weather_item_SD)
        private val low: TextView = view.findViewById(R.id.weather_item_low)
        private val high: TextView = view.findViewById(R.id.weather_item_high)
        private val rain: TextView = view.findViewById(R.id.weather_item_rain)
        private var longDesc: String = ""
        private var currentWeatherItem: WeatherItem? = null
        private val handler = Handler(Looper.getMainLooper())

        init {
            view.setOnTouchListener { v, event ->
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        v.setBackgroundColor(Color.argb(128, 128, 128, 128))
                        handler.postDelayed({
                            currentWeatherItem?.let { weatherItem ->
                                Snackbar.make(view, weatherItem.longDesc, Snackbar.LENGTH_LONG).show()
                            }
                        }, 750) // Delay of 750 milliseconds (0.75 seconds)
                    }
                    MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                        v.setBackgroundColor(Color.TRANSPARENT)
                        handler.removeCallbacksAndMessages(null) // Cancel delayed Snackbar
                    }
                }
                true
            }
        }

        fun bind(weatherItem: WeatherItem) {
            currentWeatherItem = weatherItem
            date.text = weatherItem.date
            shortDesc.text = weatherItem.shortDesc
            low.text = weatherItem.low
            high.text = weatherItem.high
            rain.text = weatherItem.rain
            longDesc = weatherItem.longDesc
        }
    }
}
