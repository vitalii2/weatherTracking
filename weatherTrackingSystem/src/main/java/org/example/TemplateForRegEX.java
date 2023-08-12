package org.example;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TemplateForRegEX {
    public static Double getTemperature(String s){
        Double d = 0.0;
        Pattern p = Pattern.compile("\"temp\":\\d{1,}.\\d{1,}");
        Matcher m = p.matcher(s);
        while (m.find()){
            d = Double.parseDouble(m.group().split("\"temp\":")[1]) - 273.15;
        }
        return (double)Math.round(d * 100) / 100;
    }
    public static Double getHumidity(String s){
        Double d = 0.0;
        Pattern p = Pattern.compile("\"humidity\":\\d{1,}.\\d{1,}|\"humidity\":\\d{1,}");
        Matcher m = p.matcher(s);
        while (m.find()){
            d = Double.parseDouble(m.group().split("\"humidity\":")[1]);
        }
        return (double)Math.round(d * 100) / 100;
    }
    public static Double getWindspeed(String s){
        Double d = 0.0;
        Pattern p = Pattern.compile("\"speed\":\\d{1,}.\\d{1,}");
        Matcher m = p.matcher(s);
        while (m.find()){
            d = Double.parseDouble(m.group().split("\"speed\":")[1]);
        }
        return (double)Math.round(d * 100) / 100;
    }
}
