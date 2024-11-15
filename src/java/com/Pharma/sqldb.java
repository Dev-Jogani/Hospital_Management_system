package com.Pharma;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 *
 * @author DELL
 */
public class sqldb {

    static Connection conn; 
    static Statement st; 

    public static void connect() {
        String url = "jdbc:mysql://localhost:3306/";
        String driver = "com.mysql.cj.jdbc.Driver";
        String dbName = "hospital";
        String userName = "root";
        String password = "";

        try {
            Class.forName(driver); 
            conn = DriverManager.getConnection(url + dbName, userName, password);
            System.out.println("Connection Established");
            st = conn.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void connclose() {
        try {
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int iud_data(String str)
    {
        int r = 0;
        try {
            r = st.executeUpdate(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return r;
    }

    public static ResultSet fetchdata(String str) throws SQLException // Used for select query
    {
        return st.executeQuery(str);
    }
}