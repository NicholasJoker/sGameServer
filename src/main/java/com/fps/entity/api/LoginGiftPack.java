package com.fps.entity.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@ApiModel
@NoArgsConstructor
@AllArgsConstructor
public class LoginGiftPack {

    //第1天 ~ 第7天礼包
    List<GiftPack> giftPacks;

    @ApiModelProperty("当前第几天")
    int days;

    @ApiModelProperty("是否领取")
    boolean todayIsGet;

}
