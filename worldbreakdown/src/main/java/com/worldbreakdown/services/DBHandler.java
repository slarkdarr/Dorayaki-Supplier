package com.worldbreakdown.services;

import java.sql.*;

public class DBHandler {
    private Connection connection;
    private Statement statement;

    private static String DB_URL = "jdbc:mysql://localhost/doraemon";
    private static String DB_USERNAME = "root";
    private static String DB_PASSWORD = "";

    public DBHandler(){
        this.init();
    }

    public void init(){
        try {
            System.out.println("Menghubungkan ke dalam server");
            Class.forName("com.mysql.jdbc.Driver");
            this.connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            System.out.println("Terubung ke dalam local database "+DB_URL+" dengan username"+DB_USERNAME);
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error ketika mencoba menghubungkan data");
        }
    }

    public Connection getConnection(){return this.connection;}

}
