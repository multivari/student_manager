package com.student.manager.mapper;


import com.student.manager.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    // 根据用户名和密码查找用户
    User findUserByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    // 根据用户名查找用户角色
    String findRoleByUsername(@Param("username") String username);

    // 根据 user_id 查找用户信息
    User findUserById(@Param("userId") Long userId);

    // 插入新用户
    void insertUser(User user);

    // 更新用户密码
    void updateUserPassword(@Param("username") String username, @Param("newPassword") String newPassword);
}
