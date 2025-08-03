package com.consultantbot.consultant.mapper;

import com.consultantbot.consultant.pojo.EnterpriseService;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EnterpriseServiceMapper {
    // 查询所有可用服务
    @Select("SELECT * FROM enterprise_service WHERE status = 1 ORDER BY create_time DESC")
    List<EnterpriseService> selectAll();
    
    // 根据分类查询服务
    @Select("SELECT * FROM enterprise_service WHERE category = #{category} AND status = 1 ORDER BY create_time DESC")
    List<EnterpriseService> selectByCategory(String category);
    
    // 根据ID查询服务
    @Select("SELECT * FROM enterprise_service WHERE id = #{id}")
    EnterpriseService selectById(Long id);
} 