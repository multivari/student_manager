package com.student.manager.service;


import com.student.manager.domain.Instructor;
import com.student.manager.mapper.InstructorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructorService {

    @Autowired
    private InstructorMapper instructorMapper;

    public List<Instructor> getAllInstructors() {
        return instructorMapper.findAll();
    }

    public Instructor getInstructorById(Long instructorId) {
        return instructorMapper.findById(instructorId);
    }

    public void saveInstructor(Instructor instructor) {
        if (instructor.getInstructorId() == null) {
            instructorMapper.insert(instructor); // 插入新教师
        } else {
            instructorMapper.update(instructor); // 更新已有教师
        }
    }

    public void deleteInstructor(Long instructorId) {
        instructorMapper.deleteById(instructorId);
    }

    List<Instructor> findByName(String username){
        return instructorMapper.findByName(username);
    }
}
