package com.student.manager.service;

import com.student.manager.domain.Student;
import com.student.manager.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentMapper studentMapper;

    public List<Student> getAllStudents() {
        return studentMapper.selectAllStudents();
    }

    public Student getStudentById(Long id) {
        return studentMapper.selectStudentById(id);
    }

    public void addStudent(Student student) {
        studentMapper.insertStudent(student);
    }

    public void updateStudent( Student student) {
        studentMapper.updateStudent(student);
    }

    public void deleteStudent(Long id) {
        studentMapper.deleteStudent(id);
    }

    public List<Student> findByName(String username){ return studentMapper.findByName(username);}
}
