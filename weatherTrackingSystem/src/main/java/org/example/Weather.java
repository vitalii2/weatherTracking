package org.example;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Weather implements Comparable<Weather>{
    private LocalDateTime dateTime;
    private double temperature;
    private double humidity;
    private double windSpeed;

    public Weather(LocalDateTime time, double temperature, double humidity, double windSpeed) {
        this.dateTime = time;
        this.temperature = temperature;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
    }

    public Weather(String dateTime, Double temperature, Double humidity, Double windspeed) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.dateTime = LocalDateTime.parse(dateTime,dateTimeFormatter);
        this.temperature = temperature;
        this.humidity = humidity;
        this.windSpeed = windspeed;
    }
    public Weather(){}

    public Weather(Date dateTime, Double temperature, Double humidity, Double windspeed) {
//        this.dateTime = LocalDateTime.parse(dateTime.toString());
//        this.temperature = temperature;
//        this.humidity = humidity;
//        this.windSpeed = windspeed;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "dateTime=" + dateTime +
                ", temperature=" + temperature +
                ", humidity=" + humidity +
                ", windSpeed=" + windSpeed +
                "}\n";
    }

    @Override
    public int compareTo(Weather o) {
        if(this.temperature > o.temperature){
            return 1;
        } else if (this.temperature < o.temperature) {
            return -1;
        }else
            return 0;
    }
}
