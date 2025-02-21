package com.fps.entity.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class SyncGoldReq {

    @ApiModelProperty("用户金币数量")
    double num;

}
