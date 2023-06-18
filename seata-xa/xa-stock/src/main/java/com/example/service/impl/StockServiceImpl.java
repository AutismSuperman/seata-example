package com.example.service.impl;

import com.example.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;


@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void deduct(String commodityCode, int orderCount) {
        jdbcTemplate.update("update t_stock set count = count - ? where commodity_code = ?",
                orderCount, commodityCode);
    }
}
