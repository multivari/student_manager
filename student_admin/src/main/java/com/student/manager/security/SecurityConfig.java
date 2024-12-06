package com.student.manager.security;

import com.student.manager.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtUtil jwtUtil;  // 注入 JwtUtil

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // 创建 JwtAuthenticationFilter 实例并传入 JwtUtil
        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(jwtUtil);


            http
                    .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                    .authorizeRequests()
                    .antMatchers("/api/**").permitAll()  // 允许未认证用户访问 login 接口
                    .anyRequest().authenticated()  // 其他接口需要认证
                    .and()
                    .csrf().disable();  // 如果不使用 CSRF，可以禁用



        // 添加到过滤器链中
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
