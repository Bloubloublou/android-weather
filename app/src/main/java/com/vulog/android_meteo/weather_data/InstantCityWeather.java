package com.vulog.android_meteo.weather_data;

import java.util.Calendar;

public class InstantCityWeather {
    private int weather;
    private String weatherDescription;
    // we get the temperature in kelvin since the api give us this metrics by default. It's more efficient to
    // re-compute new temperatures by hand than making an api call each time the user wants to switch temperature unit

    // TODO set as double
    private int kelvinTemperature;
    private int humidity;
    private int windSpeed;
    private int pressure;

    // TODO we could call https://sunrise-sunset.org/api for sunrise and sunset
    //private Calendar sunrise;
    //private Calendar sunset;

    public InstantCityWeather(int weather, String weatherDescription, int kelvinTemperature, int humidity, double windSpeed, int pressure) {
        this.weather = weather;
        this.weatherDescription = weatherDescription;
        this.kelvinTemperature = kelvinTemperature;
        this.humidity = humidity;
        this.windSpeed = (int)Math.round(windSpeed);
        this.pressure = pressure;
    }

    /**
     * Getting the temperature in a peculiar unit (see unit at https://openweathermap.org/weather-data)
     * @param unit defines the needed unit
     * @return the correct temperature
     */
    public int getTemperatureInUnit(UnitEnum unit) {
        switch (unit) {
            case IMPERIAL:
                return (int)Math.round((this.kelvinTemperature - 273.15)*9/5 + 32);
            case METRIC:
                return (int)Math.round(this.kelvinTemperature - 273.15);
            default:
                return  this.kelvinTemperature;
        }
    }

    public int getWeather() {
        return weather;
    }

    public int getHumidity() {
        return humidity;
    }

    public int getWindSpeed() {
        return windSpeed;
    }

    public int getPressure() {
        return pressure;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }
}
