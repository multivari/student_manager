package com.student.manager.controller;


import com.student.manager.domain.Enrollment;
import com.student.manager.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    /**
     * 获取所有选课记录
     * 
     * @return 选课记录列表
     */
    @GetMapping
    public List<Enrollment> getAllEnrollments() {
        return enrollmentService.getAllEnrollments();
    }

    /**
     * 根据选课ID获取选课记录
     * 
     * @param enrollmentId 选课ID
     * @return 选课记录
     */
    @GetMapping("/{enrollmentId}")
    public Enrollment getEnrollmentById(@PathVariable Long enrollmentId) {
        return enrollmentService.getEnrollmentById(enrollmentId);
    }

    /**
     * 添加选课记录
     * 
     * @param enrollment 选课记录对象
     */
    @PostMapping
    public void addEnrollment(@RequestBody Enrollment enrollment) {
        enrollmentService.addEnrollment(enrollment);
    }

    /**
     * 更新选课记录
     * 
     * @param enrollment 选课记录对象
     */
    @PutMapping("/{enrollmentId}")
    public void updateEnrollment(@PathVariable Long enrollmentId, @RequestBody Enrollment enrollment) {
        enrollment.setEnrollmentId(enrollmentId);
        enrollmentService.updateEnrollment(enrollment);
    }

    /**
     * 删除选课记录
     * 
     * @param enrollmentId 选课ID
     */
    @DeleteMapping("/{enrollmentId}")
    public void deleteEnrollment(@PathVariable Long enrollmentId) {
        enrollmentService.deleteEnrollment(enrollmentId);
    }
}
