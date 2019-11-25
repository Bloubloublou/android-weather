package com.vulog.android_meteo.detailled;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.vulog.android_meteo.R;
import com.vulog.android_meteo.weather_data.WeatherEnum;

import java.util.ArrayList;

public class DetailledAdapter extends BaseAdapter {

    private ArrayList<DetailledRowObject> dataSource;
    private Context context;

    public DetailledAdapter(Context context, ArrayList<DetailledRowObject> dataSource) {
        this.context = context;
        this.dataSource = dataSource;
    }
    @Override
    public int getCount() {
        return dataSource.size();
    }

    @Override
    public Object getItem(int i) {
        return dataSource.get(i);
    }

    @Override
    public long getItemId(int i) {
        return (long) i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View row= LayoutInflater.from(context).inflate(R.layout.detailled_row, viewGroup, false);
        TextView dayOfWeek,minTemp,maxTemp;
        ImageView imageView;

        dayOfWeek = row.findViewById(R.id.day_of_week);
        minTemp = row.findViewById(R.id.min_temp);
        maxTemp = row.findViewById(R.id.max_temp);
        imageView = row.findViewById(R.id.weather_image);

        dayOfWeek.setText(dataSource.get(i).getDayOfWeek());
        minTemp.setText(dataSource.get(i).getMinTemp());
        maxTemp.setText(dataSource.get(i).getMaxTemp());
        imageView.setImageDrawable(context.getDrawable(dataSource.get(i).getWeatherEnum().getDrawableId()));

        return (row);
    }
}
