package com.student.manager.mapper;


import com.student.manager.domain.Instructor;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface InstructorMapper {

    List<Instructor> findAll();

    Instructor findById(Long instructorId);

    void insert(Instructor instructor);

    void update(Instructor instructor);

    void deleteById(Long instructorId);
}
