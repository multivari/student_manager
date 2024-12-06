package com.student.manager.controller;

import com.student.manager.common.ApiResponse;
import com.student.manager.domain.LoginRequest;
import com.student.manager.domain.User;
import com.student.manager.service.LoginService;
import com.student.manager.util.JwtUtil;  // 新增JWT工具类
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private JwtUtil jwtUtil;  // 注入JWT工具类

    @PostMapping
    public ApiResponse<?> login(@RequestBody LoginRequest loginRequest) {
        boolean isValid = false;
        String role = loginRequest.getRole();
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        // 根据角色验证用户身份
        switch (role) {
            case "student":
                isValid = loginService.validateStudentLogin(username, password);
                break;
            case "instructor":
                isValid = loginService.validateInstructorLogin(username, password);
                break;
            case "admin":
                isValid = loginService.validateAdminLogin(username, password);
                break;
            default:
                return ApiResponse.error("Invalid role");
        }

        if (isValid) {
            // 生成 JWT Token
            String token = jwtUtil.generateToken(username, role);
            User user = new User();
            user.setUsername(username);
            user.setRole(role);
            // 返回成功响应，包含数据和 Token
            return ApiResponse.successWithToken(user, token); // 如果需要，可以在 data 中返回其他信息
        } else {
            return ApiResponse.error("Invalid username or password");
        }
    }

}
