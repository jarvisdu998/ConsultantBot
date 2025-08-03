package com.consultantbot.consultant;

import com.consultantbot.consultant.pojo.Reservation;
import com.consultantbot.consultant.service.ReservationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@SpringBootTest
public class ReservationServiceTest {
    @Autowired
    private ReservationService reservationService;
    //测试添加
    @Test
    //@Transactional
    void testInsert(){
        Reservation reservation = new Reservation();
        reservation.setId(null);
        reservation.setName("张2");
        reservation.setPhone("13800138000");
        reservation.setGender("男");
        reservation.setCommunicationTime(LocalDateTime.now()); // 2025年8月2日10点30分
        reservation.setProvince("江苏省");
        reservation.setEstimatedScore(85);

        reservationService.insert(reservation);

    }
    //测试查询
    @Test
    void testSelectByPhone(){
        String phone = "13800138000";
        Reservation reservation = reservationService.selectByPhone(phone);
        System.out.println(reservation);
    }
}
