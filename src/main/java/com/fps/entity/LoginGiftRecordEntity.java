package com.fps.entity;


import lombok.Data;

@Data
public class LoginGiftRecordEntity {

    private int id;
    private int user_id;
    private int days;
    private int goods_id;         //物品ID
    private int num;            //领取的数量
    private int receive_status;     //领取状态码；0：未领取；1：已领取
    private String created_time;

}
