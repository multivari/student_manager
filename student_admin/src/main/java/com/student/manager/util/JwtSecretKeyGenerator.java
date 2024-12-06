package com.student.manager.util;

import java.security.SecureRandom;
import java.util.Base64;

public class JwtSecretKeyGenerator {
    public static void main(String[] args) {
        SecureRandom secureRandom = new SecureRandom();

        // 对于 HS512 算法，密钥大小需要是 64 字节（512 位）
        byte[] key = new byte[64];  // 64 bytes = 512 bits
        secureRandom.nextBytes(key);

        // 使用 Base64 编码输出密钥
        String secretKey = Base64.getEncoder().encodeToString(key);

        System.out.println("Generated JWT Secret Key for HS512: " + secretKey);
    }
}
