package com.vulog.android_meteo.weather_data;

import com.vulog.android_meteo.R;

/**
 * Enum which defines big families of weather.
 */
public enum WeatherEnum {
    THUNDERSTORM(R.drawable.thunder),
    DRIZZLE(R.drawable.drizzle),
    RAIN(R.drawable.rainy),
    SNOW(R.drawable.snowy),
    CLEAR(R.drawable.bluesky),
    CLOUDS(R.drawable.cloudy);

    private int drawableId;

    /**
     * Creating the enum
     * @param drawableId the drawable id of the image associated with the weather enum
     */
    WeatherEnum(int drawableId) {
        this.drawableId = drawableId;
    }

    public static WeatherEnum getWeather(int id) {
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

    public int getDrawableId() {
        return drawableId;
    }
}
