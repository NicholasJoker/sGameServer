package com.fps.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;


@Data
public class ProductsEntity implements Serializable {

    private Integer id;

    //购买的商品ID
    private Integer goods_id;

    //商品名称
    private String name;

    //购买所需的虚拟货币数量
    private double price;

    private String created_time;

}
