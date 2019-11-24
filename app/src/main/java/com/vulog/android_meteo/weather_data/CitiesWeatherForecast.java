package com.vulog.android_meteo.weather_data;

import android.content.Context;

import com.vulog.android_meteo.ApiCaller;
import com.vulog.android_meteo.UserPrefs;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class contains the forecast of all needed cities, for five days and every three hours (this has been chosen because
 * it is the max data we'll get if using the api for free)
 */
public class CitiesWeatherForecast {
    private static CitiesWeatherForecast instance;

    // the max stored data
    public static int MAX_NB_OF_DATA = 40;

    // for all array, first entry is current weather, the next 40 are forecast weathers
    private HashMap<String,InstantCityWeather[]> forecasts;

    public CitiesWeatherForecast() {
        this.forecasts = new HashMap<>();
    }

    public static CitiesWeatherForecast getInstance() {
        if(instance == null) {
            instance = new CitiesWeatherForecast();
        }
        return instance;
    }

    /**
     * returns the min and max temperatures for one city and on day, in one peculiar unit
     * @param cityName the city name
     * @param indexOfDay the index of the day, it needs to be from 0 to 4
     * @param unit the unit we've chosen
     * @return a two data array, first is min ,second is max
     */
    public int[] getMinMaxTempOfDay(String cityName, int indexOfDay, UnitEnum unit){
        InstantCityWeather[] weatherArray = forecasts.get(cityName);
        int minDataIndex = 8 * indexOfDay;
        int maxDataIndex = minDataIndex + 8;

        int resultMin = weatherArray[minDataIndex].getTemperatureInUnit(unit);
        int resultMax = resultMin;

        int temperatureAtI = 0;
        for(int i = minDataIndex + 1; i <= maxDataIndex; i ++) {
            temperatureAtI = weatherArray[i].getTemperatureInUnit(unit);
            resultMin = resultMin <  temperatureAtI ? resultMin : temperatureAtI;
            resultMax = resultMax >  temperatureAtI ? resultMax : temperatureAtI;
        }

        return new int[]{resultMin,resultMax};
    }

    /**
     * Returns the forecast for one city
     * @param city
     * @return
     */
    public InstantCityWeather[] getForecast(String city) {
        return forecasts.get(city);
    }

    /**
     * Adds one forecast for a new city, or overwrites it
     * @param city the city name
     * @param weathers the city forecast
     */
    public void putForecast(String city, InstantCityWeather[] weathers) {
        forecasts.put(city, weathers);
    }

    /**
     * remove a forecast
     * @param city the city name
     */
    public void removeForecast(String city) {
        forecasts.remove(city);
    }
}
