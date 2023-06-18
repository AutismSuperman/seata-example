package com.example.service.impl;

import com.example.holder.ResultHolder;
import com.example.service.StockService;
import io.seata.rm.tcc.api.BusinessActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;


@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean deduct(BusinessActionContext actionContext, String commodityCode, int orderCount) {
        jdbcTemplate.update("update t_stock set tran_count = ? where commodity_code = ?",
                orderCount, commodityCode);
        ResultHolder.setStockActionResult(actionContext.getXid(), "T");
        return true;
    }

    @Override
    public boolean commit(BusinessActionContext actionContext) {
        // 防止幂等性，如果commit阶段重复执行则直接返回
        if (ResultHolder.getStockActionResult(actionContext.getXid()) == null) {
            return true;
        }
        String commodityCode = (String) actionContext.getActionContext("commodityCode");
        Integer orderCount = (Integer) actionContext.getActionContext("orderCount");
        jdbcTemplate.update("update t_stock set count = count-? ,tran_count =tran_count- ?  where commodity_code = ?",
                orderCount, orderCount, commodityCode);
        // 清除标记
        ResultHolder.setStockActionResult(actionContext.getXid(), "C");
        return true;
    }

    @Override
    public boolean rollback(BusinessActionContext actionContext) {

        //第一阶段没有完成的情况下，不必执行回滚
        //因为第一阶段有本地事务，事务失败时已经进行了回滚。
        //如果这里第一阶段成功，而其他全局事务参与者失败，这里会执行回滚
        //幂等性控制：如果重复执行回滚则直接返回
        if (ResultHolder.getStockActionResult(actionContext.getXid()) == null) {
            return true;
        }
        String commodityCode = (String) actionContext.getActionContext("commodityCode");
        Integer orderCount = (Integer) actionContext.getActionContext("orderCount");
        jdbcTemplate.update("update t_stock set tran_count =tran_count- ? where commodity_code = ?",
                orderCount, commodityCode);
        // 清除标记
        ResultHolder.setStockActionResult(actionContext.getXid(), "R");
        return true;
    }
}
