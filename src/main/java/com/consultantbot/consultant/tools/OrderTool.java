package com.consultantbot.consultant.tools;

import com.consultantbot.consultant.pojo.Order;
import com.consultantbot.consultant.service.OrderService;
import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class OrderTool {
    @Autowired
    private OrderService orderService;

    @Tool("创建订单")
    public Order createOrder(
            @P("学员ID") Long studentId,
            @P("课程ID") Long courseId,
            @P("支付金额") BigDecimal amount
    ) {
        Order order = new Order();
        order.setStudentId(studentId);
        order.setCourseId(courseId);
        order.setAmount(amount);
        orderService.createOrder(order);
        return order;
    }

    @Tool("根据学员ID查询订单")
    public List<Order> getOrdersByStudentId(@P("学员ID") Long studentId) {
        return orderService.getByStudentId(studentId);
    }

    @Tool("查询所有订单")
    public List<Order> getAllOrders() {
        return orderService.getAll();
    }

    @Tool("根据ID查询订单")
    public Order getOrderById(@P("订单ID") Long id) {
        return orderService.getById(id);
    }

    @Tool("根据订单号查询订单")
    public Order getOrderByOrderNo(@P("订单号") String orderNo) {
        // 这个方法在Service中没有，暂时返回null
        return null;
    }

    @Tool("取消订单")
    public void cancelOrder(@P("订单ID") Long id) {
        orderService.cancelOrder(id);
    }

    @Tool("删除订单")
    public void deleteOrder(@P("订单ID") Long id) {
        orderService.deleteById(id);
    }

    @Tool("更新订单信息")
    public void updateOrder(
            @P("订单ID") Long id,
            @P("学员ID") Long studentId,
            @P("课程ID") Long courseId,
            @P("支付金额") BigDecimal amount
    ) {
        Order order = new Order();
        order.setId(id);
        order.setStudentId(studentId);
        order.setCourseId(courseId);
        order.setAmount(amount);
        // 这个方法在Service中没有，暂时不调用
    }

    @Tool("根据时间范围查询订单")
    public List<Order> getOrdersByTimeRange(
            @P("开始时间") String startTime,
            @P("结束时间") String endTime
    ) {
        // 这个方法在Service中没有，暂时返回null
        return null;
    }

    @Tool("根据金额范围查询订单")
    public List<Order> getOrdersByAmountRange(
            @P("最低金额") BigDecimal minAmount,
            @P("最高金额") BigDecimal maxAmount
    ) {
        // 这个方法在Service中没有，暂时返回null
        return null;
    }
} 