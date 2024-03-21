package com.example.weatherapi.strategy.caching;

import com.example.weatherapi.model.ForecastData;

/**
 * Strategy which will be used to decide how to cache searches.
 */
public interface cachingStrategy {

    // Method to add forecast data into cache
    public void addForecastData(String zipcode, ForecastData forecastData);

    // Method to get forecast data
    public ForecastData getForecastData(String zipcode);

    // Method to remove forecast data
    public void removeForecastData(String zipcode);
}
