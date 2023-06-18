package com.example.service.impl;

import com.example.entity.Order;
import com.example.holder.ResultHolder;
import com.example.service.OrderService;
import io.seata.rm.tcc.api.BusinessActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

@Service
public class OrderServiceImpl implements OrderService {
    static DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean create(BusinessActionContext actionContext, String orderId, Order record) {
        jdbcTemplate.update(
                "insert into t_order(order_no,commodity_code, count,status) values (?, ?, ?, ?)",
                orderId, record.getCommodityCode(), record.getCount(), 1
        );
        ResultHolder.setOrderActionResult(actionContext.getXid(), "T");
        return true;
    }

    @Override
    public boolean commit(BusinessActionContext actionContext) {
        // 防止幂等性，如果commit阶段重复执行则直接返回
        if (ResultHolder.getOrderActionResult(actionContext.getXid()) == null) {
            return true;
        }
        String orderId = (String) actionContext.getActionContext("orderId");
        jdbcTemplate.update("update t_order set status = 0 where order_no = ?", orderId);
        ResultHolder.setOrderActionResult(actionContext.getXid(), "C");
        return true;
    }

    @Override
    public boolean rollback(BusinessActionContext actionContext) {
        // 防止幂等性，如果commit阶段重复执行则直接返回
        if (ResultHolder.getOrderActionResult(actionContext.getXid()) == null) {
            return true;
        }
        String orderId = (String) actionContext.getActionContext("orderId");
        jdbcTemplate.update("delete from t_order where order_no = ?", orderId);
        ResultHolder.setOrderActionResult(actionContext.getXid(), "R");
        return true;
    }
}
