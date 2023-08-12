package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {
    private static final String url = "jdbc:mysql://localhost:3306/weatherarchive";
    private static final String username = "root";
    private static final String passwor = "vit@l26041993";
    public static Connection getConnection(){
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(url,username,passwor);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return connection;
    }
}
