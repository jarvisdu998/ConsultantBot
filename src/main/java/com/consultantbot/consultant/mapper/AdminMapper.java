package com.consultantbot.consultant.mapper;

import com.consultantbot.consultant.pojo.Admin;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AdminMapper {
    
    @Select("SELECT * FROM admin WHERE username = #{username}")
    Admin selectByUsername(String username);
    
    @Select("SELECT * FROM admin WHERE id = #{id}")
    Admin selectById(Long id);
    
    @Select("SELECT * FROM admin")
    List<Admin> selectAll();
    
    @Insert("INSERT INTO admin (username, password, name, phone, email) VALUES (#{username}, #{password}, #{name}, #{phone}, #{email})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Admin admin);
    
    @Update("UPDATE admin SET username = #{username}, password = #{password}, name = #{name}, phone = #{phone}, email = #{email} WHERE id = #{id}")
    void update(Admin admin);
    
    @Delete("DELETE FROM admin WHERE id = #{id}")
    void deleteById(Long id);
} 