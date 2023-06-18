package com.example.controller;

import com.example.entity.Order;
import com.example.service.OrderService;
import io.seata.core.context.RootContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping("/api/order")
public class OrderController {


    @Autowired
    private OrderService orderService;


    @GetMapping(value = "/debit")
    public void deduct(@RequestParam String commodityCode, @RequestParam Integer count) throws SQLException {
        System.out.println("order XID " + RootContext.getXID());
        Order order = new Order();
        order.setCommodityCode(commodityCode);
        order.setCount(count);
        orderService.create(order);
    }
}
