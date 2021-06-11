/*
 * Copyright (c) 2021 Ksuvot
 */

package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnect {
    private static String userName = "myuser";
    private static String password = "12345";
    private static String connectionUrl = "jdbc:postgresql://localhost:5432/hostel";
    private static Connection connection;

    public static Connection getConnect() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(connectionUrl, userName, password);
            System.out.println("[ You have successfully connected to the database! ]");
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return connection;
    }
}