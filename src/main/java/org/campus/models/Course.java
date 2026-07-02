package org.campus.models;

public class Course {
    private int id;
    private String title;
    private String instructor;

    public Course() {}

    public Course(int id, String title, String instructor) {
        this.id = id;
        this.title = title;
        this.instructor = instructor;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getInstructor() { return instructor; }

    public void setId(int id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setInstructor(String instructor) { this.instructor = instructor; }
}
