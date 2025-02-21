package com.fps.entity.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class LoginReq {

    @ApiModelProperty("用户登录的渠道平台（1：微信，2：手q，3：oppo，4：vivo）")
    private Integer platformType;
    @ApiModelProperty("昵称")
    private String username;
    @ApiModelProperty("头像")
    private String headImg;

}
