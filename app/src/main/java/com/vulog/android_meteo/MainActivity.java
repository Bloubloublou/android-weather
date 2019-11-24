package com.vulog.android_meteo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.vulog.android_meteo.weather_data.CitiesWeatherForecast;
import com.vulog.android_meteo.weather_data.UnitEnum;

import java.util.HashSet;

public class MainActivity extends AppCompatActivity {

    private ApiCaller caller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        new Thread(new Runnable() {
            @Override
            public void run() {
                caller = new ApiCaller();
                HashSet<String> strings = new HashSet<>();
                strings.add("Miramas");
                strings.add("Valbonne");
                strings.add("Grasse");
                caller.updateForecasts(strings);

                Log.d("HENLO",CitiesWeatherForecast.getInstance().getForecast("Miramas")[0].getTemperatureInUnit(UnitEnum.METRIC)+"");
            }
        }).start();
    }
}
