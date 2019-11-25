package com.vulog.android_meteo.detailled;

import com.vulog.android_meteo.weather_data.WeatherEnum;

public class DetailledRowObject {
    private String dayOfWeek;
    private String minTemp;
    private String maxTemp;
    private WeatherEnum weatherEnum;

    public DetailledRowObject(String dayOfWeek, String minTemp, String maxTemp, WeatherEnum weatherEnum) {
        this.dayOfWeek = dayOfWeek;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.weatherEnum = weatherEnum;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public String getMinTemp() {
        return minTemp;
    }

    public String getMaxTemp() {
        return maxTemp;
    }

    public WeatherEnum getWeatherEnum() {
        return weatherEnum;
    }
}
