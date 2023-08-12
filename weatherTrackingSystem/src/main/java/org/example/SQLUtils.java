package org.example;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SQLUtils {
    private static String INSERT_WEATHER = "INSERT INTO weather(datetime,temperature,humidity,windspeed)VALUES(?,?,?,?)";
    public static List<Weather> saveWeather(Weather weather){
        Date dateTime = null;
        List<Weather> weathers = new ArrayList<>();
        try(Connection connection = DBUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_WEATHER)) {
            preparedStatement.setTimestamp(1, Timestamp.valueOf(weather.getDateTime()));
            preparedStatement.setDouble(2,weather.getTemperature());
            preparedStatement.setDouble(3,weather.getHumidity());
            preparedStatement.setDouble(4,weather.getWindSpeed());
            preparedStatement.executeUpdate();
            PreparedStatement allweather = connection.prepareStatement("SELECT * FROM weather");
            ResultSet res = allweather.executeQuery();
            while (res.next()){
                dateTime = res.getDate("datetime");
                Double temperature = res.getDouble("temperature");
                Double humidity = res.getDouble("humidity");
                Double windspeed = res.getDouble("windspeed");
                weathers.add(new Weather(dateTime,temperature,humidity,windspeed));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return weathers;
    }
    public static List<Weather> getData(String query){
        List<Weather> weathers = new ArrayList<>();
        try(Connection connection = DBUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                String dateTime = rs.getString("datetime");
                Double temperature = rs.getDouble("temperature");
                Double humidity = rs.getDouble("humidity");
                Double windspeed = rs.getDouble("windspeed");
                weathers.add(new Weather(dateTime,temperature,humidity,windspeed));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return weathers;
    }

}
