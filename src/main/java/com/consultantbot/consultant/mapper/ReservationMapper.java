package com.consultantbot.consultant.mapper;

import com.consultantbot.consultant.pojo.Reservation;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ReservationMapper {
    // 添加预约信息
    @Insert("INSERT INTO reservation (name, phone, gender, communication_time, province, technical_requirement) VALUES (#{name}, #{phone}, #{gender}, #{communicationTime}, #{province}, #{technicalRequirement})")
    void insert(Reservation reservation);
    // 根据手机号查询预约信息
    @Select("SELECT * FROM reservation WHERE phone = #{phone}")
    Reservation selectByPhone(String phone);
}
