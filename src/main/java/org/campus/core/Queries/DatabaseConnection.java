package org.campus.core.Queries;

import org.campus.configurations.EnvConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    public static Connection getConnection() throws SQLException {
        String url = EnvConfig.DB_URL;       // e.g. jdbc:postgresql://localhost:5432/myapp
        String user = EnvConfig.DB_USER;
        String password = EnvConfig.DB_PASSWORD;
        return DriverManager.getConnection(url, user, password);
    }
}