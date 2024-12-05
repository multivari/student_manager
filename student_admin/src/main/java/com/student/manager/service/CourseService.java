package com.student.manager.service;



import com.student.manager.domain.Course;

import java.util.List;

public interface CourseService {

    List<Course> findAll();

    Course findById(Integer courseId);

    void insert(Course course);

    void update(Course course);

    void delete(Integer courseId);
}
