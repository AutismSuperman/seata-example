package com.example.service;

import com.example.clinet.OrderClient;
import com.example.clinet.StockClient;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalLock;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TCCBusinessService {


    @Autowired
    private StockClient stockClient;
    @Autowired
    private OrderClient orderClient;

    /**
     * 减库存，下订单
     *
     * @param userId
     * @param commodityCode
     * @param orderCount
     */
    @GlobalTransactional
    public void purchase(String commodityCode, int orderCount) {
        log.info("purchase begin ... xid: " + RootContext.getXID());
        stockClient.deduct(commodityCode, orderCount);
        int i = 1 / 0;
        orderClient.create(commodityCode, orderCount);
    }
}