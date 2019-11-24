package com.vulog.android_meteo.weather_data;

/**
 * Enum which defines big families of weather.
 */
public enum WeatherEnum {
    THUNDERSTORM(200,232),
    DRIZZLE(300,321),
    RAIN(500,531),
    SNOW(600,622),
    ATMOSPHERE(700,781),
    CLEAR(800,800),
    CLOUDS(801,804);

    private int minId;
    private int maxId;

    /**
     * Defining a weather enum according to min and max ids, which are defined on the api.
     * Check this as reference https://openweathermap.org/weather-conditions
     * @param minId the min id
     * @param maxId the max id
     */
    WeatherEnum(int minId, int maxId) {
        this.minId = minId;
        this.maxId = maxId;
    }
}
