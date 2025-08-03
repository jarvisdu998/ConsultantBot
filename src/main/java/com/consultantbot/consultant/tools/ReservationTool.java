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

    @Tool("预约技术指导服务")
    public void addReservation(
            @P("学员姓名")String name,
            @P("学员电话")String phone,
            @P("学员性别")String gender,
            @P("预约沟通时间，格式为：yyyy-MM-dd'T'HH:mm")String communicationTime,
            @P("学员所在地区")String province,
            @P("技术需求描述")String technicalRequirement
    ){
        Reservation reservation = new Reservation(null, name, phone, gender, LocalDateTime.parse(communicationTime), province, technicalRequirement);
        reservationService.insert(reservation);
    }

    @Tool("根据手机号查询预约单")
    public Reservation findReservation(@P("学员手机号")String phone){
        return reservationService.selectByPhone(phone);
    }
}
