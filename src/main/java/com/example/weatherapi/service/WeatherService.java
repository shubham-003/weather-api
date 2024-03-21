package com.example.weatherapi.service;

import com.example.weatherapi.constants.CommonConstants;
import com.example.weatherapi.model.ForecastData;
import com.example.weatherapi.model.WeatherResponse;
import com.example.weatherapi.strategy.caching.cachingStrategy;
import com.example.weatherapi.strategy.caching.fifoStrategy;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.io.IOException;

@Service
public class WeatherService {

    // Caching strategy
    private cachingStrategy cachedForecastStrategy = new fifoStrategy();

    public void setCachingStrategy(cachingStrategy cachingStrategy) {
        this.cachedForecastStrategy = cachingStrategy;
    }

    public WeatherResponse getWeatherForecast(String zipcode, String country) {
        ForecastData cachedForecast = cachedForecastStrategy.getForecastData(zipcode);

        // Check if forecast for the given zip code is cached
        if (cachedForecast != null) {
            // Check if the cached forecast is still valid (within 30 minutes)
            if (cachedForecast.isFresh()) {
                return new WeatherResponse(cachedForecast, true);
            } else {
                // If cache has expired, remove it from cache and queue
                cachedForecastStrategy.removeForecastData(zipcode);
            }
        }

        // Fetch forecast data from external API
        ForecastData forecastTemperature = fetchWeatherDataFromExternalAPI(zipcode, country);

        if (forecastTemperature != null) {
            // Cache the forecast data & add zipcode to the queue
            cachedForecastStrategy.addForecastData(zipcode, forecastTemperature);
            return new WeatherResponse(forecastTemperature, false);
        } else {
            return null;
        }
    }

    // Method to fetch weather data from the OpenWeatherMap API
    public ForecastData fetchWeatherDataFromExternalAPI(String zipcode, String country) {
        String apiKey = CommonConstants.API_KEY;
        // String country = CommonConstants.COUNTRY;
        String apiUrl;
        if(country != null)
            apiUrl = String.format(CommonConstants.API_URL, zipcode, country, apiKey);
        else    
            apiUrl = String.format(CommonConstants.DEFAULT_API_URL, zipcode, apiKey);

        RestTemplate restTemplate = new RestTemplate();
        String jsonResponse = restTemplate.getForObject(apiUrl, String.class);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(jsonResponse);
            double currentTemperature = root.path("main").path("temp").asDouble();
            double lowTemperature = root.path("main").path("temp_min").asDouble();
            double highTemperature = root.path("main").path("temp_max").asDouble();
            return new ForecastData(currentTemperature, lowTemperature, highTemperature);
        } catch (IOException e) {
            e.printStackTrace();
            return null; // Return error code if unable to fetch weather data
        }
    }
}

