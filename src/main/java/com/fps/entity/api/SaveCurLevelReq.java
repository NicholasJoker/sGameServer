package com.fps.entity.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ApiModel
@ToString(callSuper = true)
public class SaveCurLevelReq extends RequestApi {

    @ApiModelProperty("章节")
    int chapter;
    @ApiModelProperty("关卡")
    int level;

}
