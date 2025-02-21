package com.fps.entity.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@ApiModel
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @ApiModelProperty("用户ID")
    private Integer userId;
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("用户头像")
    private String userImg;
    @ApiModelProperty("第三方平台登录openId")
    private String openId;
    @ApiModelProperty("游客登录openId")
    private String youkeOpenId;
    @ApiModelProperty("用户登录的渠道平台（1：微信，2：手q，3：oppo，4：vivo）")
    private int platformType;

}
