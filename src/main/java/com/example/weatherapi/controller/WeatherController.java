package com.example.weatherapi.controller;

import com.example.weatherapi.model.WeatherResponse;
import com.example.weatherapi.service.WeatherService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/weather")
    public ResponseEntity<Object> getWeatherForecast(@RequestParam(required = true) String zipcode, @RequestParam(required = false) String country) {
        if (zipcode == null) {
            return ResponseEntity.badRequest().body("Zipcode parameter is required.");
        }
        
        try {
            WeatherResponse weatherResponse = weatherService.getWeatherForecast(zipcode, country);
            if (weatherResponse != null) {
                return ResponseEntity.ok(weatherResponse);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to fetch weather data.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to fetch weather data: " + e.getMessage());
        }
    }
}
