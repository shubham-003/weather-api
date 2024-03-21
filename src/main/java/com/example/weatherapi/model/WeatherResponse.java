package com.example.weatherapi.model;

public class WeatherResponse {
    private ForecastData forecast;
    private boolean fromCache;

    public WeatherResponse(ForecastData forecast, boolean fromCache) {
        this.forecast = forecast;
        this.fromCache = fromCache;
    }

    public ForecastData getForecast() {
        return forecast;
    }

    public void setForecast(ForecastData forecast) {
        this.forecast = forecast;
    }

    public boolean isFromCache() {
        return fromCache;
    }

    public void setFromCache(boolean fromCache) {
        this.fromCache = fromCache;
    }
}

