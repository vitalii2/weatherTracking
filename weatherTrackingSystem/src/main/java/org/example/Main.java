package org.example;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
//    public final static String REQUEST = "https://api.openweathermap.org/data/2.5/weather?lat=32.794044&lon=34.989571&appid=eb98b07832d7b6338715ba348f8d658c";
    public final static String REQUEST = "https://api.openweathermap.org/data/2.5/weather?lat=40.730610&lon=-73.935242&appid=eb98b07832d7b6338715ba348f8d658c";

    public static void main(String[] args) {
        Scanner scr = new Scanner(System.in);
        TemplateForRegEX template = new TemplateForRegEX();
        List<Weather> weathers = new ArrayList<>();
        LocalDateTime date = LocalDateTime.now();
        System.out.println(date);
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                APIRequest request = new APIRequest();
                String a = request.getWeatherData(REQUEST);
                System.out.println(a);
//                System.out.println(template.getTemperature(a));
//                System.out.println(template.getHumidity(a));
//                System.out.println(template.getWindspeed(a));
//                System.out.println("Request weather");
                Weather weather = new Weather();
                weather.setDateTime(date);
                weather.setTemperature(template.getTemperature(a));
                weather.setHumidity(template.getHumidity(a));
                weather.setWindSpeed(template.getWindspeed(a));
                SQLUtils.saveWeather(weather);
                weathers.add(weather);
//                System.out.println(weather);
//                actionsWithCSV.toCSV(date,template.getTemperature(a),template.getHumidity(a),template.getWindspeed(a));
                List<Weather> w = SQLUtils.getData("SELECT * FROM weather");
//                System.out.println(w);
//                actionsWithCSV.fromDBtoCSV(w);
//                System.out.println("Enter file name");
//                String s1 = scr.nextLine();
//                actionsWithCSV.fromCSVtoDB(seekingFile.seek(s1));
//                System.out.println(seekingFile.seek(s1));
//                reportPDF.createReport(w.toString());
                w = w.stream().filter(e -> e.getTemperature() > 29).collect(Collectors.toList());
                System.out.println(w);
            }
        };
        timer.schedule(timerTask,0,6 * 60 * 60 * 1000);

    }
}