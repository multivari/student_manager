package com.student.manager.common;

public class ApiResponse<T> {
    private int code;
    private String message;
    private T data;

    private String token;

    public ApiResponse(int code, String message, T data, String token) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.token = token;
    }

    public ApiResponse(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    // 静态方法用于快速构建成功和失败响应
    public static <T> ApiResponse<T> success(T data, String message) {
        return new ApiResponse<>(200, message, data, null);
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(200, "请求成功", data, null);
    }

    public static <T> ApiResponse<T> successWithToken(T data, String token) {
        // 如果返回的响应包含 JWT Token
        return new ApiResponse<>(200, "登录成功", data, token);
    }

    public static <T> ApiResponse<T> error(String message) {
        return new ApiResponse<>(500, message, null, null);
    }

    // Getter 和 Setter
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
