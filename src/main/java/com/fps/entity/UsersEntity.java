package com.fps.entity;

import lombok.Data;


@Data
public class UsersEntity {

    private int id;
    private String username;
    private String head_img;
    private String openid;
    private String youke_openid;
    private double gold;
    private double diamond;
    private int level;
    private int chapter;
    private int default_gun;
    private int login_days;
    private int platform_type;
    private int kongtou_gun_num;
    private String guide;
    private int offline_time;      //当前用户离线时间（单位：s）；
    private String created_time;
    private String updated_time;

}
