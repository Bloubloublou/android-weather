package com.vulog.android_meteo;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;

/**
 * stores the user object in SharedPreferences
 */
public class UserPrefs {

    private static UserPrefs instance;

    /**
     * This application's preferences label
     */
    private static final String PREFS_NAME = "com.vulog.userprefs";

    /**
     * The prefix for flattened user keys
     */
    private static final String KEY_PREFIX = "com.weather.KEY";

    /**
     * generic field keys
     */
    private static final String KEY_CITIES = KEY_PREFIX + "_CITIES";

    /**
     * This application's preferences
     */
    private static SharedPreferences settings;

    public UserPrefs(Context ctx) {
        if (settings == null) {
            settings = ctx.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        }
    }

    public static UserPrefs getInstance(Context context) {
        if(instance == null) {
            instance = new UserPrefs(context);
        }

        return instance;
    }

    public void deleteAllData() {
        //writing immediately changes
        SharedPreferences.Editor editor = settings.edit();
        editor.putStringSet(KEY_CITIES, new HashSet<String>());
        editor.apply();
    }

    public void setCities(Set<String> cities) {
        SharedPreferences.Editor editor = settings.edit();
        editor.putStringSet(KEY_CITIES, cities);
        editor.apply();
    }

    public Set<String> getCities() {
        return settings.getStringSet(KEY_CITIES,new HashSet<String>());
    }

    public void addCity(String city) {
        Set<String> cities = getCities();
        cities.add(city);
        setCities(cities);
    }
}