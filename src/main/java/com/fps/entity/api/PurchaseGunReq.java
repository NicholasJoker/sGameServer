package com.fps.entity.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ApiModel
@ToString(callSuper = true)
public class PurchaseGunReq extends RequestApi {

    @ApiModelProperty("需要购买的枪ID")
    int buyGunId;

}
