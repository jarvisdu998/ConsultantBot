package com.consultantbot.consultant.tools;

import com.consultantbot.consultant.pojo.Reservation;
import com.consultantbot.consultant.service.ReservationService;
import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;

@Component
public class ReservationTool {
    @Autowired
    private ReservationService reservationService;

    @Tool("预约志愿填报服务")
    public void addReservation(
            @P("考生姓名")String name,
            @P("考生电话")String phone,
            @P("考生性别")String gender,
            @P("预约沟通时间，格式为：yyyy-MM-dd'T'HH:mm")String communicationTime,
            @P("考生所在省份")String province,
            @P("考生预估分数")Integer estimateScore
    ){
//        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
//                .appendPattern("yyyy-MM-dd HH:mm")
//                .optionalStart()
//                .appendPattern(":ss")
//                .optionalEnd()
//                .toFormatter();
//
//        LocalDateTime dateTime;
//        try {
//            dateTime = LocalDateTime.parse(communicationTime, formatter);
//        } catch (DateTimeParseException e) {
//            throw new IllegalArgumentException("预约时间格式有误，请使用 yyyy-MM-dd HH:mm 或 yyyy-MM-dd HH:mm:ss");
//        }

        Reservation reservation = new Reservation(null, name, phone, gender, LocalDateTime.parse(communicationTime), province, estimateScore);
        reservationService.insert(reservation);
    }

    @Tool("根据手机号查询预约单")
    public Reservation findReservation(@P("考生手机号")String phone){
        return reservationService.selectByPhone(phone);
    }
}
