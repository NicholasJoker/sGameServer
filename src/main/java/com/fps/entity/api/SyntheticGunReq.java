package com.fps.entity.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class SyntheticGunReq {

    @ApiModelProperty("需要合成的枪ID")
    int gunId;

}
