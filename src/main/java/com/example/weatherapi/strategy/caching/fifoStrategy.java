package com.example.weatherapi.strategy.caching;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import com.example.weatherapi.model.ForecastData;

public class fifoStrategy implements cachingStrategy {
    // Cache capacity
    private static final int CACHE_CAPACITY = 10;
    // Cache to store forecast data with zip code as key
    private Map<String, ForecastData> forecastCache = new HashMap<>();
    // Queue to maintain the insertion order of cache entries
    private Queue<String> cacheQueue = new LinkedList<>();

    // Method to add zipcode to the cache queue
    public void addForecastData(String zipcode, ForecastData forecastData) {
        // If cache queue is at its capacity, remove the oldest entry
        if (cacheQueue.size() >= CACHE_CAPACITY) {
            String removedZipcode = cacheQueue.poll();
            forecastCache.remove(removedZipcode);
        }
        // Add new zipcode to the end of the queue & map
        forecastCache.put(zipcode, forecastData);
        cacheQueue.add(zipcode);
    }

    // Method to get forecast data
    public ForecastData getForecastData(String zipcode) {
        if (forecastCache.containsKey(zipcode)) {
            return forecastCache.get(zipcode);
        }
        return null;
    }

    // Method to remove forecast data
    public void removeForecastData(String zipcode) {
        forecastCache.remove(zipcode);
        cacheQueue.remove(zipcode);
    }
}
