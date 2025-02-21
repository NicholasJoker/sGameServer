package com.fps.entity;

import lombok.Data;


@Data
public class PurchaseRecordEntity {

    private int id;
    private int user_id;
    private int product_id;
    private int goods_id;
    private double actual_price;
    private String created_time;


}
