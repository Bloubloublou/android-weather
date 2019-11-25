package com.vulog.android_meteo.detailled;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.vulog.android_meteo.R;
import com.vulog.android_meteo.UserPrefs;
import com.vulog.android_meteo.citiesList.ListOfCitiesActivity;
import com.vulog.android_meteo.weather_data.CitiesWeatherForecast;
import com.vulog.android_meteo.weather_data.InstantCityWeather;
import com.vulog.android_meteo.weather_data.UnitEnum;
import com.vulog.android_meteo.weather_data.WeatherEnum;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DetailledActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailled);
        ImageView backArrow = findViewById(R.id.back_arrow);
        ImageView background = findViewById(R.id.weather_image);
        TextView cityName = findViewById(R.id.name_of_city);
        TextView weatherDescription = findViewById(R.id.current_weather_description);
        TextView temperature = findViewById(R.id.big_current_temperature);
        TextView currentDayOfWeek = findViewById(R.id.current_day_of_week);
        TextView currentMaxTemp = findViewById(R.id.current_max_temp);
        TextView currentMinTemp = findViewById(R.id.current_min_temp);
        ListView listview = findViewById(R.id.days_forecast);
        TextView humidity = findViewById(R.id.humidity_value);
        TextView wind = findViewById(R.id.wind_value);
        TextView pressure = findViewById(R.id.pressure_value);

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), ListOfCitiesActivity.class);
                startActivity(intent);
            }
        });

        String city ="";
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            city = extras.getString("city");
        }

        InstantCityWeather[] forecasts = CitiesWeatherForecast.getInstance().getForecast(city);

        UnitEnum unit = UnitEnum.getUnit(UserPrefs.getInstance(this).isMetric());
        cityName.setText(city);
        weatherDescription.setText(forecasts[0].getWeatherDescription());
        temperature.setText(forecasts[0].getTemperatureInUnit(unit)+"°");
        currentDayOfWeek.setText(new SimpleDateFormat("EEEE", Locale.ENGLISH).format(Calendar.getInstance().getTime().getTime()));
        int[] minMax = CitiesWeatherForecast.getInstance().getMinMaxTempOfDay(city,0,unit);
        currentMinTemp.setText(minMax[0]+"");
        currentMaxTemp.setText(minMax[1]+"");
        humidity.setText(forecasts[0].getHumidity()+"");
        wind.setText(forecasts[0].getWindSpeed()+"");
        pressure.setText(forecasts[0].getPressure()+"");
        background.setImageDrawable(getDrawable(WeatherEnum.getWeather(forecasts[0].getWeather()).getDrawableId()));

        ArrayList<DetailledRowObject> rows = new ArrayList<>();

        for(int i = 0; i < 3; i++) {
            Date date = new Date();
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.add(Calendar.DATE, i+1);

            date = c.getTime();
            minMax = CitiesWeatherForecast.getInstance().getMinMaxTempOfDay(city,i+1,unit);
            rows.add(new DetailledRowObject(new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date),
                    minMax[0]+"",
                    minMax[1]+"",
                    WeatherEnum.getWeather(forecasts[8*(i+1)].getWeather())));
        }

        DetailledAdapter adapter = new DetailledAdapter(this,rows);
        listview.setAdapter(adapter);
    }

    public static Intent newIntent(Context context,String city) {
        Intent intent =  new Intent(context, DetailledActivity.class);
        intent.putExtra("city",city);
        return intent;
    }
}
