package com.vulog.android_meteo.citiesList

import com.vulog.android_meteo.weather_data.WeatherEnum

class CityRowObject(
        val name: String,
        val weather: WeatherEnum,
        val weatherDescription: String,
        val temperature: Int) {}