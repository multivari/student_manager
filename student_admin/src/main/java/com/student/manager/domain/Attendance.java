package com.student.manager.domain;

import java.util.Date;

public class Attendance {
    private Long attendanceId;  // 考勤ID
    private Long studentId;  // 学生ID
    private Long courseId;  // 课程ID
    private Date date;  // 考勤日期
    private String status;  // 考勤状态（出勤/缺席）

    // Getter 和 Setter
    public Long getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(Long attendanceId) {
        this.attendanceId = attendanceId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
