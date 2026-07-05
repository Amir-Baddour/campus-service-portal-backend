package org.campus.api;

import com.google.gson.Gson;
import org.campus.models.Course;
import org.campus.models.Student;
import org.campus.response.ApiResponse;
import org.campus.services.CourseService;
import org.campus.services.StudentService;

import static spark.Spark.get;

public class GET {
    private static final StudentService studentService = new StudentService();
    private static final CourseService courseService = new CourseService();
    private static final Gson gson = new Gson();

    public static void run() {
        get("/health", (req, res) -> {
            res.type("application/json");
            return gson.toJson(ApiResponse.success("Campus Service Portal is running"));
        });

        get("/students", (req, res) -> {
            res.type("application/json");
            try {
                return gson.toJson(ApiResponse.success(studentService.getAllStudents()));
            } catch (Exception e) {
                res.status(500);
                return gson.toJson(ApiResponse.error("Failed to fetch students"));
            }
        });

        get("/students/:id", (req, res) -> {
            res.type("application/json");
            try {
                int id = Integer.parseInt(req.params("id"));
                Student student = studentService.getStudentById(id);
                if (student == null) {
                    res.status(404);
                    return gson.toJson(ApiResponse.error("Student not found"));
                }
                return gson.toJson(ApiResponse.success(student));
            } catch (NumberFormatException e) {
                res.status(400);
                return gson.toJson(ApiResponse.error("Invalid student id"));
            } catch (Exception e) {
                res.status(500);
                return gson.toJson(ApiResponse.error("Failed to fetch student"));
            }
        });

        get("/courses", (req, res) -> {
            res.type("application/json");
            try {
                return gson.toJson(ApiResponse.success(courseService.getAllCourses()));
            } catch (Exception e) {
                res.status(500);
                return gson.toJson(ApiResponse.error("Failed to fetch courses"));
            }
        });

        get("/courses/:id", (req, res) -> {
            res.type("application/json");
            try {
                int id = Integer.parseInt(req.params("id"));
                Course course = courseService.getCourseById(id);
                if (course == null) {
                    res.status(404);
                    return gson.toJson(ApiResponse.error("Course not found"));
                }
                return gson.toJson(ApiResponse.success(course));
            } catch (NumberFormatException e) {
                res.status(400);
                return gson.toJson(ApiResponse.error("Invalid course id"));
            } catch (Exception e) {
                res.status(500);
                return gson.toJson(ApiResponse.error("Failed to fetch course"));
            }
        });
    }
}