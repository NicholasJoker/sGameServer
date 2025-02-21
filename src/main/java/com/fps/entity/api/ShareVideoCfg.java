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
public class ShareVideoCfg {

    @ApiModelProperty("ID")
    String id;
    @ApiModelProperty("最多的分享次数")
    int maxShare;
    @ApiModelProperty("最多的观看视频次数")
    int maxVideo;
    @ApiModelProperty("使用的优先级。1：分享优先，2：视频优先")
    int priority;
    @ApiModelProperty("标题")
    String title;

}
