package com.student.manager.service;


import com.student.manager.domain.Attendance;
import com.student.manager.mapper.AttendanceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceMapper attendanceMapper;

    // 获取所有考勤记录
    public List<Attendance> getAllAttendances() {
        return attendanceMapper.findAll();
    }

    // 根据考勤ID获取考勤记录
    public Attendance getAttendanceById(Long attendanceId) {
        return attendanceMapper.findById(attendanceId);
    }

    // 添加考勤记录
    public void addAttendance(Attendance attendance) {
        attendanceMapper.insert(attendance);
    }

    // 更新考勤记录
    public void updateAttendance(Attendance attendance) {
        attendanceMapper.update(attendance);
    }

    // 删除考勤记录
    public void deleteAttendance(Long attendanceId) {
        attendanceMapper.deleteById(attendanceId);
    }
}
