package org.campus.api;

import com.google.gson.Gson;
import org.campus.models.Course;
import org.campus.models.Student;
import org.campus.response.ApiResponse;
import org.campus.services.CourseService;
import org.campus.services.StudentService;

import static spark.Spark.post;

public class POST {
    private static final StudentService studentService = new StudentService();
    private static final CourseService courseService = new CourseService();
    private static final Gson gson = new Gson();

    public static void run() {
        post("/students", (req, res) -> {
            res.type("application/json");
            try {
                Student input = gson.fromJson(req.body(), Student.class);
                Student created = studentService.createStudent(input.getName(), input.getEmail());
                res.status(201);
                return gson.toJson(ApiResponse.success(created));
            } catch (IllegalArgumentException e) {
                res.status(400);
                return gson.toJson(ApiResponse.error(e.getMessage()));
            } catch (Exception e) {
                res.status(500);
                return gson.toJson(ApiResponse.error("Failed to create student"));
            }
        });

        post("/courses", (req, res) -> {
            res.type("application/json");
            try {
                Course input = gson.fromJson(req.body(), Course.class);
                Course created = courseService.createCourse(input.getTitle(), input.getInstructor());
                res.status(201);
                return gson.toJson(ApiResponse.success(created));
            } catch (IllegalArgumentException e) {
                res.status(400);
                return gson.toJson(ApiResponse.error(e.getMessage()));
            } catch (Exception e) {
                res.status(500);
                return gson.toJson(ApiResponse.error("Failed to create course"));
            }
        });
    }
}