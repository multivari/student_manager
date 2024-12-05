package com.student.manager.mapper;


import com.student.manager.domain.Attendance;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AttendanceMapper {

    // 获取所有考勤记录
    @Select("SELECT * FROM attendance")
    List<Attendance> findAll();

    // 根据考勤ID获取考勤记录
    @Select("SELECT * FROM attendance WHERE attendance_id = #{attendanceId}")
    Attendance findById(Long attendanceId);

    // 插入考勤记录
    @Insert("INSERT INTO attendance(student_id, course_id, date, status) VALUES(#{studentId}, #{courseId}, #{date}, #{status})")
    void insert(Attendance attendance);

    // 更新考勤记录
    @Update("UPDATE attendance SET student_id=#{studentId}, course_id=#{courseId}, date=#{date}, status=#{status} WHERE attendance_id=#{attendanceId}")
    void update(Attendance attendance);

    // 删除考勤记录
    @Delete("DELETE FROM attendance WHERE attendance_id = #{attendanceId}")
    void deleteById(Long attendanceId);
}
