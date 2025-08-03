package com.consultantbot.consultant.mapper;

import com.consultantbot.consultant.pojo.Instructor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface InstructorMapper {
    // 查询所有在职讲师
    @Select("SELECT * FROM instructor WHERE status = 1 ORDER BY experience_years DESC")
    List<Instructor> selectAll();
    
    // 根据ID查询讲师
    @Select("SELECT * FROM instructor WHERE id = #{id}")
    Instructor selectById(Long id);
} 