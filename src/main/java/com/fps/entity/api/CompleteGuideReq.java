package com.fps.entity.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class CompleteGuideReq {

    @ApiModelProperty("当前教程步骤")
    String step;

}
