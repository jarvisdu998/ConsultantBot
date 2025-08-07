package com.consultantbot.consultant.mapper;

import com.consultantbot.consultant.pojo.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudentMapper {
    
    @Select("SELECT * FROM student WHERE username = #{username}")
    Student selectByUsername(String username);
    
    @Select("SELECT * FROM student WHERE phone = #{phone}")
    Student selectByPhone(String phone);
    
    @Select("SELECT * FROM student WHERE id = #{id}")
    Student selectById(Long id);
    
    @Select("SELECT * FROM student")
    List<Student> selectAll();
    
    @Insert("INSERT INTO student (username, password, name, gender, phone, email, province) VALUES (#{username}, #{password}, #{name}, #{gender}, #{phone}, #{email}, #{province})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Student student);
    
    @Update("UPDATE student SET username = #{username}, password = #{password}, name = #{name}, gender = #{gender}, phone = #{phone}, email = #{email}, province = #{province}, status = #{status} WHERE id = #{id}")
    void update(Student student);
    
    @Delete("DELETE FROM student WHERE id = #{id}")
    void deleteById(Long id);
    
    @Update("UPDATE student SET status = #{status} WHERE id = #{id}")
    void updateStatus(@Param("id") Long id, @Param("status") Integer status);
} 