package com.example.controller;

import com.example.service.ATBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/api/business")
@RestController
public class BusinessController {

    @Autowired
    private ATBusinessService ATBusinessService;

    /**
     * 购买下单，模拟全局事务提交
     *
     * @return
     */
    @RequestMapping("/purchase")
    public Boolean purchaseCommit(HttpServletRequest request) {
        ATBusinessService.purchase("101", 10);
        return true;
    }


}
