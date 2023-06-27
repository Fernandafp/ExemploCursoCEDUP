package com.example.javafxexemplo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectioSingleton {

    private static Connection connection;

    public static Connection getConnection() throws SQLException {

        if (connection == null) {
            connection = DriverManager.getConnection( //
                    "jdbc:mysql://localhost:3306/javafxcedup2023",//
                    "root",
                    "");
        }
        return connection;
    }
}