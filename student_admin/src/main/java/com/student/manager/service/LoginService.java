package com.student.manager.service;

import com.student.manager.domain.Instructor;
import com.student.manager.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginService {

    @Autowired
    private StudentService studentRepository;

    @Autowired
    private InstructorService instructorRepository;

    public boolean validateStudentLogin(String username, String password) {
        List<Student> student = studentRepository.findByName(username);
        if (!student.isEmpty() && student.size()>0 && password.equals("111111")) {
            return true;
        }
        return false;
    }

    public boolean validateInstructorLogin(String username, String password) {
        List<Instructor> instructor = instructorRepository.findByName(username);
        if (!instructor.isEmpty() && instructor.size()>0  && password.equals("666666")) {
            return true;
        }
        return false;
    }

    public boolean validateAdminLogin(String username, String password) {
        // 固定的管理员用户名和密码
        return "admin".equals(username) && "admin123".equals(password);
    }
}
