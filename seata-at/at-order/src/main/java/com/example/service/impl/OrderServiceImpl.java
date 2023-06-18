package com.example.service.impl;

import com.example.entity.Order;
import com.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class OrderServiceImpl implements OrderService {
    static DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void create(Order record) {
        LocalDateTime now = LocalDateTime.now();
        record.setOrderNo(df.format(now));
        jdbcTemplate.update("insert into t_order(order_no,commodity_code, count) values (?, ?, ?)",
                record.getOrderNo(), record.getCommodityCode(), record.getCount()
        );
    }
}
