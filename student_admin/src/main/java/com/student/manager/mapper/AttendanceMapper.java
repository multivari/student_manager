package com.student.manager.mapper;


import com.student.manager.domain.Attendance;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AttendanceMapper {

    // 获取所有考勤记录
    List<Attendance> findAll();

    // 根据考勤ID获取考勤记录

    Attendance findById(Long attendanceId);

    // 插入考勤记录

    void insert(Attendance attendance);

    // 更新考勤记录

    void update(Attendance attendance);

    // 删除考勤记录

    void deleteById(Long attendanceId);
}
