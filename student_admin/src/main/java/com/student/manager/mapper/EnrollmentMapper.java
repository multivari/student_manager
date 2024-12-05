package com.student.manager.mapper;


import com.student.manager.domain.Enrollment;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EnrollmentMapper {

    List<Enrollment> findAll();

    Enrollment findById(@Param("id") Long enrollmentId);

    void insert(Enrollment enrollment);


    void update(Enrollment enrollment);

    void deleteById(@Param("id") Long enrollmentId);
}
