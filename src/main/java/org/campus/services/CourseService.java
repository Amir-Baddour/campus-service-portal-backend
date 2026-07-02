package org.campus.services;

import org.campus.core.Queries.DatabaseConnection;
import org.campus.models.Course;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseService {

    public List<Course> getAllCourses() throws SQLException {
        String sql = "SELECT id, title, instructor FROM courses ORDER BY id";
        List<Course> courses = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                courses.add(new Course(rs.getInt("id"), rs.getString("title"), rs.getString("instructor")));
            }
        }
        return courses;
    }

    public Course getCourseById(int id) throws SQLException {
        String sql = "SELECT id, title, instructor FROM courses WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Course(rs.getInt("id"), rs.getString("title"), rs.getString("instructor"));
                }
                return null;
            }
        }
    }

    public Course createCourse(String title, String instructor) throws SQLException {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Course title is required");
        }

        String sql = "INSERT INTO courses (title, instructor) VALUES (?, ?) RETURNING id";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, title);
            stmt.setString(2, instructor);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Course(rs.getInt("id"), title, instructor);
                }
                throw new SQLException("Insert failed, no ID returned.");
            }
        }
    }
}