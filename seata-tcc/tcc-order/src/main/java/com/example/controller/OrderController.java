package com.example.controller;

import com.example.entity.Order;
import com.example.service.OrderService;
import io.seata.core.context.RootContext;
import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    static DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    @Autowired
    private OrderService orderService;


    @GetMapping(value = "/debit")
    public void deduct(@RequestParam String commodityCode, @RequestParam Integer count) throws SQLException {
        System.out.println("order XID " + RootContext.getXID());
        LocalDateTime now = LocalDateTime.now();
        Order order = new Order();
        order.setCommodityCode(commodityCode);
        order.setCount(count);
        order.setOrderNo(df.format(now));
        BusinessActionContext context = BusinessActionContextUtil.getContext();
        orderService.create(context, order.getOrderNo(), order);
    }

}
