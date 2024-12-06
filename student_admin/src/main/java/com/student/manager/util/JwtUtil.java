package com.student.manager.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secretKey;  // 密钥

    @Value("${jwt.expiration}")
    private long expirationTime;  // JWT过期时间（单位：毫秒）

    // 生成JWT Token
    public String generateToken(String username, String role) {
        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)  // 将角色作为一个 claim 加入 JWT
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(SignatureAlgorithm.HS512, secretKey)  // 使用HS512签名算法
                .compact();
    }

    // 解析JWT Token
    public Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }

    // 获取用户名（从JWT中解析）
    public String getUsernameFromToken(String token) {
        return parseToken(token).getSubject();
    }

    // 判断Token是否过期
    public boolean isTokenExpired(String token) {
        return parseToken(token).getExpiration().before(new Date());
    }

    // 验证Token是否有效
    public boolean validateToken(String token, String username) {
        return username.equals(getUsernameFromToken(token)) && !isTokenExpired(token);
    }
}
