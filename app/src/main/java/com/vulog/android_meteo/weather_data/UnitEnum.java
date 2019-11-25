package com.vulog.android_meteo.weather_data;

/**
 * An enum which defines the different version of units
 */
public enum UnitEnum {
    IMPERIAL,
    METRIC;

    public static UnitEnum getUnit(boolean isMetric) {
        return isMetric ? METRIC : IMPERIAL;
    }
}
