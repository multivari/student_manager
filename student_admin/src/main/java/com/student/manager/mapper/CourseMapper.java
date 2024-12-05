package com.student.manager.mapper;


import com.student.manager.domain.Course;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CourseMapper {

    // 查询所有课程
    List<Course> findAll();

    // 根据课程ID查询课程
    Course findById(@Param("courseId") Integer courseId);

    // 插入新的课程
    void insert(Course course);

    // 更新课程信息
    void update(Course course);

    // 删除课程
    void delete(@Param("courseId") Integer courseId);
}
