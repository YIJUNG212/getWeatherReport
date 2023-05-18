package com.example.json_0325_gson;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherInfo {
    @SerializedName("weather")
    public List<Weather> weatherList;

    @SerializedName("main")
    public Main mainInfo;

    public List<Weather> getWeather() {
        return weatherList;
    }

    public Main getMain() {
        return mainInfo;
    }

    public static class Weather {
        @SerializedName("description")
        private String description;

        public String getDescription() {
            return description;
        }
    }

    public static class Main {
        @SerializedName("temp")
        private double temperature;

        public double getTemp() {
            return temperature;
        }
    }
}
