package com.fps.entity.api;


import io.swagger.annotations.ApiModelProperty;

public interface Constant {

    /**
     * 2开头是枪；如：20001
     */

    //金币
    public static final int Gold = 10001;
    //钻石
    public static final int Diamond = 10002;
    //格洛克17（枪）
    public static final int Init_Gun = 20001;


    //以1开头的是 虚拟货币；金币；钻石
    public static final int Currency_Prefix = 1;
    //以2开头的是枪；如：20001
    public static final int Gun_Prefix = 2;


    //商品购买所需金币 累加率（次数 * 1.07 * 商品所需金币）
    public static final double Purchase_Accumulation_Rate = 1.07;


    //99 --->  selectGunId
    public static final int Select_Gun_Id = 99;
    public static final int Unlock_Chapter_Level = 98;


    //背包的 最大存放枪的格子数量
    public static final int Max_Gun_Pack = 12;


    //客户端同步金币更新时间（单位：s）
    public static final int Gold_Sync_Time = 60;

    //上限2小时 300金币/秒
    public static final int Offline_Gold = 300;

}
