package com.fps.entity.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class ChangeGunReq {

    @ApiModelProperty("用户openid")
    String openid;
    @ApiModelProperty("用户openid")
    Integer newGunId;

}
