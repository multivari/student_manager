package com.student.manager.service.Impl;


import com.student.manager.domain.Course;
import com.student.manager.mapper.CourseMapper;
import com.student.manager.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public List<Course> findAll() {
        return courseMapper.findAll();
    }

    @Override
    public Course findById(Integer courseId) {
        return courseMapper.findById(courseId);
    }

    @Override
    public void insert(Course course) {
        courseMapper.insert(course);
    }

    @Override
    public void update(Course course) {
        courseMapper.update(course);
    }

    @Override
    public void delete(Integer courseId) {
        courseMapper.delete(courseId);
    }
}
