package com.consultantbot.consultant.mapper;

import com.consultantbot.consultant.pojo.Reservation;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ReservationMapper {
    // 添加预约信息
    @Insert("INSERT INTO reservation (name, phone, gender, communication_time, province, estimated_score) VALUES (#{name}, #{phone}, #{gender}, #{communicationTime}, #{province}, #{estimatedScore})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Reservation reservation);
    
    // 根据手机号查询预约信息
    @Select("SELECT * FROM reservation WHERE phone = #{phone}")
    Reservation selectByPhone(String phone);
    
    // 根据ID查询预约信息
    @Select("SELECT * FROM reservation WHERE id = #{id}")
    Reservation selectById(Long id);
    
    // 查询所有预约信息
    @Select("SELECT * FROM reservation")
    List<Reservation> selectAll();
    
    // 更新预约信息
    @Update("UPDATE reservation SET name = #{name}, phone = #{phone}, gender = #{gender}, communication_time = #{communicationTime}, province = #{province}, estimated_score = #{estimatedScore} WHERE id = #{id}")
    void update(Reservation reservation);
    
    // 删除预约信息
    @Delete("DELETE FROM reservation WHERE id = #{id}")
    void deleteById(Long id);
}
