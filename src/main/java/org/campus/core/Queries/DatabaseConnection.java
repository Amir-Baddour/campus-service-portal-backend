package org.campus.core.Queries;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.campus.configurations.EnvConfig;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnection {
    private static HikariDataSource dataSource;

    public static synchronized void init() {
        // Only create the pool once. Calling init() again will safely do nothing.
        if (dataSource != null) {
            return;
        }

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(EnvConfig.DB_URL);       // e.g. jdbc:postgresql://localhost:5432/myapp
        config.setUsername(EnvConfig.DB_USER);
        config.setPassword(EnvConfig.DB_PASSWORD);

        // Beginner-friendly pool defaults. Tune these later if your app gets more traffic.
        config.setPoolName("CampusServicePortalPool");
        config.setMaximumPoolSize(10);             // At most 10 database connections open at once.
        config.setMinimumIdle(2);                  // Keep 2 connections ready for quick requests.
        config.setConnectionTimeout(30000);        // Wait up to 30 seconds for a free connection.
        config.setIdleTimeout(600000);             // Close extra idle connections after 10 minutes.
        config.setMaxLifetime(1800000);            // Recycle connections after 30 minutes.

        dataSource = new HikariDataSource(config);

        // Close the pool when the application stops.
        Runtime.getRuntime().addShutdownHook(new Thread(DatabaseConnection::close));
    }

    // Keep this method name and signature the same so DAO/service code does not need changes.
    public static Connection getConnection() throws SQLException {
        if (dataSource == null) {
            init();
        }

        // With HikariCP, closing this Connection returns it to the pool for reuse.
        return dataSource.getConnection();
    }

    public static synchronized void close() {
        if (dataSource != null) {
            dataSource.close();
            dataSource = null;
        }
    }
}
