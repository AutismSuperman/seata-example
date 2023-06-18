package com.example.entity;


import lombok.Data;

@Data
public class Order {


    private Integer id;

    private String orderNo;

    private String commodityCode;

    private Integer count;

    /**
     * 1 T 阶段  0 正常阶段
     */
    private Integer status;


}
