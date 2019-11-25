package com.vulog.android_meteo.citiesList

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import com.vulog.android_meteo.ApiCaller
import com.vulog.android_meteo.R
import com.vulog.android_meteo.UserPrefs
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
        Log.d("HENLO","0")
        updateView()
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

    fun updateView() {
        Log.d("HENLO","1")
        Log.d("HENLO","length ${UserPrefs.getInstance(this).cities.size}")

        val t = Thread(Runnable {
            apiCaller.updateForecasts(UserPrefs.getInstance(this).cities)
        })
        t.start()
        t.join()  // wait for thread to finish

        Log.d("HENLO","2")
        val cityList = getCityRows()
        Log.d("HENLO","3")
        val adapter = CityAdapter(this,cityList)
        Log.d("HENLO","4")
        listView.adapter = adapter
        Log.d("HENLO","5")

        listView.setOnItemClickListener { _, _, position, _ ->
            val selectedRecipe = cityList[position]
            // TODO go to the detailled view
            /*val detailIntent = RecipeDetailActivity.newIntent(context, selectedRecipe)
            startActivity(detailIntent)*/
        }
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, ListOfCitiesActivity::class.java)
        }
    }
}
