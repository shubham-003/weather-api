package com.example.weatherapi.model;

public class ForecastData {
    private double currentTemperature;
    private double lowTemperature;
    private double highTemperature;
    private long timestamp;

    public ForecastData(double currentTemperature, double lowTemperature, double highTemperature) {
        this.currentTemperature = currentTemperature;
        this.lowTemperature = lowTemperature;
        this.highTemperature = highTemperature;
        this.timestamp = System.currentTimeMillis();
    }

    public boolean isFresh() {
        return (System.currentTimeMillis() - timestamp) < (30 * 60 * 1000); // 30 minutes
    }

    public double getCurrentTemperature() {
        return currentTemperature;
    }

    public double getLowTemperature() {
        return lowTemperature;
    }

    public double getHighTemperature() {
        return highTemperature;
    }
}

