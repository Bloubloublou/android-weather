package com.vulog.android_meteo.citiesList

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.vulog.android_meteo.R
import com.vulog.android_meteo.UserPrefs
import com.vulog.android_meteo.weather_data.CitiesWeatherForecast
import com.vulog.android_meteo.weather_data.UnitEnum
import com.vulog.android_meteo.weather_data.WeatherEnum
import org.json.JSONException
import org.json.JSONObject

class ListOfCitiesActivity : AppCompatActivity() {

    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_of_cities)

        listView = findViewById<ListView>(R.id.cities_list)

        val cityList = getCityRows()
        val adapter = CityAdapter(this,cityList)
        listView.adapter = adapter

        listView.setOnItemClickListener { _, _, position, _ ->
            val selectedRecipe = cityList[position]
            // TODO go to the detailled view
            /*val detailIntent = RecipeDetailActivity.newIntent(context, selectedRecipe)
            startActivity(detailIntent)*/
        }
    }

    fun getCityRows(): ArrayList<CityRowObject> {
        val result = ArrayList<CityRowObject>()

        val cityNames = UserPrefs.getInstance(this).cities
        val citiesWeatherForecast = CitiesWeatherForecast.getInstance()
        for (cityName in cityNames) {
            result.add(CityRowObject(cityName,
                    WeatherEnum.getWeather(citiesWeatherForecast.getForecast(cityName)[0].weather),
                    citiesWeatherForecast.getForecast(cityName)[0].weatherDescription,
                    citiesWeatherForecast.getForecast(cityName)[0].getTemperatureInUnit(UnitEnum.METRIC)))
        }

        return result
    }
}
