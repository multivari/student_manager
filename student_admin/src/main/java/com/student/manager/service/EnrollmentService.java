package com.student.manager.service;


import com.student.manager.domain.Enrollment;
import com.student.manager.mapper.EnrollmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentService {

    @Autowired
    private EnrollmentMapper enrollmentMapper;

    /**
     * 获取所有选课记录
     * 
     * @return 选课记录列表
     */
    public List<Enrollment> getAllEnrollments() {
        return enrollmentMapper.findAll();
    }

    /**
     * 根据选课ID获取选课记录
     * 
     * @param enrollmentId 选课ID
     * @return 选课记录
     */
    public Enrollment getEnrollmentById(Long enrollmentId) {
        return enrollmentMapper.findById(enrollmentId);
    }

    /**
     * 添加选课记录
     * 
     * @param enrollment 选课记录对象
     */
    public void addEnrollment(Enrollment enrollment) {
        enrollmentMapper.insert(enrollment);
    }

    /**
     * 更新选课记录
     * 
     * @param enrollment 选课记录对象
     */
    public void updateEnrollment(Enrollment enrollment) {
        enrollmentMapper.update(enrollment);
    }

    /**
     * 删除选课记录
     * 
     * @param enrollmentId 选课ID
     */
    public void deleteEnrollment(Long enrollmentId) {
        enrollmentMapper.deleteById(enrollmentId);
    }
}
