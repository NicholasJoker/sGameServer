package com.fps.entity.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class WeChatAuthorize {

    @ApiModelProperty("微信jsCode")
    private String code;

}
