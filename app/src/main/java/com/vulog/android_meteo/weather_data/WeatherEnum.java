package com.vulog.android_meteo.weather_data;

/**
 * Enum which defines big families of weather.
 */
public enum WeatherEnum {
    THUNDERSTORM(200,232),
    DRIZZLE(300,321),
    RAIN(500,531),
    SNOW(600,622),
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

    public WeatherEnum getWeather(int id) {
        int firstDigit = id / 100;

        switch (firstDigit) {
            case 2:
                return THUNDERSTORM;
            case 3:
                return DRIZZLE;
            case 5:
                return RAIN;
            case 6:
                return SNOW;
            case 8:
                if(id == 800) {
                    return CLEAR;
                } else {
                    return CLOUDS;
                }
            default:
                return CLEAR;
        }
    }
}
