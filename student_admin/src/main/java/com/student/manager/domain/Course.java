package com.student.manager.domain;



import java.io.Serializable;


public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer courseId;       // 课程ID
    private String courseName;      // 课程名称
    private Integer credits;        // 学分
    private Integer instructorId;   // 教师ID

    private String instructorName; //教师名字

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    public Integer getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(Integer instructorId) {
        this.instructorId = instructorId;
    }
}
