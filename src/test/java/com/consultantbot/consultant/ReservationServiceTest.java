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
    
    //测试添加预约
    @Test
    //@Transactional
    void testInsert(){
        Reservation reservation = new Reservation();
        reservation.setId(null);
        reservation.setName("张同学");
        reservation.setPhone("13800138000");
        reservation.setGender("男");
        reservation.setCommunicationTime(LocalDateTime.now().plusDays(1)); // 明天
        reservation.setProvince("江苏省");
        reservation.setTechnicalRequirement("希望学习Java Web开发，包括Spring Boot框架");

        reservationService.insert(reservation);
    }
    
    //测试查询预约
    @Test
    void testSelectByPhone(){
        String phone = "13800138000";
        Reservation reservation = reservationService.selectByPhone(phone);
        System.out.println(reservation);
    }
}
