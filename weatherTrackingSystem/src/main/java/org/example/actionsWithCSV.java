package org.example;

import java.io.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class actionsWithCSV {
    private static String INSERT_WEATHER = "INSERT INTO weather(datetime,temperature,humidity,windspeed) VALUES(?,?,?,?)";
    public static void toCSV(LocalDateTime t, Double temperature, Double humidity, Double windspeed){
        File file = new File("temperature.csv");
        while (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        try(PrintWriter pw = new PrintWriter(file);) {
          String s = "Date and time: " + t + " temperature: " + temperature + " humidity: " + humidity + " windspeed: " + windspeed + "\n";
            pw.println();
        }catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

    public static void fromDBtoCSV(List<Weather> weathers){
       File file = new File("temperatureFromDB.csv");
       while (!file.exists()){
           try {
               file.createNewFile();
           } catch (IOException e) {
               System.out.println(e.getMessage());
           }
       }
       try(PrintWriter pw = new PrintWriter(file);){
           pw.println(weathers);
       }catch (FileNotFoundException e){
           System.out.println(e.getMessage());
       }
    }
    public static void fromCSVtoDB(String s){
        Date date;
        File file = new File(s);
        LocalDateTime dateTime = null;
        Double temp = 0.0;
        Double humidity = 0.0;
        Double windSpeed = 0.0;
        List<Weather> weathers = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String wholeLine = "";
            String line;
            while ((line = br.readLine()) != null){
                wholeLine += line;
//                System.out.println(wholeLine);
            }
            Pattern p = Pattern.compile("dateTime=[0-9]{4}-[0-9]{2}-[0-9]{2}T[0-9]{2}:[0-9]{2}:[0-9]{2}");
            Matcher m = p.matcher(wholeLine);
            Pattern p2 = Pattern.compile("temperature=[0-9]{0,2}.[0-9]{0,2}");
            Matcher m2 = p2.matcher(wholeLine);
            Pattern p3 = Pattern.compile("humidity=[0-9]{2}.[0-9]{0,2}");
            Matcher m3 = p3.matcher(wholeLine);
            Pattern p4 = Pattern.compile("windSpeed=[0-9]{0,2}.[0-9]{0,2}");
            Matcher m4 = p4.matcher(wholeLine);

            while (m.find() && m2.find() && m3.find() && m4.find()){
               DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
               dateTime = LocalDateTime.parse(m.group().split("=")[1],dateTimeFormatter);
               temp = Double.parseDouble(m2.group().split("=")[1]);
               humidity = Double.parseDouble(m3.group().split("=")[1]);
               windSpeed = Double.parseDouble(m4.group().split("=")[1]);
                try(Connection connection = DBUtils.getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement(INSERT_WEATHER);){
                    preparedStatement.setTimestamp(1, Timestamp.valueOf(dateTime));
                    preparedStatement.setDouble(2,temp);
                    preparedStatement.setDouble(3,humidity);
                    preparedStatement.setDouble(4,windSpeed);
                    preparedStatement.executeUpdate();
                    PreparedStatement allweather = connection.prepareStatement("SELECT * FROM weather");
                    ResultSet rs = allweather.executeQuery();
                    while (rs.next()){
                        date = rs.getDate("datetime");
                        Double temperature = rs.getDouble("temperature");
                        Double hum = rs.getDouble("humidity");
                        Double wind = rs.getDouble("windspeed");
                        weathers.add(new Weather(date,temperature,hum,wind));
                    }
                }catch (SQLException e){
                    System.out.println(e.getMessage());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
