package org.campus.services;

import org.campus.dao.StudentDAO;
import org.campus.models.Student;

import java.sql.SQLException;
import java.util.List;

public class StudentService {
    private final StudentDAO studentDAO = new StudentDAO();

    public List<Student> getAllStudents() throws SQLException {
        return studentDAO.findAll();
    }

    public Student getStudentById(int id) throws SQLException {
        return studentDAO.findById(id);
    }

    public Student createStudent(String name, String email) throws SQLException {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name is required");
        }
        return studentDAO.insert(name, email);
    }
}