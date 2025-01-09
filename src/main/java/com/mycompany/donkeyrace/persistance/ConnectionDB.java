
package com.mycompany.donkeyrace.persistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    
    private static final String URL = "jdbc:mysql://localhost:3306/donkey_race";
    private static final String USER = "root";
    private static final String PASSWORD = "7328";
    public static Connection conexion = null;

    public static Connection getConnection() {
        try {
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            if(conexion != null) {
                System.out.println("Conexion exitosa");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conexion;
    }


}
