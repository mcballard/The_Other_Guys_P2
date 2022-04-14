package com.businessName.ticketDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionObject {
    public static Connection createConnection() {
        try {
            Connection dbConnection =
                    DriverManager.getConnection(String.format(
                            "jdbc:postgresql://%s:%s/%s?user=%s&password=%s",
                            System.getenv("HOST"),
                            System.getenv("PORT"),
                            System.getenv("DBNAME"),
                            System.getenv("DBUSER"),
                            System.getenv("DBPASS")
                    ));
            return dbConnection;
        } catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        System.out.println(createConnection());
    }
}