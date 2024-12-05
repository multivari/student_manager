package com.student.manager.controller;


import com.student.manager.domain.Instructor;
import com.student.manager.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/instructors")
public class InstructorController {

    @Autowired
    private InstructorService instructorService;

    // 获取所有教师
    @GetMapping
    public List<Instructor> getAllInstructors() {
        List<Instructor> allInstructors = instructorService.getAllInstructors();
        return allInstructors;
    }

    // 获取单个教师
    @GetMapping("/{id}")
    public ResponseEntity<Instructor> getInstructorById(@PathVariable Long id) {
        Instructor instructor = instructorService.getInstructorById(id);
        return instructor != null ? ResponseEntity.ok(instructor) : ResponseEntity.notFound().build();
    }

    // 添加教师
    @PostMapping
    public ResponseEntity<Instructor> addInstructor(@RequestBody Instructor instructor) {
        instructorService.saveInstructor(instructor);
        return ResponseEntity.status(201).body(instructor);
    }

    // 编辑教师
    @PutMapping("/{id}")
    public ResponseEntity<Instructor> updateInstructor(@PathVariable Long id, @RequestBody Instructor instructor) {
        instructor.setInstructorId(id);
        instructorService.saveInstructor(instructor);
        return ResponseEntity.ok(instructor);
    }

    // 删除教师
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInstructor(@PathVariable Long id) {
        instructorService.deleteInstructor(id);
        return ResponseEntity.noContent().build();
    }
}
