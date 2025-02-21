package com.fps.entity.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ApiModel
@ToString(callSuper = true)
public class SignInRewardReq {

    @ApiModelProperty("是否翻倍领取：true：是")
    boolean isDoubleGet;

}
