package org.campus.core.Queries;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitialization {

    private static final String initializeStudentsTable = """
        CREATE TABLE IF NOT EXISTS "students" (
          "id" SERIAL PRIMARY KEY,
          "name" varchar(100) NOT NULL,
          "email" varchar(100) UNIQUE NOT NULL,
          "created_at" TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP
        );
        """;

    private static final String initializeCoursesTable = """
        CREATE TABLE IF NOT EXISTS "courses" (
          "id" SERIAL PRIMARY KEY,
          "title" varchar(150) NOT NULL,
          "created_at" TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP
        );
        """;

    static final String[] initializationQueue = {
            initializeStudentsTable,
            initializeCoursesTable
    };

    public static void run() {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            for (String sql : initializationQueue) {
                stmt.execute(sql);
            }
            System.out.println("Database schema verified/initialized.");

        } catch (SQLException e) {
            throw new RuntimeException("Failed to initialize database schema", e);
        }
    }
}