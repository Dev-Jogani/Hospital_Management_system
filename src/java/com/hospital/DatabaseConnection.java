package com.hospital;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/hospital"; // Update with your DB details
        String username = "root"; // DB username
        String password = ""; // DB password
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Ensure the driver is loaded
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return DriverManager.getConnection(url, username, password);
    }
}
