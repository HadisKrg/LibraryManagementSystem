package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
        private static final String url = "jdbc:postgresql://localhost:5432/schema";
        private static final String username = "postgres";
        private static final String password = "4938";

    public static Connection getConnection() throws SQLException {
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }


}
