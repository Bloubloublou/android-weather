package com.vulog.android_meteo.citiesList

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.vulog.android_meteo.ApiCaller
import com.vulog.android_meteo.R
import com.vulog.android_meteo.UserPrefs
import com.vulog.android_meteo.detailled.DetailledActivity
import com.vulog.android_meteo.weather_data.CitiesWeatherForecast
import com.vulog.android_meteo.weather_data.UnitEnum
import com.vulog.android_meteo.weather_data.WeatherEnum



class ListOfCitiesActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var apiCaller: ApiCaller

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_of_cities)
        listView = findViewById<ListView>(R.id.cities_list)
        apiCaller = ApiCaller(this)
    }

    override fun onResume() {
        super.onResume()
        updateView()
    }

    fun getCityRows(): ArrayList<CityRowObject> {
        val result = ArrayList<CityRowObject>()

        val cityNames = UserPrefs.getInstance(this).cities
        val citiesWeatherForecast = CitiesWeatherForecast.getInstance()
        val unit:UnitEnum = UnitEnum.getUnit(UserPrefs.getInstance(this).isMetric)
        for (cityName in cityNames) {
            result.add(CityRowObject(cityName,
                    WeatherEnum.getWeather(citiesWeatherForecast.getForecast(cityName)[0].weather),
                    citiesWeatherForecast.getForecast(cityName)[0].weatherDescription,
                    citiesWeatherForecast.getForecast(cityName)[0].getTemperatureInUnit(unit)))
        }

        return result
    }

    fun updateView() {
        val t = Thread(Runnable {
            apiCaller.updateForecasts(UserPrefs.getInstance(this).cities)
        })
        t.start()
        t.join()  // wait for thread to finish

        val cityList = getCityRows()
        val adapter = CityAdapter(this,cityList)
        listView.adapter = adapter

        listView.setOnItemClickListener { _, _, position, _ ->
            val city = cityList[position].name
            val detailIntent = DetailledActivity.newIntent(this,city)
            startActivity(detailIntent)
        }
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, ListOfCitiesActivity::class.java)
        }
    }
}
