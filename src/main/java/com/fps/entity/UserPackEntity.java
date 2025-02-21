package com.fps.entity;

import lombok.Data;

/**
 * 用户背包实体类
 */
@Data
public class UserPackEntity {

    private int id;
    private int user_id;
    private int goods_id;
    private double num;
    private String created_time;
    private String updated_time;

}






