package com.student.manager.security;

import com.student.manager.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/api/*")  // 需要保护的接口路径
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private JwtUtil jwtUtil;

    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = request.getHeader("Authorization");

        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);  // 移除 "Bearer " 前缀

            String username = jwtUtil.getUsernameFromToken(token);
            if (username != null && jwtUtil.validateToken(token, username)) {
                // 在请求上下文中设置用户信息
                request.setAttribute("username", username);
                request.setAttribute("role", jwtUtil.parseToken(token).get("role"));
            }
        }

        filterChain.doFilter(request, response);
    }
}
