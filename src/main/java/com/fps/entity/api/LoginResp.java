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
public class LoginResp {

    @ApiModelProperty("用户基本数据")
    User user;
    @ApiModelProperty("用户背包数据")
    UserPack userPack;
    @ApiModelProperty("分享/视频配置数据 shareVideoCfg")
    List<ShareVideoCfg> shareVideoCfg;
    @ApiModelProperty("七天签到数据")
    LoginGiftPack loginGiftPack;

    @ApiModelProperty("离线收益（金币数）")
    double offlineRevenue;
    @ApiModelProperty("教程")
    String[] guide;

}
