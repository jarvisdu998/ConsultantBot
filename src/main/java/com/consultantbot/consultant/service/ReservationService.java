package com.consultantbot.consultant.service;

import com.consultantbot.consultant.mapper.ReservationMapper;
import com.consultantbot.consultant.pojo.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {
    // 添加预约信息
    @Autowired
    private ReservationMapper reservationMapper;
    public void insert(Reservation reservation){
        reservationMapper.insert(reservation);
    }
    // 根据手机号查询预约信息
    public Reservation selectByPhone(String phone){
        return reservationMapper.selectByPhone(phone);
    }
}
