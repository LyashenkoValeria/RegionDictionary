package org.lyashenko.regiondict.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
    private static final String URL = "jdbc:postgresql://localhost:5432/regiondict";
    private static final String USER_NAME = "postgres";
    private static final String PASSWORD = "admin";

    private ConnectionUtil() {}

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER_NAME, PASSWORD);
    }
}
