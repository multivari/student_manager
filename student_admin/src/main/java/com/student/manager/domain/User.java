package com.student.manager.domain;




public class User {
    private Long userId;
    private String username;
    private String password; // 加密后的密码
    private String role; // 角色字段 (学生: "STUDENT", 教师: "TEACHER", 管理员: "ADMIN")

    // Getters and Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
