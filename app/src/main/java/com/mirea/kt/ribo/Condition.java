package com.mirea.kt.ribo;

public class Condition {
    private String time;
    private String temperature;
    private String humidity;
    private  double precipitation;
    private double cloud;
    private String windSpeed;
    private double snowfall;

    public Condition(String time, String temperature, String humidity, double precipitation, double cloud, String windSpeed, double snowfall) {
        this.time = time;
        this.temperature = temperature;
        this.humidity = humidity;
        this.precipitation = precipitation;
        this.cloud = cloud;
        this.windSpeed = windSpeed;
        this.snowfall = snowfall;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public double getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(double precipitation) {
        this.precipitation = precipitation;
    }

    public double getCloud() {
        return cloud;
    }

    public void setCloud(double cloud) {
        this.cloud = cloud;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public double getSnowfall() {
        return snowfall;
    }

    public void setSnowfall(double snowfall) {
        this.snowfall = snowfall;
    }
}
