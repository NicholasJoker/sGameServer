package com.fps.entity.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class ChangeGoodsNumReq extends RequestApi {

    @ApiModelProperty("添加的物品id ")
    int goodsId;
    @ApiModelProperty("增/减数量")
    double num;

}
