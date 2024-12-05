package com.student.manager.controller;

import com.student.manager.domain.Attendance;
import com.student.manager.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attendances")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    // 获取所有考勤记录
    @GetMapping
    public List<Attendance> getAllAttendances() {
        return attendanceService.getAllAttendances();
    }

    // 根据考勤ID获取考勤记录
    @GetMapping("/{attendanceId}")
    public Attendance getAttendanceById(@PathVariable Long attendanceId) {
        return attendanceService.getAttendanceById(attendanceId);
    }

    // 添加考勤记录
    @PostMapping
    public void addAttendance(@RequestBody Attendance attendance) {
        attendanceService.addAttendance(attendance);
    }

    // 更新考勤记录
    @PutMapping("/{attendanceId}")
    public void updateAttendance(@PathVariable Long attendanceId, @RequestBody Attendance attendance) {
        attendance.setAttendanceId(attendanceId);
        attendanceService.updateAttendance(attendance);
    }

    // 删除考勤记录
    @DeleteMapping("/{attendanceId}")
    public void deleteAttendance(@PathVariable Long attendanceId) {
        attendanceService.deleteAttendance(attendanceId);
    }
}
