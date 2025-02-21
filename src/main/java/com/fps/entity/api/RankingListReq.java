package com.fps.entity.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class RankingListReq {

    @ApiModelProperty("用户登录的渠道平台（1：微信，2：手q，3：oppo，4：vivo）")
    int platformType;

}
