package com.vulog.android_meteo;

import android.util.Log;

import com.vulog.android_meteo.weather_data.CitiesWeatherForecast;
import com.vulog.android_meteo.weather_data.InstantCityWeather;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Set;

public class ApiCaller {
    private static final String API_KEY = "7b5e590c39152ca6c17f04f0c32fd980";
    private static final String FORECAST_WEATHER = "http://api.openweathermap.org/data/2.5/forecast?q=%s&APPID=" + API_KEY;

    /**
     * Getting datas from api for according cities
     *
     * @param cities the cities we need
     */
    public void updateForecasts(Set<String> cities) {
        for (String city : cities) {
            try {
                // request to api for forecast
                URL url = new URL(String.format(FORECAST_WEATHER, city));
                HttpURLConnection connection =
                        (HttpURLConnection) url.openConnection();

                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(connection.getInputStream()));

                StringBuffer json = new StringBuffer(1024);
                String tmp;
                while ((tmp = reader.readLine()) != null)
                    json.append(tmp).append("\n");
                reader.close();

                JSONObject data = new JSONObject(json.toString());

                // if everything went ok, extract needed data from json
                if (data.getInt("cod") == 200) {
                    Log.d("RESPONSE_IS", data.toString());
                    CitiesWeatherForecast.getInstance().putForecast(city, jsonToForeCast(data));
                }
            } catch (Exception e) {
                Log.d("RESPONSE_IS", e.toString());
            }
        }
    }

    /**
     * Extracts forecasts for one city from a json object
     * @param json the given json object
     * @return the forecast for the next 40 slices of 3 hours
     */
    private InstantCityWeather[] jsonToForeCast(JSONObject json) {
        InstantCityWeather[] forecast = new InstantCityWeather[CitiesWeatherForecast.MAX_NB_OF_DATA];
        try {
            JSONArray tmpArray = json.getJSONArray("list");

            InstantCityWeather tmpWeather;
            JSONObject tmpJSON;
            for (int i = 0; i < CitiesWeatherForecast.MAX_NB_OF_DATA; i++) {
                tmpJSON = tmpArray.getJSONObject(i);
                forecast[i] = new InstantCityWeather(tmpJSON.getJSONArray("weather").getJSONObject(0).getInt("id"),
                        tmpJSON.getJSONArray("weather").getJSONObject(0).getString("description"),
                        tmpJSON.getJSONObject("main").getInt("temp"),
                        tmpJSON.getJSONObject("main").getInt("humidity"),
                        tmpJSON.getJSONObject("wind").getDouble("speed"),
                        tmpJSON.getJSONObject("main").getInt("pressure"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return forecast;
    }
}