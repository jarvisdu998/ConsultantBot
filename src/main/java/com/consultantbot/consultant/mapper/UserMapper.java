package com.consultantbot.consultant.mapper;

import com.consultantbot.consultant.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    
    // 根据ID查询用户
    User findById(@Param("id") Long id);
    
    // 根据用户名查询用户
    User findByUsername(@Param("username") String username);
    
    // 根据手机号查询用户
    User findByPhone(@Param("phone") String phone);
    
    // 查询所有用户
    List<User> findAll();
    
    // 根据角色查询用户
    List<User> findByRole(@Param("role") String role);
    
    // 插入用户
    int insert(User user);
    
    // 更新用户
    int update(User user);
    
    // 删除用户
    int deleteById(@Param("id") Long id);
    
    // 根据用户名和密码查询用户（用于登录）
    User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
} 