package com.consultantbot.consultant.service;

import com.consultantbot.consultant.mapper.ReservationMapper;
import com.consultantbot.consultant.pojo.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {
    @Autowired
    private ReservationMapper reservationMapper;

    /**
     * 添加预约
     */
    public boolean addReservation(Reservation reservation) {
        // 检查手机号是否已存在预约
        if (reservationMapper.selectByPhone(reservation.getPhone()) != null) {
            return false;
        }
        reservationMapper.insert(reservation);
        return true;
    }

    /**
     * 根据手机号查询预约
     */
    public Reservation getReservationByPhone(String phone) {
        return reservationMapper.selectByPhone(phone);
    }
    
    /**
     * 根据ID查询预约
     */
    public Reservation getById(Long id) {
        return reservationMapper.selectById(id);
    }
    
    /**
     * 查询所有预约
     */
    public List<Reservation> getAll() {
        return reservationMapper.selectAll();
    }
    
    /**
     * 更新预约信息
     */
    public boolean update(Reservation reservation) {
        Reservation existingReservation = reservationMapper.selectById(reservation.getId());
        if (existingReservation == null) {
            return false;
        }
        reservationMapper.update(reservation);
        return true;
    }
    
    /**
     * 取消预约
     */
    public boolean cancelReservation(Long id) {
        Reservation reservation = reservationMapper.selectById(id);
        if (reservation == null) {
            return false;
        }
        reservationMapper.deleteById(id);
        return true;
    }
    
    /**
     * 删除预约
     */
    public boolean deleteById(Long id) {
        Reservation reservation = reservationMapper.selectById(id);
        if (reservation == null) {
            return false;
        }
        reservationMapper.deleteById(id);
        return true;
    }
}
