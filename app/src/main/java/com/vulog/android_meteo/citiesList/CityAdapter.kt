package com.vulog.android_meteo.citiesList

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.vulog.android_meteo.R

class CityAdapter(private val context: Context,
                    private val dataSource: ArrayList<CityRowObject>) : BaseAdapter() {

    private val inflater: LayoutInflater
            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return dataSource.size
    }

    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        val holder: ViewHolder

        if (convertView == null) {
            view = inflater.inflate(R.layout.city_row, parent, false)
            holder = ViewHolder()

            holder.weatherImageView = view.findViewById(R.id.weather_image)
            holder.cityName = view.findViewById(R.id.city_name)
            holder.cityWeather = view.findViewById(R.id.city_weather)
            holder.temperature = view.findViewById(R.id.temperature)

            view.tag = holder
        } else {
            view = convertView
            holder = convertView.tag as ViewHolder
        }

        val weatherImageView = holder.weatherImageView
        val cityName = holder.cityName
        val cityWeather = holder.cityWeather
        val temperature = holder.temperature

        val cityRow = getItem(position) as CityRowObject

        weatherImageView.setImageDrawable(context.getDrawable(cityRow.weather.drawableId))
        cityName.text = cityRow.name
        // TODO use a external api to get hour of city and no more current weather
        cityWeather.text = cityRow.weatherDescription
        temperature.text = "${cityRow.temperature}°"

        return view
    }

    private class ViewHolder {
        lateinit var weatherImageView: ImageView
        lateinit var cityName: TextView
        lateinit var temperature: TextView
        lateinit var cityWeather: TextView
    }
}
