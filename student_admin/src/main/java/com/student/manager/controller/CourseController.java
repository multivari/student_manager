package com.student.manager.controller;


import com.student.manager.domain.Course;
import com.student.manager.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    // 获取所有课程
    @GetMapping
    public List<Course> getAllCourses() {
        return courseService.findAll();
    }

    // 根据ID获取课程
    @GetMapping("/{id}")
    public Course getCourseById(@PathVariable("id") Integer courseId) {
        return courseService.findById(courseId);
    }

    // 添加课程
    @PostMapping
    public void addCourse(@RequestBody Course course) {
        courseService.insert(course);
    }

    // 更新课程
    @PutMapping("/{id}")
    public void updateCourse(@PathVariable("id") Integer courseId, @RequestBody Course course) {
        course.setCourseId(courseId);
        courseService.update(course);
    }

    // 删除课程
    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable("id") Integer courseId) {
        courseService.delete(courseId);
    }
}
