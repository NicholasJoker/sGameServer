package com.fps.entity.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class RequestApi {

    @ApiModelProperty("用户openid")
    String openid;

}
